// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.Camera;
import frc.lib.Camera.CameraData;
import frc.robot.subsystems.ShooterAngleSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootByDistanceCommand extends CommandBase {

  ShooterSubsystem m_subsystem;
  ShooterAngleSubsystem m_angleSubsystem;
  Camera m_camera;

  public ShootByDistanceCommand(ShooterSubsystem shooterSubsystem, ShooterAngleSubsystem angleSubsystem, Camera camera) {
    m_subsystem = shooterSubsystem;
    m_angleSubsystem = angleSubsystem;
    m_camera = camera;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_camera.toggleLights(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    CameraData data = m_camera.createData();
    if (data.canSee() && data.m_regions.GetRegion(0) != null) {
      double area = (data.m_regions.GetRegion(0).m_bounds.m_bottom - data.m_regions.GetRegion(0).m_bounds.m_top) * (data.m_regions.GetRegion(0).m_bounds.m_right - data.m_regions.GetRegion(0).m_bounds.m_left);
      double distance = -3.39426154352767E-12*area*area*area+0.000000182429533380718*area*area+-0.00350938626520345*area+34.1876601964883;
      double speed = 0.515572390572389*distance*distance*distance + -20.7927489177488*distance*distance + 225.602753727752*distance + 2278.94570707071;
      double hood_angle = -0.000243055555555556*distance*distance*distance + 0.0125852272727273*distance*distance + -0.210438762626263*distance + 1.36127840909091;

      m_subsystem.setSpeed(speed, speed);
      m_angleSubsystem.setAngle(hood_angle);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_camera.toggleLights(false);
    m_subsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
