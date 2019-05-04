/*
 * Server-side of the Android application.
 * This server receives orientation information from the Android device,
 * then computes an image and sends it to the Android device.
 */
package streaming_server_computer;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Timer;
import javax.swing.JFrame;

/**
 *
 * @author arthurmanoha
 */
public class StreamingServerComputer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {

        int delay = 0;
        int period = 10;

        JFrame frame;

        // Get the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        // The values that represent the movements of the phone
        float[] mRotationMatrix = new float[16];
        float[] acceleration = new float[3];

        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        BasicFrame b = new BasicFrame(mRotationMatrix, acceleration);

        glcanvas.addGLEventListener(b);

        glcanvas.setSize(400, 400);

        // Create a frame
        frame = new JFrame(" Basic Frame");

        // Add the canvas to the frame
        frame.add(glcanvas);

        frame.setSize(640, 480);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Timer emissionTimer = new Timer();

        MyEmissionTimerTask emissionTask = new MyEmissionTimerTask(b);

        emissionTask.setMatrices(mRotationMatrix, acceleration);

        emissionTimer.schedule(emissionTask, delay, period);

        final FPSAnimator animator = new FPSAnimator(glcanvas, 300, true);

        animator.start();

    }

}
