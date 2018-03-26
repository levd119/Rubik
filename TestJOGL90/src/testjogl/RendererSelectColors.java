/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjogl;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import testjogl.MouseRotationHelper.MouseRotation;

/**
 *
 * @author lev
 */
public final class RendererSelectColors extends Renderer {

    private Room room;
    private SmallCube[][][] cubeArray;
    private int sum[][][];
    private int count = 0;

    @Override
    public void refresh() {
        super.refresh(); //To change body of generated methods, choose Tools | Templates.
        cubeArray = cube.getArray();
    }

    public SmallCube[][][] getCubeArray() {
        return cubeArray;
    }

    public RendererSelectColors(JFrame context) {
        super(context);

    }

    private void initCubeArray() {

        cubeArray = new SmallCube[3][3][3];
        sum = new int[cubeArray.length][cubeArray.length][cubeArray.length];
        for (int x = 0; x < cubeArray.length; x++) {

            for (int y = 0; y < cubeArray.length; y++) {

                for (int z = 0; z < cubeArray.length; z++) {
//                    int[] arr = {Face.ORANGE, Face.RED, 2, 3, 4, 5, 6};////////<--------------
                    int[] arr = {0, 1, 2, 3, 4, 5, 6};////////<--------------
                    cubeArray[x][y][z] = new SmallCube(x, y, z);
                    cubeArray[x][y][z].initArrayOfColors(arr);
                    cubeArray[x][y][z].initFaces();
                    cubeArray[x][y][z].setBlackColors();

                }
            }

        }

    }

    @Override
    public void init(GLAutoDrawable drawable) {
        super.init(drawable); //To change body of generated methods, choose Tools | Templates.
        final GL2 gl = drawable.getGL().getGL2();
        initTexture(gl);

        room = new Room(Renderer.TEXTURES);
//        cube.drawRandom();
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        switch (cmd) {
            case UPDATE:
                checkCamera();
                gl.glRenderMode(GL2.GL_RENDER_MODE);
                drawScene(drawable);
                break;
            case SELECT:
                pickCube(drawable);
                break;
        }

        gl.glFlush();

    }

    @Override
    public void drawScene(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glClearColor(0f, 0, 0, 1f);

        setCamera(gl);
        room.drawRoom(gl);

        cube.drawCube(drawable);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int mouse_x_drag = e.getX() - selectedMouse_x;
        int mouse_y_drag = e.getY() - selectedMouse_y;

        cube.setAnglePoint(new Point(mouse_x_drag / 5, -mouse_y_drag / 5));
        if ((isReleased && (e.getX() != selectedMouse_x && e.getY() != selectedMouse_y))) {
            if (!isHit) {
                rotation.setMousePoint(e.getPoint());
                cube.isCubeRotate = true;

                isReleased = false;

                double angle = getAngle(new Point(mouse_x_drag, mouse_y_drag));

                if (angle >= -10 && angle <= 10) {
                    rotation.setMouseRotationDirection(MouseRotation.MOUSE_DRAG_LEFT);

                } else if (angle > 10 && angle < 80) {

                    rotation.setMouseRotationDirection(MouseRotation.MOUSE_DRAG_LEFT_UP);
                } else if (angle >= 80 && angle <= 100) {
                    rotation.setMouseRotationDirection(MouseRotation.MOUSE_DRAG_UP);

                } else if (angle > 100 && angle < 170) {
                    rotation.setMouseRotationDirection(MouseRotation.MOUSE_DRAG_RIGHT_UP);

                } else if (angle >= 170 && angle <= 180 || angle < -170 && angle > -180) {
                    rotation.setMouseRotationDirection(MouseRotation.MOUSE_DRAG_RIGHT);

                } else if (angle < -10 && angle > -80) {

                    rotation.setMouseRotationDirection(MouseRotation.MOUSE_DRAG_LEFT_DOWN);
                } else if (angle <= -80 && angle >= -100) {
                    rotation.setMouseRotationDirection(MouseRotation.MOUSE_DRAG_DOWN);

                } else if (angle < -100 && angle > -170) {
                    rotation.setMouseRotationDirection(MouseRotation.MOUSE_DRAG_RIGHT_DOWN);

                }

            }

        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        cmd = SELECT;
        cube.mouseReleased = false;
//        isReleased = true;
        selectedMouse_x = e.getX();
        selectedMouse_y = e.getY();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isHit) {
            int color = ((FaceColorsSelectScreen) context).getColor();
            minFace.setColor(color);
//            minFace.setTexture(Renderer.TEXTURE[color]);

        } else {
            cube.mouseReleased = true;
            isReleased = true;

        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
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

    public boolean isValid() {
        count = 0;
        int arr[] = new int[]{0, 0, 0, 0, 0, 0};

        for (int x = 0; x < cubeArray.length; x++) {
            for (int y = 0; y < cubeArray.length; y++) {
                for (int z = 0; z < cubeArray.length; z++) {
                    for (int face = 0; face < 6; face++) {
                        if (cubeArray[x][y][z].getFaces()[face].isIsVisiable()) {
                            arr[cubeArray[x][y][z].getFaces()[face].getColor()]++;
                        }
                    }

                }
            }
        }

        for (int j = 0; j < arr.length; j++) {
            if(arr[j]==9){
                count++;
            }
        }
        System.out.println(count);
        return count == 6;

    }

    @Override
    void initCube() {

        initCubeArray();

        cube = new Cube(cubeArray);
        rotation = cube.getRotation();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

}
