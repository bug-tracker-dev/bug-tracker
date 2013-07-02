package com.abc.bt.common.ws.auth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ws.security.WSPasswordCallback;

public class ServerAuthCallback implements CallbackHandler {

	private Log _LOG = LogFactory.getLog(getClass());
	
	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		
		Map<String, String> user = new HashMap<String, String>();
		{
			user.put("admin", "888888");
			user.put("su", "1234");
		}

		
		WSPasswordCallback wpc = (WSPasswordCallback) callbacks[0];
		String identifier = wpc.getIdentifier();
		
		if(!user.containsKey(identifier)) {
			throw new SecurityException("User: " + identifier + "无权限访问。");
		}

		wpc.setPassword(user.get(identifier));
		
		
		_LOG.info("username: " + identifier + "    password: " + wpc.getPassword());
		

	}

}
