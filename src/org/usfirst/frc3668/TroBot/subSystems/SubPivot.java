package org.usfirst.frc3668.TroBot.subSystems;

import org.usfirst.frc3668.TroBot.Robot;
import org.usfirst.frc3668.TroBot.RobotMap;
import org.usfirst.frc3668.TroBot.Settings;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SubPivot extends Subsystem {

	public double getEncoders() {
		double retVal = 0;
		double pivot1Tics = -1 * RobotMap.pivot1Encoder.get();
		double pivot2Tics = -1 * RobotMap.pivot2Encoder.get();
		if (pivot1Tics < Settings.chassisEncoderDeadValueThreshold) {
			retVal = pivot2Tics;
		} else if (pivot2Tics < Settings.chassisEncoderDeadValueThreshold) {
			retVal = pivot1Tics;
		} else {
			retVal = (pivot1Tics + pivot2Tics) / 2;
		}
		return retVal;
	}

	public void resetEncoders() {
		RobotMap.pivot1Encoder.reset();
		RobotMap.pivot2Encoder.reset();
		System.err.println("Reset Pivot Encoder");
	}

	public boolean getForwardLimitSwitch() { //Talon inverted, need to change which switch is which
		return RobotMap.pivot1.getSensorCollection().isFwdLimitSwitchClosed();
	}

	public boolean getReverseLimitSwitch() { //Talon inverted, need to change which switch is which
		return RobotMap.pivot1.getSensorCollection().isRevLimitSwitchClosed();
	}

	public void pivot(double throttle) {
		if (Robot.subLift.getEncoderTics() < Settings.liftPivotLimit) {
			RobotMap.pivot1.set(ControlMode.PercentOutput, throttle);
			RobotMap.pivot2.set(ControlMode.Follower, Settings.pivot1CanID);
		} else {
			RobotMap.pivot1.set(0);
			RobotMap.pivot2.set(0);	
		}
	}
	
	public void getPivotStatus() {
		if(getEncoders() < (Settings.pivotUpEncoderThreshold + Settings.pivotEncoderWindow)) {
			Robot.pivotStatus = Settings.pivotStatus.isUp;
		}else if(getEncoders() > (Settings.pivotDownEncoderThreshold - Settings.pivotEncoderWindow)) {
			Robot.pivotStatus = Settings.pivotStatus.isDown;
		} else {
			Robot.pivotStatus = Settings.pivotStatus.isUnknown;
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

