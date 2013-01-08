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
	 */
	@Test
	public void json() {
		try {

			// write your test code here. in the try|catch block

			ResultActions ra = this.mockMvc.perform(post("/sample/json")
					.accept(MediaType.APPLICATION_JSON));

			Object o = ra.andReturn().getResponse().getContentAsString();
			_LOG.info(o);

			ra.andExpect(status().isOk())
					.andExpect(
							content().contentType(
									"application/json;charset=UTF-8"))
					.andExpect(jsonPath("$.prop_1").value(1))
					.andExpect(jsonPath("$.prop_2").value(2))
					.andExpect(jsonPath("$.prop_3").value(3))
					.andExpect(jsonPath("$.prop_4").value(4));
		} catch (Throwable e) {
			_LOG.error(e.getMessage(), e);
		}
	}

	@Test
	public void errorjson() {
		try {
			ResultActions ra = this.mockMvc
					.perform(post("/sample/error@handler").accept(
							MediaType.APPLICATION_JSON));
			Object o = ra.andReturn().getResponse().getContentAsString();
			_LOG.info(o);

		} catch (Throwable e) {
			_LOG.error(e.getMessage(), e);
		}
	}

	@Test
	public void errorxml() {
		try {
			ResultActions ra = this.mockMvc.perform(post(
					"/sample/error@handler").accept(MediaType.APPLICATION_XML));
			Object o = ra.andReturn().getResponse().getContentAsString();
			_LOG.info(o);

		} catch (Throwable e) {
			_LOG.error(e.getMessage(), e);
		}
	}

	@Test
	public void errordefault() {
		try {
			ResultActions ra = this.mockMvc
					.perform(post("/sample/errordefault"));
			Object o = ra.andReturn().getResponse().getContentAsString();
			_LOG.info(o);

		} catch (Throwable e) {
			_LOG.error(e.getMessage(), e);
		}
	}

	@Test
	public void errorsimpleurljson() {
		try {
			ResultActions ra = this.mockMvc
					.perform(post("/sample/error@handler").accept(
							MediaType.APPLICATION_JSON));
			Object o = ra.andReturn().getResponse().getContentAsString();
			_LOG.info(o);

		} catch (Throwable e) {
			_LOG.error(e.getMessage(), e);
		}
	}

	@Test
	public void errorsimpleurlxml() {
		try {
			ResultActions ra = this.mockMvc.perform(post(
					"/sample/error@handler").accept(MediaType.APPLICATION_XML));
			Object o = ra.andReturn().getResponse().getContentAsString();
			_LOG.info(o);

		} catch (Throwable e) {
			_LOG.error(e.getMessage(), e);
		}
	}
}
