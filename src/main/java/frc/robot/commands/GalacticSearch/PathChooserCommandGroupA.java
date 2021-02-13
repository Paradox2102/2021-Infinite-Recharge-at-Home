/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.GalacticSearch;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.lib.Camera;
import frc.lib.Logger;
import frc.lib.Camera.CameraData;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class PathChooserCommandGroupA extends InstantCommand {
  /**
   * Creates a new PathChooserCommand.
   */
  Camera m_camera;
  DriveSubsystem m_subsystem;
  IntakeSubsystem m_intakeSubsystem;
  double m_searchPower;
  double m_turnPower;

  public PathChooserCommandGroupA(Camera camera, DriveSubsystem subsystem, IntakeSubsystem intakeSubsystem,
      double searchPower, double turnPower) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_camera = camera;
    m_subsystem = subsystem;
    m_intakeSubsystem = intakeSubsystem;
    m_searchPower = searchPower;
    m_turnPower = turnPower;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Logger.Log("PathChooserCommand", 1, "initialize");
    CameraData cameraData = m_camera.createData();

    if (cameraData.canSee()) {
      int positionY = cameraData.m_regions.GetRegion(0).m_bounds.m_top;
      Logger.Log("PositionY:", 1, "" + positionY);
      if (positionY > 120) {
        Logger.Log("Path Chooser A", 1, "Choosing close path");
        new closePathAutoGroupA(m_camera, m_subsystem, m_intakeSubsystem, m_searchPower, m_turnPower).schedule();
      } else {
        Logger.Log("Path Chooser A", 1, "Choosing far path");
        new farPathAutoGroupA(m_camera, m_subsystem, m_searchPower, m_turnPower).schedule();
      }
    } else {
      Logger.Log("Can see:", 1, "CANT SEE");
    }
  }
}
