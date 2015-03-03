
package org.usfirst.frc.team321.robot.commands.teleop;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.util.LancerFunctions;
import org.usfirst.frc.team321.util.LancerFunctions.Integrator;
import org.usfirst.frc.team321.util.LancerPID;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 *
 */
public class RegulateSensors extends Command {

	LancerPID xI, yI, zI;
	LancerPID x2I, y2I, z2I;

	public RegulateSensors() {
		requires(Robot.feedback);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		xI = new LancerPID(0,1,0);
		yI = new LancerPID(0,1,0);
		zI = new LancerPID(0,1,0);
		x2I = new LancerPID(0,1,0);
		y2I = new LancerPID(0,1,0);
		z2I = new LancerPID(0,1,0);

		xI.setReference(0);
		yI.setReference(0);
		zI.setReference(0);
		x2I.setReference(0);
		y2I.setReference(0);
		z2I.setReference(0);


	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double xV = xI.calcPID(Robot.feedback.accel.getX());
		double yV = yI.calcPID(Robot.feedback.accel.getY());
		double zV = zI.calcPID(Robot.feedback.accel.getZ());
		SmartDashboard.putNumber("xV", xV);
		SmartDashboard.putNumber("yV", yV);
		SmartDashboard.putNumber("zV", zV);

		double xP = x2I.calcPID(xV);
		double yP = y2I.calcPID(yV);
		double zP = z2I.calcPID(zV);
		SmartDashboard.putNumber("xP", xP);
		SmartDashboard.putNumber("yP", yP);
		SmartDashboard.putNumber("zP", zP);

		//Infrared Sensor
		SmartDashboard.putBoolean("Left IR" ,Robot.feedback.leftIR.get());
		SmartDashboard.putBoolean("Right IR" ,Robot.feedback.rightIR.get());
		
		SmartDashboard.putBoolean("Tote Detector" ,Robot.feedback.toteDetector.get());


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
