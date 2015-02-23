package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;
import org.usfirst.frc.team321.robot.commands.RegulateFeeder;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Feeder extends Subsystem {

	public static SpeedController leftWheel, rightWheel;

	public Feeder(){
		//Initialize Objects
		leftWheel = new Talon(RobotMap.intakeLeft);
		rightWheel = new Talon(RobotMap.intakeRight);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new RegulateFeeder());
	}
	

	public void useFeeder(double power){
		leftWheel.set(power);
		rightWheel.set(-power);
	}

}

