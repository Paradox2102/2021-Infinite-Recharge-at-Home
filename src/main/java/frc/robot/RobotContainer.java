/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.CameraReciever.BallCamera;
import frc.lib.Camera;
import frc.robot.PositionTracker.PositionContainer;
import frc.robot.Triggers.DecreaseTrimTrigger;
import frc.robot.Triggers.IncreaseTrimTrigger;
import frc.robot.commands.Drive.ArcadeDriveCommand;
import frc.robot.commands.Drive.DriveToTargetSizeCommand;
import frc.robot.commands.GalacticSearch.BarrelPath;
import frc.robot.commands.GalacticSearch.BouncePath;
import frc.robot.commands.GalacticSearch.PathChooserCommandAll;
import frc.robot.commands.GalacticSearch.SlalomPath;
import frc.robot.commands.GalacticSearch.driveToBallCommand;
import frc.robot.commands.Intake.DropIntake;
import frc.robot.commands.Intake.RaiseIntake;
import frc.robot.commands.Serializer.PowerSerializeCommand;
import frc.robot.commands.Shooter.SetAngleCommand;
import frc.robot.commands.Shooter.SpeedByThrottleCommand;
import frc.robot.commands.Shooter.SpinUpShooterCommand;
import frc.robot.commands.Teleop.FireCommand;
import frc.robot.commands.Teleop.UnJumbleCommand;
import frc.robot.commands.Throat.ThroatAtSpeedCommand;
import frc.robot.commands.Turret.TurretMoveCommand;
import frc.robot.commands.Turret.TurretTrackingCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.DriveSubsystemSPARKMAX;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.SerializerSubsystem;
import frc.robot.subsystems.ShooterAngleSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ThroatSubsystem;
import frc.robot.subsystems.TurretSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  DriveSubsystem m_driveSubsystem = (DriveSubsystem) new DriveSubsystemSPARKMAX();
  TurretSubsystem m_turretSubsystem = new TurretSubsystem();
  ThroatSubsystem m_throatSubsystem = new ThroatSubsystem();
  ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  ShooterAngleSubsystem m_shooterAngleSubsystem = new ShooterAngleSubsystem();
  SerializerSubsystem m_serializerSubsystem = new SerializerSubsystem();
  IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();

  Camera m_turretCamera = new Camera();
  Camera m_backCamera = new Camera();
  BallCamera m_cam;

  Joystick m_showStick = new Joystick(0);
  Joystick m_stick = new Joystick(1);
  Joystick m_climbStick = new Joystick(2);
  Joystick m_calibStick = new Joystick(3);
  Joystick m_powerPortStick = new Joystick(4);
  Joystick m_velocityStick = new Joystick(5);

  // show stick
  // JoystickButton m_unJumble = new JoystickButton(m_showStick, 4);
  // JoystickButton m_intake = new JoystickButton(m_showStick, 3);
  // JoystickButton m_spinUp = new JoystickButton(m_showStick, 2);
  // JoystickButton m_turretTrack = new JoystickButton(m_showStick, 2);
  // JoystickButton m_fire = new JoystickButton(m_showStick, 1);
  // JoystickButton m_moveTurrentL = new JoystickButton(m_showStick, 7);
  // JoystickButton m_moveTurrentR = new JoystickButton(m_showStick, 8);
  // JoystickButton m_serialize = new JoystickButton(m_showStick, 5);

  // Power Port Challenge Buttons
  JoystickButton m_portFire = new JoystickButton(m_powerPortStick, 1);
  JoystickButton m_portTrack = new JoystickButton(m_powerPortStick, 3);
  JoystickButton m_farShoot = new JoystickButton(m_powerPortStick, 7);
  JoystickButton m_farMidShoot = new JoystickButton(m_powerPortStick, 8);
  JoystickButton m_closeMidShoot = new JoystickButton(m_powerPortStick, 9);
  JoystickButton m_closeShoot = new JoystickButton(m_powerPortStick, 10);

  // Driver 1 Buttons
  JoystickButton m_intake = new JoystickButton(m_stick, 3); // While hold move down and spin. move up on release
  JoystickButton m_driverTrack = new JoystickButton(m_stick, 4);

  // // Driver 2 Buttons
  JoystickButton m_spinUp = new JoystickButton(m_climbStick, 2); // Toggle command to rev up the shooter to specified
                                                                 // speed.
  JoystickButton m_turretTrack = new JoystickButton(m_climbStick, 2); // Toggle command to start turret tracking with
                                                                      // front camera.
  JoystickButton m_fire = new JoystickButton(m_climbStick, 1); // Hold command to run the throat only when shooter is
                                                               // revved
  JoystickButton m_moveTurrent = new JoystickButton(m_climbStick, 9); // Toggle command to turn the turret manually
                                                                      // (Should cancel tracking command if in use)
  // JoystickButton m_moveTurrentR = new JoystickButton(m_climbStick, 4); //
  // Toggle command to turn the turret manually
  // (Should cancel tracking command if in use)
  JoystickButton m_unJumble = new JoystickButton(m_climbStick, 8); // Toggle command to run intake, vbelt, and throat in
                                                                   // reverse
  JoystickButton m_serialize = new JoystickButton(m_climbStick, 7); // Toggle command to run serializer (V-Belt)
  // Controls shooter hood with throttle

  // Calibration buttons
  JoystickButton m_calibrateShooter = new JoystickButton(m_calibStick, 2);

  // JoystickButton m_raiseIntake = new JoystickButton(m_stick, 4);
  // JoystickButton m_feederIntake = new JoystickButton(m_stick, 6);

  // JoystickButton m_spinUpClimb = new JoystickButton(m_climbStick, 2);
  // JoystickButton m_spinUpTrackClimb = new JoystickButton(m_climbStick, 2);

  // JoystickButton m_manualControlPanel = new JoystickButton(m_climbStick, 5);

  // JoystickButton m_outtake = new JoystickButton(m_stick, 5);
  // JoystickButton m_controlPanel = new JoystickButton(m_climbStick, 6);

  // JoystickButton m_outtakeClimb = new JoystickButton(m_climbStick, 3);
  // JoystickButton m_intakeClimb = new JoystickButton(m_climbStick, 4);

  // JoystickButton m_climb = new JoystickButton(m_climbStick, 7);

  // JoystickButton m_feederIntakeClimb = new JoystickButton(m_climbStick, 8);

  // JoystickButton m_offsetRight = new JoystickButton(m_climbStick, 10);
  // JoystickButton m_offsetLeft = new JoystickButton(m_climbStick, 9);

  // JoystickButton m_calibrateSpeed = new JoystickButton(m_calibStick, 1);
  // JoystickButton m_calibrateSpeedShooter = new JoystickButton(m_calibStick, 2);
  // JoystickButton m_snootTesting = new JoystickButton(m_calibStick, 3);
  // JoystickButton m_turretTrackCalib = new JoystickButton(m_calibStick, 4);

  // JoystickButton m_snootSetRotation = new JoystickButton(m_calibStick, 5); //
  // snooter is snooting

  JoystickButton m_trackBalls = new JoystickButton(m_calibStick, 6);
  // JoystickButton m_toggleIntake = new JoystickButton(m_calibStick, 8);

  // JoystickButton m_galacticSearchA = new JoystickButton(m_stick, 12);
  // JoystickButton m_galacticSearchB = new JoystickButton(m_stick, 12);
  // JoystickButton m_bouncePath = new JoystickButton(m_stick, 12);
  // JoystickButton m_barrelPath = new JoystickButton(m_stick, 12);
  // JoystickButton m_slalomPath = new JoystickButton(m_stick, 12);
  // JoystickButton m_calibratePowerCommand = new JoystickButton(m_stick, 11);

  IncreaseTrimTrigger m_increaseTrim = new IncreaseTrimTrigger(m_climbStick);
  DecreaseTrimTrigger m_decreaseTrim = new DecreaseTrimTrigger(m_climbStick);

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  double m_shooterSpeed = 33000;// 31000; //36000;

  double m_shooterPower = 0.5;
  double m_backWheelPower = 0.5;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    DriverStation.getInstance().silenceJoystickConnectionWarning(true);
    // Configure the button bindings
    m_turretCamera.connect(Constants.m_robotConstants.k_ipAddress);
    m_backCamera.connect(Constants.m_robotConstants.k_ipAddressBack);
    m_cam = new BallCamera("10.21.2.50", 1234);

    configureButtonBindings();

    // m_intakeSubsystem.setDefaultCommand(new RaiseIntake(m_intakeSubsystem,
    // 0.25));
    m_shooterAngleSubsystem
        .setDefaultCommand(new SetAngleCommand(m_shooterAngleSubsystem, () -> m_climbStick.getThrottle()));
    m_driveSubsystem.setDefaultCommand(new ArcadeDriveCommand(m_driveSubsystem, () -> m_stick.getX(),
        () -> (-m_stick.getY() - m_velocityStick.getY()), () -> m_stick.getThrottle()));
    // m_driveSubsystem.setDefaultCommand(new ArcadeDriveCommand(m_driveSubsystem,
    // () -> m_stick.getX(),
    // () -> (-m_stick.getY()- m_velocityStick.getY()), () ->
    // m_stick.getThrottle()));
    // m_serializerSubsystem.setDefaultCommand(new
    // SerializeCommand(m_serializerSubsystem, 0.3,
    // () -> m_throatSubsystem.GetTopBreak(), () -> getThrottle(), () ->
    // !m_throatSubsystem.GetTopBreak()));
    m_throatSubsystem.setDefaultCommand(new ThroatAtSpeedCommand(m_throatSubsystem, 0.5));

    // m_intakeSubsystem.setDefaultCommand(new
    // AmbientIntakePowerCommand(m_intakeSubsystem, 0.25));

    m_chooser.addOption("Slalom Path", new SlalomPath(m_driveSubsystem));
    m_chooser.addOption("Bounce Path", new BouncePath(m_driveSubsystem));
    m_chooser.addOption("Barrel Path", new BarrelPath(m_driveSubsystem));
    m_chooser.addOption("Galactic Search", new PathChooserCommandAll(m_cam, m_driveSubsystem, m_intakeSubsystem, 0.3, 0.3));
    SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Power Port Bindings
    m_portFire.whileHeld(new FireCommand(m_throatSubsystem, m_shooterSubsystem));
    m_portTrack.toggleWhenPressed(new TurretTrackingCommand(m_turretSubsystem, m_turretCamera));
    m_farShoot
        .toggleWhenPressed(new DriveToTargetSizeCommand(m_shooterSubsystem, m_shooterAngleSubsystem, 0.23, 2400));
    m_farMidShoot
        .toggleWhenPressed(new DriveToTargetSizeCommand(m_shooterSubsystem, m_shooterAngleSubsystem, 0.28, 2600));
    m_closeMidShoot
        .toggleWhenPressed(new DriveToTargetSizeCommand(m_shooterSubsystem, m_shooterAngleSubsystem, 0.47, 2600));
    m_closeShoot
        .toggleWhenPressed(new DriveToTargetSizeCommand(m_shooterSubsystem, m_shooterAngleSubsystem, 0.8, 2600));

    // Driver 1 bindings
    // m_intake.whileHeld(new DropIntake(m_intakeSubsystem, 0.3, 0.7));
    // m_intake.whenReleased(new RaiseIntake(m_intakeSubsystem, 0.25));
    m_intake.toggleWhenPressed(new DropIntake(m_serializerSubsystem, m_intakeSubsystem, 0.2, 0.7));
    m_driverTrack.toggleWhenPressed(new TurretTrackingCommand(m_turretSubsystem, m_turretCamera));
    // m_intake.whileHeld(new IntakeCommand(m_intakeSubsystem, 1.0));
    m_unJumble.whileHeld(new UnJumbleCommand(m_intakeSubsystem, m_throatSubsystem, m_serializerSubsystem));

    // Driver 2 bindings
    m_spinUp.toggleWhenPressed(new SpinUpShooterCommand(m_shooterSubsystem, m_shooterPower, m_backWheelPower, m_stick));
    m_fire.whileHeld(new FireCommand(m_throatSubsystem, m_shooterSubsystem));
    m_turretTrack.toggleWhenPressed(new TurretTrackingCommand(m_turretSubsystem, m_turretCamera));
    m_moveTurrent.whileHeld(new TurretMoveCommand(m_turretSubsystem, () -> m_climbStick.getZ()));
    // m_moveTurrentL.whileHeld(new TurretMoveCommand(m_turretSubsystem, -0.6));
    // m_moveTurrentR.whileHeld(new TurretMoveCommand(m_turretSubsystem, 0.6));
    // m_unJumble.toggleWhenPressed(new UnJumbleCommand(m_intakeSubsystem,
    // m_throatSubsystem, m_serializerSubsystem));
    m_serialize.toggleWhenPressed(new PowerSerializeCommand(m_serializerSubsystem, -0.3));

    // Calibration bindings
    // m_calibrateShooter.toggleWhenPressed(new
    // CalibrateShooterSpeedCommand(m_shooterSubsystem, 1500.0));
    m_calibrateShooter
        .toggleWhenPressed(new SpeedByThrottleCommand(m_shooterSubsystem, () -> m_calibStick.getThrottle()));

    // m_calibrateBtn.whileHeld(new SpeedCommand(m_driveSubsystem, 11.94));
    m_trackBalls.toggleWhenPressed(new driveToBallCommand(m_cam, m_driveSubsystem, 0.25));
    // m_shoot.toggleWhenPressed(new ShootAllCommand(m_throatSubsystem,
    // m_shooterSubsystem, m_serializerSubsystem, m_indexerSubsystem,
    // m_intakeSubsystem, () -> getThrottle()));
    // m_intake.toggleWhenPressed(new IntakeCommand(m_intakeSubsystem, 0.6));

    // m_outtake.toggleWhenPressed(new IntakeCommand(m_intakeSubsystem, -0.75));
    // m_outtakeClimb.whileHeld(new IntakeCommand(m_intakeSubsystem, -0.75));

    // m_spinUpTrack.toggleWhenPressed(new TurretTrackingCommand(m_turretSubsystem,
    // m_turretCamera));
    // m_spinUpClimb.toggleWhenPressed(
    // new SpinUpCommand(m_turretSubsystem, m_turretCamera, m_shooterSubsystem,
    // m_indexerSubsystem, m_shooterSpeed));
    // m_spinUpTrackClimb.toggleWhenPressed(new
    // TurretTrackingCommand(m_turretSubsystem, m_turretCamera));

    // m_offsetLeft.whenPressed(new IncrementOffsetCommand(m_turretSubsystem, -5));
    // m_offsetRight.whenPressed(new IncrementOffsetCommand(m_turretSubsystem, 5));

    // m_feederIntakeClimb.whileHeld(new
    // AmbientIntakePowerCommand(m_intakeSubsystem, -0.5));
    // m_feederIntake.whileHeld(new AmbientIntakePowerCommand(m_intakeSubsystem,
    // -0.5));

    // m_manualControlPanel.whileActiveOnce(new
    // FixedRotationCommand(m_snootSubsystem, 0.25, 4.125));

    // m_increaseTrim.whenActive(new IncrementTrimCommand(m_shooterSubsystem, 500),
    // true);
    // m_decreaseTrim.whenActive(new IncrementTrimCommand(m_shooterSubsystem, -500),
    // true);

    // m_climb.whileHeld(new MoveClimberCommand(m_climberSubsystem, () ->
    // -m_climbStick.getY()));
    // m_calibrateSpeed.whileHeld(new ThroatMoveCommand(m_throatSubsystem, 0.85));
    // m_calibrateSpeedShooter.toggleWhenPressed(
    // new frc.robot.commands.Shooter.CalibrateSpeedCommand(m_shooterSubsystem, ()
    // -> getThrottleCalib()));
    // m_throat.toggleWhenPressed(new ParallelDeadlineGroup(new
    // ThroatAtSpeedCommand(m_throatSubsystem, 0.75), new
    // IntakeCommand(m_intakeSubsystem, 0.5)));
    // m_snootTesting.whileHeld(new SnootTesting(m_snootSubsystem, 0.25));
    // m_snootSetRotation.whenPressed(new FixedRotationCommand(m_snootSubsystem,
    // 0.25, 3.2));

    // m_toggleIntake.toggleWhenPressed(new
    // ActuateIntakeCommand(m_intakeSubsystem));

    // m_trackBalls.whileHeld(new BallDriveCommand(m_driveSubsystem, m_backCamera,
    // 0.25, BallSide.LEFT, true, 20));

    // m_turretTrackCalib.toggleWhenPressed(new
    // TurretTrackingCommand(m_turretSubsystem, m_turretCamera));

    // m_galacticSearchA.toggleWhenPressed(new
    // PathChooserCommandGroupA(m_backCamera, m_driveSubsystem, m_intakeSubsystem,
    // Constants.k_searchPower, Constants.k_turnPower));

    // m_galacticSearchB.toggleWhenPressed(new
    // PathChooserCommandGroupB(m_backCamera, m_driveSubsystem, m_intakeSubsystem,
    // Constants.k_searchPower, Constants.k_turnPower));
    // m_bouncePath.toggleWhenPressed(new BouncePath(m_driveSubsystem));
    // m_barrelPath.toggleWhenPressed(new BarrelPath(m_driveSubsystem));
    // m_slalomPath.toggleWhenPressed(new SlalomPath(m_driveSubsystem));
    // m_calibratePowerCommand.toggleWhenPressed(new
    // CalibrateSpeedCommand(m_driveSubsystem, 2200));
  }

  public void periodic() {
    // SmartDashboard.putNumber("TargetArea",
    // (m_turretCamera.createData().getTargetHeight() *
    // // m_turretCamera.createData().getTargetWidth()));
    // SmartDashboard.putNumber("XMin", m_cam.getRegions()[0].getLeftBound());
    // SmartDashboard.putNumber("XMax", m_cam.getRegions()[0].getRightBound());

    String color = DriverStation.getInstance().getGameSpecificMessage();
    String pColor = "None";
    if (color.length() > 0) {
      switch (color.charAt(0)) {
      case 'B':
        pColor = "Red";
        break;
      case 'R':
        pColor = "Blue";
        break;
      case 'G':
        pColor = "Yellow";
        break;
      case 'Y':
        pColor = "Green";
        break;
      }
    } else {
      pColor = "None";
    }
    SmartDashboard.putString("Control Panel Color:", pColor);
    // SmartDashboard.putNumber("Trim", m_shooterSubsystem.getTrim());
    SmartDashboard.putNumber("Time Left", DriverStation.getInstance().getMatchTime());
    SmartDashboard.putNumber("Offset", m_turretSubsystem.getOffset());
    SmartDashboard.putBoolean("Lights State", m_turretCamera.getLightsState());

    SmartDashboard.putNumber("Joystick Z", m_climbStick.getZ());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_chooser.getSelected();
  }

  public void startPosTracking() {
    m_driveSubsystem.startPosUpdate();
  }

  public void stopPosTracking() {
    m_driveSubsystem.stopPosUpdate();
  }

  public void setCoastMode() {
    m_driveSubsystem.setCoastMode();
  }

  public void setBrakeMode() {
    m_driveSubsystem.setBrakeMode();
  }

  public void setPos(double x, double y) {
    m_driveSubsystem.setPos(x, y);
  }

  public void setAngle(double angle) {
    m_driveSubsystem.resetAngle(angle);
  }

  public PositionContainer getPos() {
    return m_driveSubsystem.getPos();
  }

  public double getAngle() {
    return m_driveSubsystem.getAngle();
  }

  // throttle for climb stick
  public double getThrottle() {
    return (-m_climbStick.getThrottle() + 1) / 2.0;
  }

  // throttle for drive stick
  public double getDriveThrottle() {
    return (-m_stick.getThrottle() + 1) / 2.0;
  }

  // throttle for climb stick
  public double getThrottleCalib() {
    return (-m_calibStick.getThrottle() + 1) / 2.0;
  }

  public double getTargetHeight() {
    return m_turretCamera.createData().getTargetHeight();
  }

  public double getTargetWidth() {
    return m_turretCamera.createData().getTargetWidth();
  }

  public boolean canSee() {
    return m_turretCamera.createData().canSee();
  }

  // public void setTrim(double amount) {
  // m_shooterSubsystem.setTrim(amount);
  // }

  public void setLightsBackCamera(boolean lights) {
    m_backCamera.toggleLights(lights);
  }
}
