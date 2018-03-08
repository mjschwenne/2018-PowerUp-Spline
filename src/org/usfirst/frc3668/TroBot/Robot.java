package org.usfirst.frc3668.TroBot;

import org.usfirst.frc3668.TroBot.Settings.autoAction;
import org.usfirst.frc3668.TroBot.Settings.autoPosition;
import org.usfirst.frc3668.TroBot.Settings.pivotStatus;
import org.usfirst.frc3668.TroBot.commands.AutoGroupScale;
import org.usfirst.frc3668.TroBot.commands.AutoGroupSwitch;
import org.usfirst.frc3668.TroBot.commands.DUMBdrive;
import org.usfirst.frc3668.TroBot.subSystems.SubChassis;
import org.usfirst.frc3668.TroBot.subSystems.SubClimb;
import org.usfirst.frc3668.TroBot.subSystems.SubIntake;
import org.usfirst.frc3668.TroBot.subSystems.SubLift;
import org.usfirst.frc3668.TroBot.subSystems.SubPivot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

	static Command autonomousCommand;
	static SendableChooser<autoAction> autoActionChooser;
	static SendableChooser<autoPosition> autoPositionChooser;
	static SmartDashboard smartDashboard;

	public static final SubChassis subChassis = new SubChassis();
	public static final SubIntake subIntake = new SubIntake();
	public static final SubPivot subPivot = new SubPivot();
	public static final SubLift subLift = new SubLift();
	public static final SubClimb subClimb = new SubClimb();

	public static final OI oi = new OI();

	public static boolean isDriveInverted = true;
	public static pivotStatus pivotStatus = Settings.pivotStatus.isUnknown;
	public static String gameData;
	public static UsbCamera cameraOne;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.init();

		subChassis.resetNavx();
		subChassis.resetBothEncoders();
		subClimb.disengageClimber();
		subLift.resetEncoder();
		
		cameraOne = CameraServer.getInstance().startAutomaticCapture("Cube Camera", 0);
		cameraOne.setExposureAuto();
		cameraOne.setBrightness(Settings.visionImageBrightness);
		cameraOne.setFPS(Settings.visionCameraFPS);
		cameraOne.setResolution(Settings.visionImageWidthPixels, Settings.visionImageHeightPixels);
		
		smartDashboard = new SmartDashboard();

		autoPositionChooser = new SendableChooser<autoPosition>();
		autoPositionChooser.addObject("Left", autoPosition.left);
		autoPositionChooser.addObject("Right", autoPosition.right);
		autoPositionChooser.addObject("Center", autoPosition.center);
		smartDashboard.putData("Position Chooser", autoPositionChooser);

		autoActionChooser = new SendableChooser<autoAction>();
		autoActionChooser.addObject("Switch", autoAction.autoSwitch);
		autoActionChooser.addObject("Scale", autoAction.autoScale);
		autoActionChooser.addObject("Safe Scale", autoAction.autoSafeScale);
		autoActionChooser.addDefault("Line", autoAction.autoLine);
		autoActionChooser.addObject("Nothing", autoAction.nothing);
		smartDashboard.putData("Action Chooser", autoActionChooser);
	}

	/**
	 * This function is called when the disabled button is hit. You can use it to
	 * reset subsystems before shutting down.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		gameData = DriverStation.getInstance().getGameSpecificMessage().toUpperCase();
		SmartDashboard.putString("Game Data", "Game Data: " + gameData);
		autoAction selectedAction = (autoAction) autoActionChooser.getSelected();
		autoPosition selectedPosition = (autoPosition) autoPositionChooser.getSelected();

		switch (selectedPosition) {
		case center:
			if(selectedAction == autoAction.autoSwitch) {
				autonomousCommand = new AutoGroupSwitch();	
			}
			else {
				autonomousCommand = null;
			}
			break;
		default:
			if(selectedAction == autoAction.autoScale) {
				autonomousCommand = new AutoGroupScale(selectedPosition, Settings.autoAllyNotToScale);
			}
			else if(selectedAction == autoAction.autoSafeScale) {
				autonomousCommand = new AutoGroupScale(selectedPosition, Settings.autoAllyToScale);
			}
			else if(selectedAction == autoAction.autoLine) {
				autonomousCommand = new DUMBdrive(144);//new AutoDriveProfileGyro(0, Settings.autoCruiseSpeed, Settings.autoLineDistance);
			}
			else {
				autonomousCommand = null;
			}
			break;
		
		
		}

		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
}
