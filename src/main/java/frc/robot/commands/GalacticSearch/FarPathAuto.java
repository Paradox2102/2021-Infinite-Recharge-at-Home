/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.GalacticSearch;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.CameraReciever.BallCamera;
import frc.lib.Camera;
import frc.robot.commands.Camera.ToggleLightsCommand;
import frc.robot.commands.Drive.DriveByDistanceCommand;
import frc.robot.commands.Drive.SmoothTurnCommand;
import frc.robot.commands.Drive.TurnByAngleCommand;
import frc.robot.commands.Intake.DropIntake;
import frc.robot.commands.Intake.IntakeCommand;
import frc.robot.commands.Serializer.PowerSerializeCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.SerializerSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class FarPathAuto extends ParallelCommandGroup {
  /**
   * Creates a new farPathAuto.
   */
  final static double k_turnPower = .25;
  final static double k_waitTime = 0.25;

  public FarPathAuto(BallCamera camera, DriveSubsystem driveSubsystem, IntakeSubsystem intakeSubsystem, SerializerSubsystem serializerSubsystem,
      double searchPower, double drivePower) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    addCommands(
    new DropIntake(serializerSubsystem, intakeSubsystem, 0.35, 0.7),
        new SequentialCommandGroup(
          // new DriveByDistanceCommand(driveSubsystem, 2, -0.4),
          // new WaitCommand(0.5),
          new driveToBallCommand(camera, driveSubsystem, 0.4),
          new WaitCommand(k_waitTime),
          new SmoothTurnCommand(driveSubsystem, -30, -drivePower, k_turnPower),
          new WaitCommand(k_waitTime),
          new driveToBallCommand(camera, driveSubsystem, searchPower),
          new WaitCommand(k_waitTime),
          new SmoothTurnCommand(driveSubsystem, 60, -drivePower, searchPower),
          new WaitCommand(k_waitTime),
          new driveToBallCommand(camera, driveSubsystem, searchPower),
          new WaitCommand(k_waitTime),
          new SmoothTurnCommand(driveSubsystem, -15, -drivePower, k_turnPower),
          new WaitCommand(k_waitTime),
          new driveToBallCommand(camera, driveSubsystem, searchPower)));
  }
}
