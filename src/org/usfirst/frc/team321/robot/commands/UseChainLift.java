package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.subsystems.ChainLift;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UseChainLift extends Command {

	private boolean hasFinished = false;
	private int dir;

	public UseChainLift(int dir) {
		requires(Robot.chainLift);
		this.dir = dir;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		hasFinished = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(Math.signum(dir) == ChainLift.kUpward){ //Going Upwards
			if(Robot.feedback.toteDetectorHigh.get()){ //if the tote is at the top of th elevel, end the command
				hasFinished = true;
			}else{
				Robot.chainLift.useChainLift(dir);
			}
		}else{
			Robot.chainLift.useChainLift(dir);
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return hasFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.chainLift.useChainLift(ChainLift.kStop); //Stop the intake
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
