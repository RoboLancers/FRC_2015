package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;
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
	public DigitalInput leftIR, rightIR;
	public DigitalInput toteDetector; //Button that detects totes
	
	public Feedback(){
		
		leftIR = new DigitalInput(RobotMap.leftIR);
		rightIR = new DigitalInput(RobotMap.rightIR);
		
		toteDetector = new DigitalInput(RobotMap.toteDetector);
		
		accel = new BuiltInAccelerometer();
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new RegulateSensors());
    }
}

