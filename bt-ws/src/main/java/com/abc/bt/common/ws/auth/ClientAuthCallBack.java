package com.abc.bt.common.ws.auth;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

public class ClientAuthCallBack implements CallbackHandler {

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
		
		pc.setIdentifier("admin");
		//pc.setIdentifier("abcd");
		pc.setPassword("888888");
		//pc.setPassword("123");

	}

}
