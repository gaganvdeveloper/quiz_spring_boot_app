package org.jsp.quiz.exceptionhandler;

import org.jsp.quiz.exceptionclasses.InvalidQuestionIdException;
import org.jsp.quiz.exceptionclasses.NoQuestionFoundException;
import org.jsp.quiz.responsestructure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class QuestionExceptionHandler {

	@ExceptionHandler(NoQuestionFoundException.class)
	public ResponseEntity<?> noQuestionFoundExceptionHandler(NoQuestionFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder().httpStatus(HttpStatus.NOT_FOUND.value()).message("No Questions Found in the Database").body("No Questions Present in the databse").build());
	}
	
	
	@ExceptionHandler(InvalidQuestionIdException.class)
	public ResponseEntity<?> invalidQuestionIdExceptionHandler(InvalidQuestionIdException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder().httpStatus(HttpStatus.NOT_FOUND.value()).message("No Questions Found in the Database").body("No Questions Present in the databse").build());
	}
	
	
	
	
	
	

}
