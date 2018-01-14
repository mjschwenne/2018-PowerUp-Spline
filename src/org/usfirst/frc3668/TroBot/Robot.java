package org.usfirst.frc3668.TroBot;

import org.usfirst.frc3668.TroBot.subSystems.SubChassis;
import org.usfirst.frc3668.TroBot.subSystems.SubClimb;
import org.usfirst.frc3668.TroBot.subSystems.SubIntake;
import org.usfirst.frc3668.TroBot.subSystems.SubLift;
import org.usfirst.frc3668.TroBot.subSystems.SubTray;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class Robot extends TimedRobot {

    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();

    public static final SubChassis subChassis = new SubChassis();
    public static final SubIntake subIntake = new SubIntake();
    public static final SubLift subLift = new SubLift();
    public static final SubClimb subClimb = new SubClimb();
    public static final SubTray subTray = new SubTray();
    
    public static final OI oi = new OI();
    
	public static boolean isDriveInverted = false;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        RobotMap.init();
        subChassis.initializeGyro();
        
        //chooser.addDefault("Autonomous Command", new AutonomousCommand());
        //SmartDashboard.putData("Auto mode", chooser);
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
        //autonomousCommand = chooser.getSelected();
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        //Scheduler.getInstance().run();
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
