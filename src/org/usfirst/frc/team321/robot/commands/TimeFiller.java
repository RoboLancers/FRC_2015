package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TimeFiller extends Command {

	double startTime, targetTime;
	boolean isFirstPass = true;


	public TimeFiller(double targetTime) {
		requires(Robot.driveTrain);

		startTime = Timer.getFPGATimestamp();
		this.targetTime = targetTime;

		isFirstPass = true;
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	protected void onFirstPass(){
		startTime = Timer.getFPGATimestamp();
		isFirstPass = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(isFirstPass){ onFirstPass(); }

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Timer.getFPGATimestamp() - startTime > targetTime;
	}

	// Called once after isFinished returns true
	protected void end() {
		isFirstPass = true;
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
