package org.usfirst.frc3668.TroBot.subSystems;

import org.usfirst.frc3668.TroBot.Robot;
import org.usfirst.frc3668.TroBot.RobotMap;
import org.usfirst.frc3668.TroBot.Settings;
import org.usfirst.frc3668.TroBot.commands.TeleopDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class SubChassis extends Subsystem {
	public boolean _isSafeToMove = true;

	public void Drive(Joystick stick) {
		double joyX = stick.getX();
		double joyY = stick.getY();

		if (Math.abs(joyX) < Settings.joyDriveDeadband) {
			joyX = 0;
		} else if (Math.abs(joyY) < Settings.joyDriveDeadband) {
			joyY = 0;
		}

		double rightMotorThrottle;
		double leftMotorThrottle;
		if (Robot.isDriveInverted) {
			rightMotorThrottle = (joyX + joyY) * Settings.chassisRightSideScalar;
			leftMotorThrottle = (joyX - joyY) * Settings.chassisLeftSideScalar;
			if (Settings.chassisSquareJoyInput) {
				double rightSignum = Math.signum(rightMotorThrottle);
				double leftSignum = Math.signum(leftMotorThrottle);
				rightMotorThrottle = Math.pow(rightMotorThrottle, 2) * rightSignum;
				leftMotorThrottle = Math.pow(leftMotorThrottle, 2) * leftSignum;
			}
			//System.err.println(
			//		String.format("Joy X: %1$.3f \t Joy Y: %2$.3f \t Right Throttle: %3$.4f \t Left Throttle: %4$.4f",
			//				joyX, joyY, rightMotorThrottle, leftMotorThrottle));
			setLeftMotors(leftMotorThrottle);
			setRightMotors(rightMotorThrottle);

		} else {
			rightMotorThrottle = (joyX - joyY) * Settings.chassisRightSideScalar;
			leftMotorThrottle = (joyX + joyY) * Settings.chassisLeftSideScalar;
			if (Settings.chassisSquareJoyInput) {
				double rightSignum = Math.signum(rightMotorThrottle);
				double leftSignum = Math.signum(leftMotorThrottle);
				rightMotorThrottle = Math.pow(rightMotorThrottle, 2) * rightSignum;
				leftMotorThrottle = Math.pow(leftMotorThrottle, 2) * leftSignum;
			}
			//System.err.println(
			//		String.format("Joy X: %1$.3f \t Joy Y: %2$.3f \t Right Throttle: %3$.4f \t Left Throttle: %4$.4f",
			//				joyX, joyY, rightMotorThrottle, leftMotorThrottle));
			setLeftMotors(leftMotorThrottle);
			setRightMotors(rightMotorThrottle);
		}
	}

	public void DriveMan(double leftThrottle, double rightThrottle) {
		setLeftMotors(leftThrottle);
		setRightMotors(-rightThrottle);
	}

	public void setRightMotors(double throttle) {
		RobotMap.rightDrive1.set(ControlMode.PercentOutput, throttle);
		RobotMap.rightDrive2.set(ControlMode.PercentOutput, throttle);
	}

	public void setLeftMotors(double throttle) {
		RobotMap.leftDrive1.set(ControlMode.PercentOutput, throttle);
		RobotMap.leftDrive2.set(ControlMode.PercentOutput, throttle);
	}

	public void Drive(double move, double rotate) {
		double rightMotorThrottle;
		double leftMotorThrottle;
		if (Robot.isDriveInverted) {
			rightMotorThrottle = (rotate + move) * Settings.chassisRightSideScalar;
			leftMotorThrottle = (rotate - move) * Settings.chassisLeftSideScalar;
			setLeftMotors(leftMotorThrottle);	
			setRightMotors(rightMotorThrottle);
		} else {
			rightMotorThrottle = (-rotate - move) * Settings.chassisRightSideScalar;
			leftMotorThrottle = (-rotate + move) * Settings.chassisLeftSideScalar;
			setLeftMotors(leftMotorThrottle);
			setRightMotors(rightMotorThrottle);
		}

	}

	public double getEncoderAvgDistInch() {
		double retVal = 0;
		double leftDistance = getLeftEncoderDist();
		double rightDistance = getRightEncoderDist();
		if (Math.abs(leftDistance) < Settings.chassisEncoderDeadValueThreshold) {
			retVal = rightDistance;
		} else if (Math.abs(rightDistance) < Settings.chassisEncoderDeadValueThreshold) {
			retVal = leftDistance;
		} else {
			retVal = (leftDistance + rightDistance) / 2;
		}
		return retVal;
	}

	public double getRightEncoderDist() {
		return -RobotMap.rightDrive1.getSelectedSensorPosition(0) * Settings.chassisEncoderDistancePerPulse;
	}

	public double getLeftEncoderDist() {
		return -RobotMap.leftDrive1.getSelectedSensorPosition(0) * Settings.chassisEncoderDistancePerPulse;
	}

	public void resetRightEncoder() {
		RobotMap.rightDrive1.setSelectedSensorPosition(0, 0, 0);
	}

	public void resetLeftEncoder() {
		RobotMap.leftDrive1.setSelectedSensorPosition(0, 0, 0);
	}

	public void resetBothEncoders() {
		RobotMap.rightDrive1.setSelectedSensorPosition(0, 0, 0);
		RobotMap.leftDrive1.setSelectedSensorPosition(0, 0, 0);
	}

	public double getGyroAngleRaw() {
		return RobotMap.gyro.getAngle();
	}

//	public double getNavxAngleRaw() {
//		return RobotMap.navx.getAngle();
//	}

	public double getNormalizedGyroAngle() {
		return gyroNormalize(getGyroAngleRaw());
	}

//	public double getNormaliziedNavxAngle() {
//		return gyroNormalize(getNavxAngleRaw());
//	}

	public void initializeGyro() {
		RobotMap.gyro.initGyro();
		RobotMap.gyro.calibrate();
		RobotMap.gyro.reset();
	}

	public void resetGyro() {
		RobotMap.gyro.reset();
	}

//	public void resetNavx() {
//		RobotMap.navx.reset();
//	}

	public double gyroNormalize(double heading) {
		// takes the full turns out of heading
		// gives us values from 0 to 180 for the right side of the robot
		// and values from 0 to -179 degrees for the left side of the robot

		double degrees = heading % 360;

		if (degrees > 180) {
			degrees = degrees - 360;
		}

		if (degrees < -179) {
			degrees = degrees + 360;
		}

		return degrees;
	}

	public boolean gyroInTol(double currHeading, double desiredHeading, double tol) {

		double upperTol = gyroNormalize(desiredHeading + tol);
		double lowerTol = gyroNormalize(desiredHeading - tol);
		double normalCurr = gyroNormalize(currHeading);

		double signumUpperTol = Math.signum(upperTol);
		double signumLowerTol = Math.signum(lowerTol);

		boolean retValue = false;
		// works for all positive numbers direction values
		if (signumUpperTol > 0 && signumLowerTol > 0) {
			if ((normalCurr >= lowerTol) && (normalCurr <= upperTol)) {
				retValue = true;
			}
		}

		// works for negative values
		else if (signumUpperTol < 0 && signumLowerTol < 0) {
			if ((normalCurr >= lowerTol) && (normalCurr <= upperTol)) {
				retValue = true;
			}
		}
		// mixed values -tol to + tol This happens at 180 degrees
		else if ((signumUpperTol < 0) && (signumLowerTol > 0)) {
			// System.out.println("upperTol " + upperTol + " Current " +
			// normalCurr + " lowerTol " + lowerTol);
			if ((Math.abs(normalCurr) >= Math.abs(lowerTol)) && (Math.abs(normalCurr) >= Math.abs(upperTol))) {
				retValue = true;
			}

		}
		// mixed values -tol to + tol This happens at 0 degrees
		else if ((signumUpperTol > 0) && (signumLowerTol < 0)) {
			// System.out.println("upperTol " + upperTol + " Current " +
			// normalCurr + " lowerTol " + lowerTol);
			if ((Math.abs(normalCurr) <= Math.abs(lowerTol)) && (Math.abs(normalCurr) <= Math.abs(upperTol))) {
				retValue = true;
			}

		}
		return (retValue);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new TeleopDrive());
	}

	@Override
	public void periodic() {
		// Put code here to be run every loop

	}
}
