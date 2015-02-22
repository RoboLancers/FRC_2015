
package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.subsystems.DriveTrain;
import org.usfirst.frc.team321.util.LancerConstants;
import org.usfirst.frc.team321.util.LancerFunctions;
import org.usfirst.frc.team321.util.LancerFunctions.Integrator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 *
 */
public class RegulateSensors extends Command {

	LancerFunctions obj = new LancerFunctions();
	
	Integrator xI, yI, zI;
	Integrator x2I, y2I, z2I;
	
    public RegulateSensors() {
    	requires(Robot.feedback);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	xI = obj.new Integrator();
    	yI = obj.new Integrator();
    	zI = obj.new Integrator();
    	x2I = obj.new Integrator();
    	y2I = obj.new Integrator();
    	z2I = obj.new Integrator();
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.nonResettingGyroVal = Robot.driveTrain.driveGyro.getAngle() - Robot.gyroOffset;
    	
    	
    	
    	SmartDashboard.putBoolean("Left IR" ,Robot.feedback.leftIR.get());
    	SmartDashboard.putBoolean("Right IR" ,Robot.feedback.rightIR.get());
    	//Accelerometer Sensor
    	SmartDashboard.putNumber("Accelerometer X", Robot.feedback.accel.getX());
    	SmartDashboard.putNumber("Accelerometer Y", Robot.feedback.accel.getY());
    	SmartDashboard.putNumber("Accelerometer Z", Robot.feedback.accel.getZ());
    	
    	
    	double xAccel = Robot.feedback.accel.getX() * Math.cos(Robot.nonResettingGyroVal * LancerConstants.deg2Rad) + Math.sin(Robot.nonResettingGyroVal * LancerConstants.deg2Rad) * Robot.feedback.accel.getY();
    	double yAccel = Robot.feedback.accel.getY() * Math.cos(Robot.nonResettingGyroVal * LancerConstants.deg2Rad) + Math.sin(Robot.nonResettingGyroVal * LancerConstants.deg2Rad) * Robot.feedback.accel.getX();
    	double zAccel = Robot.feedback.accel.getZ();
    	
    
    	//Get the velocity via integration of aceleration
    	Robot.xVel = xI.getI(xAccel);
    	Robot.yVel = yI.getI(yAccel);
    	Robot.zVel = zI.getI(zAccel);
    	
    	//Get Location via integration of Velocity
    	Robot.xLoc = x2I.getI(Robot.xVel);
    	Robot.yLoc = y2I.getI(Robot.yVel);
    	Robot.zLoc = z2I.getI(Robot.zVel);
    	
    	
    	SmartDashboard.putNumber("X Location", Robot.xLoc);
    	SmartDashboard.putNumber("Y Location", Robot.yLoc);
    	SmartDashboard.putNumber("Z Location", Robot.zLoc);
    	
    	
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
