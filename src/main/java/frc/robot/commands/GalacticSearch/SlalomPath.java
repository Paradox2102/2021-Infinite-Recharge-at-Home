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


/*
5,3.5,90,3,5,0
 0,10,90,1,1, 12
0.089,17.13,90
5.547,25.607,90,3,3,0
 0,26.5,-90,2,0.992,0
4.66,22.811,-90, 0,0, 12
4.749,19.438,-90,11,8,0
 -.07,2,-90
*/
/*
5,3.5,92.41,3.87,3.967
 0,10,448.077,1,1, 12
-0.089,17.441,90, 4,3
5.947,23.609,91.269,3.995,3.156
-0.71,26.627,266.783,1.692,0.897
4.482,24.32,267.624,2.132, 0, 12
4.527,19.527,-90,13,7.675
-0.843,1.864,269.809
*/
static final Waypoint[] k_path = {
  new Waypoint(5, 3.5, Math.toRadians(92.41), 3.87, 3.967,0),
  new Waypoint( 0, 10, Math.toRadians(448.077), 1, 1,  12),
  new Waypoint(-0.089, 17.441, Math.toRadians(90),  4, 3,0),
  new Waypoint(5.947, 23.609, Math.toRadians(91.269), 3.995, 3.156,0),
  new Waypoint(-0.71, 26.627, Math.toRadians(266.783), 1.692, 0.897,0),
  new Waypoint(4.482, 24.32, Math.toRadians(267.624), 2.132,  0,  12),
  new Waypoint(4.527, 19.527, Math.toRadians(-90), 13, 7.675,0),
  new Waypoint(-0.843, 1.864, Math.toRadians(269.809))
};
  // static final Waypoint[] k_drive20ft = { new Waypoint(5, 0, Math.toRadians(90)),
  //     new Waypoint(5, 20, Math.toRadians(90)) };

  DriveSubsystem m_driveSubsystem;

  public SlalomPath(DriveSubsystem driveSubsystem) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    m_driveSubsystem = driveSubsystem;
    addCommands(new SequentialCommandGroup(
        new CreatePathCommand(driveSubsystem, k_path, PathConfigs.slalom, false, true, true)));
  }

  @Override
  public void initialize() {
    super.initialize();
    m_driveSubsystem.resetAngle(90);
  }
}
