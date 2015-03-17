package org.usfirst.frc.team321.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DSolenoidToggle extends Command {

	private DoubleSolenoid ds;
	private boolean hasFinished = false;
	Value value = null;

	public DSolenoidToggle(Subsystem sub, DoubleSolenoid ds) {
		this(sub, ds, null);
	}

	public DSolenoidToggle(Subsystem sub, DoubleSolenoid ds, Value value){
		requires(sub);
		this.ds = ds;
		this.value = value;
	}

	// Called just before this Command runs the first time
	protected void initialize() {

		if(value == null){
			if(ds.get() == DoubleSolenoid.Value.kForward){
				ds.set(DoubleSolenoid.Value.kReverse);
			}

			else if(ds.get() == DoubleSolenoid.Value.kReverse){
				ds.set(DoubleSolenoid.Value.kForward);
			}
		}else{
			ds.set(value);
		}
		hasFinished = true;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return hasFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
