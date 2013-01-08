package com.abc.bt.common.web.handler.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.minidev.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

	private Log _LOG = LogFactory.getLog(getClass());
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {

		// TODO exception not finished yet
		// http://blog.csdn.net/wutbiao/article/details/7454358
		// read others code,learn
		_LOG.warn("Handle exception: " + ex.getClass().getName());
        
        Map model = new HashMap();
        model.put("ex class name", ex.getClass().getSimpleName());
        model.put("error msg", ex.getMessage());
        
        
        JSONObject object = new JSONObject();
        object.put("exception",model);

        response.setContentType("application/json;charset=gbk");
        PrintWriter writer = null;
        try {
    		writer = response.getWriter();
    		writer.print(object.toString());
    	} catch (IOException e) {
    		e.printStackTrace();
    		// TODO
    	} finally{
    		if(writer != null){
    			writer.close();
    		}
    	}

        return null;

//        return new ModelAndView("error", model);

	}

}
