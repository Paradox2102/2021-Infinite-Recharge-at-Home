// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.Camera;
import frc.lib.Camera.CameraData;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterAngleSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class DriveToTargetSizeCommand extends CommandBase {
  /** Creates a new DriveToTargetSizeCommand. */
  DriveSubsystem m_driveSubsystem;
  ShooterSubsystem m_shooterSubsystem;
  ShooterAngleSubsystem m_ShooterAngleSubsystem;
  Camera m_camera;

  double m_angle;
  double m_speed;
  double m_targetHeight;

  public DriveToTargetSizeCommand(DriveSubsystem driveSubsystem, ShooterSubsystem shooterSubsystem,
      ShooterAngleSubsystem shooterAngleSubsystem, Camera camera, double angle, double speed, double targetHeight) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_driveSubsystem = driveSubsystem;
    m_shooterSubsystem = shooterSubsystem;
    m_ShooterAngleSubsystem = shooterAngleSubsystem;
    m_camera = camera;
    m_angle = angle;
    m_speed = speed;
    m_targetHeight = targetHeight;

    addRequirements(m_driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    CameraData data = m_camera.createData();
    if (data.canSee()) {
      if (data.getTargetHeight() > m_targetHeight) {

      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
