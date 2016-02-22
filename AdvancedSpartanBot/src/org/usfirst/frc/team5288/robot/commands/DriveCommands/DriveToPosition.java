package org.usfirst.frc.team5288.robot.commands.DriveCommands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5288.robot.Robot;
import edu.wpi.first.wpilibj.DriverStation;
/**
 *
 */
public class DriveToPosition extends Command {
	double currentTime;
	double startTime;
    public DriveToPosition() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.DrivetrainSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
		startTime = DriverStation.getInstance().getMatchTime();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentTime = DriverStation.getInstance().getMatchTime();
     if(currentTime >= (startTime +0) && currentTime <= (startTime +1))
     {
    	 Robot.DrivetrainSubsystem.turnInPlace(0.4, "L");
     }
     else if (currentTime>=  (startTime+1.5) && currentTime <= (startTime+5) )
     {
    	 Robot.DrivetrainSubsystem.setPowerLeft(1);
    	 Robot.DrivetrainSubsystem.setPowerRight(1);
     }
     else if (currentTime>=(startTime+5) && currentTime <= (startTime+6) )
     {
    	 Robot.DrivetrainSubsystem.setPowerLeft(0);
    	 Robot.DrivetrainSubsystem.setPowerRight(0);
     }
     else if (currentTime >= (startTime+6) && currentTime <= (startTime+7))
     {
    	 Robot.DrivetrainSubsystem.turnInPlace(1, "R");
     }

     else if (currentTime >= (startTime+7) && currentTime <= (startTime+8))
     {
    	 Robot.DrivetrainSubsystem.turnInPlace(0, "0");
     }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(currentTime >= (startTime+8))
        {
        	return true;
        }
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
