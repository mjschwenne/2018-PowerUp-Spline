package org.usfirst.frc3668.TroBots;

public class Settings {
	//Chassis Settings
	public static final int chassisRightDrive1CanID = 1;
	public static final int chassisRightDrive2CanID = 2;
	public static final int chassisLeftDrive1CanID = 3;
	public static final int chassisLeftDrive2CanID = 4;
	public static final boolean chassisDriveSafety = true;
	public static final double chassisDriveExpiration = 0.1;
	public static final double chassisDriveMaxOutput = 1.0;
	public static final int chassisGyroAnalogPort = 0;
	public static final double chassisGyroSensitivity = 0.007;
	public static final int chassisLeftSonarPortA = 1;
	public static final int chassisLeftSonarPortB = 2;
	public static final int chassisRightSonarPortA = 3;
	public static final int chassisRightSonarPortB = 4;
	public static final double chassisEncoderDeadValueThreshold = 0.5;
	//Intake Settings
	public static final int intakeRightIntakeWheelCanID = 5;
	public static final int intakeLeftIntakeWheelCanID = 6;
	public static final int intakeRightArmCanID = 7;
	public static final int intakeLeftArmCanID = 8;
	public static final double intakeRightValue = 1;
	public static final double intakeLeftValue = -1;
	
	//Lift Settings
	public static final int liftMotorCanID = 9;
	public static final int liftPotentiometerAnalogPort = 2;
	public static final double liftPotentiometerRange = 1.0;//Max Reading
	public static final double liftPotentiometerOffset = 0.0;
	
	//Climb Settings
	public static final int climbMotor1CanID = 10;
	public static final int climbMotor2CanID = 11;
	public static final double climbMotorSpeed = 0.8;
	
	//Tray Settings
	public static final int trayRollerCanID = 12;
	public static final int trayRightLimitDIOPort = 6;
	public static final int trayLeftLimitDIOPort = 5;
	
	//Profile Settings
	public static final String profileTestLogName = "logs\\motionProfileTestResults";
	public static final String profileLogName = "//media//sda1//motionProfile";
	public static final double profileAdditionLoopNumber = 0;
	public static final String profileLogFileExtension = ".txt";
		
}
