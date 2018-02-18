package org.usfirst.frc3668.TroBot.commands;

import org.usfirst.frc3668.TroBot.Robot;
import org.usfirst.frc3668.TroBot.Settings;
import org.usfirst.frc3668.TroBot.Settings.autoPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoGroupSwitch extends CommandGroup {

	public AutoGroupSwitch() {
		double switchAngle = 0;
		double switchDistance = 0;
		double approachSwitch = 0;
		boolean goodData = true;
		if (Robot.gameData.charAt(0) == 'L') {
			switchAngle = Settings.autoAngleToSwitchLeft;
			switchDistance = Settings.autoDiagToSwitchLeft;
			approachSwitch = Settings.autoApproachSwitchLeft;
		} else if (Robot.gameData.charAt(0) == 'R') {
			switchAngle = Settings.autoAngleToSwitchRight;
			switchDistance = Settings.autoDiagToSwitchRight;
			approachSwitch = Settings.autoApproachSwitchRight;
		} else {
			goodData = false;
			switchAngle = Settings.autoAngleToSwitchLeft;
			switchDistance = Settings.autoDiagToSwitchLeft;
		}

		addSequential(new AutoDriveProfileGyro(0, Settings.autoCruiseSpeed, Settings.autoPivotToBumper));
		addParallel(new CmdCalibrateLift());
		addParallel(new CmdCalibratePivot());
		addSequential(new AutoTurnGyro(Settings.autoTurnSpeed, switchAngle));
		addSequential(new AutoDriveProfileGyro(switchAngle, Settings.autoCruiseSpeed, switchDistance));
		addSequential(new AutoTurnGyro(Settings.autoTurnSpeed, 0));
		addSequential(new AutoDriveProfileGyro(0, Settings.autoCruiseSpeed, approachSwitch));
		if (goodData == true) {
			addSequential(new AutoIntake(Settings.intakeAutoSwitchOut, Settings.autoEjectCubeTime));
			addSequential(new CmdPivotUp());
		}
	}
}
