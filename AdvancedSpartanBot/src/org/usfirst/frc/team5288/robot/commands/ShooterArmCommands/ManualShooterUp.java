package org.usfirst.frc.team5288.robot.commands.ShooterArmCommands;

import org.usfirst.frc.team5288.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualShooterUp extends Command {

    public ManualShooterUp() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ShooterY);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    Robot.ShooterY.angleShooter(1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ShooterY.angleShooter(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
