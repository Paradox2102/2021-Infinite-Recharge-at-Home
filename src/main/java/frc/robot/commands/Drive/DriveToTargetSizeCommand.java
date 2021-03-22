// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.Camera;
import frc.lib.Logger;
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
  double m_targetArea;
  boolean m_finished = false;
  boolean m_haveSeen = false;
  boolean m_driveBack;

  double k_turnP = 0.5;

  public DriveToTargetSizeCommand(DriveSubsystem driveSubsystem, ShooterSubsystem shooterSubsystem,
      ShooterAngleSubsystem shooterAngleSubsystem, Camera camera, double angle, double speed, double targetArea) {
    // Use addRequirements() here to declare subsystem dependencies.
    // m_driveSubsystem = driveSubsystem;
    m_shooterSubsystem = shooterSubsystem;
    m_ShooterAngleSubsystem = shooterAngleSubsystem;
    // m_camera = camera;
    m_angle = angle;
    m_speed = speed;
    // m_targetArea = targetArea;
    // m_driveBack = false;

    // addRequirements(m_driveSubsystem);
  }

  public DriveToTargetSizeCommand(DriveSubsystem driveSubsystem, Camera camera) {
    // m_driveSubsystem = driveSubsystem;
    // m_camera = camera;
    // m_driveBack = true;
    // m_targetArea = 4312;
    m_speed = 0;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Logger.Log("DriveToTargetSizeCommand", 1, "Initialized");
    m_shooterSubsystem.setSpeed(m_speed, m_speed);
    m_ShooterAngleSubsystem.setAngle(m_angle);
    m_finished = false;
    // m_camera.toggleLights(true);
    m_haveSeen = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // CameraData data = m_camera.createData();
    // if (data.canSee()) {
    // m_haveSeen = true;
    // double area = data.getTargetHeight() * data.getTargetWidth();
    // double angle = m_driveSubsystem.getAngle();
    // double turnDifference = k_turnP * (angle - 90);
    // if (!m_driveBack) {
    // // Logger.Log("Area", 1, "" + area);
    // if (m_targetArea > area) {
    // m_driveSubsystem.setSpeed(1000 - turnDifference, 1000 + turnDifference);
    // } else {
    // m_finished = true;
    // }
    // } else {
    // // Logger.Log("Area", 1, "" + area);
    // if (m_targetArea < area) {
    // m_driveSubsystem.setSpeed(-1000 - turnDifference, -1000 + turnDifference);
    // } else {
    // m_finished = true;
    // }
    // }
    // }
    // // else if (haveSeen)
    // // {
    // // finished = true;
    // // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Logger.Log("DriveToTargetSizeCommand", 1, "End");
    // m_driveSubsystem.stop();
    // m_camera.toggleLights(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
