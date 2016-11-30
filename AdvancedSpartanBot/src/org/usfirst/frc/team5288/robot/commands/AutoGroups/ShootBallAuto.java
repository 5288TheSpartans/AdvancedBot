package org.usfirst.frc.team5288.robot.commands.AutoGroups;

import org.usfirst.frc.team5288.robot.commands.*;
import org.usfirst.frc.team5288.robot.commands.DriveCommands.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootBallAuto extends CommandGroup {
    
    public  ShootBallAuto() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	addSequential(new AutoPrepareBall());
    	addSequential(new AutoFireBall());
    //	addSequential(new AutoTurn());
    	addSequential(new AutoDown());
    	addSequential(new AutoTurn(80));
    //	addSequential(new DriveToPosition());
   	//addSequential(new DriveBackwardsStraight());
    	
        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
