package org.usfirst.frc3668.TroBot.subSystems;

import org.usfirst.frc3668.TroBot.RobotMap;
import org.usfirst.frc3668.TroBot.commands.TeleopIntakeLift;

import edu.wpi.first.wpilibj.command.Subsystem;

public class SubIntake extends Subsystem {

	public void intakeIn(double throttle) {
		RobotMap.rightIntakeWheel.set(throttle);
		RobotMap.leftIntakeWheel.set(-throttle);

	}

	public void intakeOut(double throttle) {
		RobotMap.rightIntakeWheel.set(-throttle);
		RobotMap.leftIntakeWheel.set(throttle);
	}
	
	public void liftIntake(double throttle) {
		RobotMap.intakeLift1.set(throttle);
		
		
	}
	public void stopIntake() {
		RobotMap.rightIntakeWheel.set(0);
		RobotMap.leftIntakeWheel.set(0);
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new TeleopIntakeLift());
	}

	@Override
	public void periodic() {
		// Put code here to be run every loop
	}
}