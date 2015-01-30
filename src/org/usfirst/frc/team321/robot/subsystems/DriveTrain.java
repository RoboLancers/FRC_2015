package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.custom.CustomMath;
import org.usfirst.frc.team321.robot.RobotMap;
import org.usfirst.frc.team321.robot.commands.MoveWithJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Gyro;
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
	
	public static double[] motorLimit = new double[4];
	
	//Gyroscope associated with the drive
	public static Gyro driveGyro;
	public double facingAngle;
	
	public boolean isGyroSteering;
	
	
	public DriveTrain(){
		super("Drive Train");
		//Create the speed controller and specify type
		
		f_left = new CANTalon(RobotMap.kF_left);
		f_right = new CANTalon(RobotMap.kF_right);
		r_left = new CANTalon(RobotMap.kR_left);
		r_right = new CANTalon(RobotMap.kR_right);
		
//		f_left = new Talon(RobotMap.kF_left);
//		f_right = new Talon(RobotMap.kF_right);
//		r_left = new Talon(RobotMap.kR_left);
//		r_right = new Talon(RobotMap.kR_right);
		
//		//Cast the speed controller as a CANTalon and set the pid to the constants above
//		((CANTalon) f_left).setPID(kP, kI, kD);
//		((CANTalon) f_right).setPID(kP, kI, kD);
//		((CANTalon) r_left).setPID(kP, kI, kD);
//		((CANTalon) r_right).setPID(kP, kI, kD);
		
		((CANTalon) f_left).changeControlMode(CANTalon.ControlMode.PercentVbus);
		((CANTalon) f_right).changeControlMode(CANTalon.ControlMode.PercentVbus);
		((CANTalon) r_left).changeControlMode(CANTalon.ControlMode.PercentVbus);
		((CANTalon) r_right).changeControlMode(CANTalon.ControlMode.PercentVbus);
		
		driveGyro = new Gyro(RobotMap.driveGyro);
		driveGyro.reset();
		facingAngle = driveGyro.getAngle() * CustomMath.deg2Rad + Math.PI / 2; //sets 90 degrees to the default facing angle
		
		isGyroSteering = true;
		
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new MoveWithJoystick());
    }

	public void formulateDrive(double axisNorm, double angVel, double angle) {
		
		double v1, v2, v3, v4;
		
		facingAngle = -(driveGyro.getAngle() * CustomMath.deg2Rad) + Math.PI / 2; //sets 90 degrees to the forward facing angle
    	double angleToMove = angle - facingAngle + Math.PI / 2;
    	
    	//localize the variables into 4 formulas to be interpreted, as the range goes from [-2, 2] instead of [-1, 1]
    	if(isGyroSteering){
    		//Gyro Steerinng, the movement is based on the map of the field
    		v1 = -(axisNorm * Math.sin(angleToMove + (Math.PI / 4)) + angVel);
			v2 = -(axisNorm * Math.cos(angleToMove + (Math.PI / 4)) + angVel);
			v3 = axisNorm * Math.cos(angleToMove + (Math.PI / 4)) - angVel;
			v4 = axisNorm * Math.sin(angleToMove + (Math.PI / 4)) - angVel;
    	}else{
    		//Non Gyro Steering (Traditional): the movement is based on a defined forward for the robot
    		v1 = -(axisNorm * Math.sin(angle + (Math.PI / 4)) + angVel);
    		v2 = -(axisNorm * Math.cos(angle + (Math.PI / 4)) + angVel);
    		v3 = axisNorm * Math.cos(angle + (Math.PI / 4)) - angVel;
    		v4 = axisNorm * Math.sin(angle + (Math.PI / 4)) - angVel;
    	}
		
		double[] speeds = new double[]{v1,v2,v3,v4};
		
		double max = 1; //default 1 to have something to compare to (also the minimum of this value) 
		
		for(int i = 0; i < speeds.length; i++){
			//if the speed checked is higher than the current maximum
			if(Math.abs(speeds[i]) > max){
				//clamp max to the speed of the index
				max = CustomMath.clamp(Math.abs(speeds[i]), 1.0, 2.0);
			}
		}
		
		v1 = v1 / max;
		v2 = v2 / max;
		v3 = v3 / max;
		v4 = v4 / max;
    	
//    	f_left.set(v1);
//    	f_right.set(v2);
//    	r_left.set(v3);
//    	r_right.set(v4);
    	
    	((CANTalon) f_left).set(v1);
    	((CANTalon) f_right).set(v2);
    	((CANTalon) r_left).set(v3);
    	((CANTalon) r_right).set(v4);
	}
	
	public static void formulateMotorLim(){
		motorLim[1]
	}
	
	public void moveTowards(double from, double to, double speed) {
		//TODO: Method sub
		
	}
	
	public void rotateTowards(double current, double target, double speed){
		//TODO: Method sub
	}
}