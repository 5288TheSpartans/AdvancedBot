package org.usfirst.frc.team5288.robot.commands.DriveCommands;

import org.usfirst.frc.team5288.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
/**
 *
 */
public class DriveStraight extends Command {
    public double leftDistance = 0;
    public double rightDistance = 0;
    public double avgDistance = 0;
    public final double speed = 0.4;
    public final double gain = 0.1;
    double error;
    public DriveStraight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.DrivetrainSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	leftDistance = 0;
    	rightDistance = 0;
    	Robot.DrivetrainSubsystem.resetEncoders();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	leftDistance = Robot.DrivetrainSubsystem.leftEncTicks();
    	rightDistance = Robot.DrivetrainSubsystem.rightEncTicks();
    	error = gain* (rightDistance - leftDistance);
    	if (error > 0.3)
    	{
    		error = 0.3;
    	}
    	if (error < -0.3)
    	{
    		error = -0.3;
    	}
    	Robot.DrivetrainSubsystem.setPowerLeft(speed+ error);
    	Robot.DrivetrainSubsystem.setPowerRight(speed - error);


    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.DrivetrainSubsystem.setPowerLeft(0);
    	Robot.DrivetrainSubsystem.setPowerRight(0);
    
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
