/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANDigitalInput.LimitSwitch;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {
  CANSparkMax m_climber = new CANSparkMax(Constants.k_climb, MotorType.kBrushless);
  CANSparkMax m_follower = new CANSparkMax(Constants.k_climbFollower, MotorType.kBrushless);

  CANEncoder m_encoder = new CANEncoder(m_climber);
  CANDigitalInput m_revLimit = new CANDigitalInput(m_climber, LimitSwitch.kReverse, LimitSwitchPolarity.kNormallyOpen);
  CANDigitalInput m_fwdLimit = new CANDigitalInput(m_climber, LimitSwitch.kForward, LimitSwitchPolarity.kNormallyOpen);

  Solenoid m_brake = new Solenoid(Constants.k_brake);

  public ClimberSubsystem() {
    m_follower.follow(m_climber, true);

    m_climber.setInverted(false);
    m_encoder = m_climber.getEncoder();

    m_fwdLimit.enableLimitSwitch(true);

    m_climber.setIdleMode(IdleMode.kBrake);
    m_follower.setIdleMode(IdleMode.kBrake);
  }

  @Override
  public void periodic() {
    checkReset();
    SmartDashboard.putBoolean("Rev Limit", m_revLimit.get());
    SmartDashboard.putBoolean("Fwd Limit", m_fwdLimit.get());
  }

  public void setPower(double power){
    // if((power > 0 && m_fwdLimit.get()) || (power < 0 && m_revLimit.get())){
    //   stop();
    // }else{
      setBrake(true);
      m_climber.set(power);
    // }
  }

  public void setBrake(boolean set){
    m_brake.set(set);
  }
  
  public void stop(){
    setBrake(false);
    m_climber.set(0);
  }

  public double getPos(){
    return m_encoder.getPosition();
  }

  public void resetPos(){
    m_encoder.setPosition(0);
  }

  public void checkReset(){
    if(m_revLimit.get()){
      resetPos();
    }
  }

}
