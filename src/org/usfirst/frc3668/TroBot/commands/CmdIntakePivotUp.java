package org.usfirst.frc3668.TroBot.commands;

import org.usfirst.frc3668.TroBot.Robot;
import org.usfirst.frc3668.TroBot.Settings;

import edu.wpi.first.wpilibj.command.Command;

public class CmdIntakePivotUp extends Command {
	double _initEncoder;
	boolean _isFinished = false;
	boolean _goingDown = false;

	public CmdIntakePivotUp() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.subIntake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.err.println("Robot.isIntakeDown: " + Robot.isIntakeDown);
	}

	protected void execute() {
		double throttle = 0;
		double currentTics = Robot.subIntake.getLiftEncoder();
		if (_goingDown == true && currentTics < Settings.intakePivotGravityFallThreshold) {
			throttle = -Settings.intakePivotGravityFallFast;
		} else if (_goingDown == false && currentTics > Settings.intakePivotGravityFallThreshold) {
			throttle = Settings.intakePivotGravityFallFast;
		} else if (_goingDown == true && currentTics > Settings.intakePivotGravityFallThreshold) {
			throttle = -Settings.intakePivotGravityFallSlow;
		} else if (_goingDown == false && currentTics < Settings.intakePivotGravityFallThreshold) {
			throttle = Settings.intakePivotGravityFallSlow;
		}

		System.err.println("currentTics: " + currentTics + " Rev Limit: " + Robot.subIntake.getReversePivotLimitSwitch()
				+ " Frw Limit: " + Robot.subIntake.getForwardPivotLimitSwitch() + " Throttle: " + throttle);
		Robot.subIntake.liftIntake(throttle);

//		if (_goingDown == true && Robot.subIntake.getForwardPivotLimitSwitch()) {
//			_isFinished = true;
//		}
		if (_goingDown == false && Robot.subIntake.getReversePivotLimitSwitch()) {
			_isFinished = true;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return _isFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
		System.err.println("Done Pivot");
		Robot.isIntakeDown = !Robot.isIntakeDown;
		Robot.subIntake.liftIntake(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}