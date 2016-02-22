
package org.usfirst.frc.team5288.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5288.robot.commands.DriveCommands.DriveToPosition;
import org.usfirst.frc.team5288.robot.commands.ShooterCommands.ShootBall;
import org.usfirst.frc.team5288.robot.subsystems.*;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static DrivetrainSubsystem DrivetrainSubsystem; 
	public static ShooterSubsystem ShooterSubsystem;
	public static ShooterY ShooterY;
 	public static double matchTime = 0;
	Command manualDrive;
    Command autonomousCommand;
    Command lowBarDoubleBallAuto;
    Command portcullisDoubleBallAuto;
    Command DoubleBallAuto;
    Command ManualShooterUp;
    Command ManualShooterDown;
    SendableChooser chooser;
	public static OI oi;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	public void robotInit() {
    	DrivetrainSubsystem = new DrivetrainSubsystem(); 
    	ShooterSubsystem = new ShooterSubsystem();
    	ShooterY = new ShooterY();
    	oi = new OI();
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", new ShootBall());
        chooser.addObject("Shoot Ball", new ShootBall());
        //Creates all of the smartdashboard widgets and displays all variables
        SmartDashboard.putData("Auto mode", chooser);
        SmartDashboard.putNumber("Left Drive Speed",DrivetrainSubsystem.getLeftDrive());
        SmartDashboard.putNumber("Right Drive Speed",DrivetrainSubsystem.getRightDrive());
        SmartDashboard.putNumber("Match Time", DriverStation.getInstance().getMatchTime());//creates matchtime widget
        SmartDashboard.putNumber("Throttle",DrivetrainSubsystem.throttle);
        SmartDashboard.putNumber("Left Encoder ", DrivetrainSubsystem.leftEncTicks());
        SmartDashboard.putNumber("Right Encoder Memes", DrivetrainSubsystem.rightEncTicks());
        SmartDashboard.putNumber("Feet/s", 0);

        //SmartDashboard.
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        
		 String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new DriveToPosition();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ShootBall();
			break;
		} 
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        //if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	Scheduler.getInstance().run();
    	SmartDashboard.putData("Auto mode", chooser);
        SmartDashboard.putNumber("Left Drive Speed",DrivetrainSubsystem.getLeftDrive());
        SmartDashboard.putNumber("Right Drive Speed",DrivetrainSubsystem.getRightDrive());
        SmartDashboard.putNumber("Match Time", DriverStation.getInstance().getMatchTime());//creates matchtime widget
        SmartDashboard.putNumber("Throttle",DrivetrainSubsystem.throttle);
        SmartDashboard.putNumber("Left Encoder Memes", DrivetrainSubsystem.leftEncTicks());
        SmartDashboard.putNumber("Right Encoder Memes", DrivetrainSubsystem.rightEncTicks());
        Robot.DrivetrainSubsystem.updateSpeed(); 
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
 
        LiveWindow.run();
    }
}
