package org.usfirst.frc.team321.robot.commands.autonomous;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.util.LancerConstants;
import org.usfirst.frc.team321.util.LancerPID;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {

	private LancerPID pid = new LancerPID(1,1,1);
	double angle;
	
    public DriveStraight() {
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//make sure the set point is now forward
    	Robot.driveTrain.driveGyro.reset();
    	angle = Math.PI/2;
    	
    	pid.setReference(angle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	while(Timer.getMatchTime() < 4.0){
    		Robot.driveTrain.driveWithJoystick(0.5, 0, Math.PI/2);
    	}
    	
    	//stop the robot
    	Robot.driveTrain.driveWithJoystick(0, 0, 0);
    	
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
