package org.usfirst.frc3668.TroBot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3668.TroBot.commands.*;
import org.usfirst.frc3668.TroBot.subSystems.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    public Joystick joyDrive = new Joystick(Settings.joyDrivePort);
    public Joystick joyArt = new Joystick(Settings.joyArtPort);
    
    public Button invertDrive = new JoystickButton(joyDrive, Settings.joyDriveInvertDriveButton);
    
    public Button intakeIn = new JoystickButton(joyArt, Settings.joyArtIntakeInButton);
    public Button intakeReverse = new JoystickButton(joyArt, Settings.joyArtReverseIntakeButton);
    
    public Button liftToZero = new JoystickButton(joyArt, Settings.joyArtSetLiftToZeroButton);
    public Button liftToSwitch = new JoystickButton(joyArt, Settings.joyArtSetLiftToSwitchButton);
    public Button liftToScale = new JoystickButton(joyArt, Settings.joyArtSetLiftToScaleButton);
    
    public Button enableClimb = new JoystickButton(joyArt, Settings.joyArtClimbButton);
    
    public Button intakePivot = new JoystickButton(joyArt, Settings.joyArtIntakePivotButton);

    public OI() {
    	invertDrive.whenPressed(new TeleopInvertDrive());
    	
    	intakeIn.whileHeld(new TeleopIntakeIn());
    	intakeReverse.whileHeld(new TeleopIntakeOut());
    	intakePivot.whenPressed(new CmdIntakePivot());
    	
    	liftToZero.whenPressed(new CmdLift(Settings.liftStandardSpeed, 0));
    	liftToSwitch.whenPressed(new CmdLift(Settings.liftStandardSpeed, Settings.liftTicsToSwitch));
    	liftToScale.whenPressed(new CmdLift(Settings.liftStandardSpeed, Settings.liftTicsToScale));
    	
    	enableClimb.whileHeld(new TeleopClimb());
    }

    public Joystick getjoyDrive() {
        return joyDrive;
    }

    public Joystick getjoyArt() {
        return joyArt;
    }
}