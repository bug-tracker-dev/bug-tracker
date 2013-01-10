package com.abc.bt.common.test;

import java.util.Collection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath*:conf/applicationContext-*.xml")
public class SVCCommonTester {
	
	private static final Logger _LOG = Logger.getLogger(SVCCommonTester.class);
	
	protected void trace(Object o) {
		_LOG.info(JSONObject.fromObject(o));
	}
	
	protected void trace(Collection<?> coll) {
		_LOG.info(JSONArray.fromObject(coll));
	}

}
