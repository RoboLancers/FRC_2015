package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotPorts;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Feeder extends Subsystem {

	public static final int 
	kInward = 1, 
	kOutward = -1, 
	kStop = 0;

	public static SpeedController leftWheel, rightWheel;

	public DoubleSolenoid feederSolenoid;

	public Feeder(){
		//Initialize Objects
		leftWheel = new Talon(RobotPorts.kIntakeLeft);
		rightWheel = new Talon(RobotPorts.kIntakeRight);
		feederSolenoid = new DoubleSolenoid(RobotPorts.F_FEEDER, RobotPorts.R_FEEDER);

		feederSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	public void initDefaultCommand() {

	}


	public void useFeeder(double power){
		leftWheel.set(power);
		rightWheel.set(-power);
	}

}

