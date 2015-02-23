package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.OI;
import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.util.LancerPID;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RegulateIntake extends Command {

	public RegulateIntake() {
		requires(Robot.chainLift);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		//Manual Intake Commands
		if(OI.maniStick.getPOV() == 0){
			Robot.chainLift.useIntakeManual(1);
		}
		else if(OI.maniStick.getPOV() == 180){
			Robot.chainLift.useIntakeManual(-1);
		}
		else{
			if(Robot.chainLift.isManual)
				Robot.chainLift.stopIntake();
		}

		//PID Based methods
		if(!Robot.chainLift.isRunning){
			if(OI.maniBtn[4].get()){
				Robot.chainLift.invokeLevelAdjust(1);
			}
			else if(OI.maniBtn[2].get()){
				Robot.chainLift.invokeLevelAdjust(-1);
			}
		}else{
			Robot.chainLift.moveToSetpoint();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		//Stop the intake
		Robot.chainLift.stopIntake();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
