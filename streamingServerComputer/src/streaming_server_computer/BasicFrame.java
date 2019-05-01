/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming_server_computer;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author arthurmanoha
 */
public class BasicFrame implements GLEventListener, KeyListener {

    private GLU glu;
    private float[] rotation;
    private float[] acceleration;
    private float rtri = 0.0f;

    public BasicFrame() {
        glu = new GLU();
    }

    public BasicFrame(float[] rotMatrix, float[] accMatrix) {
        this();
        rotation = rotMatrix;
        acceleration = accMatrix;
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(0f, 0f, 0f, 0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    @Override
    public void display(GLAutoDrawable drawable) {

        final GL2 gl = drawable.getGL().getGL2();

        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(0f, 0f, 0f, 0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        // Clear The Screen And The Depth Buffer
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();  // Reset The View
        gl.glTranslatef(-0.5f, 0.0f, -6.0f);  // Move the triangle 

        rtri = rotation[0];
        gl.glRotatef(rtri * 100, 0.0f, 1.0f, 0.0f);

        gl.glBegin(GL2.GL_TRIANGLES);

        //front
        gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
        gl.glVertex3f(1.0f, 2.0f, 0.0f); // Top
        gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
        gl.glVertex3f(-1.0f, -1.0f, 1.0f); // Left
        gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
        gl.glVertex3f(1.0f, -1.0f, 1.0f); // Right)
        //right
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(1.0f, 2.0f, 0.0f); // Top 
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f); // Left 
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f); // Right
        //left
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(1.0f, 2.0f, 0.0f); // Top 
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f); // Left 
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f); // Right 
        //top
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(1.0f, 2.0f, 0.0f); // Top 
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f); // Left 
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f); // Right 
        gl.glEnd(); // Done Drawing 3d  triangle (Pyramid)            
        gl.glFlush();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        final GL2 gl = drawable.getGL().getGL2();
        if (height <= 0) {
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * Receive phone information from the TimerTask.
     *
     */
    public void updateRotation(float[] rotationParam) {
        for (int i = 0; i < rotationParam.length; i++) {
            rotation[i] = rotationParam[i];
        }
    }

}
