package org.usfirst.frc3668.TroBot.commands;

import org.usfirst.frc3668.TroBot.Robot;
import org.usfirst.frc3668.TroBot.RobotMap;
import org.usfirst.frc3668.TroBot.Settings;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopIntake extends Command {

	double _leftThrottle;
	double _rightThrottle;
	boolean _diffThrottle = false;

	private boolean _isFinished = false;

	public TeleopIntake(double throttle) {
		requires(Robot.subIntake);
		_leftThrottle = throttle;
		_rightThrottle = throttle;
	}

	public TeleopIntake(double leftThrottle, double rightThrottle) {
		requires(Robot.subIntake);
		_leftThrottle = leftThrottle;
		_rightThrottle = rightThrottle;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		if (_leftThrottle != _rightThrottle) {
			_diffThrottle = true;
		}
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		//System.err.println("Limit: " + Robot.subIntake.getLimit() + " Diff Throttle " + _diffThrottle);
		
		if (_diffThrottle == true) {
			Robot.subIntake.intakeMan(_leftThrottle, _rightThrottle);
		} else {
			Robot.subIntake.intake(_rightThrottle);
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return _isFinished;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.subIntake.stopIntake();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
