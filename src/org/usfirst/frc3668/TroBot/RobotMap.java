package org.usfirst.frc3668.TroBot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
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
	public static WPI_TalonSRX intakeLift1;
	public static WPI_TalonSRX intakeLift2;
	public static Encoder intakeLift1Encoder;
	public static Encoder intakeLift2Encoder;
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
		rightDrive1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		rightDrive2 = new WPI_TalonSRX(Settings.chassisRightDrive2CanID);
		rightDrive2.setNeutralMode(NeutralMode.Brake);
		rightChassisMotors = new SpeedControllerGroup(rightDrive1, rightDrive2);

		leftDrive1 = new WPI_TalonSRX(Settings.chassisLeftDrive1CanID);
		leftDrive1.setNeutralMode(NeutralMode.Brake);
		leftDrive1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
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
				LimitSwitchNormal.NormallyClosed, Settings.limitSwitchTimeOut);
		rightIntakeWheel.setNeutralMode(NeutralMode.Coast);
		leftIntakeWheel = new WPI_TalonSRX(Settings.intakeLeftIntakeWheelCanID);
		leftIntakeWheel.setNeutralMode(NeutralMode.Coast);
		intakeLift1 = new WPI_TalonSRX(Settings.intakeLift1CanID);
		intakeLift1.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
				LimitSwitchNormal.NormallyClosed, Settings.limitSwitchTimeOut);
		intakeLift1.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
				LimitSwitchNormal.NormallyClosed, Settings.limitSwitchTimeOut);
		intakeLift1.setNeutralMode(NeutralMode.Brake);
		intakeLift2 = new WPI_TalonSRX(Settings.intakeLift2CanID);
		intakeLift2.follow(intakeLift1);
		intakeLift2.setInverted(true);
		intakeLift2.setNeutralMode(NeutralMode.Brake);
		
		intakeLift1Encoder = new Encoder(Settings.intakeLift1DIOPortA, Settings.intakeLift1DIOPortB);
		intakeLift2Encoder = new Encoder(Settings.intakeLift2DIOPortA, Settings.intakeLift2DIOPortB);
		
		liftMotor = new WPI_TalonSRX(Settings.liftMotorCanID);
		liftMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed,
				Settings.limitSwitchTimeOut);
		liftMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed,
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