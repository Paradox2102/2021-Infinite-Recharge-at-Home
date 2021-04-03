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
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.SerializerSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ClosePathAuto extends ParallelCommandGroup {
  /**
   * Creates a new driveToBallAuto.
   */
  final static double k_turnPower = .25;
  final static double k_waitTime = 0.25;

  public ClosePathAuto(BallCamera camera, DriveSubsystem driveSubsystem, IntakeSubsystem intakeSubsystem, SerializerSubsystem serializerSubsystem,
      double searchPower, double drivePower) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    addCommands(/* new IntakeCommand(intakeSubsystem, 0.9), */
        new DropIntake(serializerSubsystem, intakeSubsystem, 1.0, 0.7),
        new SequentialCommandGroup(
            // new DriveByDistanceCommand(driveSubsystem, 1, -0.6),
            new driveToBallCommand(camera, driveSubsystem, searchPower),
            new WaitCommand(k_waitTime),
            new SmoothTurnCommand(driveSubsystem, 10, -drivePower, k_turnPower),
            new WaitCommand(k_waitTime),
            new driveToBallCommand(camera, driveSubsystem, searchPower),
            new WaitCommand(k_waitTime),
            new SmoothTurnCommand(driveSubsystem, -60, -drivePower, k_turnPower),
            new WaitCommand(k_waitTime),
            new driveToBallCommand(camera, driveSubsystem, searchPower),
            new WaitCommand(k_waitTime),
            new SmoothTurnCommand(driveSubsystem, 40, -drivePower, k_turnPower),
            new WaitCommand(k_waitTime),
            new driveToBallCommand(camera, driveSubsystem, 0.65)
            ));
  }
}
