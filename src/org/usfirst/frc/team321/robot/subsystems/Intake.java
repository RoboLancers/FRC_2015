package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;
import org.usfirst.frc.team321.robot.commands.UseIntake;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
    
	public static SpeedController leftWheel, rightWheel;
	public static SpeedController liftMotor;
	
	public Intake(){
		
		leftWheel = new Talon(RobotMap.intakeLeft);
		rightWheel = new Talon(RobotMap.intakeRight);
		
		liftMotor = new Talon(RobotMap.liftMotor);
		
	} 
	
    public void initDefaultCommand() {
    	setDefaultCommand(new UseIntake());
    }
    
    public void useFeeder(double power){
    	leftWheel.set(power);
    	rightWheel.set(-power);
    }
    
    public void raiseIntake(double power){
    	liftMotor.set(power);
    }
    
}

