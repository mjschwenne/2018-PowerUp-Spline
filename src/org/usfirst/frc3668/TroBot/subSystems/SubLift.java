package org.usfirst.frc3668.TroBot.subSystems;

import org.usfirst.frc3668.TroBot.RobotMap;
import org.usfirst.frc3668.TroBot.commands.AutoLift;

import com.ctre.phoenix.CANifier;

import edu.wpi.first.wpilibj.command.Subsystem;

public class SubLift extends Subsystem {

	public void lift(double throttle) {
		RobotMap.liftMotor.set(throttle);
	}
	
	public boolean readLiftLimits() {
		//boolean liftLimit = RobotMap.liftCANifier.getGeneralInput(CANifier.GeneralPin.LIMF);
		boolean liftLimit = !RobotMap.liftMotor.getSensorCollection().isFwdLimitSwitchClosed();
		return liftLimit;
	}
	
    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new AutoLift());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }
}