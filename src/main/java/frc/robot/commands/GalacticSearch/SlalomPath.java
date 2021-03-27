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

  // Waypoints
  // 5,3.5,90,3,3
  // 0,10,90
  // 0,19,90,4,4
  // 5.5,26,90,4,4
  // 0,26,-90
  // 5,20,-90
  // 5,12,-90,5,6
  // -1.5,1,-90

  static final Waypoint[] k_driveSlalom = { 
      new Waypoint(5, 3.5, Math.toRadians(90), 4, 4, 0),
      //new Waypoint(1.5,7.5, Math.toRadians(170), 1, 1, 0),
      new Waypoint(0, 10, Math.toRadians(90), 1, 1, 12), 
      new Waypoint(0, 18, Math.toRadians(90)),
      new Waypoint(5.5, 24, Math.toRadians(90), 3, 3, 0),
      new Waypoint(0, 26.5, Math.toRadians(-90), 2, 1, 0),
      new Waypoint(4.5, 24, Math.toRadians(-90), 0, 0, 12),
      new Waypoint(4.5, 19, Math.toRadians(-90), 13, 7, 0),
      new Waypoint(-0.7, 2, Math.toRadians(-90)) };

  // static final Waypoint[] k_drive20ft = { new Waypoint(5, 0, Math.toRadians(90)),
  //     new Waypoint(5, 20, Math.toRadians(90)) };

  DriveSubsystem m_driveSubsystem;

  public SlalomPath(DriveSubsystem driveSubsystem) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    m_driveSubsystem = driveSubsystem;
    addCommands(new SequentialCommandGroup(
        new CreatePathCommand(driveSubsystem, k_driveSlalom, PathConfigs.slalom, false, true, true)));
  }

  @Override
  public void initialize() {
    super.initialize();
    m_driveSubsystem.resetAngle(90);
  }
}
