package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.RobotMap;
import org.usfirst.frc.team321.robot.commands.RegulateSensors;

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
	
	public Feedback(){
		
		leftIR = new DigitalInput(RobotMap.leftIR);
		rightIR = new DigitalInput(RobotMap.rightIR);
		
		accel = new BuiltInAccelerometer();
	}
	
	public void update(){
		//Convert into Ft/sec
    	/*double xAccel = Robot.feedback.accel.getX() * 32.144;
    	double yAccel = Robot.feedback.accel.getY() * 32.144;
    	double zAccel = Robot.feedback.accel.getZ() * 32.144;
    	Robot.xAccel = xAccel;
    	Robot.yAccel = yAccel;
    	Robot.zAccel = zAccel;
    
    	//Get the velocity via integration of aceleration
    	Robot.xVel = xI.getI(xAccel);
    	Robot.yVel = yI.getI(yAccel);
    	Robot.zVel = zI.getI(zAccel);
    	
    	//Get Location via integration of Velocity
    	Robot.xLoc = x2I.getI(Robot.xVel);
    	Robot.yLoc = y2I.getI(Robot.yVel);
    	Robot.zLoc = z2I.getI(Robot.zVel);
    	*/
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new RegulateSensors());
    }
}

