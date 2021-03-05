# Joystick layout

<img align="right" src="Joystick.png">

## Joystick 1: stick

* __Button 3__: intake: No actions configured

## Joystick 2: climbStick

* __Button 1__: fire: _whileHeld_: ```new FireCommand(m_throatSubsystem, m_shooterSubsystem)```

* __Button 2__:

  * spinUp: _toggleWhenPressed_: ```new SpinUpShooterCommand(m_shooterSubsystem, m_shooterPower, m_backWheelPower, m_stick)```

  * turretTrack: _toggleWhenPressed_: ```new TurretTrackingCommand(m_turretSubsystem, m_turretCamera)```

* __Button 3__: moveTurrentL: _whileHeld_: ```new TurretMoveCommand(m_turretSubsystem, -0.6)```

* __Button 4__: moveTurrentR: _whileHeld_: ```new TurretMoveCommand(m_turretSubsystem, 0.6)```

* __Button 7__: serialize: _toggleWhenPressed_: ```new PowerSerializeCommand(m_serializerSubsystem, -0.3)```

* __Button 8__: unJumble: _toggleWhenPressed_: ```new UnJumbleCommand(m_intakeSubsystem, m_throatSubsystem, m_serializerSubsystem)```

* __Button POV DOWN__: decreaseTrim: No actions configured

* __Button POV UP__: increaseTrim: No actions configured

## Joystick 3: calibStick

* __Button 2__: calibrateShooter: _toggleWhenPressed_: ```new SpeedByThrottleCommand(m_shooterSubsystem, () -> m_calibStick.getThrottle())```

```commit b2e39eb670c2573a6321e3646f6d1dd4328950d1 Author: Ryder Casazza <rydercasazza@gmail.com> Date:   Thu Mar 4 17:58:03 2021 -0800 tried to fix intake no t working still ```

