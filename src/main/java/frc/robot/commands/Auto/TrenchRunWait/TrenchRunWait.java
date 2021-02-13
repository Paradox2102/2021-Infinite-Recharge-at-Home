/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Auto.TrenchRunWait;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.lib.Camera;
import frc.pathfinder.Pathfinder.Waypoint;
import frc.robot.commands.Intake.IntakeCommand;
import frc.robot.commands.Serializer.PowerSerializeCommand;
import frc.robot.commands.Teleop.SpinUpCommand;
import frc.robot.commands.Turret.TurretTrackingCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.SerializerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ThroatSubsystem;
import frc.robot.subsystems.TurretSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TrenchRunWait extends SequentialCommandGroup {
  /**
   * Creates a new DriveToTrenchConmand.
   */

  final Waypoint[] k_2balls = { new Waypoint(-11, 15, Math.toRadians(90), 1, 2, 3, 2, 6),
      new Waypoint(-5, 17, Math.toRadians(40)) };

  public TrenchRunWait(DriveSubsystem driveSubsystem, IntakeSubsystem intakeSubsystem, ShooterSubsystem shooterSubsystem,
      TurretSubsystem turretSubsystem, ThroatSubsystem throatSubsystem, IndexerSubsystem indexerSubsystem,
      SerializerSubsystem serializerSubsystem, Camera turretCamera, double shooterSpeed, DoubleSupplier getPosX,
      DoubleSupplier getPosY) {
    addCommands(new ParallelCommandGroup(
        new SpinUpCommand(turretSubsystem, turretCamera, shooterSubsystem, indexerSubsystem, shooterSpeed),
        new TurretTrackingCommand(turretSubsystem, turretCamera), new IntakeCommand(intakeSubsystem, 0.9),
        new PowerSerializeCommand(serializerSubsystem, 0.75),
        new SequentialCommandGroup(
            new BackTrenchRunCommand(driveSubsystem, intakeSubsystem, shooterSubsystem, turretSubsystem,
                throatSubsystem, getPosX, getPosY, turretCamera),
            new FrontTrenchRunCommand(driveSubsystem, turretSubsystem, throatSubsystem, shooterSubsystem,
                intakeSubsystem, turretCamera))));
    // // new ToggleLightsCommand(frontCamera, true),
    // new ParallelDeadlineGroup(new WaitForBallCommand(frontCamera), new
    // CreatePathCommand(driveSubsystem, k_2balls, PathConfigs.fast, true, false,
    // true)),
    // new BallDriveCommand(driveSubsystem, frontCamera, -0.25)

  }
}
