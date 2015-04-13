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
public class ThreeToteAuto extends CommandGroup {  

	public  ThreeToteAuto() {
		addParallel(new RegulateCompressor()); //Start the compressor
		
		//1st Tote
		addSequential(new DSolenoidToggle(Robot.feeder, Robot.feeder.feederSolenoid, Value.kForward)); //Make sure the feeder is open
		addSequential(new IRLockOnDrive(1.0)); //Lock on and obtain tote
		addSequential(new DSolenoidToggle(Robot.feeder, Robot.feeder.feederSolenoid, Value.kReverse)); //Close the Feeder
		addSequential(new UseFeeder(Feeder.kInward)); //Start the feeder
		addSequential(new DSolenoidToggle(Robot.feeder, Robot.feeder.feederSolenoid, Value.kForward)); //Make sure the feeder is open
		addSequential(new ChainToSetPoint(ChainToSetPoint.TYPE_ABSOLUTE, 400)); //After it hits the button, start using the chain lift
		addSequential(new DSolenoidToggle(Robot.feeder, Robot.feeder.feederSolenoid, Value.kReverse)); //Close the Feeder
		
		//2nd Tote
		addSequential(new DSolenoidToggle(Robot.feeder, Robot.feeder.feederSolenoid, Value.kForward)); //Make sure the feeder is open
		addSequential(new IRScan(0, Math.PI/2)); //Move Left and Find new Tote
		addSequential(new IRLockOnDrive(1.0)); //Lock on and get the tote
		addSequential(new DSolenoidToggle(Robot.feeder, Robot.feeder.feederSolenoid, Value.kReverse)); //Close the Feeder
		addSequential(new UseFeeder(Feeder.kInward)); //Start the feeder
		addSequential(new DSolenoidToggle(Robot.feeder, Robot.feeder.feederSolenoid, Value.kForward)); //Make sure the feeder is open
		addSequential(new ChainToSetPoint(ChainToSetPoint.TYPE_ABSOLUTE, 800)); //After it hits the button, start using the chain lift
		addSequential(new DSolenoidToggle(Robot.feeder, Robot.feeder.feederSolenoid, Value.kReverse)); //Close the Feeder

		
		//3rd Tote
		addSequential(new DSolenoidToggle(Robot.feeder, Robot.feeder.feederSolenoid, Value.kForward)); //Make sure the feeder is open
		addSequential(new IRScan(0, Math.PI/2)); //Move Left and Find New Tote
		addSequential(new IRLockOnDrive(1.0)); //Lock on and get the tote
		addSequential(new DSolenoidToggle(Robot.feeder, Robot.feeder.feederSolenoid, Value.kReverse)); //Close the Feeder
		addSequential(new UseFeeder(Feeder.kInward)); //Start the feeder
		
		//Drive Into Auto Zone
		addSequential(new DriveFacingAngle(0.5, 270, 0, 3)); //Move into auto zone sideways
		addSequential(new DSolenoidToggle(Robot.feeder, Robot.feeder.feederSolenoid, Value.kForward)); //Make sure the feeder is open
		addSequential(new ChainToSetPoint(ChainToSetPoint.TYPE_ABSOLUTE, -400)); //After it hits the button, start using the chain lift
		addSequential(new DSolenoidToggle(Robot.feeder, Robot.feeder.feederSolenoid, Value.kReverse)); //Close the Feeder

		//Drive Away from Totes
		
		addSequential(new DriveFacingAngle(0.5, 180, 0, 3)); //Back up away from the totes facing the same angle
		
		
	}
}
