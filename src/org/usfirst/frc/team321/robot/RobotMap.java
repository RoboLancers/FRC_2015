package org.usfirst.frc.team321.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//Intake Motors
	public static final int intakeRight = 1;
	public static final int intakeLeft = 2;
	//
	public static final int liftMotor = 0;
	
	public static final int driveGyro = 0;
	
	
	public static final int leftIR = 0;
	public static final int rightIR = 1;
	
	//Double Solenoids
	public static final int F_LIFT = 1;
	public static final int R_LIFT = 0;
}
