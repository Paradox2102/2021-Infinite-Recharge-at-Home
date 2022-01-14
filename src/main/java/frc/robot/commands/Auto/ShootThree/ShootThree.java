// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto.ShootThree;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.lib.Camera;
import frc.robot.commands.Camera.ToggleLightsCommand;
import frc.robot.commands.Shooter.ShootByDistanceCommandInterpolate;
import frc.robot.commands.Teleop.FireCommand;
import frc.robot.commands.Turret.TurretTrackingCommand;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterAngleSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ThroatSubsystem;
import frc.robot.subsystems.TurretSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootThree extends SequentialCommandGroup {
  public ShootThree(IntakeSubsystem intakeSubsystem, ThroatSubsystem throatSubsystem, TurretSubsystem turretSubsystem, ShooterSubsystem shooterSubsystem, ShooterAngleSubsystem angleSubsystem, Camera camera) {
    addCommands(
      new ParallelCommandGroup(
        new ToggleLightsCommand(camera, true),
        new TurretTrackingCommand(turretSubsystem, camera),
        new ShootByDistanceCommandInterpolate(shooterSubsystem, angleSubsystem, camera)
      ),
      new WaitCommand(1),
      new FireCommand(throatSubsystem, shooterSubsystem, intakeSubsystem)
    );
  }
}
