package org.usfirst.frc3668.TroBot.subSystems;

import org.usfirst.frc3668.TroBot.RobotMap;
import org.usfirst.frc3668.TroBot.commands.AutoLift;

import edu.wpi.first.wpilibj.command.Subsystem;

public class SubLift extends Subsystem {

	public void lift(double throttle) {
		RobotMap.liftMotor.set(throttle);
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