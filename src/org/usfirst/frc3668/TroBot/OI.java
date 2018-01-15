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
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

    public Joystick joyDrive = new Joystick(Settings.joyDrivePort);
    public Joystick joyArt = new Joystick(Settings.joyArtPort);
    
    public Button invertDrive = new JoystickButton(joyDrive, Settings.joyDriveInvertDriveButton);
    
    public Button intakeIn = new JoystickButton(joyArt, Settings.joyArtIntakeInButton);
    public Button intakeReverse = new JoystickButton(joyArt, Settings.joyArtReverseIntakeButton);
    
    public Button liftToZero = new JoystickButton(joyArt, Settings.joyArtSetLiftToZeroButton);
    public Button liftToSwitch = new JoystickButton(joyArt, Settings.joyArtSetLiftToSwitchButton);
    public Button liftToScale = new JoystickButton(joyArt, Settings.joyArtSetLiftToScaleButton);
    
    public Button enableClimb = new JoystickButton(joyArt, Settings.joyArtClimbButton);

    public OI() {
    	invertDrive.whenPressed(new TeleopInvertDrive());
    	
    	intakeIn.whileHeld(new TeleopIntakeIn());
    	intakeReverse.whileHeld(new TeleopIntakeOut());
    	
    	//liftToZero.whenPressed(new TeleopLift(0));
    	//liftToSwitch.whenPressed(new TeleopLift(Settings.liftSwitchHeight));
    	//liftToScale.whenPressed(new TeleopLift(Settings.liftScaleHeight));
    	
    	enableClimb.whileHeld(new TeleopClimb());
    }

    public Joystick getjoyDrive() {
        return joyDrive;
    }

    public Joystick getjoyArt() {
        return joyArt;
    }
}

