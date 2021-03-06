package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.subsystems.Feeder;

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
		hasFinished = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		if(Math.signum(dir) == 1){ //Feeding inwards
			if(!Robot.feedback.toteDetectorLow.get()){ //if it hits the button, end the command
				hasFinished = true;
			}else{
				Robot.feeder.useFeeder(dir);
			}
		}else{
			Robot.feeder.useFeeder(dir);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return hasFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.feeder.useFeeder(Feeder.kStop); //Stop the intake
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
