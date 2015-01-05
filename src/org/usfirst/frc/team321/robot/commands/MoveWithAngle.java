package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.OI;
import org.usfirst.frc.team321.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveWithAngle extends Command {

    public MoveWithAngle() {
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//local reference for the joystick 
    	double xIn = OI.driveStick.getRawAxis(1);
    	double yIn =  OI.driveStick.getRawAxis(2);
    	
    	//get the magnitude of the joystick
    	double axisNormalized = Math.tan(yIn/ xIn);
    	//get the value of the flexible axis to rotate, this will determine angular velocity
    	double angVel = OI.driveStick.getRawAxis(3);
    	//The angle defined by the cartesian plane transferred into polar coordinates
    	double angle = Math.atan2(yIn, xIn);
    	
    	Robot.driveTrain.angleToDriveMechanum(axisNormalized, angVel, angle);
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
