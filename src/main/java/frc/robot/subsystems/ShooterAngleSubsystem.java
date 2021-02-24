// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.Shooter.SetAngleCommand;

public class ShooterAngleSubsystem extends SubsystemBase {
  /** Creates a new ShooterAngleSubsystem. */

  Servo m_angleServo = new Servo(9);

  public ShooterAngleSubsystem() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setAngle(double angle) {
    m_angleServo.set(angle);
  }
}
