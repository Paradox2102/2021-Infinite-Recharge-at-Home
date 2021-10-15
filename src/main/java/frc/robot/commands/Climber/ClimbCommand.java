// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climber;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimbCommand extends CommandBase {

  ClimberSubsystem m_climberSubsystem;
  DoubleSupplier y;

  public ClimbCommand(ClimberSubsystem climberSubsystem, DoubleSupplier joystickY) {
    m_climberSubsystem = climberSubsystem;
    y = joystickY;
    addRequirements(m_climberSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // m_climberSubsystem.setBrake(false);
    // m_climberSubsystem.unLock();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_climberSubsystem.setPower(y.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_climberSubsystem.setPower(0);
    // m_climberSubsystem.setBrake(true);
    // m_climberSubsystem.lock();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
