package org.usfirst.frc3668.TroBot;

public class RobotMath {
	public static double getTime() {
		return (System.nanoTime() / Math.pow(10, 9));
	}

	public static double calcTurnRate( double currentHeading, double targetHeading, double proportion) {

        double headingDelta = 0;

        //Positive value
        if (currentHeading >= 0 && targetHeading >= 0) {
            headingDelta = targetHeading - currentHeading;
        }
        // one of each
        else if (currentHeading >= 0 && targetHeading <= 0) {
            headingDelta =  (targetHeading + currentHeading);
        }
        //one of each again
        else if (currentHeading <= 0 && targetHeading >= 0) {
            headingDelta = -1 * (targetHeading + currentHeading);
        }
        // both negative
        else if (currentHeading <= 0 && targetHeading <= 0) {
            headingDelta = targetHeading - currentHeading;
        }

		double commandedTurnRate = headingDelta * proportion;
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
