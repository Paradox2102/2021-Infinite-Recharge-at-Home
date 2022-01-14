// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto.FiveWheel;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.pathfinder.Pathfinder.Waypoint;
import frc.robot.PurePursuit.PathConfigs;
import frc.robot.commands.Auto.CreatePathCommand;
import frc.robot.subsystems.DriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class MoveBack2Ball extends SequentialCommandGroup {

  Waypoint[] k_points = {
    new Waypoint(-11, 16, Math.toRadians(90)),
    new Waypoint(-11, 19, Math.toRadians(90))
  };

  public MoveBack2Ball(DriveSubsystem driveSubsystem) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new CreatePathCommand(driveSubsystem, k_points, PathConfigs.fast, true, false, true)
    );
  }
}
