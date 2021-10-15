// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.pathfinder.Pathfinder.Path;
import frc.robot.PositionTracker.PositionContainer;
import frc.robot.PurePursuit.PathConfig;
import frc.robot.PurePursuit.PathConfigs;

/** Add your docs here. */
public interface DriveSubsystem extends Subsystem {
    public void setSpeed(double leftSpeed, double rightSpeed);

    public void setPower(double leftPower, double rightPower);

    public void startPosUpdate();

    public void stopPosUpdate();

    public void setCoastMode();

	public void setBrakeMode();

	public void setPos(double x, double y);

	public void resetAngle(double angle);

	public double getAngle();

	public PigeonIMU getGyro();

	public PositionContainer getPos();

	public void stop();

	public PathConfig getPathConfig(PathConfigs config);

	public void loadFollowPath(Path m_path, boolean m_isReversed, boolean m_isExtended);

	public void startFollow();

	public boolean isFollowFinished();

	public void stopFollow();

	public double getLeftPos();

	public double getLeftVel();

	public double getRightVel();

	public void configPID();

	public void setSpeedFeet(double m_speed, double m_speed2);
}

