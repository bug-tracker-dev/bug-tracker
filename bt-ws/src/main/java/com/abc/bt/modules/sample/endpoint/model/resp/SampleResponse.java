package com.abc.bt.modules.sample.endpoint.model.resp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SAMPLE_RESP")
public class SampleResponse {

	
	private String response;

	public String getResponse() {
		return response;
	}

	@XmlElement(name = "RESP")
	public void setResponse(String response) {
		this.response = response;
	}

}
