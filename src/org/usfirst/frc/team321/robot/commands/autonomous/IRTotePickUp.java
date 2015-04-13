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
public class IRTotePickUp extends CommandGroup {

	public  IRTotePickUp() {

		addParallel(new RegulateCompressor()); //Start the compressor
		
		addSequential(new DSolenoidToggle(Robot.feeder, Robot.feeder.feederSolenoid, Value.kForward)); //Make sure the feeder is open
		addSequential(new IRLockOnDrive(1.0)); //Lock on to the tote
		addSequential(new DSolenoidToggle(Robot.feeder, Robot.feeder.feederSolenoid, Value.kReverse)); //Close the Feeder
		addSequential(new UseFeeder(Feeder.kInward)); //Start the feeder
		addSequential(new ChainToSetPoint(ChainToSetPoint.TYPE_ABSOLUTE, 400)); //After it hits the button, start using the chain lift
		addSequential(new DriveFacingAngle(0.5 ,270, 3 * Math.PI/2, 1.3)); //After the chain gets to its set position, drive forward into auto zone
		addSequential(new ChainToSetPoint(ChainToSetPoint.TYPE_ABSOLUTE, -400)); //Drop the tote
	}
}
