/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjogl;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 *
 * @author lev
 */
public final class SmallCube implements Serializable {

    int arr[];
    public float angle = 0;
    boolean isRotate = true;
    public static int ID = 0;
    private int x, y, z;
    private final int _id;
    private Face[] faces;//front,back,left,right,top,bottom
    // green blue violate orange,red yellow

    private transient FloatBuffer vertexBuffer;  // Buffer for vertex-array
    private transient FloatBuffer textureBuffer;  // Buffer for Texture-array
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
        -1.0f, -1.0f, 1.0f,
        1.0f, -1.0f, 1.0f,
        1.0f, 1.0f, 1.0f,
        -1.0f, 1.0f, 1.0f,
        // Back Face
        -1.0f, -1.0f, -1.0f,
        -1.0f, 1.0f, -1.0f,
        1.0f, 1.0f, -1.0f,
        1.0f, -1.0f, -1.0f,
        // Left Face
        -1.0f, -1.0f, -1.0f,
        -1.0f, -1.0f, 1.0f,
        -1.0f, 1.0f, 1.0f,
        -1.0f, 1.0f, -1.0f,
        // Right face
        1.0f, -1.0f, -1.0f,
        1.0f, 1.0f, -1.0f,
        1.0f, 1.0f, 1.0f,
        1.0f, -1.0f, 1.0f,
        // Top Face
        -1.0f, 1.0f, -1.0f,
        -1.0f, 1.0f, 1.0f,
        1.0f, 1.0f, 1.0f,
        1.0f, 1.0f, -1.0f,
        // Bottom Face
        -1.0f, -1.0f, -1.0f,
        1.0f, -1.0f, -1.0f,
        1.0f, -1.0f, 1.0f,
        -1.0f, -1.0f, 1.0f,};

    public void setBlackColors() {
        for (int color = 0; color < 6; color++) {
            boolean flag = true;

            if (x == 0) {
                if (color == Face.RIGHT) {
                    faces[color].setColor(Face.BLACK);
                    flag = false;
                }
            } else if (x == 1) {
                if (color == Face.RIGHT || color == Face.LEFT) {
                    faces[color].setColor(Face.BLACK);
                    flag = false;
                }
            } else if (x == 2) {
                if (color == Face.LEFT) {
                    faces[color].setColor(Face.BLACK);
                    flag = false;
                }
            }
            if (y == 0) {
                if (color == Face.TOP) {
                    faces[color].setColor(Face.BLACK);
                    flag = false;
                }
            } else if (y == 1) {
                if (color == Face.TOP || color == Face.BOTTOM) {
                    faces[color].setColor(Face.BLACK);
                    flag = false;
                }
            } else if (y == 2) {
                if (color == Face.BOTTOM) {
                    faces[color].setColor(Face.BLACK);
                    flag = false;
                }
            }
            if (z == 0) {
                if (color == Face.BACK) {
                    faces[color].setColor(Face.BLACK);
                    flag = false;
                }
            } else if (z == 1) {
                if (color == Face.BACK || color == Face.FRONT) {
                    faces[color].setColor(Face.BLACK);
                    flag = false;

                }
            } else if (z == 2) {
                if (color == Face.FRONT) {
                    faces[color].setColor(Face.BLACK);
                    flag = false;

                }
            }
            if (flag) {
//                faces[color].setTexture(Renderer.TEXTURE[arr[color]]);
                faces[color].setVisiable(true);
            }
////            faces[color].setTextureBlink(textureBlink[arr[color]]);
        }

    }

    public void initFaces() {
        faces = new Face[6];
        for (int i = 0; i < 6; i++) {

            faces[i] = new Face(arr[i]);

        }
    }

    public void initArrayOfColors(int arr[]) {
        this.arr = arr;
    }

    public void rotateLeft() {

        swap(Face.FRONT, Face.LEFT);
        swap(Face.BACK, Face.FRONT);
        swap(Face.RIGHT, Face.FRONT);
//        faces[Face.TOP].rotateFace(Face.ROTATE_LEFT);
//        faces[Face.BOTTOM].rotateFace(Face.ROTATE_LEFT);
    }

    public void rotateRight() {
        swap(Face.FRONT, Face.RIGHT);
        swap(Face.BACK, Face.FRONT);
        swap(Face.LEFT, Face.FRONT);
//        faces[Face.TOP].rotateFace(Face.ROTATE_RIGHT);
//        faces[Face.BOTTOM].rotateFace(Face.ROTATE_RIGHT);

    }

    public void rotateUp() {
        swap(Face.FRONT, Face.TOP);
        swap(Face.FRONT, Face.BACK);
        swap(Face.FRONT, Face.BOTTOM);
//        faces[Face.BACK].rotateFace(Face.ROTATE_DOWN);
//        faces[Face.LEFT].rotateFace(Face.ROTATE_LEFT);
//        faces[Face.RIGHT].rotateFace(Face.ROTATE_RIGHT);
    }

    public void rotateDown() {
        swap(Face.FRONT, Face.BOTTOM);
        swap(Face.FRONT, Face.BACK);
        swap(Face.FRONT, Face.TOP);
//        faces[Face.BACK].rotateFace(Face.ROTATE_DOWN);
//        faces[Face.LEFT].rotateFace(Face.ROTATE_LEFT);
//        faces[Face.RIGHT].rotateFace(Face.ROTATE_RIGHT);

    }

    public void rotateZLeft() {
        swap(Face.TOP, Face.LEFT);
        swap(Face.TOP, Face.BOTTOM);
        swap(Face.TOP, Face.RIGHT);

    }

    public void rotateZRight() {
        swap(Face.TOP, Face.RIGHT);
        swap(Face.TOP, Face.BOTTOM);
        swap(Face.TOP, Face.LEFT);

    }

    public void swap(int x1, int x2) {
        int tempFace;
        int tempID;
        int tempColor;
        boolean isVisibleTemp;
        tempID = faces[x1].getID();
        tempColor = faces[x1].getColor();
        isVisibleTemp = faces[x1].isIsVisiable();

        faces[x1].setID(faces[x2].getID());
        faces[x1].setColor(faces[x2].getColor());
        faces[x1].setVisiable(faces[x2].isIsVisiable());

        faces[x2].setID(tempID);
        faces[x2].setColor(tempColor);
        faces[x2].setVisiable(isVisibleTemp);
    }

    public SmallCube(int x, int y, int z) {

        setX(x);
        setY(y);
        setZ(z);

        ID++;
        _id = ID;
        arr = new int[6];
    }

    private void setBuffers() {
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;

    }

    public void setY(int y) {
        this.y = y;

    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;

    }

    public int getID() {
        return _id;
    }

    public Face getFaceByID(int id) {
        return faces[id];//returns the color id 
    }

    public Face[] getFaces() {
        return faces;
    }

    public void drawCube(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        setBuffers();
        gl.glFrontFace(GL2.GL_CCW);    // Front face in counter-clockwise orientation
        gl.glEnable(GL2.GL_CULL_FACE); // Enable cull face
        gl.glCullFace(GL2.GL_BACK);    // Cull the back face (don't display)

        gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL2.GL_TEXTURE_COORD_ARRAY);

        gl.glVertexPointer(3, GL2.GL_FLOAT, 0, vertexBuffer);
        gl.glTexCoordPointer(2, GL2.GL_FLOAT, 0, textureBuffer);

        // Render all the faces
        for (int i = 0; i < 6; i++) {
            faces[i].drawFace(drawable);
        }

        gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL2.GL_TEXTURE_COORD_ARRAY);
        gl.glDisable(GL2.GL_CULL_FACE);
    }

    public boolean equals(SmallCube sc) {

        for (int i = 0; i < faces.length; i++) {
//            System.out.println(sc.getFaces()[i].toString() + "," + getFaces()[i].toString());
            if (sc.getFaces()[i].getColor() != getFaces()[i].getColor()) {

                return false;
            }
        }

        return true;
    }

}
