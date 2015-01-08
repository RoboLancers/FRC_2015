package org.usfirst.frc.team321.robot.subsystems;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.AxisCamera;

/**
 *
 */
public class Camera extends Subsystem {
    
	int session;
    Image frame;
    AxisCamera camera;

    public void initDefaultCommand() {
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        
        camera = new AxisCamera("10.1.91.100");//This is just default, change to actual IP soon
    }
    
    public void test(){
    	
    	NIVision.Rect rect = new NIVision.Rect(1080/2+150, 1920/2-150, 150, 150);
    	NIVision.Rect reticle = new NIVision.Rect(1080/2+1, 1920/2-1, 2, 2);
    	
    	
    	camera.getImage(frame);
    	NIVision.imaqDrawShapeOnImage(frame, frame, rect,
                DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);
    	NIVision.imaqDrawShapeOnImage(frame, frame, reticle,
                DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);
    }
}

