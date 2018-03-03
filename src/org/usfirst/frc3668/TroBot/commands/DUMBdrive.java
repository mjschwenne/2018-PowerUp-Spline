package org.usfirst.frc3668.TroBot.commands;

import org.usfirst.frc3668.TroBot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DUMBdrive extends Command {

	double _dist = 0;
	boolean _isFinished = false;
	
    public DUMBdrive(double dist) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.subChassis);
        _dist = dist;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.subChassis.resetBothEncoders();
    	_isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.subChassis.Drive(0.5, 0);
    	if(Robot.subChassis.getEncoderAvgDistInch() > _dist) {
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
