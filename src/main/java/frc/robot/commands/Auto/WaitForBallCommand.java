/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.Camera;
import frc.lib.Camera.BallSide;
import frc.lib.Camera.CameraData;

public class WaitForBallCommand extends CommandBase {
  /**
   * Creates a new WaitForBallCommand.
   */
  Camera m_camera;
  public WaitForBallCommand(Camera camera) {
    m_camera = camera;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    CameraData data = m_camera.createData();
    int size = data.ballFilter().size();
    return size >= 1 && data.ballBelowHeight(200, data.ballFilter(), BallSide.RIGHT);
  }
}
