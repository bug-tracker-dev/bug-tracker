package com.abc.bt.common;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Deprecated
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:conf/dispatcher-servlet.xml")
public class CommonTester {

	@Resource(name = "requestMappingHandlerMapping")
	private RequestMappingHandlerMapping handlerMapping;

	@Resource(name = "requestMappingHandlerAdapter")
	private RequestMappingHandlerAdapter handlerAdapter;
	
	protected ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HandlerExecutionChain chain = handlerMapping.getHandler(request);
		return handlerAdapter.handle(request, response, chain.getHandler());
	}

}
