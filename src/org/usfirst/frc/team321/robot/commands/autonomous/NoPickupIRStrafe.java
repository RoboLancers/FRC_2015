package org.usfirst.frc.team321.robot.commands.autonomous;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.commands.ChainToSetPoint;
import org.usfirst.frc.team321.robot.commands.DSolenoidToggle;
import org.usfirst.frc.team321.robot.commands.UseFeeder;
import org.usfirst.frc.team321.robot.commands.teleop.RegulateCompressor;
import org.usfirst.frc.team321.robot.subsystems.Feeder;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class NoPickupIRStrafe extends CommandGroup {

	public  NoPickupIRStrafe() {

		addParallel(new RegulateCompressor()); //Start the compressor
		
		addSequential(new DSolenoidToggle(Robot.feeder, Robot.feeder.feederSolenoid, Value.kForward)); //Make sure the feeder is open
		addSequential(new IRLockOnDrive(1.0)); //Lock on to the tote
		addSequential(new DSolenoidToggle(Robot.feeder, Robot.feeder.feederSolenoid, Value.kReverse)); //Close the Feeder
		addSequential(new UseFeeder(Feeder.kInward)); //Start the feeder
		addSequential(new DriveFacingAngle(90, 90, 1.3)); //After the chain gets to its set position, drive forward into auto zone
	}
}
