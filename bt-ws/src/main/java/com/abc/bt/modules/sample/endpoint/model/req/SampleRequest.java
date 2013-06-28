package com.abc.bt.modules.sample.endpoint.model.req;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SAMPLE_REQ")
public class SampleRequest {

	
	private String request;

	@XmlElement(name = "REQ")
	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

}
