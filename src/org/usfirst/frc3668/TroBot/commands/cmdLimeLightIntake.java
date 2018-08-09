package org.usfirst.frc3668.TroBot.commands;

import org.usfirst.frc3668.TroBot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class cmdLimeLightIntake extends Command {

    public cmdLimeLightIntake() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.subIntake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.err.println("hi");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    public double limeLightNormalize(double heading) {
		// takes the full turns out of heading
		// gives us values from 0 to 180 for the right side of the robot
		// and values from 0 to -179 degrees for the left side of the robot

		double degrees = heading % 360;

		if (degrees > 90) {
			degrees = degrees - 360;
		}

		if (degrees < 0) {
			degrees = degrees + 360;
		}

		return degrees;
	}
}
