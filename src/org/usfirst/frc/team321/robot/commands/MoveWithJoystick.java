package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.OI;
import org.usfirst.frc.team321.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveWithJoystick extends Command {

    public MoveWithJoystick() {
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//local reference for the joystick 
    	double xIn, yIn;
    	
    	//1% Tolerance
    	if(Math.abs(OI.driveStick.getRawAxis(0)) > 0.01){
    		xIn = OI.driveStick.getRawAxis(0);
    	}else{
    		xIn = 0;
    	}
    	
    	if(Math.abs(OI.driveStick.getRawAxis(1)) > 0.01){
    		yIn = -OI.driveStick.getRawAxis(1);
    	}else{
    		yIn = 0;
    	}
    	
    	//get the magnitude of the joystick
    	double axisNormalized = Math.hypot(xIn, yIn);
    	//get the value of the flexible axis to rotate, this will determine angular velocity
    	double angVel;
    	
    	if(Math.abs(OI.driveStick.getRawAxis(4)) > 0.01){
    		angVel = OI.driveStick.getRawAxis(4);
    	}else{
    		angVel = 0;
    	}
    	//The angle defined by the cartesian plane transferred into polar coordinates
    	double angle;
    	//Make sure the angle is not undefined
    	if(xIn != 0){
    		angle = Math.atan2(-OI.driveStick.getRawAxis(1),OI.driveStick.getRawAxis(0));
    	}
    	else if(yIn == 0 && xIn == 0){
    		angle = Math.PI/2;
    	}else{
    		angle = Math.PI - Math.abs(OI.driveStick.getRawAxis(1)) / -OI.driveStick.getRawAxis(1)*Math.PI/2;
    	}
    	
    	Robot.driveTrain.formulateDrive(axisNormalized, angVel, angle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
