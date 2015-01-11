package org.usfirst.frc.team321.custom;

public class PID {
	
	public static final double kP = 1.0;
    public static final double kI = 1.0;
    public static final double kD = 1.0;
    public static final double kTolerance = 3.0;
    public static final double kEncoder = 1.0;
    
    public double error;
    private double errorInit=0;
    private double proportion;
    private double integral=0;
    private double derivative;
    private long tI = System.currentTimeMillis();
    public long tD;
    private double result;
    public int wheel;
    
    public PID(int wheel){
    	this.wheel = wheel;
    }
    
    
    private double PIDCalculate(double error){
    	  tD=System.currentTimeMillis();
          
          proportion = (kP*error);
          derivative = (kD*(error-errorInit)/(System.currentTimeMillis()-tD));
          errorInit=error;
          integral = (kI*(integral+error*(System.currentTimeMillis()-tI)));
          tI = System.currentTimeMillis();
          result = (proportion + integral +derivative);
          
          return result;
    }
    
    private double PIDError(double encoder, double xIn,double yIn,double zIn){
    	switch(wheel){
    		case 1:
    			error = (Math.hypot(xIn,yIn)*Math.sin(Math.atan2(yIn, xIn)+Math.PI/4)+ zIn)-kEncoder*encoder;
    			break;
    		case 2:
    			error = (Math.hypot(xIn,yIn)*Math.cos(Math.atan2(yIn, xIn)+Math.PI/4)- zIn)-kEncoder*encoder;
        		break;
    		case 3:
    			error = (Math.hypot(xIn,yIn)*Math.cos(Math.atan2(yIn, xIn)+Math.PI/4)+ zIn)-kEncoder*encoder;
        		break;
    		case 4:
    			error = (Math.hypot(xIn,yIn)*Math.sin(Math.atan2(yIn, xIn)+Math.PI/4)- zIn)-kEncoder*encoder;
        		break;
        		
    	}
    	return error;
    	
    	
    }
    public double PIDVal(double encoder, double xIn,double yIn,double zIn){
		return PIDCalculate(PIDError(encoder, xIn, yIn, zIn));
	}
    
	
}
