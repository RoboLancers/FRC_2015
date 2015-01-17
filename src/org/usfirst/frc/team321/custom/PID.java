package org.usfirst.frc.team321.custom;

import org.usfirst.frc.team321.robot.subsystems.DriveTrain;

public class PID {
	
	public static final double kP = 1.0;
    public static final double kI = 1.0;
    public static final double kD = 1.0;
    public static final double kTolerance = 3.0;
    public static final double kEncoder = 1.0;
    
    public double error;
    public long tD;
    public int wheel;
    
    private long tI = System.currentTimeMillis();
    private double errorInit = 0;
    private double proportion;
    private double integral = 0;
    private double derivative;
    private double result;
    
    
    
    public PID(){
    }
    
    //overload with specific wheel
    public PID(int wheel){
    	this.wheel = wheel;
    }
    
    
    private double PIDCalculate(double error){
    	
    	tD = System.currentTimeMillis();
          
    	proportion = (kP * error);
        derivative = (kD * (error - errorInit) / (System.currentTimeMillis() - tD));
        errorInit = error;
        integral = (kI * (integral + error * (System.currentTimeMillis() - tI)));
          
        tI = System.currentTimeMillis();
          
        result = (proportion + integral + derivative);
          
        return result;
    }
    
    private double PIDError(double encoder, double xIn, double yIn, double zIn){
    	switch(wheel){
    	
    		//TODO: fix error formulae (see "DriveTrain.angleToDriveMechanum" method)
    		case 1:
    			error = (Math.hypot(xIn,yIn)*Math.sin(Math.atan2(yIn, xIn)+Math.PI/4)+ zIn) -kEncoder * encoder;
    			break;
    		case 2:
    			error = (Math.hypot(xIn,yIn)*Math.cos(Math.atan2(yIn, xIn)+Math.PI/4)- zIn) -kEncoder * encoder;
        		break;
    		case 3:
    			error = (Math.hypot(xIn,yIn)*Math.cos(Math.atan2(yIn, xIn)+Math.PI/4)+ zIn) -kEncoder* encoder;
        		break;
    		case 4:
    			error = (Math.hypot(xIn,yIn)*Math.sin(Math.atan2(yIn, xIn)+Math.PI/4)- zIn) - kEncoder * encoder;
        		break;
    	}
    	return error;
    	
    	
    }
    
    private double gyroError(){
    	error = /*Math.atan(yIn/xIn)*/ (90 - DriveTrain.driveGyro.getAngle()) * CustomMath.deg2Rad;
    	return error;
    	
    	
    }
    
    public double gyroCalc(){
    	return(PIDCalculate(gyroError()));
    }
    
    public double PIDVal(double encoder, double xIn,double yIn,double zIn){
		return PIDCalculate(PIDError(encoder, xIn, yIn, zIn));
	}
    
	
}
