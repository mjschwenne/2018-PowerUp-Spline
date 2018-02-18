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
	public static SpeedControllerGroup rightChassisMotors;
	public static WPI_TalonSRX leftDrive1;
	public static WPI_TalonSRX leftDrive2;
	public static SpeedControllerGroup leftChassisMotors;
	public static DifferentialDrive chassisDrive;
	public static AnalogGyro gyro;
	public static AHRS navx;
	public static WPI_TalonSRX rightIntakeWheel;
	public static WPI_TalonSRX leftIntakeWheel;
	public static WPI_TalonSRX intakePivot1;
	public static WPI_TalonSRX intakePivot2;
	public static Encoder intakePivot1Encoder;
	public static Encoder intakePivot2Encoder;
	public static WPI_TalonSRX liftMotor;
	public static Encoder liftEncoder;
	public static DigitalInput liftZeroLimit;
	public static WPI_TalonSRX climb1;
	public static WPI_TalonSRX climb2;
	public static SpeedControllerGroup climbController;
	public static Servo climbServo;

	public static void init() {
		rightDrive1 = new WPI_TalonSRX(Settings.chassisRightDrive1CanID);
		rightDrive1.setNeutralMode(NeutralMode.Brake);
		rightDrive1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, Settings.chassisEncoderTimeOut);
		rightDrive2 = new WPI_TalonSRX(Settings.chassisRightDrive2CanID);
		rightDrive2.setNeutralMode(NeutralMode.Brake);
		rightChassisMotors = new SpeedControllerGroup(rightDrive1, rightDrive2);

		leftDrive1 = new WPI_TalonSRX(Settings.chassisLeftDrive1CanID);
		leftDrive1.setNeutralMode(NeutralMode.Brake);
		leftDrive1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, Settings.chassisEncoderTimeOut);
		leftDrive2 = new WPI_TalonSRX(Settings.chassisLeftDrive2CanID);
		leftDrive2.setNeutralMode(NeutralMode.Brake);
		leftChassisMotors = new SpeedControllerGroup(leftDrive1, leftDrive2);

		chassisDrive = new DifferentialDrive(leftChassisMotors, rightChassisMotors);
		chassisDrive.setSafetyEnabled(Settings.chassisDriveSafety);
		chassisDrive.setExpiration(Settings.chassisDriveExpiration);
		chassisDrive.setMaxOutput(Settings.chassisDriveMaxOutput);

		gyro = new AnalogGyro(Settings.chassisGyroAnalogPort);
		gyro.setSensitivity(Settings.chassisGyroSensitivity);

		navx = new AHRS(SPI.Port.kMXP);

		rightIntakeWheel = new WPI_TalonSRX(Settings.intakeRightIntakeWheelCanID);
		rightIntakeWheel.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
				LimitSwitchNormal.NormallyOpen, Settings.limitSwitchTimeOut);
		rightIntakeWheel.setNeutralMode(NeutralMode.Coast);

		leftIntakeWheel = new WPI_TalonSRX(Settings.intakeLeftIntakeWheelCanID);
		leftIntakeWheel.configForwardLimitSwitchSource(RemoteLimitSwitchSource.RemoteTalonSRX,
				LimitSwitchNormal.NormallyOpen, Settings.intakeRightIntakeWheelCanID, Settings.limitSwitchTimeOut);
		leftIntakeWheel.setInverted(true);
		leftIntakeWheel.setNeutralMode(NeutralMode.Coast);

		intakePivot1 = new WPI_TalonSRX(Settings.pivot1CanID);
		intakePivot1.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen,
				Settings.limitSwitchTimeOut);
		intakePivot1.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen,
				Settings.limitSwitchTimeOut);
		intakePivot1.setNeutralMode(NeutralMode.Brake);

		intakePivot2 = new WPI_TalonSRX(Settings.pivot2CanID);
		intakePivot2.setInverted(true);
		intakePivot2.setNeutralMode(NeutralMode.Brake);

		intakePivot1Encoder = new Encoder(Settings.pivot1EncoderDIOPortA, Settings.pivot1EncoderDIOPortB);
		intakePivot2Encoder = new Encoder(Settings.pivot2EncoderDIOPortA, Settings.pivot2EncoderDIOPortB); 

		liftMotor = new WPI_TalonSRX(Settings.liftMotorCanID);
		liftMotor.setInverted(true);
		liftMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen,
				Settings.limitSwitchTimeOut);
		liftMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen,
				Settings.limitSwitchTimeOut);
		liftMotor.setNeutralMode(NeutralMode.Brake);

		liftEncoder = new Encoder(Settings.liftEncoderDIOPortA, Settings.liftEncoderDIOPortB);

		climb1 = new WPI_TalonSRX(Settings.climbMotor1CanID);
		climb1.setNeutralMode(NeutralMode.Brake);
		climb2 = new WPI_TalonSRX(Settings.climbMotor2CanID);
		climb2.setNeutralMode(NeutralMode.Brake);
		climbController = new SpeedControllerGroup(climb1, climb2);

		climbServo = new Servo(Settings.climbServoPWMPort);
	}
}