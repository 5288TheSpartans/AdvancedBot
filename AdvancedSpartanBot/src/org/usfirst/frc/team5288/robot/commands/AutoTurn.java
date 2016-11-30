package org.usfirst.frc.team5288.robot.commands;

import org.usfirst.frc.team5288.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoTurn extends Command {
	double startPosition = 0;
	double difPosition = 0;
	public double endPosition = 0;
    public AutoTurn(int totaldegrees) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	endPosition =	totaldegrees ;
    	requires(Robot.DrivetrainSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startPosition = Robot.DrivetrainSubsystem.getGyroAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	difPosition =Robot.DrivetrainSubsystem.getGyroAngle() - startPosition;
    	
    	Robot.DrivetrainSubsystem.turnInPlace(0.3, "R");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Math.abs(difPosition) >= Math.abs(endPosition + startPosition)){
    	return true;
    	}
    	else {
        return false;
    	}
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
