// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class CalibrateSpeedCommand extends CommandBase {
  /** Creates a new CalibrateSpeedCommand. */

  ShooterSubsystem m_subsystem;
  DoubleSupplier m_speed;

  public CalibrateSpeedCommand(ShooterSubsystem subsytem, DoubleSupplier speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_subsystem = subsytem;
    m_speed = speed;

    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_subsystem.configPID();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = m_speed.getAsDouble();

    if(speed < m_subsystem.getSpeed()) {
      m_subsystem.stop();
    } else {
      
    }
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
