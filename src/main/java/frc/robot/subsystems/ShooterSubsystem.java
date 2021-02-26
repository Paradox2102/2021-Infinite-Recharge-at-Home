/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
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
  CANPIDController m_shooterController;
  CANSparkMax m_shooterFollower = new CANSparkMax(Constants.k_shooterFollower, MotorType.kBrushless);
  CANSparkMax m_backWheels = new CANSparkMax(Constants.k_backWheels, MotorType.kBrushless);

  ShuffleboardTab shooterTab = Shuffleboard.getTab("Shooter Tuning");

  double k_f = Constants.m_robotConstants.k_shooterF;
  double k_p = Constants.m_robotConstants.k_shooterP;
  double k_i = Constants.m_robotConstants.k_shooterI;

  int k_iRange = Constants.m_robotConstants.k_shooterIRange;
  int k_slot = 0;

  CANEncoder m_shooterEncoder;
  CANEncoder m_backWheelEncoder;
  


  public ShooterSubsystem() {
    m_shooter.restoreFactoryDefaults();

    m_shooterEncoder = m_shooter.getEncoder();
    m_backWheelEncoder = m_backWheels.getEncoder();

    m_shooterFollower.follow(m_shooter, true);
    m_shooter.setInverted(true);
    
    m_shooter.setIdleMode(IdleMode.kCoast);
    m_shooterFollower.setIdleMode(IdleMode.kCoast);

    m_backWheels.restoreFactoryDefaults();
    m_backWheels.setIdleMode(IdleMode.kCoast);

    m_shooterController = m_shooter.getPIDController();

    m_shooterController.setFF(k_f);
    m_shooterController.setP(k_p);
    m_shooterController.setI(k_i);
    m_shooterController.setD(0);
    m_shooterController.setIZone(k_iRange);


    SmartDashboard.setDefaultNumber("shooter P", k_p);
    SmartDashboard.setDefaultNumber("shooter I", k_i);
    SmartDashboard.setDefaultNumber("Shooter F", k_f);
    SmartDashboard.setDefaultNumber("Shooter Izone", k_iRange);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // SmartDashboard.putNumber("Shooter Encoder Position", m_shooterEncoder.getPosition());
    // SmartDashboard.putNumber("Backwheel Encoder Postion", m_backWheelEncoder.getPosition());

    SmartDashboard.putNumber("Shooter Speed", m_shooterEncoder.getVelocity());
    SmartDashboard.putNumber("Backwheel Speed", m_backWheelEncoder.getVelocity());
  }

  public void configPID(){
    k_p = SmartDashboard.getNumber("shooter P", k_p);
    k_i = SmartDashboard.getNumber("shooter I", k_i);
    k_f = SmartDashboard.getNumber("Shooter F", k_f);
    k_iRange = (int)(SmartDashboard.getNumber("Shooter Izone", k_iRange));

    m_shooterController.setFF(k_f);
    m_shooterController.setP(k_p);
    m_shooterController.setI(k_i);
    m_shooterController.setD(0);
    m_shooterController.setIZone(k_iRange);
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

  public void setSpeed(double speed) {
    
  }

  public double getSpeed() {
    return 0;
  }

  public void stop() {
    m_shooter.set(0);
    m_backWheels.set(0);
  }
}
