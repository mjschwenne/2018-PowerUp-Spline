package org.usfirst.frc3668.TroBot.commands;
import org.usfirst.frc3668.TroBot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopInvertDrive extends Command {

	private boolean _isFinished = false;
	
    public TeleopInvertDrive() {
    	
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	Robot.isDriveInverted = !Robot.isDriveInverted;
    	_isFinished = true;
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
