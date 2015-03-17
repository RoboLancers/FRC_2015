package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotPorts;
import org.usfirst.frc.team321.robot.commands.teleop.RegulateSensors;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Feedback extends Subsystem {


	public BuiltInAccelerometer accel;

	//Digital Sensors
	public DigitalInput leftVL, rightVL;
	public DigitalInput toteDetectorLow, toteDetectorHigh; //Button that detects totes

	public Feedback(){

		leftVL = new DigitalInput(RobotPorts.leftVL);
		rightVL = new DigitalInput(RobotPorts.rightVL);

		toteDetectorLow = new DigitalInput(RobotPorts.toteDetectorLow);
		toteDetectorHigh = new DigitalInput(RobotPorts.toteDetectorHigh);

		accel = new BuiltInAccelerometer();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new RegulateSensors());
	}
}

