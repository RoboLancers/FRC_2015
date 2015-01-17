package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.custom.CustomMath;
import org.usfirst.frc.team321.custom.PID;
import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {

	private PID correct = new PID();
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
    	double angle = 90 * CustomMath.deg2Rad;
    	
    	while(Timer.getMatchTime() < 4.0){
    		Robot.driveTrain.formulateDrive(0.5, 0, angle);
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
