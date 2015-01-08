package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;
import org.usfirst.frc.team321.robot.commands.MoveWithAngle;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
	//PID Constants
	protected static float kP = 1.0f;
	protected static float kI = 1.0f;
	protected static float kD = 1.0f;
	
	//The speed controllers associated with the drive train
	public static SpeedController f_left, f_right, r_left, r_right;
	
	public DriveTrain(){
		super("Drive Train");
		//Create the speed controller and specify type
		f_left = new CANTalon(RobotMap.kF_left);
		f_right = new CANTalon(RobotMap.kF_right);
		r_left = new CANTalon(RobotMap.kR_left);
		r_right = new CANTalon(RobotMap.kR_right);
		
		//Cast the speed controller as a CANTalon and set the pid to the constants above
		((CANTalon) f_left).setPID(kP, kI, kD);
		((CANTalon) f_right).setPID(kP, kI, kD);
		((CANTalon) r_left).setPID(kP, kI, kD);
		((CANTalon) r_right).setPID(kP, kI, kD);
		
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new MoveWithAngle());
    }

	public void angleToDriveMechanum(double axisNorm, double angVel, double angle) {
    	//Enables the movement with PID
    	f_left.pidWrite(axisNorm * Math.sin(angle + (Math.PI / 4) ) + angVel);
    	f_right.pidWrite(axisNorm * Math.cos(angle + (Math.PI / 4) ) - angVel);
    	r_left.pidWrite(axisNorm * Math.cos(angle + (Math.PI / 4) ) + angVel);
    	r_right.pidWrite(axisNorm * Math.sin(angle + (Math.PI / 4) ) - angVel);
	}
}