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
    ROI roi;
    
    public Camera(){	
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
    	
        camServer.setQuality(50); //TODO: Configure camera quality
        camServer.startAutomaticCapture("cam0");    
                
    }
    
    public void initDefaultCommand() {
    }
    
    
    public void test(){

    	 NIVision.imaqDetectRectangles(frame, 
    			 new NIVision.RectangleDescriptor(/*0,0,50,50*/),
    			 new NIVision.CurveOptions(/*NIVision.ExtractionMode.NORMAL_IMAGE, 5 ,NIVision.EdgeFilterSize.NORMAL, 20,20,20,20,20,20*/), 
    			 new NIVision.ShapeDetectionOptions(),
    			 roi //I think it takes in this blank object and writes onto it
    	 );
       

    }
}

