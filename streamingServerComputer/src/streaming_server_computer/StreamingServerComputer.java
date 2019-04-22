/*
 * Server-side of the Android application.
 * This server receives orientation information from the Android device,
 * then computes an image and sends it to the Android device.
 */
package streaming_server_computer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arthurmanoha
 */
public class StreamingServerComputer {

    long sleepTime = 1000;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {

        int port = 2345;

        int delay = 0;
        int period = 10;

        DatagramSocket client = new DatagramSocket(port);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int count = 0;

            @Override
            public void run() {

                try {
                    boolean loop = true;
                    while (loop) {

                        // Receive information from the phone
                        byte[] buffer = new byte[8192];
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                        client.receive(packet);

                        // Display info received from the phone
                        String receivedText = new String(packet.getData());
                        receivedText = receivedText.substring(0, packet.getLength());
                        count++;

                        // Send information to the phone
                        String textToSend = "from computer: " + count + " !";
                        buffer = textToSend.getBytes();

                        packet = new DatagramPacket(buffer, buffer.length,
                                packet.getAddress(), packet.getPort());

                        packet.setData(buffer);

                        // Send the packet
                        client.send(packet);

                    }
                } catch (IOException e) {
                    System.out.println("error");
                }
            }
        }, delay, period);

    }

}
