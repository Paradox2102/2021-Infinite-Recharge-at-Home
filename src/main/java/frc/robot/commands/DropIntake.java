// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class DropIntake extends CommandBase {
  /** Creates a new DropIntake. */

  IntakeSubsystem m_subsystem;
  boolean finished = false;

  public DropIntake(IntakeSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_subsystem = subsystem;

    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_subsystem.deploy();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.stopDeploy();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(m_subsystem.getEncoder() >= 1055) {
      return true;
    } else {
      return false;
    }
  }
}
