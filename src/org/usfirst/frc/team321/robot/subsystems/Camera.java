package org.usfirst.frc.team321.robot.subsystems;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ROI;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Camera extends Subsystem {
	
	Image frame;
    CameraServer camServer = CameraServer.getInstance();
    
    public Camera(){
    	
        camServer.setQuality(50); //TODO: Configure camera quality
        camServer.startAutomaticCapture("cam0");    
                
    }
    
    public void initDefaultCommand() {
    }
    
    public String getCamToTable(){
    	
    	return null;
    }
}

