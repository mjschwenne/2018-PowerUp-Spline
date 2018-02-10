package org.usfirst.frc3668.TroBot.commands;

import org.usfirst.frc3668.TroBot.Robot;
import org.usfirst.frc3668.TroBot.Settings;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleopJoyLift extends Command {

	public TeleopJoyLift() {
		 requires(Robot.subLift);
	}

	protected void initialize() {
	}

	protected void execute() {
		double joyY = Robot.oi.joyArt.getY();
		if(joyY < 0) {
			Robot.subLift.lift(joyY);
		} else {
			Robot.subLift.lift(joyY * Settings.liftJoyScalar);
		}
	}
	

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.subLift.lift(0);
	}

	protected void interrupted() {
		end();
	}
	
}
