package org.usfirst.frc3668.TroBot.subSystems;

import org.usfirst.frc3668.TroBot.Robot;
import org.usfirst.frc3668.TroBot.RobotMap;
import org.usfirst.frc3668.TroBot.Settings;
import org.usfirst.frc3668.TroBot.commands.TeleopClimb;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

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
		RobotMap.climb1.set(ControlMode.PercentOutput, Settings.climbMotorSpeed);
		RobotMap.climb2.set(ControlMode.PercentOutput, Settings.climbMotorSpeed);
	}

	public void joyClimb(Joystick joy) {
		double throttle = Math.abs(joy.getY());
		setClimbMotors(throttle);
		//RobotMap.liftMotor.setNeutralMode(NeutralMode.Coast);
		Robot.subLift.lift(throttle);
	}
	public void setClimbMotors(double throttle) {
		RobotMap.climb1.set(ControlMode.PercentOutput, throttle);
		RobotMap.climb2.set(ControlMode.PercentOutput, throttle);
	}

	public void stopClimb() {
		setClimbMotors(0);
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
