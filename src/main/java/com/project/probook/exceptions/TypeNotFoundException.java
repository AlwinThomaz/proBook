package com.project.probook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "This type does not exist")
public class TypeNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 522252481993935933L;

}
