package com.project.probook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "This is an invalid entry, please follow the given rules:"
		+ "Names must be less than 50 characters containing no special characters")
public class TypeInvalidEntryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7326550536783660977L;

}
