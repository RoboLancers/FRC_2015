package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.subsystems.ChainLift;

import edu.wpi.first.wpilibj.command.Command;

public class ChainToSetPoint extends Command{


	private boolean isFirstPass = true;
	private double type, val;

	public static final int TYPE_LEVEL = 0, TYPE_ABSOLUTE = 1; 

	public ChainToSetPoint(double type, int val){
		requires(Robot.chainLift);
		this.type = type;
		this.val = val;
	}

	protected void initialize() {

	}
	
	protected void onFirstPass(){
		Robot.chainLift.chainPID.resetErrorSum(); //Reset Integral
		Robot.chainLift.chainPID.resetPrevious(); //Reset Derivitave
		Robot.chainLift.setPoint += (val * ChainLift.kLevelDist);
		
		//Set Setpoint
		if(this.type == TYPE_ABSOLUTE){
			Robot.chainLift.chainPID.setReference(val);
		}
		else if(this.type == TYPE_LEVEL){
			Robot.chainLift.chainPID.setReference(Robot.chainLift.setPoint);
		}
		isFirstPass = false;
	}

	protected void execute() {
		if(isFirstPass){
			onFirstPass();
		}
		
		Robot.chainLift.useChainLift(Robot.chainLift.chainPID.calcPID(ChainLift.enc.getRaw()));
	}

	protected boolean isFinished() {

		return Robot.chainLift.chainPID.isDone();
	}

	protected void end() {
		Robot.chainLift.useChainLift(ChainLift.kStop);
		isFirstPass = true; //reset first pass
	}

	protected void interrupted() {
		end();
	}


}
