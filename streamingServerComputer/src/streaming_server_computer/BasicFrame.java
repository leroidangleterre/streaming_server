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

/**
 *
 * @author arthurmanoha
 */
class BasicFrame implements GLEventListener {

    @Override
    public void init(GLAutoDrawable drawable) {
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    @Override
    public void display(GLAutoDrawable drawable) {

        final GL2 gl = drawable.getGL().getGL2();
        gl.glBegin(GL2.GL_TRIANGLES);

        // Drawing Using Triangles 
        gl.glColor3f(1.0f, 0.0f, 0.0f);   // Red 
        gl.glVertex3f(0.5f, 0.7f, 0.0f);    // Top 

        gl.glColor3f(0.0f, 1.0f, 0.0f);     // green 
        gl.glVertex3f(-0.2f, -0.50f, 0.0f); // Bottom Left 

        gl.glColor3f(0.0f, 0.0f, 1.0f);     // blue 
        gl.glVertex3f(0.5f, -0.5f, 0.0f);   // Bottom Right 

        gl.glEnd();

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        final GL2 gl = drawable.getGL().getGL2();
        final GLU glu = GLU.createGLU(gl);

        if (height <= 0) {
            height = 1;
        }

        // display area to cover the entire window 
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(gl.GL_PROJECTION);        //transforming projection matrix 
        gl.glLoadIdentity();

//        glu.gluPerspective(45.0f, h, 1.0, 20.0);
//        gl.glMatrixMode(GL2.GL_MODELVIEW);
//        gl.glLoadIdentity();
//        glu.gluLookAt(-5, 0, 0, 0, 0, 0, 0, 0, 1);
//        gl.glTranslatef(-5, 0, 0);
    }

}
