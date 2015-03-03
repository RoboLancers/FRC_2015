package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.util.LancerConstants;
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

	private ControlMode mode = ControlMode.PercentVbus;
	double moveAngle, facingAngle;

	public DriveFacingAngle(double moveAngle, double facingAngle) {
		requires(Robot.driveTrain);

		this.moveAngle = -moveAngle + 90;

		this.facingAngle = facingAngle * LancerConstants.deg2Rad;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		//make sure the set point is now forward
		//Robot.driveTrain.gyroPID.setReference(angle);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double correctedAngle = moveAngle - Robot.driveTrain.driveGyro.pidGet();

		if(Timer.getMatchTime() < 4.0){
			Robot.driveTrain.formulateDrive(0.5 * Math.cos(correctedAngle * LancerConstants.deg2Rad), 
					0.5 * Math.sin(correctedAngle * LancerConstants.deg2Rad), 
					facingAngle,
					mode);
		}else{
			Robot.driveTrain.formulateDrive(0, 0, 0, mode);
		}

		//		if(Timer.getMatchTime() < 4.0){
		//			Robot.driveTrain.formulateDrive(0.5 * Math.cos(Robot.driveTrain.gyroPID.calcPID(Robot.driveTrain.driveGyro.getAngle())*LancerConstants.deg2Rad), 
		//					0.5 * Math.sin(Robot.driveTrain.gyroPID.calcPID(Robot.driveTrain.driveGyro.getAngle()) *LancerConstants.deg2Rad), 
		//					Math.PI/2,
		//					mode);
		//		}else{
		//			Robot.driveTrain.formulateDrive(0, 0, 0, mode);
		//		}
		//
		//		double error = 90 - Robot.driveTrain.driveGyro.getAngle();
		//
		//		SmartDashboard.putNumber("Gyro PID", Robot.driveTrain.gyroPID.calcPID(error));
		//

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
