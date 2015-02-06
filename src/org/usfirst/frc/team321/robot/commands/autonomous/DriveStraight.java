package org.usfirst.frc.team321.robot.commands.autonomous;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.subsystems.DriveTrain;
import org.usfirst.frc.team321.util.LancerFunctions;
import org.usfirst.frc.team321.util.LancerPID;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {

	private LancerPID correct = new LancerPID();
	boolean pid = true;
	
    public DriveStraight() {
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//make sure the set point is now forward
    	DriveTrain.driveGyro.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//double angle = Math.PI / 2;
    	double angle = Math.PI/2;
    	double corrected = angle;
    	
    	if(pid){
    		corrected = correct.gyroCalc();
    	}
    	
    	while(Timer.getMatchTime() < 4.0){
    		Robot.driveTrain.formulateDrive(0.5, corrected, angle);
    	}
    	
    	//stop the robot
    	Robot.driveTrain.formulateDrive(0, 0, 0);
    	
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
