package com.project.probook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "This type already exists")
public class TypeDuplicateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4108393356362170879L;

}
