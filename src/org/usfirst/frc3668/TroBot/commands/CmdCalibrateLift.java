package org.usfirst.frc3668.TroBot.commands;

import org.usfirst.frc3668.TroBot.Robot;
import org.usfirst.frc3668.TroBot.Settings;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CmdCalibrateLift extends Command {
	boolean _isFinished = false;
    public CmdCalibrateLift() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.subLift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.subLift.getLiftReverseLimit()) {
    		_isFinished = true;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.subLift.lift(Settings.liftCalibrationSpeed);
    	if(Robot.subLift.getLiftReverseLimit()) {
    		_isFinished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return _isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.subLift.resetEncoder();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
