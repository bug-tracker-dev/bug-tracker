package com.abc.bt.modules.sample.endpoint;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.abc.bt.common.ws.exception.ServiceException;
import com.abc.bt.modules.sample.endpoint.model.req.SampleRequest;
import com.abc.bt.modules.sample.endpoint.model.resp.SampleResponse;

@Service(value = "sampleEndpointImplementor")
@WebService(endpointInterface = "com.abc.bt.modules.sample.endpoint.SampleEndpoint")
public class SampleEndpointImpl implements SampleEndpoint {

	public SampleResponse echo(SampleRequest sampleReuqest) throws ServiceException {

		System.out.println((Integer.parseInt("aaa")));
		SampleResponse response = new SampleResponse();

		response.setResponse(sampleReuqest.getRequest());

		return response;
	}

}
