package org.usfirst.frc.team321.util;

public class LancerPID {

	private double kP, kI, kD, epsilon;

	private double ref;
	private double prevVal, prevD,prevT;//added prevT
	private double errorSum;
	private double maxOut;

	private	boolean isFirstCycle;
	private int cycleCount, minCycleCount;

	//Blank Constructor: Remember, The PID will not increment for the output
	public LancerPID(){
		this(0.0, 0.0, 0.0, 0.0);
	}

	//Constructor with no Epsilon
	public LancerPID(double p, double i, double d){
		this(p, i, d, 0.0);
	}

	//Complete Constructor
	public LancerPID(double p, double i, double d, double e){
		this.kP = p;
		this.kI = i;
		this.kD = d;
		this.epsilon = e; //tolerance

		this.ref = 0.0; //setpoint
		this.isFirstCycle = true;

		this.cycleCount = 0;
		this.minCycleCount = 5;
	}


	public void setEpsilon(double e){ this.epsilon = e; }

	public void setReference(double ref){ this.ref = ref; }

	public void setMaxOut(double max){
		if(max < 0.0){
			this.maxOut = 0.0;
		}
		else if(max > 1.0){
			this.maxOut = 1.0; 
		} else {
			this.maxOut = max;
		}
	}

	public void setMinCycleCount(int count){ this.minCycleCount = count; }

	public void resetErrorSum(){
		this.errorSum = 0.0;
	}

	public double getRef(){ return this.ref; }

	public double calcPID(double currentVal){
		//PID Error Values
		double pErr = 0.0;
		double iErr = 0.0;
		double dErr = 0.0;

		if(this.isFirstCycle){
			this.prevVal = currentVal;
			this.isFirstCycle = false;
			this.prevT = System.currentTimeMillis();//Didn't exist

		}

		//Calculate P
		/*Tells the output to try to match the desired value by setting the output equal 
		 * to the difference between the actual value and the setpoint
		 * */
		double error = this.ref - currentVal; 
		pErr = this.kP * error;

		//Calculate I
		/*This is the "memory" part. So suppose the motors are going exactly as fast as you want it to be, then the proportion
		 * term is 0 so the output  becomes 0 */
		this.errorSum += 1/2*(error+(ref-prevVal))*(System.currentTimeMillis()-prevT);//this.errorSum+=error
		iErr = this.kI * this.errorSum;

		//Calculate D
		//
		double delta = (currentVal - this.prevVal)/(System.currentTimeMillis()-prevT);//double delta = currentVal-this.prevVal;
		dErr = this.kD * delta;

		//Calculate Output
		double output = pErr + iErr + dErr;

		output = LancerFunctions.clamp(output, -this.maxOut, this.maxOut);

		this.prevVal = currentVal;

		return output;

	}

	public double calcPIDIncrement(double currentVal){
		//PID Error Values
		double pErr = 0.0;
		double iErr = 0.0;
		double dErr = 0.0;

		if(this.isFirstCycle){
			this.prevVal = currentVal;
			this.isFirstCycle = false;
		}

		//Calculate P
		double delta = currentVal - this.prevVal;
		pErr = this.kP * delta;

		//Calculate I
		double error = this.ref - currentVal;
		iErr = this.kI * error;

		//Calculate D
		double dDelta = delta - this.prevD;
		dErr = this.kD * dDelta;

		//Calculate Output
		double output = pErr + iErr + dErr;

		output = LancerFunctions.clamp(output, -this.maxOut, this.maxOut);

		this.prevVal = currentVal;

		return output;

	}

	public boolean isDone(){

		double currentError = Math.abs(this.ref - this.prevVal);

		//If close to target
		if(currentError <= this.epsilon){
			this.cycleCount ++;
		} else{
			this.cycleCount = 0; //Restart the process
		}		

		return this.cycleCount > this.minCycleCount;

	}

	public void resetPrevious(){
		this.prevD = 0.0;
		this.prevVal = 0.0;
	}

}
