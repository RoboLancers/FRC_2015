package org.usfirst.frc.team321.robot;


import org.usfirst.frc.team321.robot.commands.autonomous.*;
import org.usfirst.frc.team321.robot.subsystems.*;
import org.usfirst.frc.team321.util.LancerConstants;
import org.usfirst.frc.team321.util.LancerFunctions;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	Command autonomousCommand;

	public static DriveTrain driveTrain;
	public static Camera camera;
	public static ChainLift chainLift;
	public static Feeder feeder;
	public static Feedback feedback;   
	public static Pneumatics pneumatics;

	public static boolean isPractice = true;

	//time for location tracking using built in accelerometer in RegulateSensors
	public static double xAccel = 0, yAccel = 0, zAccel = 0, xVel = 0, yVel = 0, zVel = 0, xLoc, yLoc, zLoc;

	//always create OI last
	public static OI oi;

	public SendableChooser autoChooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {

		//initialize all subsystem
		driveTrain = new DriveTrain();
		//chainLift = new ChainLift();
		//feeder = new Feeder();
		//feedback = new Feedback();
		//pneumatics = new Pneumatics();
		//camera = new Camera();


		//Always create OI last
		oi = new OI();

		// instantiate the command used for the autonomous period
		SmartDashboard.putData(driveTrain);
		//SmartDashboard.putData(camera);
		//SmartDashboard.putData(chainLift);
		//SmartDashboard.putData(feeder);

		//Autonomous Chooser in the Smart Dashboard
		autoChooser = new SendableChooser();
		autoChooser.addDefault("No Autonomous", null);
		//autoChooser.addObject("Drive Forward LANDFILL", new DriveFacingAngle(90, 90, 1.3));
		//autoChooser.addObject("Drive Forward CONTAINER", new DriveFacingAngle(90, 90, 3));

		//autoChooser.addObject("Drive Forward", new DriveFacingAngle(90, 90, 4));
		//autoChooser.addObject("VL Tote Strafe", new IRTotePickUp());
		//autoChooser.addObject("VL Tote Strafe NO PICKUP", new NoPickupIRStrafe());
		//autoChooser.addObject("Container Strafe", new PickUpContainer());

		//autoChooser.addObject("Name of Autonomous", new AutoCommand());

		SmartDashboard.putData("Auto Mode", autoChooser);

		//driveTrain.driveGyro.initGyro();
		//ChainLift.enc.reset();

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();

		//SmartDashboard.putNumber("Gyro Value", driveTrain.driveGyro.getAngle());
		//		
		//SmartDashboard.putBoolean("Gyro Enabled", driveTrain.isGyroSteering);
		//SmartDashboard.putNumber("Facing Angle", LancerFunctions.getRefAngle(driveTrain.getFacingAngle() * LancerConstants.rad2Deg));
		//SmartDashboard.putNumber("offsetSwitch", driveTrain.getOffsetAngle());
		//SmartDashboard.putNumber("offsetSwitchVoltage", driveTrain.gyroOffsetSwitch.getAverageVoltage());
		//SmartDashboard.putNumber("Gyro Value", driveTrain.driveGyro.getAngle());
		//		
		//SmartDashboard.putNumber("Intake encoder", ChainLift.enc.getRaw());
		//
		//SmartDashboard.putNumber("Compressor", pneumatics.getPressure());


		SmartDashboard.putBoolean(  "IMU_Connected",        driveTrain.navX.isConnected());
		SmartDashboard.putBoolean(  "IMU_IsCalibrating",    driveTrain.navX.isCalibrating());
		SmartDashboard.putNumber(   "IMU_Yaw",              driveTrain.navX.getYaw());
		SmartDashboard.putNumber(   "IMU_Pitch",            driveTrain.navX.getPitch());
		SmartDashboard.putNumber(   "IMU_Roll",             driveTrain.navX.getRoll());
		SmartDashboard.putNumber(   "IMU_CompassHeading",   driveTrain.navX.getCompassHeading());
		SmartDashboard.putNumber(   "IMU_Update_Count",     driveTrain.navX.getUpdateCount());
		SmartDashboard.putNumber(   "IMU_Byte_Count",       driveTrain.navX.getByteCount());

		SmartDashboard.putNumber(   "IMU_Accel_X",          driveTrain.navX.getWorldLinearAccelX());
		SmartDashboard.putNumber(   "IMU_Accel_Y",          driveTrain.navX.getWorldLinearAccelY());
		SmartDashboard.putBoolean(  "IMU_IsMoving",         driveTrain.navX.isMoving());
		SmartDashboard.putNumber(   "IMU_Temp_C",           driveTrain.navX.getTempC());

		SmartDashboard.putNumber(   "Velocity_X",           driveTrain.navX.getVelocityX() );
		SmartDashboard.putNumber(   "Velocity_Y",           driveTrain.navX.getVelocityY() );
		SmartDashboard.putNumber(   "Displacement_X",       driveTrain.navX.getDisplacementX() );
		SmartDashboard.putNumber(   "Displacement_Y",       driveTrain.navX.getDisplacementY() );
	}

	public void autonomousInit() {
		// schedule the autonomous command (example)
		autonomousCommand = (Command) autoChooser.getSelected();

		if (autonomousCommand != null) autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.


		if (autonomousCommand != null) autonomousCommand.cancel();
	}

	/**
	 * This function is called when the disabled button is hit.
	 * You can use it to reset subsystems before shutting down.
	 */
	public void disabledInit(){

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		//		
		//		SmartDashboard.putNumber("Gyro Value", driveTrain.driveGyro.getAngle());
		//		
		//		SmartDashboard.putBoolean("Gyro Enabled", driveTrain.isGyroSteering);
		//		SmartDashboard.putNumber("Facing Angle", LancerFunctions.getRefAngle(driveTrain.getFacingAngle() * LancerConstants.rad2Deg));
		//		SmartDashboard.putNumber("offsetSwitch", driveTrain.getOffsetAngle());
		//		SmartDashboard.putNumber("offsetSwitchVoltage", driveTrain.gyroOffsetSwitch.getAverageVoltage());
		//		SmartDashboard.putNumber("Gyro Value", driveTrain.driveGyro.getAngle());
		//		
		//		SmartDashboard.putNumber("Intake encoder", ChainLift.enc.getRaw());
		//
		//		SmartDashboard.putNumber("Compressor", pneumatics.getPressure());
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}