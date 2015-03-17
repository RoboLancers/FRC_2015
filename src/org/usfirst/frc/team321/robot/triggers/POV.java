package org.usfirst.frc.team321.robot.triggers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class POV extends Trigger {

	Joystick joystick;
	int pov;

	public POV(Joystick joystick, int pov){
		this.joystick = joystick;
		this.pov = pov;
	}
	public boolean get() {
		return joystick.getPOV() == pov;
	}
}
