# Joystick layout

<img align="right" src="Joystick.png">

## Joystick 0: showStick

* No buttons configured

## Joystick 1: stick

* __Button 3__: intake: _toggleWhenPressed_: ```new DropIntake(m_serializerSubsystem, m_intakeSubsystem, 0.2, 0.7)```

* __Button 12__: barrelPath: No actions configured

## Joystick 2: climbStick

* __Button 1__: fire: _whileHeld_: ```new FireCommand(m_throatSubsystem, m_shooterSubsystem)```

* __Button 2__:

  * spinUp: _toggleWhenPressed_: ```new SpinUpShooterCommand(m_shooterSubsystem, m_shooterPower, m_backWheelPower, m_stick)```

  * turretTrack: _toggleWhenPressed_: ```new TurretTrackingCommand(m_turretSubsystem, m_turretCamera)```

* __Button 3__: moveTurrentL: _whileHeld_: ```new TurretMoveCommand(m_turretSubsystem, -0.6)```

* __Button 4__: moveTurrentR: _whileHeld_: ```new TurretMoveCommand(m_turretSubsystem, 0.6)```

* __Button 7__: serialize: _toggleWhenPressed_: ```new PowerSerializeCommand(m_serializerSubsystem, -0.3)```

* __Button 8__: unJumble: _whileHeld_: ```new UnJumbleCommand(m_intakeSubsystem, m_throatSubsystem, m_serializerSubsystem)```

* __Button POV DOWN__: decreaseTrim: No actions configured

* __Button POV UP__: increaseTrim: No actions configured

## Joystick 3: calibStick

* __Button 2__: calibrateShooter: _toggleWhenPressed_: ```new SpeedByThrottleCommand(m_shooterSubsystem, () -> m_calibStick.getThrottle())```

```commit ae56e1688514012d5f1311bfc5c2f98b8671438e Author: Ryder Casazza <rydercasazza@gmail.com> Date:   Tue Mar 9 18:02:12 2021 -0800 Started on shooter. Back wheel PID needs fixing. ```

