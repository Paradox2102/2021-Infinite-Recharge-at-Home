package frc.CameraReciever;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class BallCamera extends Thread {
    private String m_host;
    private int m_port; // 1234
    boolean cansee = false;
    int centerLine = 200;

    Region m_cameraData[];

    Socket sock;

    Object m_lock = new Object();

    public BallCamera(String host, int port) {
        m_host = host;
        m_port = port;
        Recieve();
    }

    public void Recieve() {
        new Thread(() -> {
            try {
                sock = new Socket(m_host, m_port);
                while (true) {
                    byte command[] = new byte[4];
                    byte data[];
                    ByteBuffer commandBuffer = ByteBuffer.wrap(command);
                    ByteBuffer dataBuffer;
                    DataOutputStream out = new DataOutputStream(sock.getOutputStream());

                    out.writeByte(0x10);
                    sock.getInputStream().read(command);

                    short numBalls;

                    Region cameraData[] = null;
                    if (commandBuffer.getShort() == 0x10) {
                        numBalls = commandBuffer.getShort();
                        if (numBalls != 0) {
                            data = new byte[numBalls * 8];
                            dataBuffer = ByteBuffer.wrap(data);
                            cameraData = new Region[numBalls];

                            sock.getInputStream().read(data);

                            for (int i = 0; i < cameraData.length; i++) {
                                cameraData[i] = new Region(dataBuffer.getShort(), dataBuffer.getShort(),
                                        dataBuffer.getShort(), dataBuffer.getShort());
                            }
                            // System.out.println(m_cameraData[0].getTopBound());
                        }
                    }

                    synchronized (m_lock) {
                        m_cameraData = cameraData;
                    }

                }
            }

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

            catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

        }).start();

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

    public Region[] getRegions() {
        synchronized (m_lock) {
            return m_cameraData;
        }
    }

    private static byte[] toByteArray(InputStream stream, int length) {
        byte[] buffer = new byte[length];

        int offset = 0;
        while (offset < length) {
            try {
                int count = stream.read(buffer, offset, (length - offset));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return buffer;
    }

    public int getCenterLine() {
        return centerLine;
    }
}
