package org.usfirst.frc3668.TroBot;

public class Settings {
	//Chassis Settings
	public static final int chassisRightDrive1CanID = 1;
	public static final int chassisRightDrive2CanID = 2;
	public static final int chassisLeftDrive1CanID = 3;
	public static final int chassisLeftDrive2CanID = 4;
	public static final boolean chassisDriveSafety = false;
	public static final double chassisDriveExpiration = 1.0;
	public static final double chassisDriveMaxOutput = 1.0;
	public static final int chassisGyroAnalogPort = 0;
	public static final double chassisGyroSensitivity = 0.007;
	public static final double chassisGyroTol = 1;
	public static final double chassisEncoderDeadValueThreshold = 0.5;
	public static final double testChassisGearRatio = 10.71; //10.71:1
	public static final int chassisEncoderTicsPerRevolution = 4096; 
	public static final double chassisEncoderDistancePerPulse = (6*Math.PI)/chassisEncoderTicsPerRevolution;
	public static final boolean chassisSquareJoyInput = false;
	public static final double chassisLeftSideScalar = 18.98301225008481/19.843575472088656; //0.9566326530612244
	public static final double chassisRightSideScalar = 1;
	public static final double chassisMaxInchesPerSecond = 126;//168;
	public static final double chassisDriveStraightGyroKp = 0.04;
	public static final double chassisMaxDregeesPreSecond = 15;
	public static final double chassisTurnKp = 0.0027;

	
	public static enum chassisTurnDirection{
		turnRight, turnLeft
	}
	
	
	//Intake Settings
	public static final int intakeRightIntakeWheelCanID = 5;
	public static final int intakeLeftIntakeWheelCanID = 6;
	public static final int intakeRightArmCanID = 7;
	public static final int intakeLeftArmCanID = 8;
	public static final double intakeRightValue = 1;
	public static final double intakeLeftValue = -1;
	
	//Lift Settings
	public static final int liftMotorCanID = 9;
	public static final int liftEncoderDIOPortA = 1;
	public static final int liftEncoderDIOPortB = 2;
	public static final int liftLimitSwitchDIOPort = 3;
	public static final double liftSwitchHeight = 20;
	public static final double liftScaleHeight = 66;
	
	//Climb Settings
	public static final int climbMotor1CanID = 10;
	public static final int climbMotor2CanID = 11;
	public static final double climbMotorSpeed = 0.8;
	
	//Tray Settings
	public static final int trayRollerCanID = 12;
	public static final int trayRightLimitDIOPort = 4;
	public static final int trayLeftLimitDIOPort = 5;
	
	//Profile Settings
	public static final String profileTestLogName = "logs\\motionProfileTestResults";
	public static final String profileLogName = "//media//sda1//motionProfile";
	public static final double profileAdditionLoopNumber = 50;
	public static final String profileLogFileExtension = ".txt";
	public static final double profileDriveAccelration = 20; //inches/sec/sec
	public static final double profileDriveKp = 0.125;
	public static final double profileDriveKi = 0.0006;
	public static final double profileDriveKd = 0.0007555555;
	public static final double profileTurnKp = 0.0;
	public static final double profileTurnKi = 0.0;
	public static final double profileTurnKd = 0.0;
	public static final double profileAnglarAccelration = 10;
	public static final double profileInitVelocity = 0.0;
	public static final double profileMovementThreshold = 0.5;
	
	//Interface Settings
	public static final int joyDrivePort = 0;
	public static final int joyDriveInvertDriveButton = 2;
	public static final int joyArtPort = 1;
	public static final int joyArtIntakeInButton = 3;
	public static final int joyArtReverseIntakeButton = 5;
	public static final int joyArtSetLiftToZeroButton = 12;
	public static final int joyArtSetLiftToSwitchButton = 11;
	public static final int joyArtSetLiftToScaleButton = 10;
	public static final int joyArtClimbButton = 6;
	
	//Auto Settings
	public static enum autoAction {
		autoSwitch, autoScale, autoLine, nothing
	}
	public static enum autoPosition{
		leftPortal, center, rightPortal
	}
	public static final double autoCruiseSpeed = 135;
	public static final double autoLineDistance = 144;
	public static final double autoTurnSpeed = 0.1;
}
