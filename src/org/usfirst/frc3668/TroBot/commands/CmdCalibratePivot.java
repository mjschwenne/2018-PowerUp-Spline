package org.usfirst.frc3668.TroBot.commands;

import org.usfirst.frc3668.TroBot.Robot;
import org.usfirst.frc3668.TroBot.Settings;

import edu.wpi.first.wpilibj.command.Command;


public class CmdCalibratePivot extends Command {
	boolean _isFinished = false;
    public CmdCalibratePivot() {
    	requires(Robot.subIntake);
    }

    
    protected void initialize() {
    	if(Robot.subPivot.getReverseLimitSwitch()) {
    		_isFinished = true;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.subPivot.pivot(Settings.pivotCalibrationSpeed);
    	if(Robot.subPivot.getReverseLimitSwitch()) {
    		_isFinished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return _isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.subPivot.pivot(0);
    	Robot.subPivot.resetEncoders();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
