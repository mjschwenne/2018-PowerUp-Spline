package org.usfirst.frc3668.TroBot.subSystems;

import org.usfirst.frc3668.TroBot.Robot;
import org.usfirst.frc3668.TroBot.RobotMap;
import org.usfirst.frc3668.TroBot.Settings;
import org.usfirst.frc3668.TroBot.commands.TeleopJoyLift;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class SubLift extends Subsystem {
	public void setLiftNeutralMode(NeutralMode mode) {
		RobotMap.liftMotor.setNeutralMode(mode);
	}

	public void lift(double throttle) {
		Robot.subPivot.getPivotStatus();
		if (Robot.pivotStatus == Settings.pivotStatus.isUnknown) {
			RobotMap.liftMotor.set(0);
		} else if (Robot.pivotStatus == Settings.pivotStatus.isUp) {
			RobotMap.liftMotor.set(throttle);
		} else if (Robot.pivotStatus == Settings.pivotStatus.isDown) {
			if(throttle > 0) {
				RobotMap.liftMotor.set(throttle);
			} else if (getEncoderTics() < Settings.liftDownHieghtLimit) {
				RobotMap.liftMotor.set(throttle);
			} else {
				RobotMap.liftMotor.set(0);
			}
		} else {
			RobotMap.liftMotor.set(0);
		}
	}

	public void resetEncoder() {
		RobotMap.liftEncoder.reset();
	}

	public double getEncoderTics() {
		return -RobotMap.liftEncoder.get();
	}

	public boolean getLiftForwardLimit() {

		return !RobotMap.liftMotor.getSensorCollection().isFwdLimitSwitchClosed();

	}

	public boolean getLiftReverseLimit() {
		return !RobotMap.liftMotor.getSensorCollection().isRevLimitSwitchClosed();
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new TeleopJoyLift());
	}

	@Override
	public void periodic() {
		// Put code here to be run every loop

	}
}