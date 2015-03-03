package org.usfirst.frc.team321.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//PWMS
	public static final int intakeRight = 8;
	public static final int intakeLeft = 7;
	public static final int liftMotor = 9;
	
	//Analog Sensors
	public static final int driveGyro = 0;
	
	//Digital Sensors
	public static final int leftIR = 0;
	public static final int rightIR = 1;
	public static final int liftEncA = 2;
	public static final int liftEncB = 3;
	public static final int toteDetector = 4;
	
	//Double Solenoids
	public static final int F_LIFT = 1;
	public static final int R_LIFT = 0;
}
