/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Snoot;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SnootSubsystem;

public class SnootCommand extends CommandBase {
  /**
   * Creates a new SnootCommand.
   */
  SnootSubsystem m_subsystem;
  double m_power;
  public SnootCommand(SnootSubsystem subsystem, double power) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_subsystem = subsystem;
    m_power = power;

    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_subsystem.setPower(m_power);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
