/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjogl;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import static com.jogamp.opengl.glu.GLU.createGLU;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import java.awt.Point;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.nio.IntBuffer;
import javax.swing.JFrame;

/**
 *
 * @author lev
 */
public abstract class Renderer implements GLEventListener, MouseMotionListener, MouseListener, KeyListener {

    public static final int NOTHING = 1, UPDATE = 2, SELECT = 3, ROTATE_CAMERA_LEFT = 4, ROTATE_CAMERA_RIGHT = 5, ROTATE_CAMERA_DOWN = 6;
    protected boolean isReleased = true;
    protected static final float CAMERA_SPEED = (float) 1;
    protected int textureRoom;
    protected int selectedMouse_x, selectedMouse_y;
    protected int cmd = UPDATE;
    protected boolean isHit = false;
    protected float width, height, aspect;
    protected int q = 0, w = 0, e = 0, r, t, u, i = 1, o;
    protected SmallCube smallCube = null;
    protected final JFrame context;
    protected Score score;
    public static int[] TEXTURES;

    protected Cube cube;
    protected GLU glu;
    protected MouseRotationHelper rotation;
    protected Face minFace;
//    public static boolean isInitComplete;

    abstract void initCube();

    public void refresh() {
        cube = new Cube(3);
        cube.drawRandom();

    }

    public Renderer(JFrame context) {
//        isInitComplete = false;
        this.context = context;
    }

    public void checkCamera() {

        if ((int) q < 10) {
            q += CAMERA_SPEED;
        } else if ((int) q > 10) {
            q -= CAMERA_SPEED;
        }
        if ((int) e < 10) {
            e += CAMERA_SPEED;
        } else if ((int) e > 10) {
            e -= CAMERA_SPEED;

        }
        if ((int) w < 8) {
            w += CAMERA_SPEED;
        } else if ((int) w > 8) {
            w -= CAMERA_SPEED;
        }

    }

    public double getAngle(Point target) {

        return Math.atan2(0 - target.y, 0 - target.x) * 180.0 / Math.PI;

    }

    public void setCamera(GL2 gl) {
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();

        glu.gluLookAt(q, w, e,
                r, t, 0,
                u, i, o);

    }

    public void rotateCameraLeft() {
        if ((int) q > -9) {
            q -= CAMERA_SPEED;
        }

    }

    public void rotateCameraRight() {
        if ((int) e > - 9) {
            e -= CAMERA_SPEED;
        }
    }

    public void rotateCameraDown() {
        if ((int) w > - 9) {
            w -= CAMERA_SPEED;
        }
    }

    protected void initTexture(GL2 gl) {
        ClassLoader cl = getClass().getClassLoader();
        TEXTURES = new int[8];
//        textureBlink = new int[6];
        Texture text;
        int fileName;
        try {
            File im;
            for (fileName = 0; fileName < 8; fileName++) {
                if (fileName < 7) {
                    im = new File(cl.getResource("Texture/" + fileName + ".png").getPath());
                } else {
                    im = new File(cl.getResource("Texture/" + fileName + ".png").getPath());

                }
                text = TextureIO.newTexture(im, true);
                TEXTURES[fileName] = text.getTextureObject(gl);
                System.out.println("Textures" + TEXTURES[fileName]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void init(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        glu = createGLU(gl);//new GLU();
//        glu = createGLU();//new GLU();
        gl.glEnable(GL2.GL_TEXTURE_2D);//////////////
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(0f, 0f, 0f, 0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
//        initTexture(gl);
//        isInitComplete = true;
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();

        this.width = width;
        this.height = height;

        if (height == 0) {
            height = 1;
        }
        aspect = (float) width / height;

        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, aspect, 0.1f, 100.0f);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();

    }

    public abstract void drawScene(GLAutoDrawable drawable);

    void pickCube(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        int viewport[] = new int[4];
        double modelView[] = new double[16];//GL_MODELVIEW_MATRIX
        double projection[] = new double[16];//GL_PROJECTION_MATRIX
        float realy = 0;// GL y coord pos
        double wcoord[] = new double[4];// wx, wy, wz;// returned xyz coords

        IntBuffer selectBuffer = Buffers.newDirectIntBuffer(512);
        int hint;

        gl.glSelectBuffer(512, selectBuffer);
        gl.glGetIntegerv(GL2.GL_VIEWPORT, viewport, 0);

        gl.glRenderMode(GL2.GL_SELECT);
        gl.glInitNames();

        gl.glPushName(0);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        glu.gluPickMatrix(selectedMouse_x, (double) viewport[3] - selectedMouse_y, 2, 2, viewport, 0);
        glu.gluPerspective(45.0f, aspect, 0.1f, 100.0f);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        drawScene(drawable);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glPopMatrix();

        gl.glFlush();
        hint = gl.glRenderMode(GL2.GL_RENDER);
        System.out.println("hits" + hint);
        processHits(hint, selectBuffer);

        cmd = UPDATE;
    }

    void processHits(int hits, IntBuffer buffer) {

        Face face = null;
        int offset = 0;
        int names;
        float z1, z2, minz = 2;
        for (int _i = 0; _i < hits; _i++) {
            names = buffer.get(offset);
            offset++;
            z1 = (float) (buffer.get(offset) & 0xffffffffL) / 0x7fffffff;//z point value
            offset++;
            z2 = (float) (buffer.get(offset) & 0xffffffffL) / 0x7fffffff;
            offset++;

            if (buffer.get(offset) == 123 && hits == 1) {
                hits = 0;

            }
            for (int j = 0; j < names; j++) {
                if (j == (names - 1)) {
                    if (z1 < minz) {//find the smallest z
                        minz = z1;

                        for (int x = 0; x < cube.getSize(); x++) {
                            for (int y = 0; y < cube.getSize(); y++) {

                                for (int z = 0; z < cube.getSize(); z++) {
                                    for (int id = 0; id < 6; id++) {

                                        if (cube.getSmallCube(x, y, z).getFaceByID(id).getID() == buffer.get(offset)) {
                                            System.out.println(cube.getSmallCube(x, y, z).getFaceByID(id).getID());
                                            smallCube = cube.getSmallCube(x, y, z);
                                            minFace = smallCube.getFaceByID(id);

                                        }
                                    }
                                }
                            }
                        }

                    }

                } else {
                }
                offset++;
            }
        }
        if (hits != 0) {
            cube.rotation.setFaceToRotate(minFace.getFace());
            cube.rotation.setXYZ(smallCube.getX(), smallCube.getY(), smallCube.getZ());
//            System.out.println("face id+" + minFace.getID());
//            System.out.println("x+" + smallCube.getX());
//            System.out.println("y+" + smallCube.getY());
//            System.out.println("z+" + smallCube.getZ());
//            System.out.println("id " + smallCube.getID());
            switch (minFace.getFace()) {
                case Face.FRONT:
                    System.out.println("FRONT" + minFace.toString());
                    isHit = true;

                    break;
                case Face.BACK:
                    System.out.println("BACK" + minFace.toString());
                    isHit = true;

                    break;
                case Face.LEFT:
                    System.out.println("LEFT" + minFace.toString());
                    isHit = true;

                    break;
                case Face.RIGHT:
                    System.out.println("RIGHT" + minFace.toString());
                    isHit = true;

                    break;
                case Face.TOP:
                    System.out.println("TOP" + minFace.toString());
                    isHit = true;

                    break;
                case Face.BOTTOM:
                    System.out.println("BOTTOM" + minFace.toString());
                    isHit = true;

                    break;
            }

        } else {
            isHit = false;
        }
    }

}
