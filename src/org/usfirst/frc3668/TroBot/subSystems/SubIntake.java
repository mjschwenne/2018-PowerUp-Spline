package org.usfirst.frc3668.TroBot.subSystems;

import org.usfirst.frc3668.TroBot.Robot;
import org.usfirst.frc3668.TroBot.RobotMap;
import org.usfirst.frc3668.TroBot.Settings;
import org.usfirst.frc3668.TroBot.commands.CmdPivotDown;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class SubIntake extends Subsystem {

	public void intakeIn(double throttle) {
		RobotMap.rightIntakeWheel.set(throttle);
		RobotMap.leftIntakeWheel.set(ControlMode.Follower, Settings.intakeRightIntakeWheelCanID);
	}

	public void intakeOut(double throttle) {
		RobotMap.rightIntakeWheel.set(-throttle);
		RobotMap.leftIntakeWheel.set(ControlMode.Follower, Settings.intakeRightIntakeWheelCanID);
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