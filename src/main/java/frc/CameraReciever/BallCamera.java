package frc.CameraReciever;

import java.net.Socket;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BallCamera extends Thread {
    private String m_host;
    private int m_port; // 1234
    boolean cansee = false;
    int centerLine = 200;

    Region[] m_cameraData;

    Socket sock;

    Object m_lock = new Object();

    public BallCamera(String host, int port) {
        m_host = host;
        m_port = port;
        Receive();
    }

    public void Receive() {
        double[] nums = SmartDashboard.getNumberArray("ballcoords", (double[]) null);
        if (nums != null) {
        m_cameraData = new Region[nums.length / 4];
            for (int i = 0; i < m_cameraData.length; i++) {
                m_cameraData[i] = new Region(nums[4*i], nums[4*i + 1], nums[4*i + 2], nums[4*i + 3]);
            }
        } else {
            m_cameraData = null;
        }

        // new Thread(() -> {
        // try {
        // // sock = new Socket(m_host, m_port);
        // while (true) {
        // // byte command[] = new byte[4];
        // // short data[];
        // // ByteBuffer commandBuffer = ByteBuffer.wrap(command);
        // // ByteBuffer dataBuffer;
        // DataOutputStream out = new DataOutputStream(sock.getOutputStream());
        // DataInputStream in = new DataInputStream(sock.getInputStream());

        // out.writeByte(0x10);
        // // sock.getInputStream().read(command);
        // short command = in.readShort();

        // short numBalls;

        // Region cameraData[] = null;
        // if (command == 0x10) {
        // numBalls = in.readShort();
        // if (numBalls != 0) {
        // // data = new short[numBalls * 4];
        // // dataBuffer = ByteBuffer.wrap(data);
        // cameraData = new Region[numBalls];

        // // sock.getInputStream().read(data);

        // for (int i = 0; i < cameraData.length; i++) {
        // cameraData[i] = new Region(in.readShort(), in.readShort(), in.readShort(),
        // in.readShort());
        // }
        // // System.out.println(m_cameraData[0].getTopBound());
        // }
        // }

        // synchronized (m_lock) {
        // m_cameraData = cameraData;
        // }

        // }
        // }

        // catch (UnknownHostException e) {
        // // TODO Auto-generated catch block
        // try {
        // Thread.sleep(1000);
        // } catch (InterruptedException e1) {
        // // TODO Auto-generated catch block
        // e1.printStackTrace();
        // }
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

        // catch (Exception e) {
        // try {
        // Thread.sleep(1000);
        // } catch (InterruptedException e1) {
        // // TODO Auto-generated catch block
        // e1.printStackTrace();
        // }
        // }

        // }).start();

    }

    public Region[] getRegions() {
        Region[] cameraData;
        double[] nums = SmartDashboard.getNumberArray("ballcoords", (double[]) null);
        if (nums != null && nums.length > 0) {
            cameraData = new Region[nums.length / 4];
            for (int i = 0; i < cameraData.length; i++) {
                cameraData[i] = new Region(nums[4 * i], nums[4 * i + 1], nums[4 * i + 2], nums[4 * i + 3]);
            }
        } else {
            cameraData = null;
        }
        return cameraData;
    }

    public Region findClosestRegion(Region cameraData[]) {
        Region closest = cameraData[0];
        for (int i = 1; i < cameraData.length; i++) {
            if (cameraData[i].getTopBound() > closest.getTopBound()) {
                closest = cameraData[i];
            }
        }
        return closest;
    }

    // public Region[] getRegions() {
    //     synchronized (m_lock) {
    //         return m_cameraData;
    //     }
    // }

    public int getCenterLine() {
        return centerLine;
    }
}
