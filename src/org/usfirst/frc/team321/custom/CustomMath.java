package org.usfirst.frc.team321.custom;

public class CustomMath {
	
	public static float deltaTime = System.currentTimeMillis();
	
	public static double rad2Deg = 180 / Math.PI;
	public static double deg2Rad = Math.PI / 180;
	/*
	 * Compares a min and a max to the value, and returns the value if within the bounds
	 * else compares either the min or max, depending on its value
	*/
	public static <T extends Comparable <T>> T clamp(T val, T min, T max){
		if (val.compareTo(min) < 0) return min;
	    else if(val.compareTo(max) > 0) return max;
	    else return val;
	}
	
	/*
	 *  Takes 2 points and interpolates movement between them
	 *  Linear Interpolation (LERP) is a method of curve fitting linearly
	 *  This function can be used to fill gaps between variables 
	 */
	public static float lerp(float from, float to, float delta){
		return (1 - delta) * from + delta * to;
	}
	public static float slerp(float from, float to, float delta){
		return 0;
	}
	
}
