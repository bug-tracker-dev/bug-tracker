package com.abc.bt.modules.sample.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.abc.bt.modules.sample.model.ComplexForm;

@Controller
@RequestMapping(value = "/sample")
public class SampleController {

	private static final Logger _LOG = Logger.getLogger(SampleController.class);

	@RequestMapping(value = "/home")
	public String home() {
		_LOG.info("sample home page");

		return "sample/home";
	}

	@RequestMapping(value = "/json")
	public @ResponseBody Map<?, ?> json() {
		_LOG.info("json");

		Map<Object, Object> map = new HashMap<Object, Object>();
		
		for (int i = 0; i < 5; i++) {
			map.put("prop_" + i, i);
		}
		
		return map;
	}
	
	@RequestMapping(value = "/jsonarray")
	public @ResponseBody List<ComplexForm> jsonarray() {
		_LOG.info("jsonarray");

		List<ComplexForm> fs = new ArrayList<ComplexForm>();
		ComplexForm f = null;
		
		for (int i = 0; i < 5; i++) {
			f = new ComplexForm();
			f.setUsername("guest_"+i);
			fs.add(f);
		}
		
		return fs;
	}
	
	@RequestMapping(value="complex")
	public @ResponseBody ComplexForm complex(ComplexForm form) {
		_LOG.info("sample complex form");
		
		return form;
	}
	
	@RequestMapping(value="legacy")
	public String legacy(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model) {
		_LOG.info("sample legacy request");
		
		_LOG.info(req.getContextPath());
		_LOG.info(resp.getCharacterEncoding());
		_LOG.info(session.getId());
		_LOG.info(model);
		
		model.addAttribute("a1", req.getContextPath());
		model.addAttribute("a2", resp.getCharacterEncoding());
		model.addAttribute("a3", session.getId());
		
		_LOG.info(model);
		
		return "sample/legacy";
	}
	
	@RequestMapping(value = "xml")
	public @ResponseBody ComplexForm xml() {
		
		_LOG.info("sample xml request");
		
		ComplexForm cf = new ComplexForm();
		cf.setUsername("guest");
		cf.setPassword("123456");
		cf.setGender("M");
		
		return cf;
	}
	
	@RequestMapping(value = "upload")
	public void upload(MultipartFile file) {
		//TODO 
	}
}
