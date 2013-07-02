package com.abc.bt.common.web.model.exception;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Exception")
public class ExceptionM {

	private String message;
	private Throwable cause;

	public ExceptionM() {
	}

	public ExceptionM(Exception ex) {
		this.message = ex.getMessage();
		this.cause = ex.getCause();
		
		
		System.out.println(this.message + " " + this.cause.toString());
	}

	@XmlElement(name = "message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@XmlElement(name = "cause")
	public String getCause() {
		return null == this.cause ? "" : cause.toString();
	}
	
	public void setCause(Throwable cause) {
		this.cause = cause;
	}
}
