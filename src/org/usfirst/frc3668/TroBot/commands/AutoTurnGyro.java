package org.usfirst.frc3668.TroBot.commands;

import org.usfirst.frc3668.TroBot.Robot;
import org.usfirst.frc3668.TroBot.RobotMath;
import org.usfirst.frc3668.TroBot.Settings;
import org.usfirst.frc3668.TroBot.Settings.chassisTurnDirection;

import edu.wpi.first.wpilibj.command.Command;

public class AutoTurnGyro extends Command {
	double _initLeftThrottle;
	double _initRightThrottle;
	double _desiredHeading;
	chassisTurnDirection _turnDir;
	boolean _isFinished = false;
	double _rightSignum;
	double _leftSignum;

	public AutoTurnGyro(double speed, double desiredHeading, chassisTurnDirection turnDir) {
		requires(Robot.subChassis);
		_desiredHeading = desiredHeading;
		_turnDir = turnDir;
		_initRightThrottle = speed;
		_initLeftThrottle = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (_turnDir == chassisTurnDirection.turnLeft) {
			_initLeftThrottle = _initLeftThrottle * -1;

		}
		if (_turnDir == chassisTurnDirection.turnRight) {
			_initRightThrottle = _initRightThrottle * -1;
		}
		_rightSignum = Math.signum(_initRightThrottle);
		_leftSignum = Math.signum(_initLeftThrottle);
		System.err.println(String.format("Init Right Throttle: %1$.3f \t Init Left Throttle: %2$.3f", _initRightThrottle, _initLeftThrottle));
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double currHeading = Robot.subChassis.getNormaliziedNavxAngle();
		double headingDelta = RobotMath.headingDelta(currHeading, _desiredHeading);
		double scaledHeading = headingDelta * Settings.chassisTurnKp;
		double _rightThrottle = _initRightThrottle - (scaledHeading * _rightSignum);
		double leftThrottle = _initLeftThrottle - (scaledHeading * _leftSignum);

		Robot.subChassis.DriveMan(leftThrottle, _rightThrottle);
		System.err.println(String.format(
				"Current Heading: %1$.3f \t Heading Delta: %2$.3f \t Scaled Heading: %3$.3f \t Right Throttle: %4$.3f \t Left Throttle: %5$.3f",
				currHeading, headingDelta, scaledHeading, _rightThrottle, leftThrottle));

		if (Robot.subChassis.gyroInTol(currHeading, _desiredHeading, Settings.chassisGyroTol)) {
			_isFinished = true;
			System.out.println("Finished!");
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return _isFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.subChassis.Drive(0, 0);
		System.err.println("Finished at " + Robot.subChassis.getNormaliziedNavxAngle());
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
