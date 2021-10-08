// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto.TrenchRun;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.pathfinder.Pathfinder.Waypoint;
import frc.robot.PurePursuit.PathConfigs;
import frc.robot.commands.Auto.CreatePathCommand;
import frc.robot.subsystems.DriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html

public class MoveBack6Ball extends SequentialCommandGroup {
  /** Creates a new MoveBack6Ball. */

  final static Waypoint[] k_back = {
      new Waypoint(-11, 16, Math.toRadians(90)),
      new Waypoint(-11, 26, Math.toRadians(90)),
  };

  final static Waypoint[] k_forward = {
      new Waypoint(0, 0, Math.toRadians(-90)),
      new Waypoint(0, -5, Math.toRadians(-90)),
  };
  
  public MoveBack6Ball(DriveSubsystem subsystem) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new CreatePathCommand(subsystem, k_back, PathConfigs.fast, true, false, true),
      new CreatePathCommand(subsystem, k_forward, PathConfigs.fast, false, true, true));
  }
}
