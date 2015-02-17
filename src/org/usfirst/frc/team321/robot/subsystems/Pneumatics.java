package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.commands.RegulateCompressor;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Pneumatics extends Subsystem {

	public Compressor compressor;

	public Pneumatics(){
		compressor = new Compressor(0);

	}
	public void initDefaultCommand() {
		setDefaultCommand(new RegulateCompressor());
	}

	public void regulateCompressor(){
		boolean isError = false;
		/*
		//Error Regulations
		if((compressor.getCompressorCurrentTooHighFault() && !compressor.getCompressorCurrentTooHighStickyFault()) ||
				(compressor.getCompressorNotConnectedFault() && !compressor.getCompressorNotConnectedStickyFault()) ||
				(compressor.getCompressorShortedFault() && !compressor.getCompressorShortedStickyFault()))
		{
			isError = true;
		}else{
			isError = false;
		}*/

		if(!compressor.getPressureSwitchValue() && !compressor.enabled() && !isError){
			compressor.start();
		}
		else if((compressor.getPressureSwitchValue() && compressor.enabled()) || isError){
			compressor.stop();
		}
	}

}

