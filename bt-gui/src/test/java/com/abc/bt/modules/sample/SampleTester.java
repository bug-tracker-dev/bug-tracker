package com.abc.bt.modules.sample;


import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import com.abc.bt.common.CommonTester;

@Deprecated
public class SampleTester extends CommonTester {

	private static final Logger _LOG = Logger.getLogger(SampleTester.class);
	
	private MockHttpServletRequest mockreq;
	private MockHttpServletResponse mockresp;
	
	@Before
	public void setup() {
		mockreq = new MockHttpServletRequest();
		mockresp = new MockHttpServletResponse();
	}
	
	@Test
	public void home() throws Exception{
		mockreq.setRequestURI("/sample/home");
		mockreq.setMethod("POST");
		ModelAndView mav = execute(mockreq, mockresp);
		_LOG.info(mav);
	}
	
	@Test
	public void lagacy() throws Exception{
		mockreq.setRequestURI("/sample/legacy");
		mockreq.setMethod("POST");
		ModelAndView mav = execute(mockreq, mockresp);
		_LOG.info(mav);
	}

	@Test
	public void json() throws Exception {
		mockreq.setRequestURI("/sample/json");
		mockreq.setMethod("POST");

		ModelAndView mav = execute(mockreq, mockresp);
		_LOG.info(mav);
		String returnjson = mockresp.getContentAsString();
		_LOG.info(returnjson);
	}
	
	@Test
	public void complex() throws Exception {
		mockreq.setRequestURI("/sample/complex");
		mockreq.setMethod("POST");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", "guest");
		params.put("password", "12345");
		params.put("gender", "M");
		
		mockreq.setParameters(params);

		ModelAndView mav = execute(mockreq, mockresp);
		_LOG.info(mav);
		String returnjson = mockresp.getContentAsString();
		_LOG.info(returnjson);
	}
	
	@Test
	public void jsonarray() throws Exception {
		mockreq.setRequestURI("/sample/jsonarray");
		mockreq.setMethod("POST");
		
		ModelAndView mav = execute(mockreq, mockresp);
		_LOG.info(mav);
		String returnjson = mockresp.getContentAsString();
		_LOG.info(returnjson);
	}
}
