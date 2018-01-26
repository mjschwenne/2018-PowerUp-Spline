package org.usfirst.frc3668.TroBot.commands;

import org.usfirst.frc3668.TroBot.Robot;
import org.usfirst.frc3668.TroBot.Settings;
import org.usfirst.frc3668.TroBot.Settings.autoPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoGroupScale extends CommandGroup {

	public AutoGroupScale(autoPosition position) {
		autoPosition scaleSide;
		double angleMod = 1;
		if (Robot.gameData.charAt(1) == 'R') {
			scaleSide = autoPosition.right;
		} else {
			scaleSide = autoPosition.left;
		}
		if (position == autoPosition.right) {
			angleMod = -1;
		}
		if (scaleSide == position) {
			addSequential(new AutoDriveProfileGyro(0, Settings.autoCruiseSpeed, Settings.autoStraightToScale));
			addSequential(new AutoTurnGyro(Settings.autoTurnSpeed, angleMod * Settings.autoStraightTurnToScale));
			addSequential(new AutoDriveProfileGyro(angleMod * Settings.autoStraightTurnToScale,
					Settings.autoCruiseSpeed, Settings.autoForwardToBeCloseToScale));
		}
		if (scaleSide != position) {
			addSequential(new AutoDriveProfileGyro(0, Settings.autoCruiseSpeed, Settings.autoWallToScaleDist));
			addSequential(new AutoTurnGyro(Settings.autoTurnSpeed, angleMod * Settings.autoTurnToFaceWall));
			addSequential(new AutoDriveProfileGyro(angleMod * Settings.autoTurnToFaceWall, Settings.autoCruiseSpeed,
					Settings.autoDrivePastSwitch));
			addSequential(new AutoTurnGyro(Settings.autoTurnSpeed, angleMod * Settings.autoTurnToFaceScale));
			addSequential(new AutoDriveProfileGyro(Settings.autoTurnToFaceScale, Settings.autoCruiseSpeed,
					Settings.autoDriveToScale));
			addSequential(new AutoTurnGyro(Settings.autoTurnSpeed, angleMod * Settings.autoTurnToPlaceInScale));
		}
	}
}
