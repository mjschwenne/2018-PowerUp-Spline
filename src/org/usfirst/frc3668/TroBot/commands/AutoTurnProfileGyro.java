package org.usfirst.frc3668.TroBot.commands;

import org.usfirst.frc3668.TroBot.PID;
import org.usfirst.frc3668.TroBot.Robot;
import org.usfirst.frc3668.TroBot.RobotMath;
import org.usfirst.frc3668.TroBot.Settings;
import org.usfirst.frc3668.TroBot.Settings.chassisTurnDirection;
import org.usfirst.frc3668.TroBot.motionProfile.MotionProfiler;

import edu.wpi.first.wpilibj.command.Command;

public class AutoTurnProfileGyro extends Command {

	private boolean _isFinished = false;
	private double _desiredAngle;
	private double _anglarCruiseSpeed;
	private double _anglarDelta;
	private double _initHeading;
	private double _startTime;
	private double _abortTime;
	private MotionProfiler mp;
	private PID pid = new PID(Settings.profileTurnKp, Settings.profileTurnKi, Settings.profileTurnKd);
	private chassisTurnDirection turnDir;
	
    public AutoTurnProfileGyro(double anglarCuriseSpeed, double desiredAngle, chassisTurnDirection dir) {
        requires(Robot.subChassis);
        _anglarCruiseSpeed = anglarCuriseSpeed;
        _desiredAngle = desiredAngle;
        turnDir = dir;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	_initHeading = Robot.subChassis.getNormalizedGyroAngle();
    	_startTime = RobotMath.getTime();
    	_anglarDelta = RobotMath.calcAnglarDelta(_initHeading, _desiredAngle);
    	mp = new MotionProfiler(_anglarDelta, Settings.profileInitVelocity, _anglarCruiseSpeed, Settings.profileAnglarAccelration);
    	_abortTime = _anglarDelta / _anglarCruiseSpeed;
    	System.err.println(String.format("INIT! Anglar Delta: %1$.3f \t Abort Time: %2$.3f", _anglarDelta, _abortTime));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currGyro = Robot.subChassis.getNormalizedGyroAngle();
    	double deltaTime = RobotMath.getTime() - _startTime;
    	double currVelocity = mp.getProfileCurrVelocity(deltaTime);
    	double pidVal = pid.calcPID(_desiredAngle, currGyro);
    	double throttlePos = (currVelocity / Settings.chassisMaxDregeesPreSecond) + pidVal;
    	double leftThrottle = throttlePos;
    	double rightThrottle = throttlePos;
    	
    	if(turnDir == Settings.chassisTurnDirection.turnLeft) {
    		leftThrottle = leftThrottle * -1;
    	} else {
    		rightThrottle = rightThrottle * -1;
    	}
    	
    	Robot.subChassis.DriveMan(leftThrottle, rightThrottle);
    	String msg = String.format("Delta Time: %1$.3f Curr Gyro: %2$.3f \t PID value: %3$.3f \t Throttle: %4$.3f \t Left Throttle: %5$.3f", deltaTime, currGyro, pidVal, throttlePos, leftThrottle, rightThrottle);
    	System.err.println(msg);
    	
    	if(Robot.subChassis.gyroInTol(currGyro, _desiredAngle, Settings.chassisGyroTol)) {
    		_isFinished = true;
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return _isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.subChassis.Drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
