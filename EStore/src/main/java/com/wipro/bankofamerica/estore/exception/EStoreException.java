package com.wipro.bankofamerica.estore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Jeevan
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EStoreException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public EStoreException(String errorMessage) {
		super(errorMessage); //calling constrcutor of super class- RunTimException
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
