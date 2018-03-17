package org.usfirst.frc3668.TroBot;

public class Settings {	
	// Chassis Settings
	public static final int chassisRightDrive1CanID = 1;
	public static final int chassisRightDrive2CanID = 2;
	public static final int chassisLeftDrive1CanID = 3;
	public static final int chassisLeftDrive2CanID = 4;
	public static final boolean chassisDriveSafety = false;
	public static final double chassisDriveExpiration = 1.0;
	public static final double chassisDriveMaxOutput = 1.0;
	public static final int chassisGyroAnalogPort = 0;
	public static final int chassisDriveMaxCurrentLimit = 55;
	public static final int chassisDriveMaxCurrentTimeout = 500;
	public static final double chassisGyroSensitivity = 0.007;
	public static final double chassisGyroTol = 1.5;
	public static final double chassisEncoderDeadValueThreshold = 0.5;
	public static final double testChassisGearRatio = 10.71; // 10.71:1
	public static final int chassisEncoderTicsPerRevolution = 4096;
	public static final double chassisEncoderDistancePerPulse = (6 * Math.PI) / chassisEncoderTicsPerRevolution;
	public static final boolean chassisSquareJoyInput = true;
	public static final double chassisRightSideScalar = 1;//142.62799967681903/144.0730095790073;//141.65238789572376/145.0578252448299;//18.98301225008481 / 19.843575472088656;
	public static final double chassisLeftSideScalar = 1;
	public static final double chassisMaxInchesPerSecond = 168;
	public static final double chassisDriveStraightGyroKp = 0.05;
	public static final double chassisMaxDregeesPreSecond = 15;
	public static final double chassisTurnKp = 0.0027;
	public static final int limitSwitchTimeOut = 30;
	public static final int talonTimeOut = 10;

	public static enum chassisTurnDirection {
		turnRight, turnLeft
	}

	// Intake Settings
	public static final int intakeRightIntakeWheelCanID = 5;
	public static final int intakeLeftIntakeWheelCanID = 6;
	public static final int intakeMaxCurrentLimit = 40;
	public static final int intakeMaxCurrentTimeout = chassisDriveMaxCurrentTimeout;
	public static final double intakeOutSlow = 0.5;
	public static final double intakeOutFast = 1;
	public static final double intakeIn = -0.75;
	public static final double intakeInOffWheel = 0;
	public static final double intakeAutoScaleOut = 1;
	public static final double intakeAutoSwitchOut = 0.6;
	
	// Pivot Settings
	public static enum pivotStatus {
		isUp, isDown, isUnknown
	}
	
	public static final int pivot1CanID = 8;
	public static final int pivot2CanID = 7;
	public static final int pivot1EncoderDIOPortA = 3;
	public static final int pivot1EncoderDIOPortB = 4;
	public static final int pivot2EncoderDIOPortA = 6;
	public static final int pivot2EncoderDIOPortB = 7;
	public static final int pivotMaxCurrentLimit = 40;
	public static final int pivotMaxCurrentTimeout = chassisDriveMaxCurrentTimeout;
	public static final double pivotGravityFallSlow = -0.75;
	public static final double pivotGravityFallFast = -0.75;
	public static final double pivotCalibrationSpeed = -0.25;
	public static final int pivotDownEncoderThreshold = 540;
	public static final int pivotUpEncoderThreshold = 0;
	public static final double pivotEncoderWindow = 20;
	public static final int pivotTicsPerRevolution = 1988;// 497 * 4 (gear ratio)
	public static final int pivotGravityFallThreshold = 497;
	
	// Lift Settings
	public static final int liftMotorCanID = 9;
	public static final int liftMaxCurrentLimit = chassisDriveMaxCurrentLimit;
	public static final int liftMaxCurrentTimeout = chassisDriveMaxCurrentTimeout;
	public static final int liftEncoderDIOPortA = 1;
	public static final int liftEncoderDIOPortB = 2;
	public static final double liftEncoderTicsPerInch = (3.25 * Math.PI) / 1440;
	public static final double liftMaxHight = 45;
	public static final int liftMaxTics = 2203;//(int) (liftMaxHight / liftEncoderTicsPerInch);
	public static final int slowLiftThresh = 210;
	public static final double slowConstantForLift = 0.6;
	public static final double liftSwitchHeight = 10;
	public static final int liftTicsToSwitch = 1025;//(int) (liftSwitchHeight / liftEncoderTicsPerInch);
	public static final double liftScaleHeight = 40;
	public static final int liftTicsToScale = 1700;//(int) (liftScaleHeight / liftEncoderTicsPerInch);
	public static final int liftTicsToMaxScaleAuto = liftMaxTics - 100;
	public static final int liftDownHieghtLimit = 1025;
	public static final int liftPivotLimit = 100;
	public static final double liftCalibrationSpeed = -0.1;
	public static final double liftUpSpeed = -1;
	public static final double liftDownSpeed = 0.73;
	public static final double liftTeleUpSpeed = -1;
	public static final double liftTeleDownSpeed = 0.73;
	public static final int liftWindow = 5;
	public static final double liftJoyScalar = 0.5;
	
	// Climb Settings
	public static final int climbMotor1CanID = 10;
	public static final int climbMotor2CanID = 11;
	public static final int climbMotorMaxCurrentLimit = chassisDriveMaxCurrentLimit;
	public static final int climbMotorMaxCurrentTimeout = chassisDriveMaxCurrentTimeout;
	public static final double climbMotorSpeed = 0.8;
	public static final int climbServoPWMPort = 0;
	public static final int climbEngaged = 180;
	public static final int climbDisengaged = 0;

	// Profile Settings
	public static final String profileTestLogName = "logs\\motionProfileTestResults";
	public static final String profileLogName = "//media//sda1//motionProfile";
	public static final double profileAdditionLoopNumber = 50;
	public static final String profileLogFileExtension = ".txt";
	public static final double profileDriveAccelration = 55; // inches/sec/sec
	public static final double profileDriveKp = 0.1;
	public static final double profileDriveKi = 0.002;
	public static final double profileDriveKd = 0.0009;
	public static final double profileTurnKp = 0.0;
	public static final double profileTurnKi = 0.0;
	public static final double profileTurnKd = 0.0;
	public static final double profileAnglarAccelration = 10;
	public static final double profileInitVelocity = 0.0;
	public static final double profileMovementThreshold = 0.75;
	public static final double profileEndTimeScalar = 1.3;

	// Interface Settings
	public static final int joyDrivePort = 0;
	public static final int joyDriveInvertDriveButton = 2;
	public static final int joyDriveIntakeInButton = 9;
	public static final int joyDriveIntakeOutFastButton = 8;
	public static final int joyDriveIntakeOutSLowButton = 12;
	public static final int joyDriveOneWheelLeftIntake = 11;
	public static final int joyDriveOneWheelRightIntake = 7;
	public static final double joyDriveDeadband = 0.05;
	public static final int joyArtPort = 1;
	public static final int joyArtLiftUpButton = 7;
	public static final int joyArtLiftDownButton = 9;
	public static final int joyArtSetLiftToZeroButton = 12;
	public static final int joyArtSetLiftToSwitchButton = 10;
	public static final int joyArtSetLiftToScaleButton = 8;
	public static final int joyArtClimbButton = 11;
	public static final int joyArtPivotUpButton = 5;
	public static final int joyArtTogglePivotStatus = 6;
	public static final int joyArtPivotDownButton = 3;

	// Auto Settings
	public static enum autoAction {
		autoSwitch, autoScale, autoSafeScale, autoLine, nothing
	}

	public static enum autoPosition {
		left, center, right, error
	}

	public static final double autoEjectCubeTime = 1.5;
	public static final double autoCruiseSpeed = 145;
	public static final double autoLineDistance = 144;
	public static final double autoTurnSpeed = 0.27; // 0.18 for carpet, 0.12 for tile
	public static final double autoPivotToBumper = 30; // Not really, added a buffer
	public static final double autoApproachSwitchLeft = 21;
	public static final double autoApproachSwitchRight = 16;
	public static final double autoAngleToSwitchLeft = -45;
	public static final double autoAngleToSwitchRight = 39.5;
	public static final double autoDiagToSwitchLeft = 88.75;
	public static final double autoDiagToSwitchRight = 82.5;
	public static final double autoBehindSwitchDist =  224;//.235;
	public static final double autoTurnToFaceWall = 90;
	public static final double autoDrivePastSwitch = 205.5;// 144;
	public static final double autoTurnToFaceScale = 0;
	public static final double autoDriveToScale = 88.765;// 12;
	public static final double autoTurnToPlaceInScale = -90;
	public static final double autoStraightToScale = 283; //297.5;
	public static final double autoStraightTurnToScale = 45;//90;
	public static final double autoForwardToBeCloseToScale = 14.57;
	public static final boolean autoAllyToScale = false;
	public static final boolean autoAllyNotToScale = true;
	
	public static int visionCameraFPS = 7;
	public static int visionCubeCameraID = 1;
	public static int visionBackCameraID = 0;
	public static int visionImageWidthPixels = 480;
	public static int visionImageHeightPixels = 240;
	public static int visionHalfImageWidthPixels = 240;
	public static int visionHalfImageHeightPixels = 120;
	public static int visionImageBrightness = 50;
}