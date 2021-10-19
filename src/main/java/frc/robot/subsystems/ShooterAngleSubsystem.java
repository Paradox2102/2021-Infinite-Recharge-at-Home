// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.PiCamera.Logger;
import frc.robot.Constants;

public class ShooterAngleSubsystem extends SubsystemBase {
  /** Creates a new ShooterAngleSubsystem. */

  //maximum is 0.230469

  Servo m_angleServo = new Servo(Constants.k_shooterAngle);

  ShuffleboardTab driverTab = Shuffleboard.getTab("Driver Tab");
  
  NetworkTableEntry m_fudgeFactor = driverTab.add("Angle Factor", 0).withWidget(BuiltInWidgets.kNumberSlider)
      .withProperties(Map.of("min", -0.3, "max", 0.3)).getEntry();

  public ShooterAngleSubsystem() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setAngle(double angle) {
    //Logger.Log("Shooter angle", 1, angle+"");
    if(angle + m_fudgeFactor.getDouble(0) > 0.25) {
      SmartDashboard.putNumber("Servo Angle", angle);
      m_angleServo.set(angle + (m_fudgeFactor.getDouble(0)));
    } else {
      SmartDashboard.putNumber("Servo Angle", 0.25);
      m_angleServo.set(0.25);
    }
  }

  public void stop() {
    Logger.Log("ShooterAngleSubsystem", 1, "Servo Disabled");
    m_angleServo.setDisabled();
  }

}
