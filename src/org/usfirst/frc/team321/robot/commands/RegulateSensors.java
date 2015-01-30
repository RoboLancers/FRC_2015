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
