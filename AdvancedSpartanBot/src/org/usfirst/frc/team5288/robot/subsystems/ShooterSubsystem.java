package org.usfirst.frc.team5288.robot.subsystems;

import org.usfirst.frc.team5288.robot.RobotMap;
import org.usfirst.frc.team5288.robot.commands.*;
import org.usfirst.frc.team5288.robot.commands.ShooterCommands.StopShooterWheels;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *+
 *
 */
public class ShooterSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private TalonSRX shooterLeft = new TalonSRX(RobotMap.shooterLeft);     //Left side second motor
	private TalonSRX shooterRight = new TalonSRX(RobotMap.shooterRight);    //Right side first motor
	private Servo ballPusher = new Servo(RobotMap.shooterServo);    //Right side second motor
    // Put methods for controlling this subsystem
	// Sensors
	 
    // here. Call these from Commands.
	
	public double servoAngle;
	public double leftWheelPower;
	public double rightWheelPower;
	public boolean IsLoaded;
	public void setServoAngle(double angle)
	{
		servoAngle = angle;
		ballPusher.setAngle(angle);
	}
	public void servoDown()
	{
		setServoAngle(153);
	}
	public void servoUp()
	{
		setServoAngle(115);
	}
	//The code here is only for the setting of shooter wheel speed
	public void powerShooterL(double power)
	{
		shooterLeft.set(power);
		leftWheelPower = power;
	}
	public void powerShooterR( double power)
	{
		shooterRight.set(power);
		rightWheelPower = power;
	}
	
	public void intake()
	{
		powerShooterL(0.4);
		powerShooterR(-0.4);
	}
	public void Shoot()
	{
		powerShooterL(-0.8);
		powerShooterR(0.8);
	}
	public void Stop()
	{
		powerShooterL(0);
		powerShooterR(0);
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new StopShooterWheels());
    }

}

