// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import java.util.ArrayList;
import java.util.HashMap;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.Camera;
import frc.lib.Camera.CameraData;
import frc.robot.subsystems.ShooterAngleSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootByDistanceCommandInterpolate extends CommandBase {

  ShooterSubsystem m_subsystem;
  ShooterAngleSubsystem m_angleSubsystem;
  Camera m_camera;

  // m_keys array must be sorted
  double[] m_keys = { 44, 45, 50, 56, 62, 70, 79, 92, 102 };
  HashMap<Double, ArrayList<Double>> m_distances;

  public ShootByDistanceCommandInterpolate(ShooterSubsystem subsystem, ShooterAngleSubsystem angleSubsystem, Camera camera) {
    m_subsystem = subsystem;
    m_angleSubsystem = angleSubsystem;
    m_camera = camera;

    m_distances = new HashMap<>();
    m_distances.put(m_keys[0], new ArrayList<>() {
      {
        add(2700d);
        add(0.23);
      }
    });
    m_distances.put(m_keys[1], new ArrayList<>() {
      {
        add(2700d);
        add(0.23);
      }
    });
    m_distances.put(m_keys[2], new ArrayList<>() {
      {
        add(2600d);
        add(0.23);
      }
    });
    m_distances.put(m_keys[3], new ArrayList<>() {
      {
        add(2600d);
        add(0.23);
      }
    });
    m_distances.put(m_keys[4], new ArrayList<>() {
      {
        add(2700d);
        add(0.23);
      }
    });
    m_distances.put(m_keys[5], new ArrayList<>() {
      {
        add(2800d);
        add(0.23);
      }
    });
    m_distances.put(m_keys[6], new ArrayList<>() {
      {
        add(3000d);
        add(0.23);
      }
    });
    m_distances.put(m_keys[7], new ArrayList<>() {
      {
        add(3000d);
        add(0.3d);
      }
    });
    m_distances.put(m_keys[8], new ArrayList<>() {
      {
        add(3000d);
        add(0.43d);
      }
    });

    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_camera.toggleLights(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    CameraData data = m_camera.createData();
    if (data != null && data.canSee()) {
      if (data.m_regions.GetRegionCount() > 0) {
        // Currently O(n) but could be O(log(n)) if we binary search
        double height = data.m_regions.GetRegion(0).m_bounds.m_bottom - data.m_regions.GetRegion(0).m_bounds.m_top;
        double[] ans = { 0d, 0d };

        if (height < m_keys[0]) {
          ans[0] = m_distances.get(m_keys[0]).get(0);
          ans[1] = m_distances.get(m_keys[0]).get(1);
        } else if (height > m_keys[m_keys.length - 1]) {
          ans[0] = m_distances.get(m_keys[m_keys.length - 1]).get(0);
          ans[1] = m_distances.get(m_keys[m_keys.length - 1]).get(1);
        } else {
          for (int i = 0; i < m_keys.length; i++) {
            if (m_keys[i] == height) {
              ans[0] = m_distances.get(m_keys[i]).get(0);
              ans[1] = m_distances.get(m_keys[i]).get(1);
              break;
            }

            if (m_keys[i] > height) {
              ans = new double[] {
                  interpolate(height, m_keys[i - 1], m_distances.get(m_keys[i - 1]).get(0), m_keys[i],
                      m_distances.get(m_keys[i]).get(0)),
                  interpolate(height, m_keys[i - 1], m_distances.get(m_keys[i - 1]).get(1), m_keys[i],
                      m_distances.get(m_keys[i]).get(1)) };
              break;
            }
          }
        }

        m_subsystem.setSpeed(ans[0], ans[0]);
        m_angleSubsystem.setAngle(ans[1]);
      }
    }
  }

  private double interpolate(double x, double x1, double x2, double y1, double y2) {
    if (x1 != x2)
      return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
    else
      throw new ArithmeticException("x1 == x2");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_camera.toggleLights(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
