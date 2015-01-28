package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;
import org.usfirst.frc.team321.robot.commands.RegulateSensors;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Feedback extends Subsystem {
    
	//Digital Sensors
	public DigitalInput leftIR, rightIR;
	
	public Feedback(){
		
		leftIR = new DigitalInput(RobotMap.leftIR);
		rightIR = new DigitalInput(RobotMap.rightIR);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new RegulateSensors());
    }
}

