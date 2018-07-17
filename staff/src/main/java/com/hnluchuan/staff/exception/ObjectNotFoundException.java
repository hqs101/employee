package com.hnluchuan.staff.exception;

import com.hnluchuan.utils.exception.KException;

public class ObjectNotFoundException extends KException {
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException() {
	}

	public ObjectNotFoundException(String message) {
		super(message);
	}

}
