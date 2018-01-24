package org.usfirst.frc3668.TroBot;

import org.usfirst.frc3668.TroBot.Settings.autoAction;
import org.usfirst.frc3668.TroBot.Settings.autoPosition;
import org.usfirst.frc3668.TroBot.commands.AutoDriveProfileGyro;
import org.usfirst.frc3668.TroBot.commands.AutoGroupSwitch;
import org.usfirst.frc3668.TroBot.commands.AutoGroupScale;
import org.usfirst.frc3668.TroBot.commands.AutoTurnGyro;
import org.usfirst.frc3668.TroBot.subSystems.SubChassis;
import org.usfirst.frc3668.TroBot.subSystems.SubClimb;
import org.usfirst.frc3668.TroBot.subSystems.SubIntake;
import org.usfirst.frc3668.TroBot.subSystems.SubLift;
import org.usfirst.frc3668.TroBot.subSystems.SubTray;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

    Command autonomousCommand;
    SendableChooser<autoAction> autoActionChooser;
    SendableChooser<autoPosition> autoPositionChooser;
    
    public static final SubChassis subChassis = new SubChassis();
    public static final SubIntake subIntake = new SubIntake();
    public static final SubLift subLift = new SubLift();
    public static final SubClimb subClimb = new SubClimb();
    public static final SubTray subTray = new SubTray();
    
    public static final OI oi = new OI();
    
	public static boolean isDriveInverted = false;
	public static String gameData;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        RobotMap.init();
        subChassis.resetNavx();
        
        autoPositionChooser = new SendableChooser<autoPosition>();
        autoPositionChooser.addObject("Left", autoPosition.leftPortal);
        autoPositionChooser.addObject("Right", autoPosition.rightPortal);
        autoPositionChooser.addObject("Center", autoPosition.center);
        SmartDashboard.putData("Position Chooser", autoPositionChooser);
        
        autoActionChooser = new SendableChooser<autoAction>();
        autoActionChooser.addObject("Switch", autoAction.autoSwitch);
        autoActionChooser.addDefault("Scale", autoAction.autoScale);
        autoActionChooser.addObject("Line", autoAction.autoLine);
        autoActionChooser.addObject("Nothing", autoAction.nothing);
        SmartDashboard.putData("Action Chooser", autoActionChooser);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit(){

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
    	gameData = DriverStation.getInstance().getGameSpecificMessage();
    	//if (gameData.charAt(0)== 'L')
        autoAction selectedAction = (autoAction) autoActionChooser.getSelected();
        autoPosition selectedPosition = (autoPosition) autoPositionChooser.getSelected();
        switch(selectedAction) {
        case autoSwitch:
        	autonomousCommand = new AutoGroupSwitch();
        	break;
        case autoScale:
        	autonomousCommand = new AutoGroupScale(selectedPosition);
        	break;
        case autoLine:
        	System.err.println("Settings up the command");
        	autonomousCommand = new AutoDriveProfileGyro(0, Settings.autoCruiseSpeed, Settings.autoLineDistance );
        	break;
        case nothing:
        	autonomousCommand = null;
        }
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
}
