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
	public static final double chassisGyroSensitivity = 0.007;
	public static final double chassisGyroTol = 1.5;
	public static final double chassisEncoderDeadValueThreshold = 0.5;
	public static final double testChassisGearRatio = 10.71; // 10.71:1
	public static final int chassisEncoderTicsPerRevolution = 4096;
	public static final double chassisEncoderDistancePerPulse = (6 * Math.PI) / chassisEncoderTicsPerRevolution;
	public static final boolean chassisSquareJoyInput = true;
	public static final double chassisLeftSideScalar = 1;//18.98301225008481 / 19.843575472088656; // 0.9566326530612244
	public static final double chassisRightSideScalar = 1;
	public static final double chassisMaxInchesPerSecond = 126;// 168;
	public static final double chassisDriveStraightGyroKp = 0.06;
	public static final double chassisMaxDregeesPreSecond = 15;
	public static final double chassisTurnKp = 0.0027;
	public static final int limitSwitchTimeOut = 30;

	public static enum chassisTurnDirection {
		turnRight, turnLeft
	}

	// Intake Settings
	public static final int intakeRightIntakeWheelCanID = 5;
	public static final int intakeLeftIntakeWheelCanID = 6;
	public static final int intakePivot1CanID = 12;//7;
	public static final int intakePivot2CanID = 7;//8';
	public static final int intakeLift1DIOPortA = 3;
	public static final int intakeLift1DIOPortB = 4;
	public static final int intakeLift2DIOPortA = 6;
	public static final int intakeLift2DIOPortB = 7;
	public static final int intakeTicsPerRevolution = 1988;// 497 * 4 (gear ratio)
	public static final int intakePivotGravityFallThreshold = 497;
	public static final double intakeOut = -0.75;
	public static final double intakePivotGravityFallSlow = -0.75;
	public static final double intakePivotGravityFallFast = -0.75;
	public static final double intakeCalibrationSpeed = -0.1;
	public static final int intakeDownEncoderThreshold = 550;
	public static final int intakeUpEncoderThreshold = 0;
	public static final double intakePivotEncoderWindow = 20;
	// Lift Settings
	public static final int liftMotorCanID = 9;
	public static final int liftEncoderDIOPortA = 1;
	public static final int liftEncoderDIOPortB = 2;
	public static final double liftEncoderTicsPerInch = (3.25 * Math.PI) / 1440;
	public static final double liftMaxHight = 45;
	public static final int liftMaxTics = 2203;//(int) (liftMaxHight / liftEncoderTicsPerInch);
	public static final double liftSwitchHeight = 10;
	public static final int liftTicsToSwitch = (int) (liftSwitchHeight / liftEncoderTicsPerInch);
	public static final double liftScaleHeight = 40;
	public static final int liftTicsToScale = (int) (liftScaleHeight / liftEncoderTicsPerInch);
	public static final int liftDownHieghtLimit = 700;//1000;
	public static final int liftPivotLimit = 100;
	public static final double liftCalibrationSpeed = -0.1;
	public static final double liftUpSpeed = -1;
	public static final double liftDownSpeed = 0.5;
	public static final double liftTeleUpSpeed = -1;
	public static final double liftTeleDownSpeed = 0.5;
	public static final int liftWindow = 5;
	public static final double liftJoyScalar = 0.5;
	// Climb Settings
	public static final int climbMotor1CanID = 10;
	public static final int climbMotor2CanID = 11;
	public static final double climbMotorSpeed = 0.8;
	public static final int climbServoPWMPort = 0;
	public static final int climbEngaged = 180;
	public static final int climbDisengaged = 0;

	// Profile Settings
	public static final String profileTestLogName = "logs\\motionProfileTestResults";
	public static final String profileLogName = "//media//sda1//motionProfile";
	public static final double profileAdditionLoopNumber = 50;
	public static final String profileLogFileExtension = ".txt";
	public static final double profileDriveAccelration = 20; // inches/sec/sec
	public static final double profileDriveKp = 0.125;
	public static final double profileDriveKi = 0.001;
	public static final double profileDriveKd = 0.0009;
	public static final double profileTurnKp = 0.0;
	public static final double profileTurnKi = 0.0;
	public static final double profileTurnKd = 0.0;
	public static final double profileAnglarAccelration = 10;
	public static final double profileInitVelocity = 0.0;
	public static final double profileMovementThreshold = 0.5;

	// Interface Settings
	public static final int joyDrivePort = 0;
	public static final int joyDriveInvertDriveButton = 2;
	public static final double joyDriveDeadband = 0.05;
	public static final int joyArtPort = 1;
	public static final int joyArtIntakeInButton = 3;
	public static final int joyArtReverseIntakeButton = 5;
	public static final int joyArtLiftUpButton = 7;
	public static final int joyArtLiftDownButton = 9;
	public static final int joyArtSetLiftToZeroButton = 12;
	public static final int joyArtSetLiftToSwitchButton = 10;
	public static final int joyArtSetLiftToScaleButton = 8;
	public static final int joyArtClimbButton = 11;
	public static final int joyArtIntakePivotUpButton = 6;
	public static final int joyArtIntakePivotDownButton = 4;

	// Auto Settings
	public static enum autoAction {
		autoSwitch, autoScale, autoSafeScale, autoLine, nothing
	}

	public static enum autoPosition {
		left, center, right, error
	}

	public static final double autoEjectCubeTime = 500;
	public static final double autoCruiseSpeed = 135;
	public static final double autoLineDistance = 144;
	public static final double autoTurnSpeed = 0.12; // 0.18 for carpet, 0.12 for tile
	public static final double autoPivotToBumper = 36; // Not really, added a buffer
	public static final double autoAngleToSwitchLeft = -40;
	public static final double autoAngleToSwitchRight = 34.5;
	public static final double autoDiagToSwitchLeft = 88.75;
	public static final double autoDiagToSwitchRight = 82.5;
	public static final double autoWallToScaleDist =  201.235;// 12;
	public static final double autoTurnToFaceWall = 90;
	public static final double autoDrivePastSwitch = 205.5;// 144;
	public static final double autoTurnToFaceScale = 0;
	public static final double autoDriveToScale = 88.765;// 12;
	public static final double autoTurnToPlaceInScale = -90;
	public static final double autoStraightToScale = 297.5; // 240;
	public static final double autoStraightTurnToScale = 90;
	public static final double autoForwardToBeCloseToScale = 14.57;
	public static final boolean autoAllyToScale = false;
	public static final boolean autoAllyNotToScale = true;

}
