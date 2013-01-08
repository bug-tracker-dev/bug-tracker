package com.abc.bt.common.web.handler.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class BTMappingExceptionResolver extends SimpleMappingExceptionResolver {

	private Log _LOG = LogFactory.getLog(getClass());
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		
		String accept = request.getHeader("Accept");
		_LOG.info(accept);
		
		ModelAndView mav = null;
		Integer statusCode = null;
		if (accept.contains(MediaType.APPLICATION_XML_VALUE)) {
			statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			mav = new ModelAndView("xml-view");
			mav.addObject(new Exception(ex));
		} else if (accept.contains(MediaType.APPLICATION_JSON_VALUE)) {
			statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			mav = new ModelAndView("json-view");
			mav.addObject(new Exception(ex));
		} else {
			String viewName = determineViewName(ex, request);
			if (viewName != null) {
				statusCode = determineStatusCode(request, viewName);
				mav = getModelAndView(viewName, ex, request);
			}
		}
		
		applyStatusCodeIfPossible(request, response, statusCode);
		
		return mav;
	}

}
