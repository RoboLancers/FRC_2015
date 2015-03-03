package org.usfirst.frc.team321.robot.commands.autonomous;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.util.LancerPID;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IRLockOnDrive extends Command {

	private boolean lastLeft, onTarget;
	private ControlMode mode = ControlMode.PercentVbus;
	private double startTargetTime = 0;
	private double targetMaxTime;

	public IRLockOnDrive(double time) {
		requires(Robot.driveTrain);
		this.targetMaxTime = time;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		//localize variables
		boolean left, right;
		left = Robot.feedback.leftIR.get();
		right = Robot.feedback.rightIR.get(); 


		if(!left || !right){
			if(left && !right){
				//Strafe left to correct the robot
				Robot.driveTrain.formulateDrive(0.3, 0, Math.PI, mode);
				lastLeft = true;
			}
			else if(!left && right){
				//Strafe right to correct the robot

				Robot.driveTrain.formulateDrive(0.3, 0, 0, mode);
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
			}

			//Set target timers to zero
			startTargetTime = 0;
			onTarget = false;
		}

		if(left && right){
			//TODO: Drive Straight
			Robot.driveTrain.formulateDrive(0.3, 0, Math.PI/2, mode);

			onTarget = true;
			if(startTargetTime == 0){ startTargetTime = Timer.getFPGATimestamp(); }
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (Timer.getFPGATimestamp() - startTargetTime > targetMaxTime) && onTarget;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.formulateDrive(0, 0, 0, mode);	//Stop the drive motors
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
