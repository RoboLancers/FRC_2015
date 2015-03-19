package org.usfirst.frc.team321.robot.commands.autonomous;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.util.LancerConstants;
import org.usfirst.frc.team321.util.LancerFunctions;
import org.usfirst.frc.team321.util.LancerPID;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveFacingAngle extends Command {

	boolean hasFinished;

	private ControlMode mode = ControlMode.PercentVbus;
	double moveAngle, facingAngle;
	double startTime, targetTime;

	LancerPID pid;


	public DriveFacingAngle(double moveAngle, double facingAngle, double time) {
		requires(Robot.driveTrain);

		this.moveAngle = moveAngle - 90;

		this.facingAngle = facingAngle * LancerConstants.deg2Rad;
		
		this.targetTime = time;
	}

	// Called just before this Command runs the first time
	protected void initialize() {

		pid = new LancerPID(1, 0, 1); // TODO: Set PID Constants
		pid.setReference(moveAngle);
		pid.setContinuous(0, 360);
		

		startTime = Timer.getFPGATimestamp();

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		double currentAngle = LancerFunctions.getRefAngle(Robot.driveTrain.navX.getYaw());

		if(Timer.getFPGATimestamp() - startTime < targetTime){
			Robot.driveTrain.formulateDrive(0.5* Math.cos(pid.calcPID(currentAngle) * LancerConstants.deg2Rad), 
					0.5 * Math.sin(pid.calcPID(currentAngle) * LancerConstants.deg2Rad), 
					facingAngle,
					mode);
		}else{
			Robot.driveTrain.formulateDrive(0, 0, 0, mode);
			hasFinished = true;
		}

		SmartDashboard.putNumber("Gyro PID", pid.calcPID(Robot.driveTrain.driveGyro.getAngle()));

		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return hasFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.formulateDrive(0, 0, 0, mode);
	}

	// Called when another comsmand which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
