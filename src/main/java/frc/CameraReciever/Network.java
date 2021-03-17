package frc.CameraReciever;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Network {
    private String m_host; 
    private int m_port; //1234

    Region cameraData[];

    public Network(String host, int port) {
        m_host = host;
        m_port = port;
    }

    public void Recieve() {
        try {
            byte command[] = new byte[4];
            byte data[];
            ByteBuffer commandBuffer = ByteBuffer.wrap(command);
            ByteBuffer dataBuffer;
            Socket sock = new Socket(m_host, m_port);
            DataOutputStream out = new DataOutputStream(sock.getOutputStream());

            out.writeByte(0x10);
            sock.getInputStream().read(command);

            short numBalls;

            if (commandBuffer.getShort() == 0x10) {
                numBalls = commandBuffer.getShort();
                data = new byte[numBalls * 8];
                dataBuffer = ByteBuffer.wrap(data);
                // cameraData = new short[numBalls][4];
                cameraData = new Region[numBalls];


                sock.getInputStream().read(data);

                for (int i = 0; i < cameraData.length; i++) {
                    cameraData[i] = new Region(dataBuffer.getShort(), dataBuffer.getShort(), dataBuffer.getShort(), dataBuffer.getShort());
                }
            } else {
                cameraData = null;
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    
    public Region[] getRegions() {
        return cameraData;
    }

    public boolean canSee() {
        return cameraData != null;
    }    
}
