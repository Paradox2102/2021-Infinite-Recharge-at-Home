// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {

  //Two motors
  CANSparkMax climberMotor1 = new CANSparkMax(Constants.k_climberMotor1, CANSparkMax.MotorType.kBrushless);
  CANSparkMax climberMotor2 = new CANSparkMax(Constants.k_climberMotor2, CANSparkMax.MotorType.kBrushless);
  //Two servos
  Servo m_servo1 = new Servo(Constants.k_climberServo1);
  Servo m_servo2 = new Servo(Constants.k_climberServo2);

  /** Creates a new ClimberSubsystem. */
  public ClimberSubsystem() {
    climberMotor1.setIdleMode(CANSparkMax.IdleMode.kBrake);
    climberMotor2.setIdleMode(CANSparkMax.IdleMode.kBrake);
  
    climberMotor2.follow(climberMotor1);
  }

  @Override
  public void periodic() {
    
  }

  public void setPower(double power) {
    climberMotor1.set(power);
    climberMotor2.set(power);
  }

  public void unLock() {
    m_servo1.setAngle(0);
    m_servo2.setAngle(0);
  }
  public void lock() {
    m_servo1.setAngle(0);
    m_servo2.setAngle(0);
  }
}
