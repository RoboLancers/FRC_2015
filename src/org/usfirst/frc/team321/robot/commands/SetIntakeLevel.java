package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetIntakeLevel extends Command {

	public int amt;
	public int setPoint;
	public boolean hasFinished = false;

	public SetIntakeLevel(int amt) {
		this.amt = amt;
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		//The setpoint for the encoder
		setPoint = Robot.intake.lastSetPoint + (amt * Intake.kLevelDist);

		//Sets the last set point to be used as a reference for later
		Robot.intake.lastSetPoint = setPoint;
		
		Intake.enc.setPIDSourceParameter(PIDSourceParameter.kDistance);
		Intake.encController.setReference(setPoint);
		
		Robot.intake.isManual = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		//if the intake setting becomes manual or the process is finished, stop the process
		if(Robot.intake.isManual || Intake.encController.isDone())
			hasFinished = true;
		    
			Intake.encController.setMaxOut(0.0); //Sets max output to 0. so it stops
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return hasFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
		Intake.encController.setMaxOut(0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
