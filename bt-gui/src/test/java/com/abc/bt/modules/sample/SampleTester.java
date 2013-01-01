package com.abc.bt.modules.sample;


import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import com.abc.bt.common.CommonTester;

public class SampleTester extends CommonTester {

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
		System.out.println(mav);
	}
	
	@Test
	public void lagacy() throws Exception{
		mockreq.setRequestURI("/sample/legacy");
		mockreq.setMethod("POST");
		ModelAndView mav = execute(mockreq, mockresp);
		System.out.println(mav);
	}

	@Test
	public void json() throws Exception {
		//mockreq.setContentType(MediaType.APPLICATION_JSON.toString());
		//mockreq.addHeader("Accept", MediaType.APPLICATION_JSON.toString());
		mockreq.setRequestURI("/sample/json");
		mockreq.setMethod("POST");
		
		
		//Set<MediaType> mediaTypes = new HashSet<MediaType>();
		//mediaTypes.add(MediaType.APPLICATION_JSON);

		ModelAndView mav = execute(mockreq, mockresp);
		System.out.println(mav);
		String returnjson = mockresp.getContentAsString();
		System.out.println(returnjson);
		
	}
}
