/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Auto.RightBallRun;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import frc.pathfinder.Pathfinder.Waypoint;
import frc.robot.PurePursuit.PathConfigs;
import frc.robot.commands.Auto.CreatePathCommand;
import frc.robot.subsystems.DriveSubsystemOriginal;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TwoBallRun extends ParallelDeadlineGroup {
  /**
   * Creates a new TwoBallRun.
   */
  // Waypoints for straight on drive
  // static final Waypoint[] k_2Ball = { new Waypoint(10.7, 10,
  // Math.toRadians(90), 5),
  // new Waypoint(10.7, 21.2, Math.toRadians(90)) };

  // Waypoints for angled drive
  static final Waypoint[] k_2Ball = { new Waypoint(6, 10, Math.toRadians(90), 2, 4, 5),
      new Waypoint(10.1, 20.5, Math.toRadians(50)) };

  public TwoBallRun(DriveSubsystemOriginal driveSubsystem) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new CreatePathCommand(driveSubsystem, k_2Ball, PathConfigs.fast, true, true, true));
  }
}
