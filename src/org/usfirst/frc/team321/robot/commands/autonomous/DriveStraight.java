package org.usfirst.frc.team321.robot.commands.autonomous;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.util.LancerConstants;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {

	private ControlMode mode = ControlMode.PercentVbus;
	double angle;

	public DriveStraight() {
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		//make sure the set point is now forward
		Robot.driveTrain.driveGyro.reset();
		angle = Math.PI/2;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		if(Timer.getMatchTime() < 4.0){
			Robot.driveTrain.formulateDrive(0.5 * Math.cos(Robot.driveTrain.driveGyro.pidGet()*LancerConstants.deg2Rad), 
					0.5 * Math.sin(Robot.driveTrain.driveGyro.pidGet() *LancerConstants.deg2Rad), 
					Math.PI/2,
					mode);
		}else{
			Robot.driveTrain.formulateDrive(0, 0, 0, mode);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another comsmand which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
