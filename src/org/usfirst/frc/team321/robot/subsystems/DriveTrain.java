package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.RobotMap;
import org.usfirst.frc.team321.robot.commands.MoveWithJoystick;
import org.usfirst.frc.team321.util.LancerConstants;
import org.usfirst.frc.team321.util.LancerFunctions;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {


	//The speed controllers associated with the drive train
	public static SpeedController f_left, f_right, r_left, r_right;

	//Gyroscope associated with the drive
	public Gyro driveGyro;
	public PIDController gyroPID;
	
	public double facingAngle;

	public boolean isGyroSteering;

	public int[] drivePorts;

	public double kP = 1.0,
			kI = 1.0,
			kD = 1.0;

	public DriveTrain(){
		super("Drive Train");
		//Create the speed controller and specify type

		if(!Robot.isPractice){
			drivePorts = new int[]{5, 3, 2, 4};

			f_left = new CANTalon(drivePorts[0]);
			f_right = new CANTalon(drivePorts[1]);
			r_left = new CANTalon(drivePorts[2]);
			r_right = new CANTalon(drivePorts[3]);

			//Default control mode: Percentage of power: [-1 , 1]
			((CANTalon) f_left).changeControlMode(CANTalon.ControlMode.PercentVbus);
			((CANTalon) f_right).changeControlMode(CANTalon.ControlMode.PercentVbus);
			((CANTalon) r_left).changeControlMode(CANTalon.ControlMode.PercentVbus);
			((CANTalon) r_right).changeControlMode(CANTalon.ControlMode.PercentVbus);

			//Cast the speed controller as a CANTalon and set the pid to the constants above
			((CANTalon) f_left).setPID(kP, kI, kD);
			((CANTalon) f_right).setPID(kP, kI, kD);
			((CANTalon) r_left).setPID(kP, kI, kD);
			((CANTalon) r_right).setPID(kP, kI, kD);
		}else{
			drivePorts = new int[]{0, 1, 2, 3};

			f_left = new Talon(drivePorts[0]);
			f_right = new Talon(drivePorts[1]);
			r_left = new Talon(drivePorts[2]);
			r_right = new Talon(drivePorts[3]);
		}

		driveGyro = new Gyro(RobotMap.driveGyro);
		driveGyro.reset();
		Robot.nonResettingGyroVal = driveGyro.getAngle();
		facingAngle = -(LancerFunctions.getRefAngle(driveGyro.getAngle()) * LancerConstants.deg2Rad) + Math.PI / 2; //sets 90 degrees to the default facing angle

		isGyroSteering = true;

	}

	public void initDefaultCommand() {
		setDefaultCommand(new MoveWithJoystick());
	}

	public void formulateDrive(double axisNorm, double angVel, double angle, ControlMode mode) {

		//No mode because it is the practice chassis or Percentage based control
		if(mode == null || mode == ControlMode.PercentVbus){
			double v1, v2, v3, v4;

			//Non Gyro Steering (Traditional): the movement is based on a defined forward for the robot
			v1 = -(axisNorm * Math.sin(angle + (Math.PI / 4)) + angVel);
			v2 = -(axisNorm * Math.cos(angle + (Math.PI / 4)) + angVel);
			v3 = axisNorm * Math.cos(angle + (Math.PI / 4)) - angVel;
			v4 = axisNorm * Math.sin(angle + (Math.PI / 4)) - angVel;

			double[] speeds = new double[]{ v1, v2, v3, v4 };

			speeds = LancerFunctions.normalize(speeds);

			mechanumDrive(speeds[0], speeds[1], speeds[2], speeds[3]);

		}else if(mode == ControlMode.Speed){

		}
	}

	/*
	 *Use only for Field Centric Driving!!!
	 *Gyro Steering, the movement is based on the map of the field 
	 */

	public void formulateDriveGyro(double pow, double angVel, double angle, ControlMode mode){

		facingAngle = -(LancerFunctions.getRefAngle(driveGyro.getAngle()) * LancerConstants.deg2Rad) + Math.PI / 2; //sets 90 degrees to the forward facing angle

		//No mode because it is the practice chassis or Percentage based control
		if(mode == null || mode == ControlMode.PercentVbus){
			double v1, v2, v3, v4;

			double angleToMove = angle - facingAngle + Math.PI / 2;

			//localize the variables into 4 formulas to be interpreted, as the range goes from [-2, 2] instead of [-1, 1]

			v1 = -(pow * Math.sin(angleToMove + (Math.PI / 4)) + angVel);
			v2 = -(pow * Math.cos(angleToMove + (Math.PI / 4)) + angVel);
			v3 = pow * Math.cos(angleToMove + (Math.PI / 4)) - angVel;
			v4 = pow * Math.sin(angleToMove + (Math.PI / 4)) - angVel;

			double[] speeds = new double[]{ v1, v2, v3, v4 };

			speeds = LancerFunctions.normalize(speeds);

			mechanumDrive(speeds[0], speeds[1], speeds[2], speeds[3]);
		}else if(mode == ControlMode.Speed){

		}
	}

	public void mechanumDrive(double frontLeft, double frontRight, double backLeft, double backRight){

		if(!Robot.isPractice){
			((CANTalon) f_left).set(frontLeft);
			((CANTalon) f_right).set(frontRight);
			((CANTalon) r_left).set(backLeft);
			((CANTalon) r_right).set(backRight);
		}else{
			f_left.set(frontLeft);
			f_right.set(frontRight);
			r_left.set(backLeft);
			r_right.set(backRight);
		}
	}

	public void moveTowards(double from, double to, double speed) {
		//TODO: Method sub

	}

	public void rotateTowards(double current, double target, double speed){
		//TODO: Method sub
	}
}