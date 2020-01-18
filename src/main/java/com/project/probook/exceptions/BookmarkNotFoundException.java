package com.project.probook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "This bookmark does not exist")
public class BookmarkNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3767134978916672439L;

}
