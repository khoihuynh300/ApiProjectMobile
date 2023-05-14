package com.example.api.api.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.entity.Inn;
import com.example.api.entity.Users;
import com.example.api.model.InnModel;
import com.example.api.service.IInnService;
import com.example.api.service.IUsersService;
import com.example.api.utils.apiResponse.ApiResponseSimple;
import com.example.api.utils.apiResponse.ApiResponseWithMeta;
import com.example.api.utils.apiResponse.ApiResponseWithResult;

@RestController
@RequestMapping("api/admin/inns")
public class inns {
	
	@Autowired
	IInnService iInnService;
	
	@Autowired IUsersService usersService;

	@GetMapping
	public ResponseEntity<?> getInns(
			@RequestParam(value = "type", defaultValue = "page") String type,
			@RequestParam(value = "offset", defaultValue = "0") Integer  offset,
			@RequestParam(value = "limit", defaultValue = "10") Integer  limit,
			@RequestParam(value = "ascending", defaultValue = "true") Boolean  ascending,
			@RequestParam(value = "isDeleted", defaultValue = "false") Boolean  isDeleted,
			@RequestParam(value = "Address", defaultValue = "") String  address,
			@RequestParam(value = "isConfirmed", defaultValue = "all") String  isConfirmed
			){
		 
		if(type.equals("all")) {
			//get all 
			List<InnModel> inns = iInnService.getAllInnsUnConfirmed();
			ApiResponseWithResult apiResponse = new ApiResponseWithResult(false, "ok", inns);
			return ResponseEntity.ok(apiResponse);
		}
		else if(type.equals("page")){
			Pageable pageable = PageRequest.of(offset, limit, Sort.by("innId").ascending());
			List<InnModel> inn = iInnService.findAll(isDeleted, address, isConfirmed, pageable);
			ApiResponseWithMeta apiResponse = new ApiResponseWithMeta(false, "ok", inn, pageable);
			return ResponseEntity.ok(apiResponse);			
		}
		
		return ResponseEntity.ok(new ApiResponseSimple(true, "invalid type"));	
	}
	
	@PutMapping("confirm/{id}")
	public ResponseEntity<?> confirmInn(@PathVariable("id") Long innId, @RequestParam("userId") Long userId){
		Users user = usersService.findById(userId).get();
		Inn inn = iInnService.findById(innId).get();
		inn.setConfirmedById(user);
		inn.setIsConfirmed(true);
		iInnService.save(inn);
		
		return ResponseEntity.ok(new ApiResponseSimple(false,  "updated"));	
	}
	
	@PostMapping("add")
	public ResponseEntity<?> addInn(@RequestBody InnModel innModel){
		
		return iInnService.addByManager(innModel);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteInn(@PathVariable("id") Long id){
		Inn inn = iInnService.findById(id).get();
		inn.setIsDeleted(true);
		iInnService.save(inn);
		return ResponseEntity.ok(new ApiResponseSimple(false,  "deleted"));
	}
}
