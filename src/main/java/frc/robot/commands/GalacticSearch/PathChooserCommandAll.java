/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.GalacticSearch;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.CameraReciever.BallCamera;
import frc.lib.Camera;
import frc.lib.Logger;
import frc.lib.Camera.CameraData;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.SerializerSubsystem;

public class PathChooserCommandAll extends InstantCommand {
  /**
   * Creates a new PathChooserCommand.
   */
  BallCamera m_camera;
  DriveSubsystem m_subsystem;
  IntakeSubsystem m_intakeSubsystem;
  SerializerSubsystem m_serializerSubsystem;
  double m_searchPower;
  double m_turnPower;

  public PathChooserCommandAll(BallCamera camera, DriveSubsystem subsystem, IntakeSubsystem intakeSubsystem, SerializerSubsystem serializerSubsystem,
      double searchPower, double turnPower) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_camera = camera;
    m_subsystem = subsystem;
    m_intakeSubsystem = intakeSubsystem;
    m_searchPower = searchPower;
    m_turnPower = turnPower;
    m_serializerSubsystem = serializerSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Logger.Log("PathChooserCommand", 1, "initialize");
    // CameraData cameraData = m_camera.createData();

    if (m_camera.getRegions() != null) {
      double positionY = m_camera.findClosestRegion(m_camera.getRegions()).getTopBound();
      Logger.Log("PositionY:", 1, "" + positionY);
      if (positionY > 95) {
        Logger.Log("Path Chooser", 1, "Choosing close path");
        new ClosePathAuto(m_camera, m_subsystem, m_intakeSubsystem, m_serializerSubsystem, m_searchPower, m_turnPower).schedule();
      } else {
        Logger.Log("Path Chooser", 1, "Choosing far path");
        new FarPathAuto(m_camera, m_subsystem, m_intakeSubsystem, m_serializerSubsystem, m_searchPower, m_turnPower).schedule();
      }
    } else {
      Logger.Log("Can see:", 1, "CANT SEE");
    }
  }
}
