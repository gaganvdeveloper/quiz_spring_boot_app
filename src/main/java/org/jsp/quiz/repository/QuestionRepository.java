package org.jsp.quiz.repository;

import java.util.List;

import org.jsp.quiz.entity.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

	@Query("select q from Question q where q.status='ACTIVE'")
	List<Question> findAllActiveQuestions();

	@Query("SELECT q FROM Question q ORDER BY FUNCTION('RAND') ASC")
	List<Question> findRandomQuestions(Pageable pageable);

}
