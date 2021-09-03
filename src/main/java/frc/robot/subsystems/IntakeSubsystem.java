/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  TalonSRX m_intake = new TalonSRX(Constants.k_intake);
  CANSparkMax m_intakeDeploy = new CANSparkMax(Constants.k_intakeDeploy, MotorType.kBrushless);
  CANEncoder m_intakeDeployEncoder;

  double m_systime;
  double m_lastPower;
  
  public IntakeSubsystem() {
    m_intakeDeploy.restoreFactoryDefaults();

    m_intakeDeployEncoder = m_intakeDeploy.getEncoder();
    m_intakeDeploy.setIdleMode(IdleMode.kCoast);
    // m_intakeDeploy.setSmartCurrentLimit(10, 100);

    m_intake.setInverted(false);

    m_intakeDeploy.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen).enableLimitSwitch(true);
    m_intakeDeploy.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen).enableLimitSwitch(false);


    m_systime = System.currentTimeMillis();
    m_lastPower = 0;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("ForwardLimitIntake", isForwardLimitEnabled());
    SmartDashboard.putBoolean("ReverseLimitIntake", isReverseLimitEnabled());
    
    if (isReverseLimitEnabled()) {
      if (System.currentTimeMillis() - m_systime >= 5*1000) {
        stopDeploy();
      } else if(m_lastPower < 0){
        m_intakeDeploy.set(-0.1);
      }
    } else {
      m_systime = System.currentTimeMillis();
    }
  }

  public double getEncoder() {
    return m_intakeDeployEncoder.getPosition();
  }

  public boolean isForwardLimitEnabled() {
    return m_intakeDeploy.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen).get();
  }

  public boolean isReverseLimitEnabled() {
    return m_intakeDeploy.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen).get();
  }

  public void setPower(double power){
    m_intake.set(ControlMode.PercentOutput, power);
  }

  public void setAmbientPower(double power){
    m_intake.set(ControlMode.PercentOutput, power);
  }

  public void stop(){
    m_intake.set(ControlMode.PercentOutput, 0);
  }

  public void setBrakeMode() {
    m_intakeDeploy.setIdleMode(IdleMode.kBrake);
  }

  public void setCoastMode() {
    m_intakeDeploy.setIdleMode(IdleMode.kCoast);
  }

  public void deploy(double power){
    m_intakeDeploy.set(power);
    m_systime = System.currentTimeMillis();
    m_lastPower = power;
  }
  public void stopDeploy() {
    m_intakeDeploy.set(0);
  }
}
