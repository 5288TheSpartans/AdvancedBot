package org.usfirst.frc.team5288.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor1 = 1;
    // public static int rightMotor = 2;
	//Drivetrain Subsystem
    public static int rightMotor1 = 3 ;
    public static int rightMotor2 = 2 ;
    public static int leftMotor1 = 0 ;
    public static int leftMotor2 = 1 ;
    //DI/O
    public static int leftEnc1 = 0;
    public static int leftEnc2 = 1;
    public static int rightEnc1 = 2;
    public static int rightEnc2 = 3;
    //Shooter Subsystem
    public static int shooterLeft = 4;//The motor that spins the left shooter wheel
    public static int shooterRight = 5;//The motor that spins the right shooter wheel
    public static int shooterServo = 6;//The servo that pushes the ball into the shooter wheel.
    public static int shooterY = 7;// The motor that angles the shooter.
    //DI/O
    public static int shooterUpperLimit = 4;//Limit switches
    public static int shooterLowerLimit = 5;//Limit switches
    //
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}
