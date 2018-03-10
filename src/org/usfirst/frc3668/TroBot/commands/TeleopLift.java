package org.usfirst.frc3668.TroBot.commands;

import org.usfirst.frc3668.TroBot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleopLift extends Command {

	double _throttle = 0;
	
    public TeleopLift(double throttle) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.subLift);
        _throttle = throttle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.err.println("Manual lifting Encoder: " + Robot.subLift.getEncoderTics() + " throttle: " + _throttle + " Pivot Status: " + Robot.pivotStatus);
    	Robot.subLift.lift(_throttle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.subLift.lift(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//System.err.println("Interrupted man lift");
    	end();
    }
}
