package org.usfirst.frc3668.TroBot.commands;

import org.usfirst.frc3668.TroBot.Robot;
import org.usfirst.frc3668.TroBot.Settings;

import edu.wpi.first.wpilibj.command.Command;


public class CmdCalibrateIntakePivot extends Command {
	boolean _isFinished = false;
    public CmdCalibrateIntakePivot() {
    	requires(Robot.subIntake);
    }

    
    protected void initialize() {
    	if(Robot.subIntake.getReversePivotLimitSwitch()) {
    		_isFinished = true;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.subIntake.pivotIntake(Settings.intakeCalibrationSpeed);
    	if(Robot.subIntake.getReversePivotLimitSwitch()) {
    		_isFinished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return _isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.subIntake.pivotIntake(0);
    	Robot.subIntake.resetEncoders();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
