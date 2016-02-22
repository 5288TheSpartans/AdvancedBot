package org.usfirst.frc.team5288.robot.subsystems;

import org.usfirst.frc.team5288.robot.RobotMap;
import org.usfirst.frc.team5288.robot.commands.*;
import org.usfirst.frc.team5288.robot.commands.ShooterArmCommands.StopLift;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterY extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private VictorSP shooterY = new VictorSP(RobotMap.shooterY);
	public DigitalInput upperLimit = new DigitalInput(RobotMap.shooterUpperLimit);
	public DigitalInput lowerLimit = new DigitalInput(RobotMap.shooterLowerLimit);
	public double approxAngle;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new StopLift());
    }
    public void angleShooter(int dir)
	{
		if (dir == 1)
		{
			if (upperLimit.get())//If it is open
			{
				shooterY.set(-1);
			}
			else //If it is closed
			{
				shooterY.set(0);
			}
		}
		else if(dir == -1)
		{
			if (lowerLimit.get())
			{
				shooterY.set(1);
			}
			else
			{
				shooterY.set(0);
			}
		}
		else if(dir == 0)
		{
			shooterY.set(0);
		}
		else
		{
			shooterY.set(0);
		}
	}
	
}

