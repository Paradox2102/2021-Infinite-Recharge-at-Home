// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.commands.Auto;


// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import frc.lib.Camera;
// import frc.robot.commands.Throat.ThroatPowerCommand;
// import frc.robot.subsystems.ShooterSubsystem;
// import frc.robot.subsystems.ThroatSubsystem;
// import frc.robot.subsystems.TurretSubsystem;

// // NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// // information, see:
// // https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
// public class FireCommandAuto extends SequentialCommandGroup {
//   /**
//    * Creates a new FireCommandAuto.
//    */
//   public FireCommandAuto(ThroatSubsystem throatSubsystem, TurretSubsystem turretSubsystem, ShooterSubsystem shooterSubsystem, Camera turretCamera,
//       double deadzone) {
//     // Add your commands in the super() call, e.g.
//     // super(new FooCommand(), new BarCommand());
//     super(new ThroatPowerCommand(throatSubsystem, () -> shooterSubsystem.getSpeed(),
//         () -> shooterSubsystem.getSetpoint() - 4000, 0.85, deadzone, turretCamera, () -> turretSubsystem.getOffset(), false));
//   }
// }
