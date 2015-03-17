package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.subsystems.ChainLift;
import org.usfirst.frc.team321.util.LancerPID;

import edu.wpi.first.wpilibj.command.Command;

public class ChainToSetPoint extends Command{


	boolean hasFinished;
	
	public static final int TYPE_LEVEL = 0, TYPE_ABSOLUTE = 1; 

	LancerPID pid;

	public ChainToSetPoint(double type, int val){
		requires(Robot.chainLift);

		if(type == TYPE_ABSOLUTE){
			ChainLift.setSetpoint(val);
		}
		else if(type == TYPE_LEVEL){
			ChainLift.setSetpoint (ChainLift.setPoint + (val * ChainLift.kLevelDist));
		}
	}

	protected void initialize() {
		hasFinished = false;

		pid = new LancerPID(1, 0 , 2, 50);
		pid.setReference(ChainLift.setPoint);
		pid.resetPrevious();
	}

	protected void execute() {
		Robot.chainLift.useChainLift(pid.calcPID(ChainLift.enc.getRaw()));
		
		hasFinished = pid.isDone();
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		
		return hasFinished;
	}

	protected void end() {
		Robot.chainLift.useChainLift(ChainLift.kStop);
	}

	protected void interrupted() {
		end();
	}


}
