package org.usfirst.frc.team321.robot.commands.autonomous;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.util.LancerConstants;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveFacingAngle extends Command {

	private ControlMode mode = ControlMode.PercentVbus;
	double moveAngle, facingAngle;
	double startTime, targetTime;
	double maxPower;

	boolean isFirstPass = true;


	public DriveFacingAngle(double maxPower, double moveAngle, double facingAngle, double time) {
		requires(Robot.driveTrain);
		this.moveAngle = moveAngle * LancerConstants.deg2Rad;
		this.facingAngle = facingAngle;
		this.targetTime = time;
		this.maxPower = maxPower;
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	protected void onFirstPass(){
		Robot.driveTrain.facingPID.setReference(facingAngle);
		Robot.driveTrain.facingPID.setContinuous(0, 360);
		startTime = Timer.getFPGATimestamp();
		isFirstPass = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(isFirstPass){
			onFirstPass();
		}else{

			if(Robot.driveTrain.navX.isConnected()){
				Robot.driveTrain.formulateDriveGyro(
						maxPower, //Correct Facing Angle (Forward Component)
						-0.5 * Math.sin(Robot.driveTrain.facingPID.calcPID(Robot.driveTrain.getFacingAngle())), //Correct Facing Angle (Perpindicular)
						moveAngle, //Movement corrected by field centric steering
						mode);
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Timer.getFPGATimestamp() - startTime > targetTime;
	}


	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.formulateDrive(0, 0, 0, mode);
		isFirstPass = true;
	}

	// Called when another comsmand which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
