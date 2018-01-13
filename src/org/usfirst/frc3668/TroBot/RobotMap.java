package org.usfirst.frc3668.TroBot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Ultrasonic;
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
    public static Ultrasonic leftSonar;
    public static Ultrasonic rightSonar;
    public static WPI_TalonSRX rightIntakeWheel;
    public static WPI_TalonSRX leftIntakeWheel;
    public static WPI_TalonSRX intakeRightArm;
    public static WPI_TalonSRX intakeleftArm;
    public static WPI_TalonSRX liftMotor;
    public static AnalogPotentiometer liftPotentiometer;
    public static WPI_TalonSRX climb1;
    public static WPI_TalonSRX climb2;
    public static SpeedControllerGroup climbController;
    public static WPI_TalonSRX trayRoller;
    public static DigitalInput trayRightLimit;
    public static DigitalInput trayLeftLimit;

    public static void init() {
        rightDrive1 = new WPI_TalonSRX(Settings.chassisRightDrive1CanID);
        rightDrive2 = new WPI_TalonSRX(Settings.chassisRightDrive2CanID);
        rightChassisMotors = new SpeedControllerGroup(rightDrive1, rightDrive2);
        rightDrive1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
        
        leftDrive1 = new WPI_TalonSRX(Settings.chassisLeftDrive1CanID);
        //leftDrive1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
        leftDrive2 = new WPI_TalonSRX(Settings.chassisLeftDrive2CanID);
        
        leftChassisMotors = new SpeedControllerGroup(leftDrive1, leftDrive2);

        chassisDrive = new DifferentialDrive(leftChassisMotors, rightChassisMotors);
        chassisDrive.setSafetyEnabled(Settings.chassisDriveSafety);
        chassisDrive.setExpiration(Settings.chassisDriveExpiration);
        chassisDrive.setMaxOutput(Settings.chassisDriveMaxOutput);

        gyro = new AnalogGyro(Settings.chassisGyroAnalogPort);
        gyro.setSensitivity(Settings.chassisGyroSensitivity);

        leftSonar = new Ultrasonic(Settings.chassisLeftSonarPortA, Settings.chassisLeftSonarPortB);
        rightSonar = new Ultrasonic(Settings.chassisRightSonarPortA, Settings.chassisRightSonarPortB);

        rightIntakeWheel = new WPI_TalonSRX(Settings.intakeRightIntakeWheelCanID);
        leftIntakeWheel = new WPI_TalonSRX(Settings.intakeLeftIntakeWheelCanID);
        intakeRightArm = new WPI_TalonSRX(Settings.intakeRightArmCanID);
        intakeleftArm = new WPI_TalonSRX(Settings.intakeLeftArmCanID);


        liftMotor = new WPI_TalonSRX(Settings.liftMotorCanID);
        liftPotentiometer = new AnalogPotentiometer(Settings.liftPotentiometerAnalogPort, Settings.liftPotentiometerRange, Settings.liftPotentiometerOffset);

        climb1 = new WPI_TalonSRX(Settings.climbMotor1CanID);
        climb2 = new WPI_TalonSRX(Settings.climbMotor2CanID);
        climbController = new SpeedControllerGroup(climb1, climb2);

        trayRoller = new WPI_TalonSRX(Settings.trayRollerCanID);
        trayRightLimit = new DigitalInput(Settings.trayRightLimitDIOPort);
        trayLeftLimit = new DigitalInput(Settings.trayLeftLimitDIOPort);
    }
}
