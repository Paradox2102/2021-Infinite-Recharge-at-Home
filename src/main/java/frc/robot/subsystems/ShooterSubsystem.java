/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  /**
   * Creates a new ShooterSubsystem.
   */
  // ID 2 and 14 run the shooter
  // ID 12 runs the backwheels

  CANSparkMax m_shooter = new CANSparkMax(Constants.k_shooter, MotorType.kBrushless);
  CANSparkMax m_shooterFollower = new CANSparkMax(Constants.k_shooterFollower, MotorType.kBrushless);
  CANSparkMax m_backWheels = new CANSparkMax(Constants.k_backWheels, MotorType.kBrushless);

  CANEncoder m_shooterEncoder;
  CANEncoder m_backWheelEncoder;
  


  public ShooterSubsystem() {
    m_shooter.restoreFactoryDefaults();

    m_shooterEncoder = m_shooter.getEncoder();
    m_backWheelEncoder = m_backWheels.getEncoder();

    m_shooter.setInverted(true);
    //m_shooterFollower.follow(m_shooter);
    m_shooterFollower.follow(m_shooter, false);
    m_shooter.setIdleMode(IdleMode.kCoast);
    m_shooterFollower.setIdleMode(IdleMode.kCoast);

    m_backWheels.restoreFactoryDefaults();
    m_backWheels.setIdleMode(IdleMode.kCoast);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // SmartDashboard.putNumber("Shooter Encoder Position", m_shooterEncoder.getPosition());
    // SmartDashboard.putNumber("Backwheel Encoder Postion", m_backWheelEncoder.getPosition());

    SmartDashboard.putNumber("Shooter Speed", m_shooterEncoder.getVelocity());
    SmartDashboard.putNumber("Backwheel Speed", m_backWheelEncoder.getVelocity());
  }

  public void setShooterPower(double power) {
    m_shooter.set(power);
  }

  public void setBackWheelPower(double power) {
    m_backWheels.set(power);
  }

  public double getSetpoint() {
    return 0;
  }

  public double getSpeed() {
    return 0;
  }

  public void stop() {
    m_shooter.set(0);
    m_backWheels.set(0);
  }
}
