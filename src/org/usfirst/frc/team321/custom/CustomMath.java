package org.usfirst.frc.team321.custom;

public class CustomMath {
	
	/*
	 * Compares a min and a max to the value, and returns the value if within the bounds
	 * else compares either the min or max, depending on its value
	*/
	public static <T extends Comparable <T>> T clamp(T val, T min, T max){
		if (val.compareTo(min) < 0) return min;
	    else if(val.compareTo(max) > 0) return max;
	    else return val;
	}
}
