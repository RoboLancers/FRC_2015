package org.usfirst.frc.team321.robot.commands.autonomous;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.util.LancerPID;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IRLockOnDrive extends Command {

	private boolean lastLeft;
	private LancerPID pid;
	
    public IRLockOnDrive() {
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.driveGyro.reset();
    	pid = new LancerPID();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//localize variables
    	boolean left, right;
    	left = Robot.feedback.leftIR.get();
    	right = Robot.feedback.rightIR.get(); 
    	
    	//The IR picks up data from the left side
    	if(left && !right){
    		//TODO: Strafe right to correct the robot
    		
    		Robot.driveTrain.formulateDrive(1, 0, 0); //Should make formulateDrive static ?
    		 
    		lastLeft = true;
    	}
    	else if(!left && right){
    		//TODO: Strafe right to correct the robot
    		
    		Robot.driveTrain.formulateDrive(1, 0, Math.PI); //Should make formulateDrive static
    		lastLeft = false;
    	}
    	else if(!left && !right){
    		//TODO: Rotate until one of the sensors pick up some data
    		if(lastLeft){
    			//Rotate Right
    			
    			Robot.driveTrain.formulateDrive(1, 0.2, Math.PI/2); //Should make formulateDrive static
    			
    		}else{
    			//Rotate Left
    			
    			Robot.driveTrain.formulateDrive(1, -0.2, Math.PI/2); //Should make formulateDrive static
    			
    		}
    	} else {
    		//TODO: Drive Straight
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
