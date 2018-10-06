package org.usfirst.frc3668.TroBot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3668.TroBot.Robot;
import org.usfirst.frc3668.TroBot.Settings;

/**
 *
 */
public class carmaData extends Command {

    public carmaData() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.subPivot);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.err.println("valid: " + Robot.tv.getDouble(0)+("\t Horizontal: " + Robot.tx.getDouble(0)+"\t Vertical: " + Robot.ty.getDouble(0)+" " + Robot.ta.getDouble(0)+"rotation: " + Robot.ts.getDouble(0)+"latency: " + Robot.tl.getDouble(0)));
    	System.err.println(String.format("Valid: %1$.3f \t Horizontal: %2$.3f \t Vertical: %3$.3f \tTargetArea: %4$.3f \t Rotation: %5$.3f \t latency: %6$.3f \t Distance: %7$.3f", Robot.tv.getDouble(0), Robot.tx.getDouble(0),Robot.ty.getDouble(0),Robot.ta.getDouble(0),Robot.ts.getDouble(0),Robot.tl.getDouble(0), distance(Robot.ta.getDouble(0))));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }
    double distance(double area) {
    	return Settings.limelightDistEXPC * (Math.pow(Math.E , (Settings.limelightDistEXPS * area)));
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
