package com.example.api.api;

import java.util.List;
import java.util.Optional;

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

import com.example.api.entity.Message;
import com.example.api.entity.Question;
import com.example.api.entity.Users;
import com.example.api.model.MessageModel;
import com.example.api.service.IInnService;
import com.example.api.service.IQuestionService;
import com.example.api.service.IUsersService;
import com.example.api.service.MessageService;
import com.example.api.service.impl.MessageModel2;

@RestController
@RequestMapping("api/")
public class MessageAPI {
	@Autowired
	MessageService messageService;

	@Autowired
	IInnService iInnService;
	
	@Autowired
	IQuestionService iQuestionService;

	@Autowired
	IUsersService iUsersService;

	@GetMapping("getAllMessageByQuestionId/{id}")
	public ResponseEntity<?> getAllMessagesByQuestionId(@PathVariable("id") Long id) {
		List<MessageModel> messages = messageService.getAllMessageByQuestionId(id);
		if (messages.size() > 0) {
			return new ResponseEntity<List<MessageModel>>(messages, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No Object Available", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("addMessage")
	@ResponseBody
	public ResponseEntity<?> addMessageOfQuestion(@RequestParam(value = "message") String message,
			@RequestParam(value = "userId") Long userId,
			@RequestParam(value = "questionId") Long questionId){
//		System.err.println("test" + message.getUserId() + " ;" + message.getQuestionId() + ";" + message.getMessage());
		try {
			if((iUsersService.findById(userId)==null)
					|| (iQuestionService.findById(questionId)==null))
			{
				return new ResponseEntity<>("No Object Available", HttpStatus.OK);
			}
			Message entity = new Message();
			entity.setMessage(message);
			Optional<Users> user = iUsersService.findById(userId);
			if(user.isPresent()) {
				entity.setUserId(user.get());
			}
			Question question =iQuestionService.findById(questionId);
			entity.setQuestionId(question);
			messageService.addMessage(entity);
			return new ResponseEntity<>("Success", HttpStatus.CREATED);
		}catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
//		return ResponseEntity.ok(message);
	}

//	@GetMapping("messages")
//	public ResponseEntity<?> getAllMessageOfInn(@RequestParam("innId") Long innId) {
//		List<MessageModel> messageModels = messageService.getAllMessageByInnId(innId);
//		if (messageModels.size() > 0) {
//			return new ResponseEntity<List<MessageModel>>(messageModels, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>("No Object Available", HttpStatus.NOT_FOUND);
//		}
//	}
//
//	@PostMapping("messages/add")
//	@ResponseBody
//	public ResponseEntity<?> createMessageOfInn(@RequestBody MessageModel messageModel) {
//		try {
//			if (iUsersService.findById(messageModel.getUserId()) == null
//					|| iInnService.readInnById(messageModel.getInnId()) == null) {
//				return new ResponseEntity<>("Object already exists", HttpStatus.CONFLICT);
//			}
//			messageService.createMessageOfInn(messageModel);
//			return new ResponseEntity<>("Success", HttpStatus.CREATED);
//		} catch (Exception e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
}
