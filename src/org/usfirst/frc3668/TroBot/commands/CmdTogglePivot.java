package org.usfirst.frc3668.TroBot.commands;

import org.usfirst.frc3668.TroBot.Robot;
import org.usfirst.frc3668.TroBot.Settings;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CmdTogglePivot extends Command {

	boolean _isFinished = false;
	
    public CmdTogglePivot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	_isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.pivotStatus == Settings.pivotStatus.isDown) {
    		Robot.pivotStatus = Settings.pivotStatus.isUp;
    	} else if (Robot.pivotStatus == Settings.pivotStatus.isUp) {
    		Robot.pivotStatus = Settings.pivotStatus.isDown;
    	}
    	_isFinished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return _isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
