package com.abc.bt.common.ws.exception;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "com.abc.bt.common.ws.exception.ServiceFault", name = "ServiceException", targetNamespace = "http://exception.ws.common.bt.abc.com")
public class ServiceException extends Exception {

	private static final long serialVersionUID = -7686816742305561455L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

}
