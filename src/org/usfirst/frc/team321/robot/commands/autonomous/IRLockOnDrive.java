package org.usfirst.frc.team321.robot.commands.autonomous;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.util.LancerPID;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IRLockOnDrive extends Command {

	private boolean lastLeft, onTarget;
	private ControlMode mode = ControlMode.PercentVbus;

	public IRLockOnDrive() {
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrain.driveGyro.reset();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		//localize variables
		boolean left, right;
		left = Robot.feedback.leftIR.get();
		right = Robot.feedback.rightIR.get(); 
		
		
		if(left && !right){
			//Strafe right to correct the robot
			Robot.driveTrain.formulateDrive(0.5, 0, 0, mode);
			lastLeft = true;
		}
		else if(!left && right){
			//Strafe left to correct the robot

			Robot.driveTrain.formulateDrive(0.5, 0, Math.PI, mode);
			lastLeft = false;
		}
		else if(!left && !right){
			//Rotate until one of the sensors pick up some data
			if(lastLeft){
				//Rotate Right
				Robot.driveTrain.formulateDrive(0, -0.3, 0, mode);
			}else{
				//Rotate Left
				Robot.driveTrain.formulateDrive(0, 0.3, 0, mode);	
			}
		} else {
			//TODO: Drive Straight
			Robot.driveTrain.formulateDrive(0.5, 0, Math.PI/2, mode);
			
			
			//TODO: Get the current facing angle and then move forward using PID controls
			onTarget = true;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
