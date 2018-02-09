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
		if (Robot.subIntake.getReverseLiftLimitSwitch()) {
			_goingForward = true;
		} else {
			_goingForward = false;
		}
	}

	protected void execute() {
		double throttle = 0;
		double currentTics = Robot.subIntake.getLiftEncoder();
		if (_goingForward == true && currentTics < Settings.intakeLiftGravityFallThreshold) {
			throttle = Settings.intakeLiftGravityFallFast;
		} else if (_goingForward == false && currentTics > Settings.intakeLiftGravityFallThreshold) {
			throttle = -Settings.intakeLiftGravityFallFast;
		} else if (_goingForward == true && currentTics > Settings.intakeLiftGravityFallThreshold) {
			throttle = Settings.intakeLiftGravityFallSlow;
		} else if (_goingForward == false && currentTics < Settings.intakeLiftGravityFallThreshold) {
			throttle = -Settings.intakeLiftGravityFallSlow;
		}
		Robot.subIntake.liftIntake(throttle);
		if(_goingForward == true && Robot.subIntake.getForwardLiftLimitSwitch()) {
			_isFinished = true;
			
		}
		if(_goingForward == false && Robot.subIntake.getReverseLiftLimitSwitch()) {
			_isFinished = true;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return _isFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.subIntake.liftIntake(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
