package org.usfirst.frc.team321.robot.commands.autonomous;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.commands.DSolenoidToggle;
import org.usfirst.frc.team321.robot.commands.UseFeeder;
import org.usfirst.frc.team321.robot.subsystems.Feeder;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickUpContainer extends CommandGroup {
    
    public  PickUpContainer() {
    	addSequential(new DSolenoidToggle(Robot.feeder, Robot.feeder.feederSolenoid, Value.kReverse)); //Close the feeder
    	addSequential(new UseFeeder(Feeder.kInward)); //intake the container
    	addSequential(new DriveFacingAngle(0.5, 90 , 90, 1.3)); //Drive To the end
    	addSequential(new DSolenoidToggle(Robot.feeder, Robot.feeder.feederSolenoid, Value.kForward)); //Open the feeder
    	
    	
    }
}
