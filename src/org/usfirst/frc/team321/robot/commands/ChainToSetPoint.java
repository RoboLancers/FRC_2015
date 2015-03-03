package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.subsystems.ChainLift;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

public class ChainToSetPoint extends Command{
	private double setPoint = 0;
	private PIDController encPID;

	public ChainToSetPoint(double sp){
		requires(Robot.chainLift);
		this.setPoint = sp;

		encPID = new PIDController(1, 1, 1, ChainLift.enc , ChainLift.liftMotor);

		encPID.setSetpoint(setPoint);
		encPID.setPercentTolerance(5); //5 Percent Tolerance
		encPID.enable();//Starts the pid controller

	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return encPID.onTarget();
	}

	@Override
	protected void end() {
		encPID.disable();
		encPID.free(); //Destroys the pid controller
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}


}
