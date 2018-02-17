package org.usfirst.frc3668.TroBot.subSystems;

import org.usfirst.frc3668.TroBot.Robot;
import org.usfirst.frc3668.TroBot.RobotMap;
import org.usfirst.frc3668.TroBot.Settings;
import org.usfirst.frc3668.TroBot.commands.CmdIntakePivotUp;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class SubIntake extends Subsystem {

	public double getLiftEncoder() {
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

	public boolean getForwardPivotLimitSwitch() {
		return RobotMap.intakePivot1.getSensorCollection().isFwdLimitSwitchClosed();
	}

	public boolean getReversePivotLimitSwitch() {
		return RobotMap.intakePivot1.getSensorCollection().isRevLimitSwitchClosed();
	}

	public void intakeIn(double throttle) {
		RobotMap.rightIntakeWheel.set(throttle);
		RobotMap.leftIntakeWheel.set(ControlMode.Follower, Settings.intakeRightIntakeWheelCanID);
	}

	public void intakeOut(double throttle) {
		RobotMap.rightIntakeWheel.set(-throttle);
		RobotMap.leftIntakeWheel.set(ControlMode.Follower, Settings.intakeRightIntakeWheelCanID);
	}

	public void pivotIntake(double throttle) {
		if (Robot.subLift.getEncoderTics() < Settings.liftPivotLimit) {
			RobotMap.intakePivot1.set(throttle);
			RobotMap.intakePivot2.set(ControlMode.Follower, Settings.intakePivot1CanID);
		} else {
			RobotMap.intakePivot1.set(0);
			RobotMap.intakePivot2.set(0);	
		}
	}

	public void stopIntake() {
		RobotMap.rightIntakeWheel.set(0);
		RobotMap.leftIntakeWheel.set(0);
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new TeleopIntakeLift());
	}

	@Override
	public void periodic() {
		// Put code here to be run every loop
	}
}