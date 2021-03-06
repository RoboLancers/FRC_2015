package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.commands.autonomous.DriveFacingAngle;
import org.usfirst.frc.team321.robot.commands.teleop.RegulateCompressor;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TwoCanAuto extends CommandGroup {

	public  TwoCanAuto() {
		addParallel(new RegulateCompressor());
		addSequential(new DSolenoidToggle(Robot.grabber, Robot.grabber.extentionSolenoid, Value.kForward)); //Extend the grabber
		addSequential(new DriveFacingAngle(0.75, 90, 3* Math.PI/2, 0.04)); //Drive Backwards towards the cans
		//Extend the down the grabber
		addSequential(new DSolenoidToggle(Robot.grabber, Robot.grabber.grabSolenoid, Value.kForward)); //put down the grabber
		
		addSequential(new TimeFiller(1.0));
		addSequential(new DriveFacingAngle(75, 90, Math.PI/2, 0.840)); //TODO: calculate the dist from step to the autozone
		//Retract the Grabber
		addSequential(new DSolenoidToggle(Robot.grabber, Robot.grabber.grabSolenoid, Value.kReverse));
		addSequential(new DSolenoidToggle(Robot.grabber, Robot.grabber.extentionSolenoid, Value.kReverse));
	}

}
