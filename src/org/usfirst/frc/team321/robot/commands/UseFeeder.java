package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UseFeeder extends Command {

	private boolean hasFinished = false;
	private int dir;

	public UseFeeder(int dir) {
		requires(Robot.feeder);
		this.dir = dir;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		Robot.feeder.useFeeder(dir * 1);

		if(Math.signum(dir) == 1){ //Feeding inwards
			if(!Robot.feedback.toteDetector.get()){ //if it hits the button, end the command
				hasFinished = true;
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return hasFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.feeder.useFeeder(0); //Stop the intake
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
