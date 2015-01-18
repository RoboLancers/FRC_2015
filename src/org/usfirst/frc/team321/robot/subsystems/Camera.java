package org.usfirst.frc.team321.robot.subsystems;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.ColorMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.Range;


import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 *
 */
public class Camera extends Subsystem {
	
	int session;
    Image frame;
    Image frameBin;
    CameraServer camServer = CameraServer.getInstance();
    SendableChooser putter;
    Range rangeIR;

    public Camera(){	
        //frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
    	
        //camServer.setQuality(50); //TODO: Configure camera quality
        //camServer.startAutomaticCapture("cam0");
        
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_HSL, 0);//Creates a blank HSL image
        frameBin =NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_U8,0);//Creates blank binary image
        
        session = NIVision.IMAQdxOpenCamera("cam0",NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        
        
        rangeIR = new Range(280,300);//Sets hue range for IR on the camera
        
        NIVision.IMAQdxConfigureGrab(session); 
    }
    
    public void initDefaultCommand() {
    }
    
    
    public void test(){
    	 NIVision.IMAQdxStartAcquisition(session);

       

         //while (processing) {

             NIVision.IMAQdxGrab(session, frame, 1);//finally links the blank image to the camera feed, and I think the 1 just tells it to wait to be ready to grab another image??????
             
             CameraServer.getInstance().setImage(frame);//outputs image instead of outputting directly from the camera 
             
             //So once I'm here, I have the image with which to process
             
             NIVision.imaqColorThreshold(frameBin, frame, session, ColorMode.HSL, rangeIR, null, null);//Takes in frame and writes on frameBin if a certain pixel's hue is within the IR range
             //From here I'll work with the binary image
             
             //Timer.delay(0.005);
         //}
         NIVision.IMAQdxStopAcquisition(session);//When you're done processing this will tell the camera it's not working anymore
    }
}

