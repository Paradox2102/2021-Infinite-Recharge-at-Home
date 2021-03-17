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
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.PiCamera.Logger;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  /**
   * Creates a new ShooterSubsystem.
   */

  CANSparkMax m_shooter = new CANSparkMax(Constants.k_shooter, MotorType.kBrushless);
  CANPIDController m_shooterController;
  CANPIDController m_backWheelController;
  CANSparkMax m_shooterFollower = new CANSparkMax(Constants.k_shooterFollower, MotorType.kBrushless);
  CANSparkMax m_backWheels = new CANSparkMax(Constants.k_backWheels, MotorType.kBrushless);

  private double k_f = 0.00023;
  private double k_p = 0.0004; // 0.4;
  private double k_i = 0.000001; // 0.032;
  private double k_d = 0.0000007;

  private double k_bf = 0.00022;
  private double k_bp = 0.00002;
  private double k_bi = 0;// 0.0000004;// 0.0000001;
  private double k_bd = 0;

  private int k_iRange = 100;

  int k_slot = 0;

  double m_setPoint = 0;

  ShuffleboardTab shooterTab = Shuffleboard.getTab("Shooter Tuning");
  NetworkTableEntry m_f;
  NetworkTableEntry m_p;
  NetworkTableEntry m_i;
  NetworkTableEntry m_iRange;
  NetworkTableEntry m_shooterSpeed;

  CANEncoder m_shooterEncoder;
  CANEncoder m_backWheelEncoder;

  public ShooterSubsystem() {
    m_shooterEncoder = m_shooter.getEncoder();
    m_backWheelEncoder = m_backWheels.getEncoder();

    m_shooterFollower.follow(m_shooter, true);
    // m_backWheels.follow(m_shooter, false);
    m_shooter.setInverted(false);

    m_shooter.setIdleMode(IdleMode.kCoast);
    m_shooterFollower.setIdleMode(IdleMode.kCoast);

    m_backWheels.setIdleMode(IdleMode.kCoast);

    // m_shooter.setClosedLoopRampRate(1);
    // m_backWheels.setOpenLoopRampRate(1);

    m_shooterController = m_shooter.getPIDController();
    m_backWheelController = m_backWheels.getPIDController();

    Logger.Log("F value main", 1, "" + k_bf);
    Logger.Log("P value main", 1, "" + k_bp);
    Logger.Log("I value main", 1, "" + k_bi);
    Logger.Log("IZone value main", 1, "" + k_iRange);
    setSparkMaxPID(m_shooterController, k_f, k_p, k_i, k_d, k_iRange);
    setSparkMaxPID(m_backWheelController, k_bf, k_bp, k_bi, k_bd, k_iRange);

    // m_shooterController.setFF(k_f);
    // m_shooterController.setP(k_p);
    // m_shooterController.setI(k_i);
    // m_shooterController.setD(0);
    // m_shooterController.setIZone(k_iRange);

    // Logger.Log("f set", 1, ""+k_bf);
    // m_backWheelController.setFF(k_bf);
    // m_backWheelController.setP(k_bp);
    // m_backWheelController.setI(k_bi);
    // m_backWheelController.setD(0);
    // m_backWheelController.setIZone(k_iRange);

    // m_f = shooterTab.add("shooter F", k_f).getEntry();
    // m_p = shooterTab.add("shooter P", k_p).getEntry();
    // m_i = shooterTab.add("shooter F", k_i).getEntry();
    // m_iRange = shooterTab.add("shooter I Range", k_f).getEntry();
    // m_shooterSpeed = shooterTab.add("shooter speed",
    // m_shooterEncoder.getVelocity()).getEntry();

    SmartDashboard.setDefaultNumber("shooter P", k_p);
    SmartDashboard.setDefaultNumber("shooter I", k_i);
    SmartDashboard.setDefaultNumber("Shooter F", k_f);
    SmartDashboard.setDefaultNumber("Shooter Izone", k_iRange);

    SmartDashboard.setDefaultNumber("back P", k_bp);
    SmartDashboard.setDefaultNumber("back I", k_bi);
    SmartDashboard.setDefaultNumber("back F", k_bf);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // SmartDashboard.putNumber("Shooter Encoder Position",
    // m_shooterEncoder.getPosition());
    // SmartDashboard.putNumber("Backwheel Encoder Postion",
    // m_backWheelEncoder.getPosition());

    SmartDashboard.putNumber("Shooter Speed", m_shooterEncoder.getVelocity());
    SmartDashboard.putNumber("Backwheel Speed", m_backWheelEncoder.getVelocity());

  }

  public void configPID() {
    k_p = SmartDashboard.getNumber("shooter P", k_p);
    k_i = SmartDashboard.getNumber("shooter I", k_i);
    k_f = SmartDashboard.getNumber("Shooter F", k_f);
    k_iRange = (int) (SmartDashboard.getNumber("Shooter Izone", k_iRange));

    // k_bp = SmartDashboard.getNumber("back P", k_bp);
    // k_bi = SmartDashboard.getNumber("back I", k_bi);
    // k_bf = SmartDashboard.getNumber("back F", k_bf);

    Logger.Log("back wheel i term", 1, String.format("%.8f", k_bi));
    m_backWheelController.setFF(k_bf);
    m_backWheelController.setP(k_bp);
    m_backWheelController.setI(k_bi);
    m_backWheelController.setD(0);
    m_backWheelController.setIZone(k_iRange);

    m_shooterController.setFF(k_f);
    m_shooterController.setP(k_p);
    m_shooterController.setI(k_i);
    m_shooterController.setD(0);
    m_shooterController.setIZone(k_iRange);
  }

  public void setShooterPower(double power) {
    m_shooterController.setReference(power, ControlType.kDutyCycle);
  }

  public void setBackWheelPower(double power) {
    m_backWheelController.setReference(power, ControlType.kDutyCycle);
  }

  public double getSetpoint() {
    return 0;
  }

  public void setSpeed(double frontSpeed, double backSpeed) {
    m_shooterController.setReference(frontSpeed, ControlType.kVelocity);
    m_backWheelController.setReference(backSpeed, ControlType.kVelocity);
    m_setPoint = frontSpeed;

  }

  public void setSparkMaxPID(CANPIDController controller, double f, double p, double i, double d, double IZone) {
    Logger.Log("F value", 1, "" + f);
    Logger.Log("P value", 1, "" + p);
    Logger.Log("I value", 1, "" + i);
    Logger.Log("D value", 1, "" + d);
    Logger.Log("IZone value", 1, "" + IZone);

    Logger.Log("Set I", 1, "" + controller.setI(i));
    Logger.Log("Set P", 1, "" + controller.setP(p));
    Logger.Log("Set F", 1, "" + controller.setFF(f));
    Logger.Log("set D", 1, "" + controller.setD(d));
    Logger.Log("Set Izone", 1, "" + controller.setIZone(IZone));
  }

  public double getSpeed() {
    return m_shooterEncoder.getVelocity();
  }

  public void stop() {
    m_shooter.set(0);
    m_backWheels.set(0);
  }
}
