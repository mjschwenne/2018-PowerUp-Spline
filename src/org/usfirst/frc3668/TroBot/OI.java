package org.usfirst.frc3668.TroBot;

import org.usfirst.frc3668.TroBot.commands.CmdLift;
import org.usfirst.frc3668.TroBot.commands.CmdPivotDown;
import org.usfirst.frc3668.TroBot.commands.CmdPivotUp;
import org.usfirst.frc3668.TroBot.commands.CmdTogglePivot;
import org.usfirst.frc3668.TroBot.commands.TeleopClimb;
import org.usfirst.frc3668.TroBot.commands.TeleopIntake;
import org.usfirst.frc3668.TroBot.commands.TeleopInvertDrive;
import org.usfirst.frc3668.TroBot.commands.TeleopLift;
import org.usfirst.frc3668.TroBot.commands.cmdLimeLightIntake;

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
    public Button ledOff = new JoystickButton(joyArt,Settings.joyArtLimelightLEDOff );
    public Button ledOn = new JoystickButton(joyArt,Settings.joyArtLimelightLEDOn );
    public Button ledBlinking = new JoystickButton(joyArt,Settings.joyArrtLimeLightLEDBlinking );
     
    public Button intakeIn = new JoystickButton(joyDrive, Settings.joyDriveIntakeInButton);
    public Button intakeReverseSlow = new JoystickButton(joyDrive, Settings.joyDriveIntakeOutSLowButton);
    public Button intakeReverseFast = new JoystickButton(joyDrive, Settings.joyDriveIntakeOutFastButton);
    public Button intakeOneWheelRight = new JoystickButton(joyDrive, Settings.joyDriveOneWheelRightIntake);
    public Button intakeOneWheelLeft = new JoystickButton(joyDrive, Settings.joyDriveOneWheelLeftIntake);
    
    public Button liftToZero = new JoystickButton(joyArt, Settings.joyArtSetLiftToZeroButton);
    public Button liftToSwitch = new JoystickButton(joyArt, Settings.joyArtSetLiftToSwitchButton);
    public Button liftToScale = new JoystickButton(joyArt, Settings.joyArtSetLiftToScaleButton);
    
    public Button enableClimb = new JoystickButton(joyArt, Settings.joyArtClimbButton);
    
    public Button downPivot = new JoystickButton(joyArt, Settings.joyArtPivotDownButton);
    public Button upPivot = new JoystickButton(joyArt, Settings.joyArtPivotUpButton);
    public Button togglePivotStatus = new JoystickButton(joyArt, Settings.joyArtTogglePivotStatus);
    
    public Button liftUp = new JoystickButton(joyArt, Settings.joyArtLiftUpButton);
    public Button liftDown = new JoystickButton(joyArt, Settings.joyArtLiftDownButton);
    public Button limeLightIntake = new JoystickButton(joyArt, Settings.joyArtlimeLightIntake);
    
    public OI() {
    	invertDrive.whenPressed(new TeleopInvertDrive());
    	
    	intakeIn.whileHeld(new TeleopIntake(Settings.intakeIn));
    	intakeReverseSlow.whileHeld(new TeleopIntake(Settings.intakeOutSlow));
    	intakeReverseFast.whileHeld(new TeleopIntake(Settings.intakeOutFast));
    	intakeOneWheelRight.whileHeld(new TeleopIntake(Settings.intakeInOffWheel, Settings.intakeIn));
    	intakeOneWheelLeft.whileHeld(new TeleopIntake(Settings.intakeIn, Settings.intakeInOffWheel));
    	
    	upPivot.whenPressed(new CmdPivotDown());
    	downPivot.whenPressed(new CmdPivotUp());
    	togglePivotStatus.whenPressed(new CmdTogglePivot());
    	
    	liftToZero.whenPressed(new CmdLift(Settings.liftUpSpeed, 0));
    	liftToSwitch.whenPressed(new CmdLift(Settings.liftUpSpeed, Settings.liftTicsToSwitch));
    	liftToScale.whenPressed(new CmdLift(Settings.liftUpSpeed, Settings.liftTicsToScale));
    	
    	liftUp.whileHeld(new TeleopLift(Settings.liftTeleUpSpeed));
    	liftDown.whileHeld(new TeleopLift(Settings.liftTeleDownSpeed));
    	
    	//limeLightIntake.whileHeld(new cmdLimeLightIntake());
    	
    	enableClimb.whileHeld(new TeleopClimb());
    }

    public Joystick getjoyDrive() {
        return joyDrive;
    }

    public Joystick getjoyArt() {
        return joyArt;
    }
}