package org.usfirst.frc.team321.robot.triggers;

import org.usfirst.frc.team321.util.LancerFunctions;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class Axis extends Trigger {
	Joystick joystick;
	int axis;
	double min;

	public Axis(Joystick joystick, int axis, double min){
		this.joystick = joystick;
		this.axis = axis;
		this.min = min;
	}
	public boolean get() {
		return LancerFunctions.inRange(joystick.getRawAxis(axis), min, 1);
	}
}
