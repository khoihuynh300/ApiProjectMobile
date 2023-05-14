//package com.example.api.api;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.api.service.IInnService;
//import com.example.api.service.IUsersService;
//import com.example.api.service.MessageService;
//
//@RestController
//@RequestMapping("api/")
//public class MessageAPI {
//	@Autowired
//	MessageService messageService;
//
//	@Autowired
//	IInnService iInnService;
//
//	@Autowired
//	IUsersService iUsersService;
//
////	@GetMapping("messages")
////	public ResponseEntity<?> getAllMessageOfInn(@RequestParam("innId") Long innId) {
////		List<MessageModel> messageModels = messageService.getAllMessageByInnId(innId);
////		if (messageModels.size() > 0) {
////			return new ResponseEntity<List<MessageModel>>(messageModels, HttpStatus.OK);
////		} else {
////			return new ResponseEntity<>("No Object Available", HttpStatus.NOT_FOUND);
////		}
////	}
////
////	@PostMapping("messages/add")
////	@ResponseBody
////	public ResponseEntity<?> createMessageOfInn(@RequestBody MessageModel messageModel) {
////		try {
////			if (iUsersService.findById(messageModel.getUserId()) == null
////					|| iInnService.readInnById(messageModel.getInnId()) == null) {
////				return new ResponseEntity<>("Object already exists", HttpStatus.CONFLICT);
////			}
////			messageService.createMessageOfInn(messageModel);
////			return new ResponseEntity<>("Success", HttpStatus.CREATED);
////		} catch (Exception e) {
////			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
////		}
////	}
//}
