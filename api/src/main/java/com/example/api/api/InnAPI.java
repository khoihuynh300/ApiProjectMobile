package com.example.api.api;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.api.entity.ImageInn;
import com.example.api.entity.Inn;
import com.example.api.model.InnModel;
import com.example.api.service.IInnService;
import com.example.api.service.ImageInnService;
import com.example.api.service.impl.InnServiceImpl;

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
			@RequestParam("gtePrice") Double gtePrice, @RequestParam("ltePrice") Double ltePrice
			, @RequestParam("size") int size) {
		List<InnModel> inns = iInnService.searchInn(address, gtePrice, ltePrice, size);
		if (inns.size() > 0) {
			return new ResponseEntity<List<InnModel>>(inns, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No Object Available", HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("inns/add")
	public ResponseEntity<?> recommendInn(@ModelAttribute InnModel innModel, @RequestParam("imageFiles") MultipartFile[] files) {
        try {
        	List<String> imageArr = new ArrayList<>();
        	if (files != null) {
        		for (MultipartFile file : files) {
        			String fileName = file.getOriginalFilename();
        			imageArr.add(fileName);
        			byte[] bytes = file.getBytes();
        			File newFile = new File("D:\\image\\" + fileName);
        			FileCopyUtils.copy(bytes, newFile);
				}
    			
        		iInnService.recommendInn(innModel, imageArr);
        		
        		return new ResponseEntity<>("Success", HttpStatus.CREATED);
    		} else {
    			return new ResponseEntity<>("No Object Available", HttpStatus.NOT_FOUND);
    		}
        } catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
