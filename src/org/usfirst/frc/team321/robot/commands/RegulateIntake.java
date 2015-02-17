package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.OI;
import org.usfirst.frc.team321.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RegulateIntake extends Command {

	public RegulateIntake() {
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//use the intake
		if(OI.maniBtn[0].get()){
			Robot.intake.useFeeder(1.0);
		}
		else if(OI.maniBtn[1].get()){
			Robot.intake.useFeeder(-1.0);
		}else{
			Robot.intake.useFeeder(0);
		}

		if(OI.maniStick.getPOV() == 0){
			Robot.intake.useIntakeManual(1);
		}
		else if(OI.maniStick.getPOV() == 180){
			Robot.intake.useIntakeManual(-1);
		}
		else{
			Robot.intake.stopIntake();
		}
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		//Stop the intake
		Robot.intake.useFeeder(0);
		Robot.intake.stopIntake();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
