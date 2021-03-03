package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class RaiseIntake extends CommandBase {
    IntakeSubsystem m_subsystem;
    double m_power;

    public RaiseIntake(IntakeSubsystem subsystem, double power) {
        m_subsystem = subsystem;
        m_power = power;

        addRequirements(m_subsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_subsystem.deploy(-m_power);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_subsystem.stopDeploy();
        m_subsystem.setBrakeMode();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return m_subsystem.isReverseLimitEnabled();
    }
}
