// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto.TrenchRun;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.lib.Camera;
import frc.robot.commands.Auto.WaitForShooterSpeedCommand;
import frc.robot.commands.Camera.ToggleLightsCommand;
import frc.robot.commands.Intake.DropIntake;
import frc.robot.commands.Shooter.SpinUpShooterCommand;
import frc.robot.commands.Teleop.FireCommand;
import frc.robot.commands.Throat.ThroatPowerCommand;
import frc.robot.commands.Turret.TurretTrackingCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.SerializerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ThroatSubsystem;
import frc.robot.subsystems.TurretSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TrenchRunSix extends ParallelCommandGroup {
  /** Creates a new TrenchRunSix. */
  public TrenchRunSix(DriveSubsystem m_driveSubsystem, ShooterSubsystem m_shooterSubsystem, TurretSubsystem m_turretSubsystem,
    ThroatSubsystem m_throatSubsystem, SerializerSubsystem m_serializerSubsystem, IntakeSubsystem m_intakeSubsystem,
    Camera frontCam, double shootSpeed) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new SpinUpShooterCommand(m_shooterSubsystem, shootSpeed, shootSpeed),
      new TurretTrackingCommand(m_turretSubsystem, frontCam),
      new DropIntake(m_serializerSubsystem, m_intakeSubsystem, 0.3, 0.7),
      new ToggleLightsCommand(frontCam, true),
      new TrenchForwardBack(m_driveSubsystem)
    );
  }
}
