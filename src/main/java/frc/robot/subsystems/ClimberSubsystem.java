// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {

  //Two motors
  CANSparkMax m_climberMotor1 = new CANSparkMax(Constants.k_climberMotor1, CANSparkMax.MotorType.kBrushless);
  CANSparkMax m_climberMotor2 = new CANSparkMax(Constants.k_climberMotor2, CANSparkMax.MotorType.kBrushless);
  //Two servos
  Servo m_servo1 = new Servo(Constants.k_climberServo1);
  Servo m_servo2 = new Servo(Constants.k_climberServo2);
  // Switches
  DigitalInput m_limit1, m_limit2;

  

  /** Creates a new ClimberSubsystem. */
  public ClimberSubsystem() {
    m_limit1 = new DigitalInput(8);
    m_limit2 = new DigitalInput(9);

    m_climberMotor1.setIdleMode(CANSparkMax.IdleMode.kCoast);
    m_climberMotor2.setIdleMode(CANSparkMax.IdleMode.kCoast);
  }

  @Override
  public void periodic() {

    if(!m_limit1.get()) {
      m_climberMotor1.set(0);
    } else if(!m_limit2.get()) {
      m_climberMotor2.set(0);
    }

    SmartDashboard.putBoolean("Climber Limit 1", m_limit1.get());
    SmartDashboard.putBoolean("Climber Limit 2", m_limit2.get());

    SmartDashboard.putNumber("Climber Servo 1", m_servo1.get());
    SmartDashboard.putNumber("Climber Servo 2", m_servo2.get());
  }

  public void setBrake(boolean brake) {
    if (brake) {
      m_climberMotor1.setIdleMode(CANSparkMax.IdleMode.kBrake);
      m_climberMotor2.setIdleMode(CANSparkMax.IdleMode.kBrake);
    } else {
      m_climberMotor1.setIdleMode(CANSparkMax.IdleMode.kCoast);
      m_climberMotor2.setIdleMode(CANSparkMax.IdleMode.kCoast);
    }
  }

  public void setPower(double power) {
    m_climberMotor1.set(power);
    m_climberMotor2.set(power);
  }

  public void setAngle1(double angle) {
    m_servo1.set(angle);
  }

  public void setAngle2(double angle) {
    m_servo2.set(angle);
  }

  public void unLock() {
    m_servo1.set(0.226); // 0.226
    m_servo2.set(1);
  }
  public void lock() {
    m_servo1.setAngle(0.1288); // 0.1288
    m_servo2.setAngle(0);
  }
}