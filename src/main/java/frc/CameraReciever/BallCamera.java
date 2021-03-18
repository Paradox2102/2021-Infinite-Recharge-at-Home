package frc.CameraReciever;

import java.io.DataOutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class BallCamera {
    private String m_host;
    private int m_port; // 1234
    boolean cansee = false;
    int centerLine = 150;

    Region cameraData[];

    Socket sock;

    public BallCamera(String host, int port) {
        m_host = host;
        m_port = port;
        try {
            sock = new Socket(host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Recieve() {
        try {
            byte command[] = new byte[4];
            byte data[];
            ByteBuffer commandBuffer = ByteBuffer.wrap(command);
            ByteBuffer dataBuffer;
            DataOutputStream out = new DataOutputStream(sock.getOutputStream());

            out.writeByte(0x10);
            sock.getInputStream().read(command);

            short numBalls;

            if (commandBuffer.getShort() == 0x10) {
                numBalls = commandBuffer.getShort();
                if (numBalls != 0) {
                    cansee = true;
                    data = new byte[numBalls * 8];
                    dataBuffer = ByteBuffer.wrap(data);
                    // cameraData = new short[numBalls][4];
                    cameraData = new Region[numBalls];

                    sock.getInputStream().read(data);

                    for (int i = 0; i < cameraData.length; i++) {
                        cameraData[i] = new Region(dataBuffer.getShort(), dataBuffer.getShort(), dataBuffer.getShort(),
                                dataBuffer.getShort());
                    }
                    // System.out.println(cameraData[0].getTopBound());

                } else {
                    cansee = false;
                }

            } else {
                cameraData = null;
            }

        } catch (

        Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public Region findClosestRegion() {
        Region closest = cameraData[0];
        for (int i = 1; i < cameraData.length; i++) {
            if (cameraData[i].getTopBound() > closest.getTopBound()) {
                closest = cameraData[i];
            }
        }
        return closest;
    }

    public Region[] getRegions() {
        return cameraData;
    }

    public boolean canSee() {
        return cansee;
    }

    public int getCenterLine() {
        return centerLine;
    }
}
