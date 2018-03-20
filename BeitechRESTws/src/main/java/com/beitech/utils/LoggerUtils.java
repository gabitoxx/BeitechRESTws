package com.beitech.utils;

public class LoggerUtils {
	
	@SuppressWarnings("unused")
	private final long idx = -1;
	
	private static boolean bEnabled = true;
	
	public static void debug(Object clazz, String message) {
		/**
		 * Here we will set the print outs (depending on Logger configurations)
		 *  for now, only simulated behavior
		 */
		if ( bEnabled ) {
			System.out.println(clazz.getClass().getSimpleName() + " - DEBUG - " + message);
		}
	}
	
	public static void error(Object clazz, String message) {
		/**
		 * still simulating...
		 */
		if ( bEnabled ) {
			System.out.println(clazz.getClass().getSimpleName() + " - ERROR - " + message);
		}
	}
	
	/**
	 * to measurement
	 * @param methodName
	 * @param millseconds OR maybe nanoseconds
	 */
	public static void performance(String methodName, long millseconds) {
		/**
		 * still simulating...
		 */
		if ( bEnabled ) {
			System.out.println(methodName + " - PERFORMANCE - it tooks " + millseconds + " millisecs");
		}
	}
}