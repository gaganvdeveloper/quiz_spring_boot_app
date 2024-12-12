package org.jsp.quiz.servciceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.jsp.quiz.dao.QuestionDao;
import org.jsp.quiz.dto.QuestionDto;
import org.jsp.quiz.dto.QuizResponse;
import org.jsp.quiz.entity.Question;
import org.jsp.quiz.exceptionclasses.InvalidQuestionIdException;
import org.jsp.quiz.exceptionclasses.NoQuestionFoundException;
import org.jsp.quiz.responsestructure.ResponseStructure;
import org.jsp.quiz.servcice.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDao dao;

	@Override
	public ResponseEntity<?> saveQuestion(Question question) {
		question = dao.saveQuestion(question);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("Question Created Successfull...").body(question).build());
	}

	@Override
	public ResponseEntity<?> findAllQuestions() {
//		List<Question> questions =dao.findAllQuestions();

		List<Question> questions = dao.findAllActiveQuestions();

		List<QuestionDto> dtolist = new ArrayList<>();
		int c = 0;
		for (Question q : questions)
			if (c < 11) {
				c++;
				dtolist.add(new QuestionDto(q.getId(), q.getTitle(), q.getA(), q.getB(), q.getC(), q.getD()));
			}

		if (dtolist.isEmpty())
			throw NoQuestionFoundException.builder().message("No Question Found In the Databses").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("All Questions Found successfully...").body(dtolist).build());
	}

	@Override
	public ResponseEntity<?> findQuestionById(int id) {
		Optional<Question> optional = dao.findQuestionById(id);
		if (optional.isEmpty())
			throw InvalidQuestionIdException.builder().message("Invalid Question Id").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("Question Found Successfully").body(optional.get()).build());
	}

	@Override
	public ResponseEntity<?> submitQuiz(List<QuizResponse> quizResponses) {
		int c = 0;
		for (QuizResponse qr : quizResponses) {
			Optional<Question> q = dao.findQuestionById(qr.getId());
			if (q.isEmpty())
				throw InvalidQuestionIdException.builder().message("Invalid Question ID Unable to calculate Result")
						.build();
			Question question = q.get();
			if (question.getAns().equalsIgnoreCase(qr.getAns())) {
				c++;
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("Submition Successfull").body("Your Score : " + c).build());
	}

	@Override
	public ResponseEntity<?> takeQuiz() {

		List<Question> question = dao.takeQuiz();

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("Quiz Taken Successfully").body(question).build());
	}

}
