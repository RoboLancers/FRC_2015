package org.usfirst.frc.team321.util;

public class LancerFunctions {

	/*
	 * Compares a min and a max to the value, and returns the value if within the bounds
	 * else compares either the min or max, depending on its value
	 * 
	 * Can be used with all types
	 */
	public static <T extends Comparable <T>> T clamp(T val, T min, T max){
		if (val.compareTo(min) < 0) return min;
		else if(val.compareTo(max) > 0) return max;
		else return val;
	}

	/*
	 *
	 */
	public static boolean inRange(double val, double min, double max){
		return (val >= min && val <= max);
	}

	/*
	 * Creates a deadband: returns zero if the absolute value of the value is less than the threshold
	 */

	public static double deadBand(double val, double threshold) {
		if(Math.abs(val) > threshold) {
			return val;
		}

		return 0;
	}

	/*
	 *Normilization:
	 *
	 *	Take an array of values and return the percentages of the values relative to the min and max of that values
	 *	Returns values that are percentage based , while keeping initial direction from zero --> [ -1, 1 ]
	 *
	 */
	public static double[] normalize(double[] values) {
		double max_val = 0;

		double[] return_vals = new double[values.length];

		for(int i = 0; i < values.length; i++){
			if(values[i] > max_val){
				max_val = Math.abs(values[i]);
			}
		}

		if(max_val <= 1){
			return values;
		} else {
			for(int i = 0; i < values.length; i++){
				return_vals[i] = values[i] / max_val;
			}
		}
		return return_vals;
	}

	/*
	 *  Takes 2 points and interpolates movement between them
	 *  Linear Interpolation (LERP) is a method of curve fitting linearly
	 *  This function can be used to fill gaps between variables 
	 */
	public static double lerp(double from, double to, double delta){
		return (1 - delta) * from + delta * to;
	}

	public static double getRefAngle(double angle){
		int loops = (int) angle / 360;

		if(Math.signum(angle) == -1){
			return Math.abs(360 + angle - (loops * 360));
		}
		return Math.abs(angle - (loops * 360));

		//		if(angle<0){
		//			return angle % 360;
		//		}else{return 360 - (Math.abs(angle)%360);}
	}
	
	public static double coterminalize(double value, double min, double max){
		int loops = (int) (value / max);

		if(Math.signum(min) == -1){
			return Math.abs(max + value - (loops * 360));
		}
		return Math.abs(value - (loops * 360));
	}

}