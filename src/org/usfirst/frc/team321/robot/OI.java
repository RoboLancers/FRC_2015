package org.usfirst.frc.team321.robot;

import org.usfirst.frc.team321.robot.commands.ChainToSetPoint;
import org.usfirst.frc.team321.robot.commands.DSolenoidToggle;
import org.usfirst.frc.team321.robot.commands.ResetYaw;
import org.usfirst.frc.team321.robot.commands.SwitchDriveConfig;
import org.usfirst.frc.team321.robot.commands.UseChainLift;
import org.usfirst.frc.team321.robot.commands.UseFeeder;
import org.usfirst.frc.team321.robot.subsystems.ChainLift;
import org.usfirst.frc.team321.robot.subsystems.Feeder;
import org.usfirst.frc.team321.robot.triggers.Axis;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick driveStick, maniStick;
	public static JoystickButton[] driveBtn, maniBtn;

	public OI(){
		driveStick = new Joystick(0);
		maniStick = new Joystick(1);

		driveBtn = new JoystickButton[12];
		maniBtn = new JoystickButton[12];

		for(int i = 0; i < driveBtn.length; i++){
			driveBtn[i] = new JoystickButton(driveStick, i + 1);
		}

		for(int i = 0; i < maniBtn.length; i++){
			maniBtn[i] = new JoystickButton(maniStick, i + 1);
		}

		/*
		 * Drive Control:
		 * 		X -- Switch the Drive Configuration: Gyro Based / Traditonal
		 * 
		 */
		driveBtn[1].whenPressed(new SwitchDriveConfig());

		/*
		 * Manipulator Control:
		 * 		1 -- Use Feeder to bring in totes
		 * 		2 -- Use Feeder to expel totes
		 * 		
		 * 		Hat Switch Up -- Raise Chain Lift (Manual)
		 * 		Hat Switch Down -- Lower Chain Lift (Manual)
		 * 
		 *		7 -- Raise/Lower Chain Lift
		 * 		8 -- Open/Close Feeder
		 *
		 */


		new Axis(driveStick, 2, 0.5).whenActive(new DSolenoidToggle(Robot.feeder, Robot.feeder.feederSolenoid));
		new Axis(driveStick, 3, 0.5).whenActive(new DSolenoidToggle(Robot.feeder, Robot.feeder.feederSolenoid));

		driveBtn[5].whileHeld(new UseFeeder(Feeder.kInward));
		driveBtn[4].whileHeld(new UseFeeder(Feeder.kOutward));

		//		new POV(maniStick, 0).whileActive(new UseChainLift(ChainLift.kUpward));
		//		new POV(maniStick, 180).whileActive(new UseChainLift(ChainLift.kDownward));

		driveBtn[3].whenPressed(new ResetYaw());

//		maniBtn[5].whenPressed(new ChainToSetPoint(ChainToSetPoint.TYPE_LEVEL, ChainLift.kUpward));
//		maniBtn[3].whenPressed(new ChainToSetPoint(ChainToSetPoint.TYPE_LEVEL, ChainLift.kDownward));

		maniBtn[4].whileHeld(new UseChainLift(ChainLift.kUpward));
		maniBtn[2].whileHeld(new UseChainLift(ChainLift.kDownward));

		maniBtn[6].whenPressed(new DSolenoidToggle(Robot.chainLift, Robot.chainLift.liftSolenoid));
//		maniBtn[10].whenPressed(new DSolenoidToggle(Robot.grabber, Robot.grabber.grabSolenoid));
//		maniBtn[11].whenPressed(new DSolenoidToggle(Robot.grabber, Robot.grabber.extentionSolenoid));
		
	}


}

