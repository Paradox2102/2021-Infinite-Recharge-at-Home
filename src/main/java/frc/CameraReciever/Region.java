package frc.CameraReciever;

public class Region {
    double m_xmin, m_xmax, m_ymin, m_ymax;

    public Region(double xmin, double ymin, double xmax, double ymax) {
        m_xmin = xmin;
        m_xmax = xmax;
        m_ymin = ymin;
        m_ymax = ymax;
    }

    public double getTopBound() {
        return m_ymin;
    }

    public double getBottomBound() {
        return m_ymax;
    }

    public double getRightBound() {
        return m_xmax;
    }

    public double getLeftBound() {
        return m_xmin;
    }

    public double centerDiff() {
        return m_xmin + ((m_xmax - m_xmin) / 2);
    }
}
