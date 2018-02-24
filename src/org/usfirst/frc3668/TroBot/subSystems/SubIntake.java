package org.usfirst.frc3668.TroBot.subSystems;

import org.usfirst.frc3668.TroBot.Robot;
import org.usfirst.frc3668.TroBot.RobotMap;
import org.usfirst.frc3668.TroBot.Settings;
import org.usfirst.frc3668.TroBot.commands.CmdPivotDown;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class SubIntake extends Subsystem {

	public boolean getLimit() {
		return RobotMap.rightIntakeWheel.getSensorCollection().isRevLimitSwitchClosed();
	}

	public void intakeIn(double throttle) {
		RobotMap.rightIntakeWheel.set(ControlMode.PercentOutput, throttle);
		RobotMap.leftIntakeWheel.set(ControlMode.Follower, Settings.intakeRightIntakeWheelCanID);
	}

	public void intakeOut(double throttle) {
		RobotMap.rightIntakeWheel.set(ControlMode.PercentOutput, -throttle);
		RobotMap.leftIntakeWheel.set(ControlMode.Follower, Settings.intakeRightIntakeWheelCanID);

	}

	public void intakeMan(double leftThrottle, double rightThrottle) {
		RobotMap.rightIntakeWheel.set(rightThrottle);
		if (getLimit() == false) {
			RobotMap.leftIntakeWheel.set(leftThrottle);
		}
	}

	public void stopIntake() {
		RobotMap.rightIntakeWheel.set(ControlMode.PercentOutput, 0);
		RobotMap.leftIntakeWheel.set(ControlMode.PercentOutput, 0);
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