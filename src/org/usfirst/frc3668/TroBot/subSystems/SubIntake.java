package org.usfirst.frc3668.TroBot.subSystems;
 
import org.usfirst.frc3668.TroBot.RobotMap;
import org.usfirst.frc3668.TroBot.Settings;
import org.usfirst.frc3668.TroBot.commands.TeleopIntakeLift;

import edu.wpi.first.wpilibj.command.Subsystem;

public class SubIntake extends Subsystem {
	public double getLiftEncoder() {
		double retVal = 0;
		double intake1Tics = RobotMap.intakeLift1Encoder.get();
		double intake2Tics = RobotMap.intakeLift2Encoder.get();
		if (intake1Tics < Settings.chassisEncoderDeadValueThreshold) {
			retVal = intake2Tics;
		} else if (intake2Tics < Settings.chassisEncoderDeadValueThreshold) {
			retVal = intake1Tics;
		} else {
			retVal = (intake1Tics + intake2Tics) / 2;
		}
		return retVal;
	}
	public void resetEncoders( ) {
		RobotMap.intakeLift1Encoder.reset();
		RobotMap.intakeLift2Encoder.reset();
	}
	public boolean getForwardLiftLimitSwitch() {
		return !RobotMap.intakeLift1.getSensorCollection().isFwdLimitSwitchClosed();
	}
	public boolean getReverseLiftLimitSwitch() {
		return !RobotMap.intakeLift1.getSensorCollection().isRevLimitSwitchClosed();
	}
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
		//setDefaultCommand(new TeleopIntakeLift());
	}

	@Override
	public void periodic() {
		// Put code here to be run every loop
	}
}