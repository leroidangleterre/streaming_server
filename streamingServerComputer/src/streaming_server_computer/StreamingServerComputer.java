/*
 * Server-side of the Android application.
 * This server receives orientation information from the Android device,
 * then computes an image and sends it to the Android device.
 */
package streaming_server_computer;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author arthurmanoha
 */
public class StreamingServerComputer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {

//        int port = 2347;
        int delay = 0;
        int period = 10;

//        DatagramSocket client = new DatagramSocket(port);
        JFrame frame;
//        JPanel panel;

        // Get the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        BasicFrame b = new BasicFrame();

        glcanvas.addGLEventListener(b);

        glcanvas.setSize(400, 400);

        //creating frame
        frame = new JFrame(" Basic Frame");

        //adding canvas to frame
        frame.add(glcanvas);

        frame.setSize(640, 480);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Timer timer = new Timer();

        timer.schedule(new MyTimerTask(), delay, period);

//        timer.schedule(new MyTimerTask(rotationMatrixParam, accelerationParam), delay, period);
//        timer.schedule(new TimerTask() {
//            int count = 0;
//
//            @Override
//            public void run() {
//
//                try {
//                    boolean loop = true;
//                    while (loop) {
//                        System.out.println("loop");
//
//                        // Receive information from the phone
//                        byte[] buffer = new byte[8192];
//                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
//                        System.out.println("waiting");
//                        client.receive(packet);
//
//                        // Display info received from the phone
//                        String receivedText = new String(packet.getData());
//                        receivedText = receivedText.substring(0, packet.getLength());
////                        System.out.println("from phone: " + receivedText);
//                        System.out.println("From phone:");
//                        String[] matrixAndGravityElems = receivedText.split(" ");
//
//                        for (int i = 0; i < matrixAndGravityElems.length; i++) {
//                            if (4 * (i / 4) == i) {
//                                System.out.println("");
//                            }
//                            System.out.print(matrixAndGravityElems[i] + ", ");
//                        }
//                        System.out.println("");
//                        count++;
//
//                        // Receive information from the phone
//                        byte[] buffer = new byte[8192];
//                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
//                        client.receive(packet);
//
//                        // Display info received from the phone
//                        String receivedText = new String(packet.getData());
//                        receivedText = receivedText.substring(0, packet.getLength());
//                        count++;
//
//                        // Send information to the phone
//                        String textToSend = "from computer: " + count + " !";
//                        buffer = textToSend.getBytes();
//
//                        packet = new DatagramPacket(buffer, buffer.length,
//                                packet.getAddress(), packet.getPort());
//
//                        packet.setData(buffer);
//
//                        // Send the packet
//                        client.send(packet);
//
//                    }
//                } catch (IOException e) {
//                    System.out.println("error");
//                }
//                System.out.println("after loop.");
//            }
//        }, delay, period);
    }

}
