package org.usfirst.frc.team321.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotPorts {
	
	//RoboRio CAN Ports
	public static final int kCompressor = 0;
	
	//PWMS
	public static final int kIntakeRight = 8;
	public static final int kIntakeLeft = 7;
	public static final int kLiftMotor = 9;
	
	//Analog Sensors (Unused)
//	public static final int kDriveGyro = 0;
//	public static final int kOffsetSwitch = 2;
//	
	//Digital Sensors
	public static final int leftVL = 0;	
	public static final int rightVL = 1;
	public static final int liftEncA = 2;
	public static final int liftEncB = 3;
	public static final int toteDetectorLow = 4;
	public static final int toteDetectorHigh = 5;
	
	//Double Solenoids
	public static final int F_LIFT = 1;
	public static final int R_LIFT = 0;
	public static final int F_FEEDER = 2;
	public static final int R_FEEDER = 3;
	public static final int F_GRABBER = 4;
	public static final int R_GRABBER = 5;
	public static final int F_EXTENTION = 6;
	public static final int R_EXTENTION = 7;
	
}
