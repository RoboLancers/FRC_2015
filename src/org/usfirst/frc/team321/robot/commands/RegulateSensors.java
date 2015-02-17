
package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;
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
	/*
	Integrator xI, yI, zI;
	Integrator x2I, y2I, z2I;
	*/
    public RegulateSensors() {
    	requires(Robot.feedback);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	/*xI = obj.new Integrator();
    	yI = obj.new Integrator();
    	zI = obj.new Integrator();
    	x2I = obj.new Integrator();
    	y2I = obj.new Integrator();
    	z2I = obj.new Integrator();
    	*/
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	
    	
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
