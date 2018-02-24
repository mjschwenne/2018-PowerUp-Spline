package org.usfirst.frc3668.TroBot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteLimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public class RobotMap {
	public static WPI_TalonSRX rightDrive1;
	public static WPI_TalonSRX rightDrive2;
	public static FeedbackDevice rightDriveEncoder;
	public static WPI_TalonSRX leftDrive1;
	public static WPI_TalonSRX leftDrive2;
	public static AnalogGyro gyro;
	public static AHRS navx;
	public static WPI_TalonSRX rightIntakeWheel;
	public static WPI_TalonSRX leftIntakeWheel;
	public static WPI_TalonSRX pivot1;
	public static WPI_TalonSRX pivot2;
	public static Encoder pivot1Encoder;
	public static Encoder pivot2Encoder;
	public static WPI_TalonSRX liftMotor;
	public static Encoder liftEncoder;
	public static DigitalInput liftZeroLimit;
	public static WPI_TalonSRX climb1;
	public static WPI_TalonSRX climb2;
	public static Servo climbServo;

	public static void init() {
		rightDrive1 = new WPI_TalonSRX(Settings.chassisRightDrive1CanID);
		rightDrive1.setNeutralMode(NeutralMode.Brake);
		rightDrive1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0,
				Settings.talonTimeOut);
		rightDrive1.configPeakCurrentLimit(Settings.chassisDriveMaxCurrentLimit, Settings.talonTimeOut);
		rightDrive1.configPeakCurrentDuration(Settings.chassisDriveMaxCurrentTimeout, Settings.talonTimeOut);
		rightDrive2 = new WPI_TalonSRX(Settings.chassisRightDrive2CanID);
		rightDrive2.configPeakCurrentLimit(Settings.chassisDriveMaxCurrentLimit, Settings.talonTimeOut);
		rightDrive2.configPeakCurrentDuration(Settings.chassisDriveMaxCurrentTimeout, Settings.talonTimeOut);
		rightDrive2.setNeutralMode(NeutralMode.Brake);

		leftDrive1 = new WPI_TalonSRX(Settings.chassisLeftDrive1CanID);
		leftDrive1.configPeakCurrentLimit(Settings.chassisDriveMaxCurrentLimit, Settings.talonTimeOut);
		leftDrive1.configPeakCurrentDuration(Settings.chassisDriveMaxCurrentTimeout, Settings.talonTimeOut);
		leftDrive1.setNeutralMode(NeutralMode.Brake);
		leftDrive1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0,
				Settings.talonTimeOut);
		leftDrive2 = new WPI_TalonSRX(Settings.chassisLeftDrive2CanID);
		leftDrive2.configPeakCurrentLimit(Settings.chassisDriveMaxCurrentLimit, Settings.talonTimeOut);
		leftDrive2.configPeakCurrentDuration(Settings.chassisDriveMaxCurrentTimeout, Settings.talonTimeOut);
		leftDrive2.setNeutralMode(NeutralMode.Brake);

		gyro = new AnalogGyro(Settings.chassisGyroAnalogPort);
		gyro.setSensitivity(Settings.chassisGyroSensitivity);

		navx = new AHRS(SPI.Port.kMXP);

		rightIntakeWheel = new WPI_TalonSRX(Settings.intakeRightIntakeWheelCanID);
		rightIntakeWheel.configPeakCurrentLimit(Settings.intakeMaxCurrentLimit, Settings.talonTimeOut);
		rightIntakeWheel.configPeakCurrentDuration(Settings.intakeMaxCurrentTimeout, Settings.talonTimeOut);
		rightIntakeWheel.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
				LimitSwitchNormal.NormallyOpen, Settings.limitSwitchTimeOut);
		rightIntakeWheel.setNeutralMode(NeutralMode.Coast);

		leftIntakeWheel = new WPI_TalonSRX(Settings.intakeLeftIntakeWheelCanID);
		leftIntakeWheel.configPeakCurrentLimit(Settings.intakeMaxCurrentLimit, Settings.talonTimeOut);
		leftIntakeWheel.configPeakCurrentDuration(Settings.intakeMaxCurrentTimeout, Settings.talonTimeOut);
		leftIntakeWheel.configForwardLimitSwitchSource(RemoteLimitSwitchSource.RemoteTalonSRX,
				LimitSwitchNormal.NormallyOpen, Settings.intakeRightIntakeWheelCanID, Settings.limitSwitchTimeOut);
		leftIntakeWheel.setInverted(true);
		leftIntakeWheel.setNeutralMode(NeutralMode.Coast);

		pivot1 = new WPI_TalonSRX(Settings.pivot1CanID);
		pivot1.configPeakCurrentLimit(Settings.pivotMaxCurrentLimit, Settings.talonTimeOut);
		pivot1.configPeakCurrentDuration(Settings.pivotMaxCurrentTimeout, Settings.talonTimeOut);
		pivot1.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen,
				Settings.limitSwitchTimeOut);
		pivot1.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen,
				Settings.limitSwitchTimeOut);
		pivot1.setNeutralMode(NeutralMode.Brake);

		pivot2 = new WPI_TalonSRX(Settings.pivot2CanID);
		pivot2.configPeakCurrentLimit(Settings.pivotMaxCurrentLimit, Settings.talonTimeOut);
		pivot2.configPeakCurrentDuration(Settings.pivotMaxCurrentTimeout, Settings.talonTimeOut);
		pivot2.setInverted(true);
		pivot2.setNeutralMode(NeutralMode.Brake);

		pivot1Encoder = new Encoder(Settings.pivot1EncoderDIOPortA, Settings.pivot1EncoderDIOPortB);
		pivot2Encoder = new Encoder(Settings.pivot2EncoderDIOPortA, Settings.pivot2EncoderDIOPortB);

		liftMotor = new WPI_TalonSRX(Settings.liftMotorCanID);
		liftMotor.configPeakCurrentLimit(Settings.liftMaxCurrentLimit, Settings.talonTimeOut);
		liftMotor.configPeakCurrentDuration(Settings.liftMaxCurrentTimeout, Settings.talonTimeOut);
		liftMotor.setInverted(true);
		liftMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen,
				Settings.limitSwitchTimeOut);
		liftMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen,
				Settings.limitSwitchTimeOut);
		liftMotor.setNeutralMode(NeutralMode.Brake);

		liftEncoder = new Encoder(Settings.liftEncoderDIOPortA, Settings.liftEncoderDIOPortB);

		climb1 = new WPI_TalonSRX(Settings.climbMotor1CanID);
		climb1.configPeakCurrentLimit(Settings.climbMotorMaxCurrentLimit, Settings.talonTimeOut);
		climb1.configPeakCurrentDuration(Settings.climbMotorMaxCurrentTimeout, Settings.talonTimeOut);
		climb1.setNeutralMode(NeutralMode.Brake);
		climb2 = new WPI_TalonSRX(Settings.climbMotor2CanID);
		climb2.configPeakCurrentLimit(Settings.climbMotorMaxCurrentLimit, Settings.talonTimeOut);
		climb2.configPeakCurrentDuration(Settings.climbMotorMaxCurrentTimeout, Settings.talonTimeOut);
		climb2.setNeutralMode(NeutralMode.Brake);

		climbServo = new Servo(Settings.climbServoPWMPort);
	}
}