package org.usfirst.frc3668.TroBot.commands;

import org.usfirst.frc3668.TroBot.Robot;
import org.usfirst.frc3668.TroBot.RobotMap;
import org.usfirst.frc3668.TroBot.Settings;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CmdIntakePivotDown extends Command {
	double _initEncoder;
	boolean _isFinished = false;
	boolean _goingDown = true;

	public CmdIntakePivotDown() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.subIntake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.isPivotMoving = true;
		System.err.println("Robot.isIntakeDown: " + Robot.isIntakeDown + " Rev Limit: "
				+ Robot.subIntake.getReversePivotLimitSwitch() + "  Frw Limit: " + Robot.subLift.getLiftForwardLimit());
	}

	protected void execute() {
		double throttle = 0;
		double currentTics = Robot.subIntake.getLiftEncoder();
		if (currentTics > Settings.intakePivotGravityFallThreshold) {
			throttle = -Settings.intakePivotGravityFallFast;
		} else if (currentTics < Settings.intakePivotGravityFallThreshold) {
			throttle = -Settings.intakePivotGravityFallSlow;
		}

		System.err.println("currentTics: " + currentTics + " lift Tics: " + Robot.subLift.getEncoderTics() + " Throttle: " + throttle + " Robot.isPovitMoving: "
				+ Robot.isPivotMoving + " Robot.isIntakeDown: " + Robot.isIntakeDown);
		Robot.subIntake.pivotIntake(throttle);

		if (currentTics > (Settings.intakeDownEncoderThreshold - Settings.intakePivotEncoderWindow)
				/*&& currentTics < (Settings.intakeDownEncoderThreshold + Settings.intakePivotEncoderWindow)*/) {
			Robot.isIntakeDown = true;
			Robot.isPivotMoving = false;
		} else if (currentTics > (Settings.intakeUpEncoderThreshold - Settings.intakePivotEncoderWindow)
				&& currentTics < (Settings.intakeUpEncoderThreshold + Settings.intakePivotEncoderWindow)) {
			Robot.isIntakeDown = false;
			Robot.isPivotMoving = false;
		}
		
		if (_goingDown == true && Robot.subIntake.getForwardPivotLimitSwitch()) {
			//_isFinished = true;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return _isFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
		System.err.println("Done Pivot" + " Rev Limit: "
				+ Robot.subIntake.getReversePivotLimitSwitch() + "  Frw Limit: " + Robot.subLift.getLiftForwardLimit());
		//Robot.isIntakeDown = true;
		Robot.subIntake.pivotIntake(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}