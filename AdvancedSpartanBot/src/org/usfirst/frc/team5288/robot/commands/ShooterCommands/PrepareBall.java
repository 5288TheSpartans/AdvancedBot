package org.usfirst.frc.team5288.robot.commands.ShooterCommands;
import org.usfirst.frc.team5288.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PrepareBall extends Command {
double startTime = 0;
double difTime = 0;
    public PrepareBall() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ShooterSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = DriverStation.getInstance().getMatchTime();
    	difTime = startTime;
    	Robot.ShooterSubsystem.powerShooterL(-0.7);
    	Robot.ShooterSubsystem.powerShooterR(0.7);
    	Robot.ShooterSubsystem.servoDown();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	difTime = DriverStation.getInstance().getMatchTime() - startTime;
    	if(difTime >= 2)
    	{
    		end();
    	}
    	else{
    	end();
    	}
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(difTime >= 2)
    	{
    		return true;
    	}
    	else{
    		return false;
    	}
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
