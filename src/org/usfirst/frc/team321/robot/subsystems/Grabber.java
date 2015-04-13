package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotPorts;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Grabber extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public DoubleSolenoid grabSolenoid, extentionSolenoid;
	
	public Grabber(){
		grabSolenoid = new DoubleSolenoid(RobotPorts.F_GRABBER, RobotPorts.R_GRABBER);
		grabSolenoid.set(Value.kReverse);
		
		extentionSolenoid = new DoubleSolenoid(RobotPorts.F_EXTENTION, RobotPorts.R_EXTENTION);
		extentionSolenoid.set(Value.kReverse);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

