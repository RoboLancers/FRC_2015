package org.usfirst.frc.team321.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//Mechanum Drive Wheel Motors
	public static final int kF_left = 3;
	public static final int kF_right = 3;
	public static final int kR_left = 2;
	public static final int kR_right = 4;
	//Intake Motors
	public static final int intakeRight = 0;
	public static final int intakeLeft = 1;
	//
	public static final int liftMotor = 2;
	
	public static final int driveGyro = 0;
	
	
	public static final int leftIR = 0;
	public static final int rightIR = 1;
}
