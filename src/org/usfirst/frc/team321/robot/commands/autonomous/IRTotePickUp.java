package org.usfirst.frc.team321.robot.commands.autonomous;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.commands.DSolenoidToggle;
import org.usfirst.frc.team321.robot.commands.DriveFacingAngle;
import org.usfirst.frc.team321.robot.commands.UseFeeder;
import org.usfirst.frc.team321.robot.commands.teleop.RegulateCompressor;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IRTotePickUp extends CommandGroup {

	public  IRTotePickUp() {
		
		addParallel(new RegulateCompressor()); //Start the compressor
		addSequential(new DSolenoidToggle(Robot.feeder, Robot.feeder.feederSolenoid, Value.kForward)); //Make sure the feeder is open
		
		addSequential(new IRLockOnDrive(2.0)); //Lock on to the tote
		addSequential(new DSolenoidToggle(Robot.feeder, Robot.feeder.feederSolenoid, Value.kReverse)); //Close the Feeder
		addSequential(new UseFeeder(1)); //Start the feeder
		//addSequential(new ChainToSetPoint(400)); //After it hits the button, start using the chain lift
		addSequential(new DriveFacingAngle(90, 90)); //After the chain gets to its set position, drive forward into auto zone
	}
}
