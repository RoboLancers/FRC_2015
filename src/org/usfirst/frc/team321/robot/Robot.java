package org.usfirst.frc.team321.robot;


import org.usfirst.frc.team321.robot.commands.TwoCanAuto;
import org.usfirst.frc.team321.robot.commands.autonomous.DriveFacingAngle;
import org.usfirst.frc.team321.robot.commands.autonomous.NoPickupIRStrafe;
import org.usfirst.frc.team321.robot.subsystems.Camera;
import org.usfirst.frc.team321.robot.subsystems.ChainLift;
import org.usfirst.frc.team321.robot.subsystems.DriveTrain;
import org.usfirst.frc.team321.robot.subsystems.Feedback;
import org.usfirst.frc.team321.robot.subsystems.Feeder;
import org.usfirst.frc.team321.robot.subsystems.Grabber;
import org.usfirst.frc.team321.robot.subsystems.Pneumatics;
import org.usfirst.frc.team321.util.LancerConstants;
import org.usfirst.frc.team321.util.LancerFunctions;

import com.kauailabs.navx_mxp.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
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
	public static Grabber grabber;

	public static boolean isPractice = false;

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
		grabber = new Grabber();
		//camera = new Camera();


		//Always create OI last
		oi = new OI();

		// instantiate the command used for the autonomous period
		SmartDashboard.putData(driveTrain);
		SmartDashboard.putData(chainLift);
		SmartDashboard.putData(feeder);
		SmartDashboard.putData(grabber);
		//SmartDashboard.putData(camera);

		//Autonomous Chooser in the Smart Dashboard
		autoChooser = new SendableChooser();
		autoChooser.addDefault("No Autonomous", null);
		autoChooser.addObject("Drive Forward LANDFILL", new DriveFacingAngle(0.75, 90, Math.PI/2, 0.8));
		autoChooser.addObject("Drive Forward CONTAINER", new DriveFacingAngle(0.5, 90, Math.PI/2, 3.05));
		autoChooser.addObject("TWO (2) CAN STEAL", new TwoCanAuto());
		
		//		autoChooser.addObject("VL Tote Strafe NO PICKUP", new NoPickupIRStrafe());

		//		autoChooser.addObject("Container Strafe", new PickUpContainer());
		//		autoChooser.addObject("3 Tote Autonomous", new ThreeToteAuto());

		//		autoChooser.addObject("Drive Forward", new DriveFacingAngle(90, Math.PI/2, 4));
		//		autoChooser.addObject("Strafe Left", new DriveFacingAngle(180, Math.PI/2, 4));
		//		autoChooser.addObject("Strafe Right", new DriveFacingAngle(0, Math.PI/2, 4));
		//		autoChooser.addObject("VL Tote Strafe", new IRTotePickUp());

		//autoChooser.addObject("Name of Autonomous", new AutoCommand());

		SmartDashboard.putData("Auto Mode", autoChooser);

		ChainLift.enc.reset();

		driveTrain.navX.zeroYaw();
		((AHRS)driveTrain.navX).resetDisplacement();

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();

		SmartDashboard.putBoolean("Field Centric", driveTrain.isFieldCentric);
		SmartDashboard.putNumber("Facing Angle", LancerFunctions.getRefAngle(driveTrain.getFacingAngle() * LancerConstants.rad2Deg));

		SmartDashboard.putNumber("Intake encoder", ChainLift.enc.getRaw());

		SmartDashboard.putNumber("Compressor", pneumatics.getPressure());


		SmartDashboard.putBoolean(  "IMU_Connected",        driveTrain.navX.isConnected());
		SmartDashboard.putBoolean(  "IMU_IsCalibrating",    driveTrain.navX.isCalibrating());
		SmartDashboard.putNumber(   "IMU_Yaw",              driveTrain.navX.getYaw());


		//		SmartDashboard.putNumber(   "IMU_Pitch",            driveTrain.navX.getPitch());
		//		SmartDashboard.putNumber(   "IMU_Roll",             driveTrain.navX.getRoll());
		//		SmartDashboard.putNumber(   "IMU_CompassHeading",   driveTrain.navX.getCompassHeading());
		//		SmartDashboard.putNumber(   "IMU_Update_Count",     driveTrain.navX.getUpdateCount());
		//		SmartDashboard.putNumber(   "IMU_Byte_Count",       driveTrain.navX.getByteCount());
		//
		//		SmartDashboard.putNumber(   "IMU_Accel_X",       	((AHRS) driveTrain.navX).getWorldLinearAccelX());
		//		SmartDashboard.putNumber(   "IMU_Accel_Y",        	((AHRS) driveTrain.navX).getWorldLinearAccelY());
		//		SmartDashboard.putBoolean(  "IMU_IsMoving",         ((AHRS) driveTrain.navX).isMoving());
		//		SmartDashboard.putNumber(   "IMU_Temp_C",           ((AHRS) driveTrain.navX).getTempC());

		SmartDashboard.putNumber(   "Velocity_X",           ((AHRS) driveTrain.navX).getVelocityX() );
		SmartDashboard.putNumber(   "Velocity_Y",           ((AHRS) driveTrain.navX).getVelocityY() );
		SmartDashboard.putNumber(   "Displacement_X : Meters",       ((AHRS) driveTrain.navX).getDisplacementX() );
		SmartDashboard.putNumber(   "Displacement_Y : Meters",       ((AHRS) driveTrain.navX).getDisplacementY() );
		SmartDashboard.putNumber(   "Displacement_X : Feet",       ((AHRS) driveTrain.navX).getDisplacementX() * LancerConstants.meterToFeet);
		SmartDashboard.putNumber(   "Displacement_Y : Feet",       ((AHRS) driveTrain.navX).getDisplacementY() * LancerConstants.meterToFeet);

		SmartDashboard.putNumber("Facing Angle", driveTrain.getFacingAngle() * LancerConstants.rad2Deg);
		SmartDashboard.putBoolean("Chain Lift UP", chainLift.liftSolenoid.get() == Value.kForward ? true : false);

		SmartDashboard.putBoolean("GRABER EXTENDED", grabber.extentionSolenoid.get() == Value.kForward ? true : false);
		SmartDashboard.putBoolean("GRABBER OUT", grabber.grabSolenoid.get() == Value.kForward ? true : false);

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

		SmartDashboard.putBoolean("Field Centric", driveTrain.isFieldCentric);
		SmartDashboard.putNumber("Facing Angle", LancerFunctions.getRefAngle(driveTrain.getFacingAngle() * LancerConstants.rad2Deg));

		SmartDashboard.putNumber("Intake encoder", ChainLift.enc.getRaw());

		SmartDashboard.putNumber("Compressor", pneumatics.getPressure());
		SmartDashboard.putBoolean("Chain Lift UP", chainLift.liftSolenoid.get() == Value.kForward ? true : false);

		SmartDashboard.putBoolean(  "IMU_Connected",        driveTrain.navX.isConnected());
		SmartDashboard.putBoolean(  "IMU_IsCalibrating",    driveTrain.navX.isCalibrating());
		SmartDashboard.putNumber(   "IMU_Yaw",              driveTrain.navX.getYaw());
		//		SmartDashboard.putNumber(   "IMU_Pitch",            driveTrain.navX.getPitch());
		//		SmartDashboard.putNumber(   "IMU_Roll",             driveTrain.navX.getRoll());
		//		SmartDashboard.putNumber(   "IMU_CompassHeading",   driveTrain.navX.getCompassHeading());
		//		SmartDashboard.putNumber(   "IMU_Update_Count",     driveTrain.navX.getUpdateCount());
		//		SmartDashboard.putNumber(   "IMU_Byte_Count",       driveTrain.navX.getByteCount());
		//
		//		SmartDashboard.putNumber(   "IMU_Accel_X",       	((AHRS) driveTrain.navX).getWorldLinearAccelX());
		//		SmartDashboard.putNumber(   "IMU_Accel_Y",        	((AHRS) driveTrain.navX).getWorldLinearAccelY());
		//		SmartDashboard.putBoolean(  "IMU_IsMoving",         ((AHRS) driveTrain.navX).isMoving());
		//		SmartDashboard.putNumber(   "IMU_Temp_C",           ((AHRS) driveTrain.navX).getTempC());
		//

		SmartDashboard.putNumber(   "Velocity_X",           ((AHRS) driveTrain.navX).getVelocityX() );
		SmartDashboard.putNumber(   "Velocity_Y",           ((AHRS) driveTrain.navX).getVelocityY() );
		SmartDashboard.putNumber(   "Displacement_X : Meters",       ((AHRS) driveTrain.navX).getDisplacementX() );
		SmartDashboard.putNumber(   "Displacement_Y : Meters",       ((AHRS) driveTrain.navX).getDisplacementY() );
		SmartDashboard.putNumber(   "Displacement_X : Feet",       ((AHRS) driveTrain.navX).getDisplacementX() * LancerConstants.meterToFeet);
		SmartDashboard.putNumber(   "Displacement_Y : Feet",       ((AHRS) driveTrain.navX).getDisplacementY() * LancerConstants.meterToFeet);

	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}