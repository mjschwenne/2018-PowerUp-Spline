package org.usfirst.frc3668.TroBot.commands;

import org.usfirst.frc3668.TroBot.Robot;
import org.usfirst.frc3668.TroBot.RobotMath;
import org.usfirst.frc3668.TroBot.Settings;
import org.usfirst.frc3668.TroBot.Settings.chassisTurnDirection;

import edu.wpi.first.wpilibj.command.Command;

public class AutoTurnGyro extends Command {
	double _initLeftThrottle;
	double _initThrottle;
	double _desiredHeading;
	chassisTurnDirection _turnDir;
	boolean _isFinished = false;
	double _rightSignum;
	double _leftSignum;
	
	public AutoTurnGyro(double throttle, double desiredHeading) {
		requires(Robot.subChassis);
		_desiredHeading = desiredHeading;
		_initThrottle = throttle;
		
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double currHeading = Robot.subChassis.getNormaliziedNavxAngle();
		double headingDelta = RobotMath.headingDelta(currHeading, _desiredHeading);
		double scaledHeading = headingDelta * Settings.chassisTurnKp;
		double scaledHeadingSignum = Math.signum(scaledHeading);
		double throttle = (_initThrottle + Math.abs(scaledHeading)) * scaledHeadingSignum;
		
		Robot.subChassis.Drive(0, throttle);
		
		System.err.println(String.format(
						"Current Heading: %1$.3f \t Heading Delta: %2$.3f \t Scaled Heading: %3$.3f \t Throttle: %4$.3f",
						currHeading, headingDelta, scaledHeading, throttle));
		
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
