package org.usfirst.frc3668.TroBot.subSystems;

import org.usfirst.frc3668.TroBot.Robot;
import org.usfirst.frc3668.TroBot.RobotMap;
import org.usfirst.frc3668.TroBot.Settings;
import org.usfirst.frc3668.TroBot.commands.TeleopClimb;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SubClimb extends Subsystem {
	
	public void disengageClimber( ) {
		RobotMap.climbServo.setAngle(Settings.climbDisengaged);
	}
	public void engageClimber() {
		RobotMap.climbServo.setAngle(Settings.climbEngaged);
	}
	public void climbFixedSpeed() {
		RobotMap.climb1.set(Settings.climbMotorSpeed);
		RobotMap.climb2.set(Settings.climbMotorSpeed);
	}

	public void joyClimb(Joystick joy) {
		double throttle = Math.abs(joy.getY());
		RobotMap.climbController.set(throttle);
		Robot.subLift.lift(throttle);
	}

	public void stopClimb() {
		RobotMap.climbController.set(0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new TeleopClimb());
		
	}

	@Override
	public void periodic() {
		// Put code here to be run every loop

	}
}
