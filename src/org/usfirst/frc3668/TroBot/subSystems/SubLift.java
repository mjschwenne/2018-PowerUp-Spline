package org.usfirst.frc3668.TroBot.subSystems;

import org.usfirst.frc3668.TroBot.RobotMap;
import org.usfirst.frc3668.TroBot.commands.TeleopJoyLift;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class SubLift extends Subsystem {
	public void setLiftNeutralMode(NeutralMode mode) {
		RobotMap.liftMotor.setNeutralMode(mode);
	}
	public void lift(double throttle) {
		RobotMap.liftMotor.set(throttle);
	}
	public void resetEncoder() {
		RobotMap.liftEncoder.reset();
	}
	public double getEncoderTics() {
		return RobotMap.liftEncoder.get();
	}
	public boolean getLiftForwardLimit() {
		
		return !RobotMap.liftMotor.getSensorCollection().isFwdLimitSwitchClosed();
	
	}
	public boolean getLiftReverseLimit() {
		return !RobotMap.liftMotor.getSensorCollection().isRevLimitSwitchClosed();
	}
    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new TeleopJoyLift());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }
}