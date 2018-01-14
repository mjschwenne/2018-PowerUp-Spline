package org.usfirst.frc3668.TroBot.commands;

import org.usfirst.frc3668.TroBot.RobotMath;

import edu.wpi.first.wpilibj.command.Command;

public class CmdWait extends Command {

	private boolean _isFinished = false;
	private double _waitTime;
	private double _initTime;
	
    public CmdWait(double waitTime) {
    	_waitTime = waitTime;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	_initTime = RobotMath.getTime();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	if((RobotMath.getTime() - _initTime) > _waitTime) {
    		_isFinished = true;
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
