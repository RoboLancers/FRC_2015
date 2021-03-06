package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotPorts;
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


	public static final int 
	kUpward = 1, 
	kDownward = -1, 
	kStop = 0;

	public static SpeedController liftMotor;
	public static Encoder enc;

	public DoubleSolenoid liftSolenoid;

	public LancerPID chainPID;

	//The point at which a level is defined (i.e level width to be added to the current point)
	public static final int kLevelDist = 400; //TODO: Find level constant
	public int setPoint = 0;

	public boolean isManual = true;
	public boolean isRunning = false;

	public ChainLift(){

		liftMotor = new Talon(RobotPorts.kLiftMotor);

		liftSolenoid = new DoubleSolenoid(RobotPorts.F_LIFT, RobotPorts.R_LIFT);

		enc = new Encoder(RobotPorts.liftEncA, RobotPorts.liftEncB);

		//Default position is upright
		liftSolenoid.set(DoubleSolenoid.Value.kForward);

		chainPID = new LancerPID( 1, 0 , 1, 50); //TODO: Configure PID Constants

	}

	public void initDefaultCommand() {

	}

	public void useChainLift(double power){
		liftMotor.set(power);
	}
}

