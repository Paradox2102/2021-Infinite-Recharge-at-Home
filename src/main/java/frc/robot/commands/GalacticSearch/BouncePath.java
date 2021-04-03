/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.GalacticSearch;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.PiCamera.Logger;
import frc.pathfinder.Pathfinder.Waypoint;
import frc.robot.PurePursuit.PathConfigs;
import frc.robot.commands.Auto.CreatePathCommand;
import frc.robot.subsystems.DriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class BouncePath extends ParallelCommandGroup {
  /**
   * Creates a new pathAuto.
   */

  static final Waypoint[] k_legOne = { 
      new Waypoint(0, 3.5, Math.toRadians(90)),
      new Waypoint(-3.5, 7.5, Math.toRadians(180)),
     };
     static final Waypoint[] k_legTwo = { 
      new Waypoint(-3.5, 7.5, Math.toRadians(0), 1, 1,0),
      new Waypoint(5, 12.5, Math.toRadians(90), 2, 6, 9),
      new Waypoint(-4.25, 15, Math.toRadians(180)),
     };

     
/*
-4.25,15,0,4.652,0.919,0
5.059,17.086,89.339,3.861,4.256,0
-3.55,22.145,168.567
*/
/*
-4.25,15,0,4.652,0.919,0
5.059,17.086,91.886,4.04,3.983,0
-3.595,21.435,165.809
*/
/*
-4.25,15,0,4.652,0.919,0
5.059,17.086,91.886,4.04,4.559,0
-4.216,23.166,148.937
*/
/*
-4.25,15,0,4.652,0.919,0
5.37,16.331,90.003,3.506,5.22,0
-4.216,23.166,142.251
*/
/*
-4.25,15,0,4.652,0.919,0
5.37,16.331,90.003,3.506,5.006,0
-4.083,22.766,142.931
*/
/*
-4.25,15,0,4.652,0.919,0
5.37,16.331,90.003,3.506,5.006,0
-3.728,22.5,142.931
*/
/*
-4.25,15,0,4.652,0.919,0
5.37,16.331,90.003,3.506,5.006,0
-4.527,22.411,142.931
*/
static final Waypoint[] k_legThree = {
  new Waypoint(-4.25, 15, Math.toRadians(0), 4.652, 0.919, 0),
  new Waypoint(5.37, 16.331, Math.toRadians(90.003), 3.506, 5.006, 0),
  new Waypoint(-4.527, 22.411, Math.toRadians(142.931))
};
/*
-4.527, 22.411,3.891,4.582,1.96
-0.266,27.293,95.184
*/
static final Waypoint[] k_legFour = {
  new Waypoint(-4.527,  22.411, Math.toRadians(3.891), 4.582, 1.96, 0),
  new Waypoint(-0.266, 27.293, Math.toRadians(95.184))
};
  DriveSubsystem m_driveSubsystem;

  public BouncePath(DriveSubsystem driveSubsystem) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    m_driveSubsystem = driveSubsystem;
    addCommands(new SequentialCommandGroup(
      new CreatePathCommand(driveSubsystem, k_legOne, PathConfigs.bounce),
      new CreatePathCommand(driveSubsystem, k_legTwo, PathConfigs.bounce, true, true, true),
      new CreatePathCommand(driveSubsystem, k_legThree, PathConfigs.bounce),
      new CreatePathCommand(driveSubsystem, k_legFour, PathConfigs.bounce, true, true, true)
      ));
  }

  @Override
  public void initialize() {
    super.initialize();
    m_driveSubsystem.resetAngle(90);
    m_driveSubsystem.setPos(0, 3.5);
    Logger.Log("bouncePath", 0, "initialize");
  }
}
