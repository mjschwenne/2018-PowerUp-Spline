package org.usfirst.frc3668.TroBot.commands;

import org.usfirst.frc3668.TroBot.Robot;
import org.usfirst.frc3668.TroBot.Settings;
import org.usfirst.frc3668.TroBot.Settings.autoPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoGroupScale extends CommandGroup {

	public AutoGroupScale(autoPosition position, boolean safe) {
		autoPosition scaleSide;
		boolean goodData = true;
		double angleMod = 1;

		if (Robot.gameData.charAt(1) == 'R') {
			scaleSide = autoPosition.right;
		} else if (Robot.gameData.charAt(1) == 'L') {
			scaleSide = autoPosition.left;
		} else {
			scaleSide = autoPosition.error;
			SmartDashboard.putString("error", "GAME DATA ERROR");
			goodData = false;
		}
		
		if (position == autoPosition.right) {
			angleMod = -1;
		}

		if (scaleSide == position && scaleSide != autoPosition.error) {

			CommandGroup ApproachScale = new CommandGroup();
			ApproachScale.addSequential(new AutoDriveProfileGyro(angleMod * Settings.autoStraightTurnToScale,
					Settings.autoCruiseSpeed, Settings.autoForwardToBeCloseToScale));
			ApproachScale.addParallel(new CmdLift(Settings.liftStandardSpeed, Settings.liftTicsToScale));

			addSequential(new AutoDriveProfileGyro(0, Settings.autoCruiseSpeed, Settings.autoStraightToScale));
			addParallel(new CmdCalibrateLift());
			addParallel(new CmdCalibrateIntakePivot());
			addSequential(new AutoTurnGyro(Settings.autoTurnSpeed, angleMod * Settings.autoStraightTurnToScale));
			addSequential(ApproachScale);
			addSequential(new AutoIntake(Settings.autoEjectCubeTime));
		}

		if (scaleSide != position && scaleSide != autoPosition.error) {

			CommandGroup ApproachScale = new CommandGroup();
			ApproachScale.addSequential(
					new AutoTurnGyro(Settings.autoTurnSpeed, angleMod * Settings.autoTurnToPlaceInScale));
			ApproachScale.addParallel(new CmdLift(Settings.liftStandardSpeed, Settings.liftTicsToScale));

			addSequential(new AutoDriveProfileGyro(0, Settings.autoCruiseSpeed, Settings.autoWallToScaleDist));
			addParallel(new CmdCalibrateLift());
			addParallel(new CmdCalibrateIntakePivot());
			addSequential(new AutoTurnGyro(Settings.autoTurnSpeed, angleMod * Settings.autoTurnToFaceWall));
			addSequential(new AutoDriveProfileGyro(angleMod * Settings.autoTurnToFaceWall, Settings.autoCruiseSpeed,
					Settings.autoDrivePastSwitch));
			if (safe) {
				addSequential(new AutoTurnGyro(Settings.autoTurnSpeed, angleMod * Settings.autoTurnToFaceScale));
				addSequential(new AutoDriveProfileGyro(Settings.autoTurnToFaceScale, Settings.autoCruiseSpeed,
						Settings.autoDriveToScale));
				addSequential(ApproachScale);
				addSequential(new AutoIntake(Settings.autoEjectCubeTime));
			}
		}
		if (!goodData) {
			addSequential(new AutoDriveProfileGyro(0, Settings.autoCruiseSpeed, Settings.autoLineDistance));
		}
	}
}