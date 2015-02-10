package org.usfirst.frc.team321.robot.subsystems;

import java.nio.ByteBuffer;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ROI;
import com.ni.vision.NIVision.RawData;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Camera extends Subsystem {
	
	//Image frame;
    CameraServer camServer;
    int session;
    
    public Camera(){
    	camServer = CameraServer.getInstance();
    	
        camServer.setQuality(50); //TODO: Configure camera quality
        camServer.startAutomaticCapture("cam0");   
        
        //imgToDesk();
                
    }
    
//    public void imgToDesk(){
//    	frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
//    	session = NIVision.IMAQdxOpenCamera("cam0",
//                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
//    	NIVision.IMAQdxGrab(session, frame, 1);
//    	
//    	NIVision.imaqWriteJPEGFile(frame, "Image", 100, new RawData(ByteBuffer.allocate(1280*720*3/*the resolution times the byters per pixel*/))); 
//    	
//    }
    
    public void initDefaultCommand() {
    }
    
//    public String getCamToTable(){
//    	
//    	return null;
//    }
}

