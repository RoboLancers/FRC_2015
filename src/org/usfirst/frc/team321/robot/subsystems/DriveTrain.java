package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.custom.CustomMath;
import org.usfirst.frc.team321.robot.RobotMap;
import org.usfirst.frc.team321.robot.commands.MoveWithAngle;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
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
		
//		f_left = new CANTalon(RobotMap.kF_left);
//		f_right = new CANTalon(RobotMap.kF_right);
//		r_left = new CANTalon(RobotMap.kR_left);
//		r_right = new CANTalon(RobotMap.kR_right);
		
		f_left = new Talon(RobotMap.kF_left);
		f_right = new Talon(RobotMap.kF_right);
		r_left = new Talon(RobotMap.kR_left);
		r_right = new Talon(RobotMap.kR_right);
		
//		//Cast the speed controller as a CANTalon and set the pid to the constants above
//		((CANTalon) f_left).setPID(kP, kI, kD);
//		((CANTalon) f_right).setPID(kP, kI, kD);
//		((CANTalon) r_left).setPID(kP, kI, kD);
//		((CANTalon) r_right).setPID(kP, kI, kD);
		
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new MoveWithAngle());
    }

	public void angleToDriveMechanum(double axisNorm, double angVel, double angle) {
    	//localize the variables into 4 formulas to be interpreted, as the range goes from [-2, 2] instead of [-1, 1]
		double v1 = -(axisNorm * Math.sin(angle + (Math.PI / 4)) + angVel),
				v2 = -(axisNorm * Math.cos(angle + (Math.PI / 4)) + angVel),
				v3 = axisNorm * Math.cos(angle + (Math.PI / 4)) - angVel,
				v4 = axisNorm * Math.sin(angle + (Math.PI / 4)) - angVel;
		
		v1 = (double) CustomMath.clamp((float) v1, -1f, 1f);
		v2 = (double) CustomMath.clamp((float) v2, -1f, 1f);
		v3 = (double) CustomMath.clamp((float) v3, -1f, 1f);
		v4 = (double) CustomMath.clamp((float) v4, -1f, 1f);
		
		
		//Enables the movement with PID
		
//		f_left.pidWrite(v1);
//    	f_right.pidWrite(v2);
//    	r_left.pidWrite(v3);
//    	r_right.pidWrite(v4);
    	
    	f_left.set(v1);
    	f_right.set(v2);
    	r_left.set(v3);
    	r_right.set(v4);
	}
}