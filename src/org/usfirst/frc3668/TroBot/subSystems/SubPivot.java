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
		double intakePivot1Tics = -1 * RobotMap.intakePivot1Encoder.get();
		double intakePivot2Tics = -1 * RobotMap.intakePivot2Encoder.get();
		if (intakePivot1Tics < Settings.chassisEncoderDeadValueThreshold) {
			retVal = intakePivot2Tics;
		} else if (intakePivot2Tics < Settings.chassisEncoderDeadValueThreshold) {
			retVal = intakePivot1Tics;
		} else {
			retVal = (intakePivot1Tics + intakePivot2Tics) / 2;
		}
		return retVal;
	}

	public void resetEncoders() {
		RobotMap.intakePivot1Encoder.reset();
		RobotMap.intakePivot2Encoder.reset();
	}

	public boolean getForwardLimitSwitch() { //Talon inverted, need to change which switch is which
		return RobotMap.intakePivot1.getSensorCollection().isFwdLimitSwitchClosed();
	}

	public boolean getReverseLimitSwitch() { //Talon inverted, need to change which switch is which
		return RobotMap.intakePivot1.getSensorCollection().isRevLimitSwitchClosed();
	}

	public void pivot(double throttle) {
		if (Robot.subLift.getEncoderTics() < Settings.liftPivotLimit) {
			RobotMap.intakePivot1.set(ControlMode.PercentOutput, throttle);
			RobotMap.intakePivot2.set(ControlMode.Follower, Settings.pivot1CanID);
		} else {
			RobotMap.intakePivot1.set(0);
			RobotMap.intakePivot2.set(0);	
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

