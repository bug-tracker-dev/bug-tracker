package com.abc.bt.modules.sample.endpoint;

import javax.jws.WebService;

import com.abc.bt.common.ws.exception.ServiceException;
import com.abc.bt.modules.sample.endpoint.model.req.SampleRequest;
import com.abc.bt.modules.sample.endpoint.model.resp.SampleResponse;

@WebService
public interface SampleEndpoint {

	public SampleResponse echo(SampleRequest sampleReuqest) throws ServiceException;

}
