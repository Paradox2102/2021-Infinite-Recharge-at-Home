// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class SpeedByThrottleCommand extends CommandBase {
  /** Creates a new SpeedByThrottleCommand. */

  ShooterSubsystem m_subsystem;
  DoubleSupplier m_throttle;
  double m_speed;

  public SpeedByThrottleCommand(ShooterSubsystem subsystem, DoubleSupplier throttle) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_subsystem = subsystem;
    m_throttle = throttle;

    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_speed = 500 * m_throttle.getAsDouble() + 2000;
    m_subsystem.setSpeed(m_speed, m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
