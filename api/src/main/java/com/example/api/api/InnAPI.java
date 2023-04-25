package com.example.api.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.entity.ImageInn;
import com.example.api.entity.Inn;
import com.example.api.model.InnModel;
import com.example.api.service.IInnService;
import com.example.api.service.ImageInnService;

@RestController
@RequestMapping("api/")
public class InnAPI {
	@Autowired
	IInnService iInnService;

	@Autowired
	ImageInnService imageInnService;

	@GetMapping("inns")
	public ResponseEntity<?> getAllInns() {
		List<InnModel> inns = iInnService.getAllInns();
		if (inns.size() > 0) {
			return new ResponseEntity<List<InnModel>>(inns, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No Object Available", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("inns/{_id}")
	@ResponseBody
	public ResponseEntity<?> getnInnById(@PathVariable("_id") Long _id) {
		InnModel innModel = iInnService.readInnById(_id);
		if (innModel != null) {
			return new ResponseEntity<InnModel>(innModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No Object Available", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("inns/search")
	public ResponseEntity<?> searchInns(@RequestParam("address") String address,
			@RequestParam("gtePrice") Double gtePrice, @RequestParam("ltePrice") Double ltePrice) {
		List<InnModel> inns = iInnService.searchInn(address, gtePrice, ltePrice);
		if (inns.size() > 0) {
			return new ResponseEntity<List<InnModel>>(inns, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No Object Available", HttpStatus.NOT_FOUND);
		}
	}
}
