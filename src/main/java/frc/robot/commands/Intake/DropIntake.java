// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.SerializerSubsystem;

public class DropIntake extends CommandBase {
  /** Creates a new DropIntake. */

  IntakeSubsystem m_subsystem;
  SerializerSubsystem m_serializerSubsystem;
  double m_deployPower;
  double m_spinPower;

  public DropIntake(SerializerSubsystem serializerSubsystem, IntakeSubsystem subsystem, double deployPower, double spinPower) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_subsystem = subsystem;
    m_deployPower = deployPower;
    m_spinPower = spinPower;
    m_serializerSubsystem = serializerSubsystem;

    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_subsystem.deploy(m_deployPower);
    m_subsystem.setPower(m_spinPower);
    m_serializerSubsystem.setPower(-m_spinPower);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.stopDeploy();
    m_subsystem.stop();
    m_serializerSubsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
