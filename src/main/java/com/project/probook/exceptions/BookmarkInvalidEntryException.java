package com.project.probook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "This is an invalid entry, please follow the given rules:"
		+ "Names must be less than 50 characters containing no special characters"
		+ "Description must be less than 250 charcaters contaianing no special characters"
		+ "URL's must start with http:// or https://")
public class BookmarkInvalidEntryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1410221486036328488L;

}
