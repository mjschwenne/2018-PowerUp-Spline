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
		if (scaleSide != position) {
			
			CommandGroup ApproachScale = new CommandGroup();
			ApproachScale.addSequential(new AutoTurnGyro(Settings.autoTurnSpeed, angleMod * Settings.autoTurnToPlaceInScale));
			ApproachScale.addParallel(new CmdLift(Settings.liftStandardSpeed, Settings.liftTicsToScale));
			
			addSequential(new AutoDriveProfileGyro(0, Settings.autoCruiseSpeed, Settings.autoWallToScaleDist));
			addParallel(new CmdCalibrateLift());
			addParallel(new CmdCalibrateIntakePivot());
			addSequential(new AutoTurnGyro(Settings.autoTurnSpeed, angleMod * Settings.autoTurnToFaceWall));
			addSequential(new AutoDriveProfileGyro(angleMod * Settings.autoTurnToFaceWall, Settings.autoCruiseSpeed,
					Settings.autoDrivePastSwitch));
			addSequential(new AutoTurnGyro(Settings.autoTurnSpeed, angleMod * Settings.autoTurnToFaceScale));
			addSequential(new AutoDriveProfileGyro(Settings.autoTurnToFaceScale, Settings.autoCruiseSpeed,
					Settings.autoDriveToScale));
			addSequential(ApproachScale);
			addSequential(new AutoIntake(Settings.autoEjectCubeTime));
		}
	}
}
