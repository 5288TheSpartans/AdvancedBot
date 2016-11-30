package org.usfirst.frc.team5288.robot.commands;

import org.usfirst.frc.team5288.robot.Robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.*;
import edu.wpi.first.wpilibj.tables.*;
/**
 *
 */
public class AimToShoot extends Command {

	public AimToShoot() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);


		requires(Robot.DrivetrainSubsystem);
		requires(Robot.ShooterY);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}
	// Called repeatedly when this Command is scheduled to run

	protected void execute() {


		/*	if(blobX < MINERRORX){
    	    	Robot.DrivetrainSubsystem.setPowerRight(-0.3);
    	    	Robot.DrivetrainSubsystem.setPowerLeft(0.3);
    		}
    		else if(blobX > MAXERRORX){
    			Robot.DrivetrainSubsystem.setPowerRight(0.3);
    			Robot.DrivetrainSubsystem.setPowerLeft(-0.3);
    		}
    		else{
    			Robot.DrivetrainSubsystem.setPowerRight(0);
    	    	Robot.DrivetrainSubsystem.setPowerLeft(0);
    		}

    		if(blobX < MINERRORY){
    	    	Robot.ShooterY.angleShooter(1);
    		}
    		else if(blobX > MAXERRORY){
    			Robot.ShooterY.angleShooter(-1);
    		}
    		else{
    			Robot.ShooterY.angleShooter(0);
    		}


		 */
	}
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.ShooterY.angleShooter(0);
		Robot.DrivetrainSubsystem.setPowerRight(0);
		Robot.DrivetrainSubsystem.setPowerLeft(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.ShooterY.angleShooter(0);
		Robot.DrivetrainSubsystem.setPowerRight(0);
		Robot.DrivetrainSubsystem.setPowerLeft(0);

	}
}
