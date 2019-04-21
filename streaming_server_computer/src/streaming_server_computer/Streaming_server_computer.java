/*
 * Server-side of the Android application.
 * This server receives orientation information from the Android device,
 * then computes an image and sends it to the Android device.
 */
package streaming_server_computer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
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
public class Streaming_server_computer {

    long sleepTime = 1000;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {

        int port = 2345;

        int delay = 0;
        int period = 1000;

        DatagramSocket client = new DatagramSocket();
        InetAddress address = InetAddress.getByName("192.168.1.30");

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int count = 0;

            @Override
            public void run() {

                try {
                    boolean loop = true;
                    while (loop) {
                        count++;

                        // Send information to the phone
                        String textToSend = "coucou from server " + count + " !";
                        byte[] buffer = textToSend.getBytes();

                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);

                        packet.setData(buffer);

                        // Send the packet
                        System.out.println("sending " + textToSend + " to the phone");
                        client.send(packet);

//                        // Receive information from the phone
//                        buffer = new byte[8192];
//                        System.out.println("receiving from phone...");
//                        client.receive(packet);
//
//                        // Display info received from the phone
//                        String receivedText = new String(packet.getData());
//                        System.out.println("received <" + receivedText + "> from the phone");
//                        if (receivedText.contains("100")) {
//                            loop = false;
//                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Streaming_server_computer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("error");
                }
            }
        }, delay, period);

    }

}