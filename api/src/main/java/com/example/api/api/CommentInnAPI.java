package com.example.api.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.CommentInnModel;
import com.example.api.model.MessageModel;
import com.example.api.service.ICommentInnService;
import com.example.api.service.IInnService;
import com.example.api.service.IUsersService;
import com.example.api.service.MessageService;

@RestController
@RequestMapping("api/")
public class CommentInnAPI {
	@Autowired
	ICommentInnService commentInnService;

	@Autowired
	IInnService iInnService;

	@Autowired
	IUsersService iUsersService;

	@GetMapping("commentInn/{innId}")
	public ResponseEntity<?> getAllCommentOfInn(@PathVariable("innId") Long innId) {
		List<CommentInnModel> commentInnModels = commentInnService.getAllCommentByInnId(innId);
		if (commentInnModels.size() > 0) {
			return new ResponseEntity<List<CommentInnModel>>(commentInnModels, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No Object Available", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("commentInn/add")
	@ResponseBody
	public ResponseEntity<?> createCommentOfInn(@RequestBody CommentInnModel commentInnModel) {
		try {
			if (iUsersService.findById(commentInnModel.getUserId()) == null
					|| iInnService.readInnById(commentInnModel.getInnId()) == null) {
				return new ResponseEntity<>("Object already exists", HttpStatus.CONFLICT);
			}
			commentInnService.createCommentOfInn(commentInnModel);
			List<CommentInnModel> commentInnModels = commentInnService.getAllCommentByInnId(commentInnModel.getInnId());
			return new ResponseEntity<CommentInnModel>(commentInnModels.get(commentInnModels.size() - 1), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
