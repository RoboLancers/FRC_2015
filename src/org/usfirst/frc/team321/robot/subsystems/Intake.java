package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;
import org.usfirst.frc.team321.robot.commands.UseIntake;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
    
	public static SpeedController leftWheel, rightWheel;
	
	public Intake(){
		
		leftWheel = new CANTalon(RobotMap.intakeLeft);
		rightWheel = new CANTalon(RobotMap.intakeRight);
		
		((CANTalon) leftWheel).changeControlMode(CANTalon.ControlMode.PercentVbus);
		((CANTalon) rightWheel).changeControlMode(CANTalon.ControlMode.PercentVbus);
		
	} 
	
    public void initDefaultCommand() {
    	setDefaultCommand(new UseIntake());
    }
    
    public void useIntake(double power){
    	((CANTalon) leftWheel).set(power);
    	((CANTalon) rightWheel).set(-power);
    }
    
}

