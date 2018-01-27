package org.usfirst.frc3668.TroBot.commands;
import org.usfirst.frc3668.TroBot.Robot;
import org.usfirst.frc3668.TroBot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class AutoLift extends Command {
	boolean _isFinished = false;
	double _throttle;
	double _targetTicks;
	double _deltaSignum;
    public AutoLift(double throttle, double targetTicks) {
    	_throttle = throttle;
    	_targetTicks = targetTicks;
        requires(Robot.subLift);
    }

    @Override
    protected void initialize() {
    //	double initTicks = Robot.subLift
    	//_deltaSignum = Math.signum(d)
    }

 
    @Override
    protected void execute() {
    //	Robot.subLift.lift(Robot.oi.joyArt.getY());
    	System.err.println("Limit: " + Robot.subLift.readLiftLimits());
    }

    
    @Override
    protected boolean isFinished() {
        return false;
    }

  
    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
