/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.GalacticSearch;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.pathfinder.Pathfinder.Waypoint;
import frc.robot.PurePursuit.PathConfigs;
import frc.robot.commands.Auto.CreatePathCommand;
import frc.robot.subsystems.DriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class BarrelPath extends ParallelCommandGroup {
  /**
   * Creates a new pathAuto.
   */

  //Waypoints
  // 0,3.5,90
//0,13,70
//5,12.6,-90,3,3
//0,13,95
//-.75,20,100
//-5,20,-90,2,2
//-.75,19,55,1,1
//4.5,25,90,2,2
//0,25,-90,2,2
//0,3.5,-90
//changed

  static final Waypoint[] k_loopDrive = { 
      new Waypoint(0, 3.5, Math.toRadians(90), 0, 0, 8),
      new Waypoint(0, 13, Math.toRadians(70)), 
      new Waypoint(5, 12.6, Math.toRadians(-90), 3, 3, 0), 
      new Waypoint(0, 13, Math.toRadians(95), 0, 0, 8),
      new Waypoint(.5, 20, Math.toRadians(100)), 
      new Waypoint(-5, 20, Math.toRadians(-90), 2, 2, 0),
      new Waypoint(-.75, 19, Math.toRadians(55), 1, 1, 8),
      new Waypoint(5.5, 25, Math.toRadians(90), 2, 2, 0), 
      new Waypoint(1, 25, Math.toRadians(-90), 2, 2, 0),
      new Waypoint(1, 20, Math.toRadians(-95), 1, 1, 15),
      new Waypoint(-1.5, 2.5, Math.toRadians(-90))
     };
      
  DriveSubsystem m_driveSubsystem;

  public BarrelPath(DriveSubsystem driveSubsystem) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    m_driveSubsystem = driveSubsystem;
    addCommands(new SequentialCommandGroup(new CreatePathCommand(driveSubsystem, k_loopDrive, PathConfigs.fastAccel)));
  }

  @Override
  public void initialize() {
    super.initialize();
    m_driveSubsystem.setPos(0, 3.5);
    m_driveSubsystem.resetAngle(90);
  }
}
