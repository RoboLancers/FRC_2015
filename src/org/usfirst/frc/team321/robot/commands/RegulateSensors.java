package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RegulateSensors extends Command {

    public RegulateSensors() {
    	requires(Robot.feedback);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Infrared Sensor
    	SmartDashboard.putBoolean("Left IR" ,Robot.feedback.leftIR.get());
    	SmartDashboard.putBoolean("Right IR" ,Robot.feedback.rightIR.get());
    	//Accelerometer Sensor
    	SmartDashboard.putNumber("Accelerometer X", Robot.feedback.accel.getX());
    	SmartDashboard.putNumber("Accelerometer Y", Robot.feedback.accel.getY());
    	SmartDashboard.putNumber("Accelerometer Z", Robot.feedback.accel.getZ());
    	
    	
    	double xAccel = Robot.feedback.accel.getX();
    	double yAccel = Robot.feedback.accel.getY();
    	double zAccel = Robot.feedback.accel.getZ();
    	
    	// get change in time (t - to) and reset prevTime (to)
    	double changeInTime = Robot.timer.getFPGATimestamp() - Robot.prevTime;
    	Robot.prevTime = Robot.timer.getFPGATimestamp();
    	
    	// X = Xo + Vot + (at^2 / 2)
    	Robot.xLoc = Robot.xLoc + Robot.xVel *  + xAccel * changeInTime * changeInTime / 2;
    	Robot.yLoc = Robot.yLoc + Robot.yVel *  + yAccel * changeInTime * changeInTime / 2;
    	Robot.zLoc = Robot.zLoc + Robot.zVel *  + zAccel * changeInTime * changeInTime / 2;
    	
    	// V = Vo + at
    	Robot.xVel = Robot.xVel + xAccel * changeInTime;
    	Robot.yVel = Robot.yVel + yAccel * changeInTime;
    	Robot.zVel = Robot.zVel + zAccel * changeInTime;
    	SmartDashboard.putNumber("X Location", Robot.xLoc);
    	SmartDashboard.putNumber("Y Location", Robot.yLoc);
    	SmartDashboard.putNumber("Z Location", Robot.zLoc);

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
