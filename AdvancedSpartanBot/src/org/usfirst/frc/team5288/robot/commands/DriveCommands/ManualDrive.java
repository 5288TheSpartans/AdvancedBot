package org.usfirst.frc.team5288.robot.commands.DriveCommands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5288.robot.Robot;
/**
 *
 */
public class ManualDrive extends Command {
	final double SafeZone = 0.15;
	public double throttle = 1;
    public ManualDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires();
    requires(Robot.DrivetrainSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    Robot.DrivetrainSubsystem.setPowerLeft(0);// Stops the robot immediately
    Robot.DrivetrainSubsystem.setPowerRight(0);//Stops
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*The code here is organised so that the execute Checks if a directional
    	 * button is being held down, and if it is, it turns the robot in that
    	 * direction. If not, the manual drive style function of the code where 
    	 * raw inputs from both of the joysticks are used to control their
    	 * respective sides of the robot's drivetrain, outputting power equal to
    	 * the raw input multiplied by the throttle multiplier, allowing for 
    	 * extremely precise movements.
    	 * 
    	 */
    	if(Robot.oi.btnL4.get())//If the 4th button down, turn left.
    	{
    		Robot.DrivetrainSubsystem.setPowerLeft(1);
    		Robot.DrivetrainSubsystem.setPowerRight(1);
    	//Robot.DrivetrainSubsystem.turnInPlace(0.5, "L");//This method takes a direction and a speed.
    	}
    	else if(Robot.oi.btnL5.get())//If the 5th button on the left joystick is down, turn right.
    	{
    		Robot.DrivetrainSubsystem.turnInPlace(0.5, "R");//this method takes a direction, and a speed.
    	}	
    	else
    	{
    		//Pure manual Joysticks drive 
    		//Left Side 
    		if(Robot.oi.getLeftStickX() <= SafeZone || Robot.oi.getLeftStickX() >= -SafeZone)//checks if the joystick is in safezone
    		{
    			Robot.DrivetrainSubsystem.setPowerLeft(throttle*(-Robot.oi.getLeftStickY()));
    		}
    		else// if the joystick Y value is in the safezone, sets the robot's left motor to 0 output
    		{
    			Robot.DrivetrainSubsystem.setPowerRight(0);
    		}
    		//Right Side
    		if(Robot.oi.getRightStickY() >= SafeZone || Robot.oi.getRightStickY() <= (-1)*SafeZone)//checks if the joystick is in safezone
    		{
    			//sets the robots' right speed through a method declared in the drive subystem
    			Robot.DrivetrainSubsystem.setPowerRight(throttle*(-Robot.oi.getRightStickY()));
    		}
    		else
    		{
    			Robot.DrivetrainSubsystem.setPowerRight(0);
    		}
    		
    	}
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.DrivetrainSubsystem.setPowerRight(0);
    	Robot.DrivetrainSubsystem.setPowerLeft(0);
    }
}
