package com.abc.bt.modules.sample.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import com.abc.bt.common.test.GUICommonTester2;

public class SampleControllerTester2 extends GUICommonTester2 {

	private static final Logger _LOG = Logger
			.getLogger(SampleControllerTester2.class);

	/**
	 * <li>test case for
	 * {@link com.abc.bt.modules.sample.controller.SampleController#json()} <li>
	 * about the usage of jsonPath, plz refer to <a
	 * href="http://goessner.net/articles/JsonPath/">JSONPath</a>
	 * 
	 * @throws Exception
	 */
	@Test
	public void json() throws Exception {

		ResultActions ra = this.mockMvc.perform(post("/sample/json").accept(MediaType.APPLICATION_JSON));

		Object o = ra.andReturn().getResponse().getContentAsString();
		_LOG.info(o);

		ra.andExpect(status().isOk())
		  .andExpect(content().contentType("application/json;charset=UTF-8"))
		  .andExpect(jsonPath("$.prop_1").value(1))
		  .andExpect(jsonPath("$.prop_2").value(2))
		  .andExpect(jsonPath("$.prop_3").value(3))
		  .andExpect(jsonPath("$.prop_4").value(4));
	}

	@Test
	public void errorjson() throws Exception {
		ResultActions ra = this.mockMvc.perform(post("/sample/error@handler").accept(MediaType.APPLICATION_JSON));
		Object o = ra.andReturn().getResponse().getContentAsString();
		_LOG.info(o);
		ra.andExpect(status().isOk());
	}

	@Test
	public void errorxml() throws Exception {
		ResultActions ra = this.mockMvc.perform(post("/sample/error@handler").accept(MediaType.APPLICATION_XML));
		Object o = ra.andReturn().getResponse().getContentAsString();
		_LOG.info(o);
		ra.andExpect(status().isOk());
	}

	@Test
	public void errordefault() throws Exception {
		ResultActions ra = this.mockMvc.perform(post("/sample/errordefault"));
		Object o = ra.andReturn().getResponse().getContentAsString();
		_LOG.info(o);
		ra.andExpect(status().isOk());
	}

	@Test
	public void errorsimpleurljson() throws Exception {
		ResultActions ra = this.mockMvc.perform(post("/sample/error@handler").accept(MediaType.APPLICATION_JSON));
		Object o = ra.andReturn().getResponse().getContentAsString();
		_LOG.info(o);
		ra.andExpect(status().isOk());
	}

	@Test
	public void errorsimpleurlxml() throws Exception {
		ResultActions ra = this.mockMvc.perform(post("/sample/error@handler").accept(MediaType.APPLICATION_XML));
		Object o = ra.andReturn().getResponse().getContentAsString();
		_LOG.info(o);
		ra.andExpect(status().isOk());
	}
}
