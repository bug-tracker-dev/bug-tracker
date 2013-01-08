package com.abc.bt.common.web.model.exception;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Exception")
public class ExceptionM {

	private String message;
	private String cause;
	
	@XmlElement(name="message")
	public String getUsername() {
		return message;
	}
	public void setUsername(String username) {
		this.message = username;
	}
	@XmlElement(name="cause")
	public String getPassword() {
		return cause;
	}
	public void setPassword(String password) {
		this.cause = password;
	}
}
