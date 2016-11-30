package org.usfirst.frc.team5288.robot.subsystems;

import org.usfirst.frc.team5288.robot.RobotMap;
import org.usfirst.frc.team5288.robot.commands.DriveCommands.ManualDrive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
//import edu.wpi.first.wpilibj.ADXRS450_Gyro;
//import edu.wpi.first.wpilibj.ADXL345_SPI;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class DrivetrainSubsystem extends Subsystem {
    //*DRIVE MOTOR CONTROLLER VARIABLES
    TalonSRX leftDrive1 = new TalonSRX(RobotMap.leftMotor1);     //Left Side first motor
    TalonSRX leftDrive2 = new TalonSRX(RobotMap.leftMotor2);   //Left side second motor
    TalonSRX rightDrive1 = new TalonSRX(RobotMap.rightMotor1);    //Right side first motor
    TalonSRX rightDrive2 = new TalonSRX(RobotMap.rightMotor2);
    private double leftPower = 0;
    private double rightPower = 0;//Right side second motor
     //********************GYRO VARIABLES********************
     private ADXRS450_Gyro gyro = new ADXRS450_Gyro();
    //********************ACCELEROMETER VARIABLES***********
   // ADXL345_SPI accel;
    

    public double throttle = 1;
    //******************ENCODER VARIABLES*****************
    Encoder rightEncoder = new Encoder(RobotMap.rightEnc1,RobotMap.rightEnc2,false,Encoder.EncodingType.k2X);
   
    Encoder leftEncoder = new Encoder(RobotMap.leftEnc1,RobotMap.leftEnc2,true,Encoder.EncodingType.k2X);	
    // SPPED CALCULATION BASED VARIABLES
    public double currentSpeedLeft = 0;
    public double currentSpeedRight = 0;
    public double leftDistance = 0;
    public double rightDistance = 0;
    public double avgDistance = 0;
    public double currentTime = 1;
    public double lastTime = 0;
    public double currentSpeed = 0;
    public double timeDifference = 1;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public DrivetrainSubsystem()
    {
      //  accel = new ADXL345_SPI(SPI.Port.kMXP,ADXL345_SPI.Range.k2G);
    	
      //  gyro = new ADXRS450_Gyro();
     //  gyro.calibrate();
    //gyro.reset();
        gyro.calibrate();
        System.out.println("This is working.");
    	rightEncoder.setMinRate(5);
    	rightEncoder.setDistancePerPulse(18.85/360);
    	rightEncoder.setSamplesToAverage(1);
    	leftEncoder.setMinRate(5);
    	leftEncoder.setDistancePerPulse(18.85/360);
    	leftEncoder.setSamplesToAverage(1);
    	//gyro.startLiveWindowMode();
    	rightEncoder.startLiveWindowMode();
    	leftEncoder.startLiveWindowMode();
    }
    //---------------------------Most basic methods that control the drivetrain----------------------
	public void setPowerLeft(double power)
	{
		leftDrive1.set(power);
		leftDrive2.set(power);
		leftPower = power;
	}
	public void changeThrottle(double power)
	{
		
	}
	public void setPowerRight(double power)
	{
		 rightDrive1.set(-power);
		 rightDrive2.set(-power);
		 rightPower = -power;

	}
	public void turnInPlace(double power, String Dir)//This function allows the robot to turn in place
	{
		switch (Dir)
		{
			case "R":
			leftDrive1.set(power);
			leftDrive2.set(power);
			rightDrive1.set(power);
			rightDrive2.set(power);
			leftPower = power;
			rightPower = power;

			break;
			case "L":
			leftDrive1.set(-power);
			leftDrive2.set(-power);
			rightDrive1.set(-power);
			rightDrive2.set(-power);
			leftPower = -power;
			rightPower = -power;
			break;
		}
	}
	public double getLeftDrive()//This function returns the current power to left victor
	{
		return(leftPower);
		
	}
	public double getRightDrive()//This function returns the current power to right victor
	{
		return(rightPower);
	}
	public double leftEncTicks()
	{
		//leftDistance = leftEncoder.getDistance();
		//leftEncoder.reset();
		return leftEncoder.getDistance();
	}
	public void resetEncoders()
	{
		leftEncoder.reset();
		rightEncoder.reset();
	}
	public double rightEncTicks()
	{
		//rightDistance = rightEncoder.getDistance();
		//rightEncoder.reset();
		return rightEncoder.getDistance();
	}
	public double getGyroAngle(){
		gyro.updateTable();
	SmartDashboard.putNumber("CurrentGyroAngle : ", gyro.getAngle());
		return gyro.getAngle();	
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ManualDrive());
    }
    public void updateSpeed()
    {
    	leftDistance = leftEncTicks();
    	rightDistance = rightEncTicks();
    	lastTime = currentTime;
    	currentTime = DriverStation.getInstance().getMatchTime();
    	timeDifference = currentTime - lastTime;
    	avgDistance = (leftDistance + rightDistance)/2;
    	currentSpeed = (avgDistance/timeDifference)/12*1000;
        SmartDashboard.putNumber("Feet/s", currentSpeed);
    }
}

