/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming_server_computer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arthurmanoha
 *
 * This task
 */
public class MyTimerTask extends TimerTask {

    int count = 0;

    DatagramSocket client;
    int port = 2345;

    // The values that represent the movements of the phone
    float[] mRotationMatrix = new float[16];
    float[] acceleration = new float[3];

    private BasicFrame basicFrame;

    public MyTimerTask() {
        try {
            client = new DatagramSocket(port);
        } catch (SocketException ex) {
            System.out.println("SocketException");
            Logger.getLogger(MyTimerTask.class.getName()).log(Level.SEVERE, null, ex);
        }
        basicFrame = null;
    }

    public MyTimerTask(BasicFrame b) {
        this();
        basicFrame = b;
    }

    public void setMatrices(float[] mRot, float[] mAcc) {
        this.mRotationMatrix = mRot;

    }

    @Override
    public void run() {

        try {

            // Receive information from the phone
            byte[] buffer = new byte[8192];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            client.receive(packet);

            // Display info received from the phone
            String receivedText = new String(packet.getData());
            receivedText = receivedText.substring(0, packet.getLength());
            String[] matrixAndGravityElems = receivedText.split(" ");

            count++;

            for (int i = 0; i < 16; i++) {
                mRotationMatrix[i] = new Float(matrixAndGravityElems[i]);
            }
            basicFrame.updateRotation(mRotationMatrix);
            for (int i = 0; i < 3; i++) {
                acceleration[i] = new Float(matrixAndGravityElems[i + 16]);
            }

            // Generate an image and display it in a JFrame
            // Send information to the phone
            String textToSend = "from computer: " + count + " !";
            buffer = textToSend.getBytes();

            packet = new DatagramPacket(buffer, buffer.length,
                    packet.getAddress(), packet.getPort());

            packet.setData(buffer);

            // Send the packet
            client.send(packet);

        } catch (IOException e) {
            System.out.println("error in TimerTask: " + e);
        }
    }
}
