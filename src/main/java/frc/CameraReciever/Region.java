package frc.CameraReciever;

public class Region {
    short m_xmin, m_xmax, m_ymin, m_ymax;

    public Region(short xmin, short ymin, short xmax, short ymax) {
        m_xmin = xmin;
        m_xmax = xmax;
        m_ymin = ymin;
        m_ymax = ymax;
    }

    public int getTopBound() {
        return m_ymin;
    }

    public int getBottomBound() {
        return m_ymax;
    }

    public int getRightBound() {
        return m_xmax;
    }

    public int getLeftBound() {
        return m_xmin;
    }

    public double centerDiff() {
        return m_xmin + ((m_xmax - m_xmin) / 2);
    }
}
