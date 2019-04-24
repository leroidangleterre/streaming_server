/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming_server_computer;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

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

    public MyTimerTask() {
        try {
            client = new DatagramSocket(port);
        } catch (SocketException ex) {
            Logger.getLogger(MyTimerTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setMatrices(float[] mRot, float[] mAcc) {
        this.mRotationMatrix = mRot;

    }

    @Override
    public void run() {

        try {
            System.out.println("loop");

            // Receive information from the phone
            byte[] buffer = new byte[8192];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            System.out.println("waiting");
            client.receive(packet);
            System.out.println("received packet.");

            // Display info received from the phone
            String receivedText = new String(packet.getData());
            receivedText = receivedText.substring(0, packet.getLength());
            String[] matrixAndGravityElems = receivedText.split(" ");

            count++;

            System.out.println("rotation: ");
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    mRotationMatrix[i] = new Float(matrixAndGravityElems[4 * i + j]);
                    System.out.print(" - " + mRotationMatrix[i]);
                }
                System.out.println("");
            }
            System.out.println("acceleration: ");
            for (int i = 0; i < 3; i++) {
                acceleration[i] = new Float(matrixAndGravityElems[i + 16]);
                System.out.print(" - " + acceleration[i]);
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

//            }
        } catch (IOException e) {
            System.out.println("error: " + e);
        }
        System.out.println("after loop.");
    }
}
