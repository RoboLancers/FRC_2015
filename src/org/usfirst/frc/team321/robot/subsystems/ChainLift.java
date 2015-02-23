package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;
import org.usfirst.frc.team321.robot.commands.RegulateIntake;
import org.usfirst.frc.team321.util.LancerPID;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ChainLift extends Subsystem {
	
	public static SpeedController liftMotor;
	public static Encoder enc;

	public LancerPID encPID;

	public DoubleSolenoid liftSolenoid;

	//The point at which a level is defined (i.e level width to be added to the current point)
	public static final int kLevelDist = 800; //TODO: Find level constant

	public boolean isManual = false;
	public boolean isRunning = false;
	public int lastSetPoint = 0;

	public ChainLift(){

		liftMotor = new Talon(RobotMap.liftMotor);

		liftSolenoid = new DoubleSolenoid(RobotMap.F_LIFT, RobotMap.R_LIFT);

		//Default position is upright
		liftSolenoid.set(DoubleSolenoid.Value.kForward);

		enc = new Encoder(2, 3); //TODO: Set RobotMap constants
		encPID = new LancerPID(1/800, 0, 1/1600, 0.15);

	} 

	public void initDefaultCommand() {
		setDefaultCommand(new RegulateIntake());
	}
	
	public void invokeLevelAdjust(int amt){
		int setPoint;

		setPoint = lastSetPoint + (amt * kLevelDist);

		//Sets the last set point to be used as a reference for later
		lastSetPoint = setPoint;

		//enc.setPIDSourceParameter(PIDSourceParameter.kDistance);
		encPID.setReference(setPoint);

		isManual = false;
		isRunning = true;
	}

	public void moveToSetpoint(){
		if(!isManual && encPID.isDone()){
			liftMotor.set(encPID.calcPID(enc.getDistance()));
			
		}else{
			isRunning = false;
		}
	}

	public void useIntakeManual(double power){
		isManual = true;
		isRunning = false;
		liftMotor.set(power);
	}

	public void stopIntake(){
		liftMotor.set(0);
	}

}

