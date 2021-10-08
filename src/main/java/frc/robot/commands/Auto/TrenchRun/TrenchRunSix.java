// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto.TrenchRun;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.lib.Camera;
import frc.robot.commands.Auto.FireCommandAuto;
import frc.robot.commands.Auto.WaitForShooterSpeedCommand;
import frc.robot.commands.Camera.ToggleLightsCommand;
import frc.robot.commands.Intake.DropIntake;
import frc.robot.commands.Shooter.ShootByDistanceCommandInterpolate;
import frc.robot.commands.Shooter.SpinUpShooterCommand;
import frc.robot.commands.Teleop.FireCommand;
import frc.robot.commands.Throat.ThroatAtSpeedCommand;
import frc.robot.commands.Throat.ThroatPowerCommand;
import frc.robot.commands.Turret.TurretTrackingCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.SerializerSubsystem;
import frc.robot.subsystems.ShooterAngleSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ThroatSubsystem;
import frc.robot.subsystems.TurretSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TrenchRunSix extends ParallelCommandGroup {
  /** Creates a new TrenchRunSix. */
  public TrenchRunSix(DriveSubsystem driveSubsystem, ShooterSubsystem shooterSubsystem, ShooterAngleSubsystem shooterAngleSubsystem, TurretSubsystem turretSubsystem,
    ThroatSubsystem throatSubsystem, SerializerSubsystem serializerSubsystem, IntakeSubsystem intakeSubsystem,
    Camera frontCam, Camera backCamera, double shootSpeed) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ShootByDistanceCommandInterpolate(shooterSubsystem, shooterAngleSubsystem, frontCam),
      new TurretTrackingCommand(turretSubsystem, frontCam),
      new DropIntake(serializerSubsystem, intakeSubsystem, 0.3, 0.7),
      //new ToggleLightsCommand(frontCam, true),
      new SequentialCommandGroup(
        new MoveBack3Ball(driveSubsystem),
        new ParallelDeadlineGroup(
          new WaitCommand(2),
          new FireCommand(throatSubsystem, shooterSubsystem)
          // new FireCommandAuto(throatSubsystem, turretSubsystem, null, frontCam, 50)
        ),
        new ParallelDeadlineGroup(
          new MoveBack6Ball(driveSubsystem),
          new ThroatAtSpeedCommand(throatSubsystem, 0.3)
        ),
        new ParallelDeadlineGroup(
          new WaitCommand(6),
          new FireCommand(throatSubsystem, shooterSubsystem)
        )
      )
    );
  }
}
