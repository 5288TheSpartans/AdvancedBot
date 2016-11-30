package org.usfirst.frc.team5288.robot.commands;

import org.usfirst.frc.team5288.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDown extends Command {
	private double startTime = 0;
	private double difTime = 0;
    public AutoDown() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ShooterY);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = System.currentTimeMillis();
    Robot.ShooterY.angleShooter(-1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	difTime = System.currentTimeMillis() - startTime;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if( difTime >= 5000)
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
