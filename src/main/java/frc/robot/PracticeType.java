package frc.robot;

public class PracticeType {
    public final double k_driveLeftF = 0.000176;
    public final double k_driveLeftP = 0.00004;
    public final double k_driveLeftI = 0.00000025;
    public final double k_driveLeftD = 0;
    
    public final double k_driveRightF = 0.000177;
    public final double k_driveRightP = 0.00004;
    public final double k_driveRightI = 0.00000025;
    public final double k_driveRightD = 0;
    
    public final double k_driveIRange = 150;
    
    public final double k_shooterF = 0.000205;
    public final double k_shooterP = 0.0004;//0.0002;//0.4;
    public final double k_shooterI = 0.000001;//0.032;
    public final double k_shooterD = 0;

    public final double k_backWheelF = 0.00022;
    public final double k_backWheelP = 0.001;//0.000004;
    public final double k_backWheelI = 0;//0.0000001;
    public final double k_backWheelD = 0;

    public final int k_shooterIRange = 100;

    public final String k_ipAddress = "10.21.2.12";
    public final String k_ipAddressBack = "10.21.2.13";

    //Shooter Equation Coefficients
    public final double k_squareConst = 3.433;
    public final double k_linearConst = 487.948;
    public final double k_const = 47550.336;
}