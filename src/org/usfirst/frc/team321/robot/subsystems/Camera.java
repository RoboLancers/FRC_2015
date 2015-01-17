package org.usfirst.frc.team321.robot.subsystems;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 *
 */
public class Camera extends Subsystem {
    
	int session;
    Image frame;
    CameraServer camServer = CameraServer.getInstance();
    SendableChooser putter;

    public Camera(){	
        //frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
    	
        camServer.setQuality(50); //TODO: Configure camera quality
        camServer.startAutomaticCapture("cam0");
    }
    
    public void initDefaultCommand() {
    }
    
//    public void test(){
//    	
//    	NIVision.Rect rect = new NIVision.Rect(1080/2+150, 1920/2-150, 150, 150);
//    	NIVision.Rect reticle = new NIVision.Rect(1080/2+1, 1920/2-1, 2, 2);
//    	
//    	
//    	putter.addObject("Camera", frame);
//    	NIVision.imaqDrawShapeOnImage(frame, frame, rect,
//                DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);
//    	NIVision.imaqDrawShapeOnImage(frame, frame, reticle,
//                DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);
//    	
//    }
    
    public void test(){
    	
    }
}

