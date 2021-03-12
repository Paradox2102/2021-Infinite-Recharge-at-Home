/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drive;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.DriveHelper;
import frc.lib.DriveSignal;
import frc.robot.subsystems.DriveSubsystem;

public class ArcadeDriveCommand extends CommandBase {
  /**
   * Creates a new ArcadeDriveCommand.
   */
  DriveSubsystem m_subsystem;
  DoubleSupplier m_getX;
  DoubleSupplier m_getY;
  DoubleSupplier m_getThrottle;
  private static final double k_maxSpeed = 3000;
  private DriveHelper m_driveHelper = new DriveHelper();

  enum driveTypes{
    normal, halfPower, speedControl, speedControlHalfSpeed, curvature
  }
  private SendableChooser<driveTypes> m_chooser = new SendableChooser<>();


  public ArcadeDriveCommand(DriveSubsystem subsystem, DoubleSupplier getX, DoubleSupplier getY, DoubleSupplier getThrottle) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_subsystem = subsystem;
    m_getX = getX;
    m_getY = getY;
    m_getThrottle = getThrottle;

    addRequirements(m_subsystem);
    m_chooser.setDefaultOption("normal", driveTypes.normal);
    m_chooser.addOption("halfPower", driveTypes.halfPower);
    m_chooser.addOption("speedControl", driveTypes.speedControl);
    m_chooser.addOption("speedControlHalfSpeed",driveTypes.speedControlHalfSpeed);
    m_chooser.addOption("curvature", driveTypes.curvature);
    SmartDashboard.putData("drive mode", m_chooser);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double x = m_getX.getAsDouble();
    double y = m_getY.getAsDouble();
    
    

    x = x * x * x;
    y = y * y * y;

    if(m_getThrottle.getAsDouble() > 0){
      y *= -1;
    }

    switch(m_chooser.getSelected()){
      
      case normal:
        m_subsystem.setPower(y+x, y-x);
        break;
      case halfPower:
        x= x/2;
        m_subsystem.setPower(y+x, y-x);
        break;
      case speedControl:
        x = x * k_maxSpeed;
        y = y * k_maxSpeed;
        m_subsystem.setSpeed(y+x, y-x);
        break;
      case speedControlHalfSpeed:
        x = x * k_maxSpeed/2;
        y = y * k_maxSpeed;
        m_subsystem.setSpeed(y+x, y-x);
        break;
      case curvature:
        DriveSignal ds = m_driveHelper.cheesyDrive(y, x, false);
        m_subsystem.setPower(ds.leftMotor, ds.rightMotor);
        break;

    }

    // System.out.println("Arcade Drive Execute Running");

    

   

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
