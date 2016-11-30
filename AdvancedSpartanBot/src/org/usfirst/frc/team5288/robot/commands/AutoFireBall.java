package org.usfirst.frc.team5288.robot.commands;

import org.usfirst.frc.team5288.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5288.robot.*;
public class AutoFireBall extends Command {
	double startTime = 0;
	double difTime = 0;
    public AutoFireBall() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.ShooterSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    	startTime = System.currentTimeMillis();
    	difTime = startTime;
    	Robot.ShooterSubsystem.powerShooterL(-0.7);
    	Robot.ShooterSubsystem.powerShooterR(0.7);
    	Robot.ShooterSubsystem.servoUp();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	difTime =System.currentTimeMillis() - startTime;
    	Robot.ShooterSubsystem.powerShooterL(-0.7);
    	Robot.ShooterSubsystem.powerShooterR(0.7);
    	Robot.ShooterSubsystem.servoUp();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(difTime >= 4000)
    	{
    		return true;
    	}
    	else{
    	return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ShooterSubsystem.Stop();
    	Robot.ShooterSubsystem.servoDown();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.ShooterSubsystem.Stop();
    	Robot.ShooterSubsystem.servoDown();


    	end();
    }
}

