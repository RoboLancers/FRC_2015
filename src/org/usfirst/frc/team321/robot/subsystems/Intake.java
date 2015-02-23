package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;
import org.usfirst.frc.team321.robot.commands.RegulateIntake;
import org.usfirst.frc.team321.util.LancerPID;

import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	public static SpeedController leftWheel, rightWheel;
	public static SpeedController liftMotor;
	public static Encoder enc;

	public static LancerPID encController;

	public DoubleSolenoid liftSolenoid;

	//The point at which a level is defined (i.e level width to be added to the current point)
	public static final int kLevelDist = 800; //TODO: Find level constant

	public boolean isManual = false;
	public int lastSetPoint = 0;

	public Intake(){

		//Initialize Objects
		leftWheel = new Talon(RobotMap.intakeLeft);
		rightWheel = new Talon(RobotMap.intakeRight);

		liftMotor = new Talon(RobotMap.liftMotor);

		liftSolenoid = new DoubleSolenoid(RobotMap.F_LIFT, RobotMap.R_LIFT);

		//Default position is upright
		liftSolenoid.set(DoubleSolenoid.Value.kForward);

		enc = new Encoder(2, 3); //TODO: Set RobotMap constants
		encController = new LancerPID(1, 0, 1, .05); //Epsilon set to 5%
	} 

	public void initDefaultCommand() {
		setDefaultCommand(new RegulateIntake());
	}
	
	public void levelManipulator(){
		encController.setReference(kLevelDist);
	    encController.calcPID((double)enc.get());
	}
	
	public void useFeeder(double power){
		leftWheel.set(power);
		rightWheel.set(-power);
	}

	public void useIntakeManual(double power){
		isManual = true;
		liftMotor.set(power);
	}

	public void stopIntake(){
		liftMotor.set(0);
	}
}

