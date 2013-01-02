package com.abc.bt.modules.sample;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import com.abc.bt.common.CommonTester2;

public class SampleTester2 extends CommonTester2 {

	private static final Logger _LOG = Logger.getLogger(SampleTester2.class);

	
	/**
	 * <li>test case for {@link com.abc.bt.modules.sample.controller.SampleController#json()}  
	 * <li>about the usage of jsonPath, plz refer to <a href="http://goessner.net/articles/JsonPath/">JSONPath</a>
	 */
	@Test
	public void json() {
		try {
			
			// write your test code here. in the try|catch block
			
			ResultActions ra = this.mockMvc.perform(post("/sample/json"));
			
			Object o = ra.andReturn().getResponse().getContentAsString();
			_LOG.info(o);
			
			ra.andExpect(status().isOk())
			  .andExpect(content().contentType("application/json;charset=UTF-8"))
			  .andExpect(jsonPath("$.prop_1").value(1))
			  .andExpect(jsonPath("$.prop_2").value(2))
			  .andExpect(jsonPath("$.prop_3").value(3))
			  .andExpect(jsonPath("$.prop_4").value(4));
		} catch (Throwable e) {
			_LOG.error(e.getMessage(), e);
		}
	}

}
