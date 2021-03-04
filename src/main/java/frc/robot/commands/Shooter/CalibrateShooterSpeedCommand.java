// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.PiCamera.Logger;
import frc.robot.subsystems.ShooterSubsystem;

public class CalibrateShooterSpeedCommand extends CommandBase {
  /** Creates a new CalibrateSpeedCommand. */

  ShooterSubsystem m_subsystem;
  double m_speed;

  public CalibrateShooterSpeedCommand(ShooterSubsystem subsytem, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_subsystem = subsytem;
    m_speed = speed;

    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Logger.Log("CalibrateShooterCommand", 1, "Initialized");
    m_subsystem.configPID();
    m_subsystem.setSpeed(0, m_speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // m_subsystem.setSpeed(500*(m_stick.getThrottle()) + 2600);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Logger.Log("CalibrateShooterCommand", 1, "Finished");
    m_subsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
