/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjogl;

import com.jogamp.opengl.GL2;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 *
 * @author lev
 */
public class Room {

    public static float SIZE = 20;
    private int texture[];
    // green blue violate orange,red yellow

    private final FloatBuffer vertexBuffer;  // Buffer for vertex-array
    private final FloatBuffer textureBuffer;  // Buffer for Texture-array
    private final float[] tex = { // Texture
        //Front Face
        0.0f, 0.0f,
        1.0f, 0.0f,
        1.0f, 1.0f,
        0.0f, 1.0f,
        // Back Face
        1.0f, 0.0f,
        1.0f, 1.0f,
        0.0f, 1.0f,
        0.0f, 0.0f,
        // Left Face
        0.0f, 0.0f,
        1.0f, 0.0f,
        1.0f, 1.0f,
        0.0f, 1.0f,
        // Right face
        1.0f, 0.0f,
        1.0f, 1.0f,
        0.0f, 1.0f,
        0.0f, 0.0f,
        // Top Face
        0.0f, 1.0f,
        0.0f, 0.0f,
        1.0f, 0.0f,
        1.0f, 1.0f,
        // Bottom Face
        1.0f, 1.0f,
        0.0f, 1.0f,
        0.0f, 0.0f,
        1.0f, 0.0f
    };

    private final float[] vertices = { // Vertices of the 6 faces
        //Front Face
        -SIZE, -SIZE, SIZE,
        SIZE, -SIZE, SIZE,
        SIZE, SIZE, SIZE,
        -SIZE, SIZE, SIZE,
        // Back Face
        -SIZE, -SIZE, -SIZE,
        -SIZE, SIZE, -SIZE,
        SIZE, SIZE, -SIZE,
        SIZE, -SIZE, -SIZE,
        // Left Face
        -SIZE, -SIZE, -SIZE,
        -SIZE, -SIZE, SIZE,
        -SIZE, SIZE, SIZE,
        -SIZE, SIZE, -SIZE,
        // Right face
        SIZE, -SIZE, -SIZE,
        SIZE, SIZE, -SIZE,
        SIZE, SIZE, SIZE,
        SIZE, -SIZE, SIZE,
        // Top Face
        -SIZE, SIZE, -SIZE,
        -SIZE, SIZE, SIZE,
        SIZE, SIZE, SIZE,
        SIZE, SIZE, -SIZE,
        // Bottom Face
        -SIZE, -SIZE, -SIZE,
        SIZE, -SIZE, -SIZE,
        SIZE, -SIZE, SIZE,
        -SIZE, -SIZE, SIZE,};

    public Room(int texture[]) {
        this.texture = texture;
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder()); // Use native byte order
        vertexBuffer = vbb.asFloatBuffer(); // Convert from byte to float
        vertexBuffer.put(vertices);         // Copy data into buffer
        vertexBuffer.position(0);           // Rewind

        vbb = ByteBuffer.allocateDirect(tex.length * 4);
        vbb.order(ByteOrder.nativeOrder()); // Use native byte order

        textureBuffer = vbb.asFloatBuffer();
        textureBuffer.put(tex);
        textureBuffer.position(0);
    }

    void drawRoom(GL2 gl) {

        gl.glPushMatrix();
        gl.glRotatef(45, 0, 1, 0);

        gl.glFrontFace(GL2.GL_CCW);    // Front face in counter-clockwise orientation
//        gl.glEnable(GL2.GL_CULL_FACE); // Enable cull face
        gl.glCullFace(GL2.GL_BACK);    // Cull the back face (don't display)

        gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL2.GL_TEXTURE_COORD_ARRAY);

        gl.glVertexPointer(3, GL2.GL_FLOAT, 0, vertexBuffer);
        gl.glTexCoordPointer(2, GL2.GL_FLOAT, 0, textureBuffer);
        gl.glLoadName(123);

        // Render all the faces
        for (int i = 0; i < 6; i++) {
            gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

           
                gl.glBindTexture(GL2.GL_TEXTURE_2D, texture[7]);
          
            gl.glDrawArrays(GL2.GL_QUADS, i * 4, 4);

        }

        gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL2.GL_TEXTURE_COORD_ARRAY);
        gl.glDisable(GL2.GL_CULL_FACE);

        gl.glPopMatrix();

    }

}
