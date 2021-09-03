package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.PiCamera.Logger;
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
    // Stalls at -0.1 power
    @Override
    public void initialize() {
        Logger.Log("Raise Intake: ", 1, "Initialized");
        m_subsystem.deploy(-m_power);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // if(m_subsystem.isReverseLimitEnabled()) {
        //     m_subsystem.deploy(-m_stallPower);
        // } else {
        //     m_subsystem.deploy(-m_power);
        // }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_subsystem.stopDeploy();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
