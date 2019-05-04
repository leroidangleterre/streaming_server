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
 * This task sends sensor data to the server.
 *
 * @author arthurmanoha
 *
 * This task
 */
public class MyEmissionTimerTask extends TimerTask {

    int count = 0;

    DatagramSocket serverSocket;
    int clientPort = 2222;
    int serverPort = 2555;

    int timeout = 300; // milliseconds

    // The values that represent the movements of the phone
    float[] mRotationMatrix = new float[16];
    float[] acceleration = new float[3];

    byte[] buffer;

    private BasicFrame basicFrame;

    public MyEmissionTimerTask() {
        try {
            serverSocket = new DatagramSocket(serverPort);
        } catch (SocketException ex) {
            System.out.println("SocketException");
            Logger.getLogger(MyEmissionTimerTask.class.getName()).log(Level.SEVERE, null, ex);
        }
        basicFrame = null;
        buffer = new byte[8192];
    }

    public MyEmissionTimerTask(BasicFrame b) {
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
            buffer = new byte[8192];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            serverSocket.setSoTimeout(timeout);
            serverSocket.receive(packet);

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

            // Send information to the phone
            String textToSend = "from computer: " + count + " !";
            buffer = textToSend.getBytes();
            serverSocket.send(new DatagramPacket(buffer, textToSend.length(),
                    packet.getAddress(), clientPort));

        } catch (IOException e) {
            System.out.println("error in TimerTask: " + e);
        }
    }
}
