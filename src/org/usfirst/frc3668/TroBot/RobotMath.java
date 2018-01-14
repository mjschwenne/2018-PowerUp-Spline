package org.usfirst.frc3668.TroBot;

public class RobotMath {
	public static double getTime() {
		return (System.nanoTime() / Math.pow(10, 9));
	}

	public static double calcTurnRate( double currentHeading, double desiredHeading, double proportion) {
		double headingDelta = Math.abs(currentHeading - desiredHeading);
		if(headingDelta > 180) {
			headingDelta = headingDelta - 180;
		}
		double commandedTurnRate = headingDelta / proportion;
		return commandedTurnRate; //IS ALWAYS POSITIVE!
	}
	
	public static double calcAnglarDelta(double currentHeading, double desiredHeading) {
		double anglarDelta = Math.abs(currentHeading - desiredHeading);
		if(anglarDelta > 180) {
			anglarDelta = anglarDelta - 180;
		}
		return anglarDelta;
	}
}
