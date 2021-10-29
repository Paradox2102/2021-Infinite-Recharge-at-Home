// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;
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
  PigeonIMU m_gyro;

  double k_p = 0;//0.025;
  double m_difference = 0;

  boolean m_hit1, m_hit2 = false;

  /** Creates a new ClimberSubsystem. */
  public ClimberSubsystem(PigeonIMU gyro) {
    m_gyro = gyro;
    m_limit1 = new DigitalInput(4);
    m_limit2 = new DigitalInput(5);

    m_climberMotor1.setIdleMode(CANSparkMax.IdleMode.kBrake);
    m_climberMotor2.setIdleMode(CANSparkMax.IdleMode.kBrake);
    
    m_climberMotor1.setInverted(true);
    m_climberMotor2.setInverted(false);
  }

  @Override
  public void periodic() {
    double[] ypr = new double[3];
    m_gyro.getYawPitchRoll(ypr);
    m_difference = ypr[1] * k_p;

    if(!m_limit1.get()) {
      m_hit1 = true;
    } else {
      m_hit1 = false;
    }
    if(!m_limit2.get()) {
      m_hit2 = true;
    } else {
      m_hit2 = false;
    }

    SmartDashboard.putNumber("Gyro Pitch", ypr[1]);

    SmartDashboard.putBoolean("Climber Limit 1", m_limit1.get());
    SmartDashboard.putBoolean("Climber Limit 2", m_limit2.get());

    SmartDashboard.putNumber("Climber Servo 1", m_servo1.get());
    SmartDashboard.putNumber("Climber Servo 2", m_servo2.get());
  }

  // public void setBrake(boolean brake) {
  //   if (brake) {
  //     m_climberMotor1.setIdleMode(CANSparkMax.IdleMode.kBrake);
  //     m_climberMotor2.setIdleMode(CANSparkMax.IdleMode.kBrake);
  //   } else {
  //     m_climberMotor1.setIdleMode(CANSparkMax.IdleMode.kCoast);
  //     m_climberMotor2.setIdleMode(CANSparkMax.IdleMode.kCoast);
  //   }
  // }

  public void setPower(double power) {
    if(m_hit1 && power > 0) {
      m_climberMotor1.set(0);
      // setBrake(true);
    } else {
      m_climberMotor1.set(power - m_difference * power);
      // setBrake(false);
    }
    if(m_hit2 && power > 0) {
      m_climberMotor2.set(0);
      // setBrake(true);
    } else {
      m_climberMotor2.set(power*1 + m_difference * power);
      // setBrake(false);
    }
  }

  public void setAngle1(double angle) {
    m_servo1.set(angle);
  }

  public void setAngle2(double angle) {
    m_servo2.set(angle);
  }

  public void unLock() {
    m_servo1.set(0.226); // 0.226
    m_servo2.set(0.335556);
  }
  public void lock() {
    m_servo1.setAngle(0.1288); // 0.1288
    m_servo2.setAngle(0.4455);
  }
}
