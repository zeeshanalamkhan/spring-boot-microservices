package com.zeeshan.cs.utils;

public class MyThreadLocalsHolder {

	private static final ThreadLocal<String> CORRELATION_ID = new ThreadLocal<String>();

	public static String getCorrelationId() {
		return CORRELATION_ID.get();
	}

	public static void setCorrelationId(String correlationId) {
		CORRELATION_ID.set(correlationId);
	}

}
