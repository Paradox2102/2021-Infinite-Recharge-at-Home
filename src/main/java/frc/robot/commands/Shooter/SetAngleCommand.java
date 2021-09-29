// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterAngleSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class SetAngleCommand extends CommandBase {
  /** Creates a new SetAngleCommand. */

  double m_angle;
  DoubleSupplier m_throttle;
  ShooterAngleSubsystem m_subsytem;


  public SetAngleCommand(ShooterAngleSubsystem subsystem, DoubleSupplier throttle) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_subsytem = subsystem;
    m_throttle = throttle;
    m_angle = 0;

    addRequirements(m_subsytem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {  // 0.25 //0.98
    m_subsytem.setAngle(0.365*m_throttle.getAsDouble() + 0.615);
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
