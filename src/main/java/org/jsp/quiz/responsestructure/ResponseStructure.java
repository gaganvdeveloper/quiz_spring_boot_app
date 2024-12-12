package org.jsp.quiz.responsestructure;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseStructure<T> {
	private int httpStatus;
	private String message;
	private T body;
}
