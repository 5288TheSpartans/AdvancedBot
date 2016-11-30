
package org.usfirst.frc.team5288.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5288.robot.commands.*;
import org.usfirst.frc.team5288.robot.commands.DriveCommands.DriveToPosition;
import org.usfirst.frc.team5288.robot.commands.ShooterCommands.ShootBall;
import org.usfirst.frc.team5288.robot.subsystems.*;
import org.usfirst.frc.team5288.robot.commands.AutoGroups.*;
import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static double ftps = 0;
	public static double Lftps = 0;
	public static double Rftps = 0;
	public static DrivetrainSubsystem DrivetrainSubsystem; 
	public static ShooterSubsystem ShooterSubsystem;
	public static ShooterY ShooterY;
	private static double frameCounter = 0;
 	public static double matchTime = 0;
	int session;
	Image frame;
    Command autonomousCommand;
;
    SendableChooser autoChooser;
	public static OI oi;
	private static double distanceMemes = 0;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	public void robotInit() {

//CAMERA
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

		// the camera name (ex "cam0") can be found through the roborio web interface
		session = NIVision.IMAQdxOpenCamera("cam1",
				NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		NIVision.IMAQdxConfigureGrab(session); 
  	  NIVision.IMAQdxStartAcquisition(session);

//CAMERA
    	DrivetrainSubsystem = new DrivetrainSubsystem(); 
    	ShooterSubsystem = new ShooterSubsystem();
    	ShooterY = new ShooterY();
    	oi = new OI();
       autoChooser = new SendableChooser();
        autoChooser.addDefault("Shoot Ball", new ShootBallAuto());
        autoChooser.addObject("DriveBoth", null);
      //  autoChooser.addObject("DriveBoth", new DriveBothAdd());
        //Creates all of the smartdashboard widgets and displays all variables
        SmartDashboard.putData("Auto mode", autoChooser);
        SmartDashboard.putNumber("Left Drive Speed",DrivetrainSubsystem.getLeftDrive());
        SmartDashboard.putNumber("Right Drive Speed",DrivetrainSubsystem.getRightDrive());
        SmartDashboard.putNumber("Match Time", DriverStation.getInstance().getMatchTime());//creates matchtime widget
        SmartDashboard.putNumber("Throttle",DrivetrainSubsystem.throttle);
        SmartDashboard.putNumber("Total Rotation",DrivetrainSubsystem.getGyroAngle());

        SmartDashboard.putNumber("Left Encoder Memes", DrivetrainSubsystem.leftEncTicks());
        SmartDashboard.putNumber("Right Encoder Memes", DrivetrainSubsystem.rightEncTicks());
        SmartDashboard.putNumber("Feet/s", 0);
        SmartDashboard.putNumber("Auto Distance",12);
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
        SmartDashboard.putData("Auto mode", autoChooser);
        SmartDashboard.putNumber("Left Drive Speed",DrivetrainSubsystem.getLeftDrive());
        SmartDashboard.putNumber("Right Drive Speed",DrivetrainSubsystem.getRightDrive());
        SmartDashboard.putNumber("Match Time", DriverStation.getInstance().getMatchTime());//creates matchtime widget
        SmartDashboard.putNumber("Throttle",DrivetrainSubsystem.throttle);
        SmartDashboard.putNumber("Total Rotation",DrivetrainSubsystem.getGyroAngle());
      	Lftps = 0;
    	Rftps = 0;
        SmartDashboard.putNumber("Left Encoder Memes", DrivetrainSubsystem.leftEncTicks());
        SmartDashboard.putNumber("Right Encoder Memes", DrivetrainSubsystem.rightEncTicks());
        SmartDashboard.putNumber("Feet/s", 0);
        SmartDashboard.putNumber("Auto Distance",12);
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
    	distanceMemes = SmartDashboard.getNumber("Auto Distance");
		 //String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
    //	autonomousCommand = (Command) autoChooser.getSelected();
      autonomousCommand = new ShootBallAuto();
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
        // (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	frameCounter++;
    	if(frameCounter >=2){
    	updateCamera();
    	frameCounter = 0;
    	}
    	Scheduler.getInstance().run();
    	SmartDashboard.putData("Auto mode", autoChooser);
        SmartDashboard.putNumber("Left Drive Speed",DrivetrainSubsystem.getLeftDrive());
        SmartDashboard.putNumber("Right Drive Speed",DrivetrainSubsystem.getRightDrive());
        SmartDashboard.putNumber("Match Time", DriverStation.getInstance().getMatchTime());//creates matchtime widget
        SmartDashboard.putNumber("Throttle",DrivetrainSubsystem.throttle);
        SmartDashboard.putNumber("Left Encoder Memes", DrivetrainSubsystem.leftEncTicks());
        SmartDashboard.putNumber("Right Encoder Memes", DrivetrainSubsystem.rightEncTicks());
        SmartDashboard.putNumber("Total Rotation",DrivetrainSubsystem.getGyroAngle());
        //Robot.DrivetrainSubsystem.updateSpeed(); 
    }
    public void updateCamera()
    {


          /**
           * grab an image, draw the circle, and provide it for the camera server
           * which will in turn send it to the dashboard.
           */
          NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);
              NIVision.IMAQdxGrab(session, frame, 1);
              CameraServer.getInstance().setImage(frame);

              /** robot code here! **/
         // NIVision.IMAQdxStopAcquisition(session);
          

    }
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    public static void setDistance(int distance){
    	distanceMemes = distance;
    }
    public static double getDistance(){
    	return distanceMemes;
    }
}
