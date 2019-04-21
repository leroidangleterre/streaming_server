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

            String textToSend = "coucou " + count + " !";
            byte[] buffer = textToSend.getBytes();

            @Override
            public void run() {

                try {
                    System.out.println("timer " + count);
                    count++;

                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);

                    packet.setData(buffer);

                    // Send the packet
                    client.send(packet);
                    System.out.println("package sent");
                } catch (IOException e) {
                    System.out.println("error");
                }
            }
        }, delay, period);

//        Thread t = new Thread(new Runnable() {
//            int count = 0;
//
//            @Override
//            public void run() {
//                while (true) {
//                    String textToSend = "coucou " + count + " !";
//                    byte[] buffer = textToSend.getBytes();
//
//                    try {
//
//                        System.out.println("Thread running ");
//
//                        DatagramSocket client = new DatagramSocket();
//                        InetAddress address = InetAddress.getByName("503425216");
//
//                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
//
//                        packet.setData(buffer);
//
//                        // Send the packet
//                        client.send(packet);
//
//                    } catch (SocketException | UnknownHostException ex) {
//                        Logger.getLogger(Streaming_server_computer.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (IOException ex) {
//                        Logger.getLogger(Streaming_server_computer.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                    count++;
//                }
//            }
//        });
//        t.start();
    }

}
