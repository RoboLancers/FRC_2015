package org.usfirst.frc.team321.robot;


import org.usfirst.frc.team321.robot.commands.DriveFacingAngle;
import org.usfirst.frc.team321.robot.commands.autonomous.*;
import org.usfirst.frc.team321.robot.subsystems.*;
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
		chainLift = new ChainLift();
		feeder = new Feeder();
		feedback = new Feedback();
		pneumatics = new Pneumatics();
		//camera = new Camera();


		//Always create OI last
		oi = new OI();

		// instantiate the command used for the autonomous period
		SmartDashboard.putData(driveTrain);
		//SmartDashboard.putData(camera);
		//SmartDashboard.putData(chainLift);
		SmartDashboard.putData(feeder);

		//Autonomous Chooser in the Smart Dashboard
		autoChooser = new SendableChooser();
		autoChooser.addDefault("No Autonomous", null);
		autoChooser.addObject("Drive Forward", new DriveFacingAngle(90, 90));
		autoChooser.addObject("IR Strafe", new IRTotePickUp());
		//autoChooser.addObject("Name of Autonomous", new AutoCommand());

		SmartDashboard.putData("Auto Mode", autoChooser);

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
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

		SmartDashboard.putBoolean("Gyro Enabled", driveTrain.isGyroSteering);
		SmartDashboard.putNumber("Gyro Angle", LancerFunctions.getRefAngle(driveTrain.driveGyro.getAngle()));

		SmartDashboard.putNumber("Intake encoder", ChainLift.enc.getDistance());

		//Accelerometer Sensor
		SmartDashboard.putNumber("Accelerometer X", Robot.feedback.accel.getX());
		SmartDashboard.putNumber("Accelerometer Y", Robot.feedback.accel.getY());
		SmartDashboard.putNumber("Accelerometer Z", Robot.feedback.accel.getZ());

		SmartDashboard.putNumber("X Location", Robot.xLoc);
		SmartDashboard.putNumber("Y Location", Robot.yLoc);
		SmartDashboard.putNumber("Z Location", Robot.zLoc);
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}