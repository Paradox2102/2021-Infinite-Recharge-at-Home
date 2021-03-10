/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.PiCamera.Logger;
import frc.robot.subsystems.ShooterSubsystem;

public class SpinUpShooterCommand extends CommandBase {
  /**
   * Creates a new SpinUpShooterCommand.
   */

   ShooterSubsystem m_subsystem;
   double m_shooterPower;
   double m_backWheelPower;
   Joystick m_stick;


  public SpinUpShooterCommand(ShooterSubsystem subsystem, double shooterPower, double backWheelPower, Joystick stick) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_subsystem = subsystem;
    m_shooterPower = shooterPower;
    m_backWheelPower = backWheelPower;
    m_stick = stick;

    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Logger.Log("SpinUPCommand", 1, "Initialized");
    m_subsystem.setSpeed(2400, 2400);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // m_shooterPower = (m_stick.getThrottle() + 1)/2;
    // m_backWheelPower = (m_stick.getThrottle() + 1)/
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return false;
  }
}
