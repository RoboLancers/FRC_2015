package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ResetGyro extends Command {

	private boolean hasFinished;
	
    public ResetGyro() {
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	Robot.driveTrain.driveGyro.reset();
    	Robot.gyroOffset = Robot.driveTrain.driveGyro.getAngle() - Robot.nonResettingGyroVal;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	hasFinished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return hasFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
