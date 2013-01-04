package com.abc.bt.modules.sample.service;

import org.junit.Test;

import com.abc.bt.common.test.SVCCommonTester;

public class SampleServiceTester extends SVCCommonTester {

	// TODO define 2 svc: ASvc and BSvc
	// TODO 
	
	/**
	 * test the transaction propagations and rollbacks
	 * 7 cases need to be tested and 3 rollbacks
	 * 	<tx:method name="save*" propagation="REQUIRED" rollback-for="Exception"/>
		<tx:method name="remove*" propagation="REQUIRED" rollback-for="Exception" />
		<tx:method name="update*" propagation="REQUIRED" rollback-for="Exception"  />
		<tx:method name="get*" propagation="REQUIRED" />
		<tx:method name="load*" propagation="REQUIRED" />
		<tx:method name="exist*" propagation="REQUIRED" />
		<tx:method name="*" read-only="true" />
	 */
	@Test public void save() {}
	@Test public void remove() {}
	@Test public void update() {}
	@Test public void get() {}
	@Test public void load() {}
	@Test public void exist() {}
	@Test public void abc() {}
	
	/**
	 * multi- Data operations in 1 txn
	 */
	@Test public void mulitdo() {}
	
	/**
	 * multi-thread operate the same data via lock
	 */
	@Test public void lock() {}
	
	/**
	 * BSvc referenced in ASvc
	 */
	@Test public void svcref() {}
	
}
