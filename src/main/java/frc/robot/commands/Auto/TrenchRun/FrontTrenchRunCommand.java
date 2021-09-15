/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Auto.TrenchRun;

import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.lib.Camera;
import frc.pathfinder.Pathfinder.Waypoint;
import frc.robot.PurePursuit.PathConfigs;
import frc.robot.commands.Auto.CreatePathCommand;
import frc.robot.commands.Auto.FireCommandAuto;
import frc.robot.commands.Intake.IntakeCommand;
import frc.robot.commands.Teleop.FireCommand;
import frc.robot.commands.Throat.ThroatPowerCommand;
import frc.robot.commands.Turret.TurretTrackingCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ThroatSubsystem;
import frc.robot.subsystems.TurretSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class FrontTrenchRunCommand extends ParallelDeadlineGroup {
  /**
   * Creates a new FrontTrenchRunCommand.
   */
  final static Waypoint[] k_forwardsTrench = { new Waypoint(-11, 25, Math.toRadians(-90), 5),
      new Waypoint(-11, 18.5, Math.toRadians(-90)) };

  public FrontTrenchRunCommand(DriveSubsystem driveSubsystem, TurretSubsystem turretSubsystem,
      ThroatSubsystem throatSubsystem, ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem,
      Camera turretCamera) {

    super(new CreatePathCommand(driveSubsystem, k_forwardsTrench, PathConfigs.fastAccel),
          new SequentialCommandGroup(new WaitCommand(2), 
          new FireCommandAuto(throatSubsystem, turretSubsystem, shooterSubsystem, turretCamera, 50)));
  }
}
