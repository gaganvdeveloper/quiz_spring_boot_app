package org.jsp.quiz.servcice;

import java.util.List;

import org.jsp.quiz.dto.QuizResponse;
import org.jsp.quiz.entity.Question;
import org.springframework.http.ResponseEntity;

public interface QuestionService {

	ResponseEntity<?> saveQuestion(Question question);

	ResponseEntity<?> findAllQuestions();

	ResponseEntity<?> findQuestionById(int id);

	ResponseEntity<?> submitQuiz(List<QuizResponse> quizResponses);

	ResponseEntity<?> takeQuiz();

}
