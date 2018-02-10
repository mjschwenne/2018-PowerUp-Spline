package org.usfirst.frc3668.TroBot.commands;

import org.usfirst.frc3668.TroBot.Robot;
import org.usfirst.frc3668.TroBot.Settings;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CmdIntakePivot extends Command {
	double _initEncoder;
	boolean _isFinished = false;
	boolean _goingForward;

	public CmdIntakePivot() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.subIntake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (Robot.subIntake.getReversePivotLimitSwitch()) {
			_goingForward = true;
		} else {
			_goingForward = false;
		}
	}

	protected void execute() {
		double throttle = 0;
		double currentTics = Robot.subIntake.getLiftEncoder();
		if (_goingForward == true && currentTics < Settings.intakePivotGravityFallThreshold) {
			throttle = Settings.intakePivotGravityFallFast;
		} else if (_goingForward == false && currentTics > Settings.intakePivotGravityFallThreshold) {
			throttle = -Settings.intakePivotGravityFallFast;
		} else if (_goingForward == true && currentTics > Settings.intakePivotGravityFallThreshold) {
			throttle = Settings.intakePivotGravityFallSlow;
		} else if (_goingForward == false && currentTics < Settings.intakePivotGravityFallThreshold) {
			throttle = -Settings.intakePivotGravityFallSlow;
		}
		System.err.println("currentTics: " + currentTics + " Rev Limit: " + Robot.subIntake.getReversePivotLimitSwitch() + " Frw Limit: " + Robot.subIntake.getForwardPivotLimitSwitch());
		Robot.subIntake.liftIntake(throttle);
		if(_goingForward == true && Robot.subIntake.getForwardPivotLimitSwitch()) {
			_isFinished = true;
			
		}
		if(_goingForward == false && Robot.subIntake.getReversePivotLimitSwitch()) {
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
		Robot.subIntake.liftIntake(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
