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
public class SlalomPath extends ParallelCommandGroup {
  /**
   * Creates a new pathAuto.
   */

  //Waypoints
  // 5,3.5,90,1,13
  // 0,18.5,90
  // 5.5,26,90,4,4
  // 0,26,-90
  // 5,18.5,-90,12,6
  // -1.5,1,-90

  // static final Waypoint[] k_driveSlalom = { 
  //     new Waypoint(5, 3.5, Math.toRadians(90), 1, 13, 0),
  //     new Waypoint(0, 18.5, Math.toRadians(90)), 
  //     new Waypoint(5.5, 25, Math.toRadians(90), 4, 4, 0), 
  //     new Waypoint(0, 25, Math.toRadians(-90)),
  //     new Waypoint(5, 18.5, Math.toRadians(-90), 12, 6, 0), 
  //     new Waypoint(-1.5, 1, Math.toRadians(-90)) };

  static final Waypoint[] k_drive10ft = {
    new Waypoint(5, 3.5, Math.toRadians(90)),
    new Waypoint(5, 23.5, 90)
  };
      
  DriveSubsystem m_driveSubsystem;

  public SlalomPath(DriveSubsystem driveSubsystem) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    m_driveSubsystem = driveSubsystem;
    addCommands(new SequentialCommandGroup(new CreatePathCommand(driveSubsystem, k_drive10ft, PathConfigs.fastAccel, false, true, true)));
  }

  @Override
  public void initialize() {
    super.initialize();
    m_driveSubsystem.resetAngle(90);
  }
}
