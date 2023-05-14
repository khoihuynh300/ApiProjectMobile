package com.example.api.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.example.api.entity.Message;
import com.example.api.entity.Question;
import com.example.api.entity.Users;
import com.example.api.model.MessageModel;
import com.example.api.model.QuestionModel;
import com.example.api.repository.QuestionRepository;
import com.example.api.service.IQuestionService;
import com.example.api.service.IUsersService;
import com.example.api.service.MessageService;
import com.example.api.service.ResponseService;

@RestController
@RequestMapping("api/")
public class QuestionAPI {
	@Autowired
	IQuestionService questionService;

	@Autowired
	IUsersService userService;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	MessageService messageService;

	@PostMapping("addQuestion")
	public ResponseEntity<?> addQuestion(@RequestParam(value = "title") String title,
			@RequestParam(value = "askedId") Long askedId, @RequestParam(value = "message") String message) {
		try {
			List<QuestionModel> questionsList = questionService.findByTitleContaining(title);
			if (!questionsList.isEmpty()) {
				return ResponseEntity
						.ok(ResponseService.get(true, "Câu hỏi đã tồn tại, vui lòng xem lại trong danh sách câu hỏi!"));
			}
			QuestionModel newQuestion = new QuestionModel();
			double view = 0;
			Optional<Users> userOptional = userService.findById(askedId);
			if (userOptional.isEmpty()) {
				return ResponseEntity.ok(ResponseService.get(true, "Người dùng không tồn tại!"));
			}
			Users user1 = userOptional.get();
			newQuestion.setTitle(title);
			newQuestion.setView(view);
			Question questionEntity = new Question();
			BeanUtils.copyProperties(newQuestion, questionEntity);
			questionEntity.setAskedId(user1);
			questionService.save(questionEntity);
			Optional<Question> opQuestion = questionRepository.findFirstByOrderByCreatedAtDesc();
			Question newestQuestion = new Question();
			if(opQuestion.isPresent()) {
				newestQuestion = opQuestion.get();
			}
			if(userService.findById(askedId)==null)
			{
				return new ResponseEntity<>("No Object Available", HttpStatus.NOT_FOUND);
			}
			Message entity = new Message();
			entity.setMessage(message);
			Optional<Users> user2 = userService.findById(askedId);
			if(user2.isPresent()) {
				entity.setUserId(user2.get());
			}
			Question question =questionService.findById(newestQuestion.getQuestionId());
			entity.setQuestionId(question);
			messageService.addMessage(entity);
			return new ResponseEntity<>("Success", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("getAllQuestion")
	public List<QuestionModel> getAllQuestion() {
		List<QuestionModel> questionList = questionService.findAll();
		String message = "Danh sách câu hỏi trống!";
		if (questionList.isEmpty()) {
			QuestionModel emptyQuestion = new QuestionModel();
			emptyQuestion.setTitle(message);
			questionList.add(emptyQuestion);
		}
		return questionList;
	}

//	@DeleteMapping("deleteQuestion/{id}")
//	public ResponseEntity<Object> deleteQuestion(@PathVariable("id") Long id) {
//		try {
//			QuestionModel questionModel = questionService.findById(id);
//			if (!questionModel.getTitle().equals("Không tìm thấy!")) {
//				questionService.deleteById(id);
//				return ResponseEntity.ok(ResponseService.get(false, "Xóa câu hỏi thành công!"));
//			} else {
//				return ResponseEntity.ok(ResponseService.get(true, "Không tìm thấy câu hỏi!"));
//			}
//		} catch (Exception e) {
//			return ResponseEntity.ok(ResponseService.get(true, "Xóa câu hỏi thất bại!"));
//		}
//	}

//	@PutMapping("updateQuestion")
//	public ResponseEntity<Object> updateQuestion(@RequestBody QuestionModel question) {
//		try {
//			QuestionModel questionModel = questionService.findById(question.getQuestionId());
//			if (!questionModel.getTitle().equals("Không tìm thấy!")) {
//				Optional<Users> userAskedOptional = userService.findById(questionModel.getAskedId());
//				Optional<Users> userAnsweredOptional = userService.findById(questionModel.getAnswererId());
//				Users userAsked = userAskedOptional.get();
//				Users userAnswered= new Users();
//				if (userAnsweredOptional.isPresent()) {
//					userAnswered = userAnsweredOptional.get();
//				} else {
//					userAnswered.setActive(null);
//				}
//				questionModel.setTitle(question.getTitle());
//				questionService.updateQuestion(questionModel,userAsked,userAnswered);
//				return ResponseEntity.ok(ResponseService.get(false, "Cập nhật câu hỏi thành công!"));
//			} else {
//				return ResponseEntity.ok(ResponseService.get(true, "Không tìm thấy câu hỏi!"));
//			}
//		} catch (Exception e) {
//			return ResponseEntity.ok(ResponseService.get(true, "Cập nhật câu hỏi thất bại!"));
//		}
//	}
}
