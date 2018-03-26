/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjogl;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import java.awt.Point;

/**
 *
 * @author lev&Rotem
 */
public final class Cube implements Rotatble {

    private static final float AXIS_ROTATION_SPEED = (float) 6;
    private float KeyboardRotate_AXIX_Y = 0, KeyboardRotate_AXIX_X = 0, keyZoom, x_Clicked, y_Clicked;
    public boolean isCubeRotate = false, mouseReleased = true, hint = false;
    private boolean isRotateAxis = false;
    private int numberOfMoves;
    private Point anglePoint;
    private int faceToRotate;
    private SmallCube cubeArray[][][];
    private final LayerHelper layerHelper;
    public MouseRotationHelper rotation;
    private final int size;
    public int x, y, z;
    private boolean isWin = false;

    public void setCubeArray(SmallCube[][][] cubeArray) {
        this.cubeArray = cubeArray;
    }

    public MouseRotationHelper getRotation() {
        return rotation;
    }

    public int getCubeFaceColor(int face) {
        switch (face) {
            case Face.BACK:
                return cubeArray[1][1][2].getFaces()[face].getColor();
            case Face.BOTTOM:
                return cubeArray[1][0][1].getFaces()[face].getColor();

            case Face.FRONT:
                return cubeArray[1][1][0].getFaces()[face].getColor();

            case Face.LEFT:
                return cubeArray[0][1][1].getFaces()[face].getColor();

            case Face.RIGHT:
                return cubeArray[2][1][1].getFaces()[face].getColor();

            case Face.TOP:
                return cubeArray[1][2][1].getFaces()[face].getColor();

        }
        return -1;
    }

    public boolean isRotateAxis() {
        return isRotateAxis;

    }

    public void setAxisRotate(boolean is) {
        isRotateAxis = is;
    }

    public void enableHint() {
        rotation.setLevel();
        hint = true;
        rotation.hint = true;
        isRotateAxis = false;
        rotation.setFirstTime(true);
    }

    public Cube(int size) {
        this.size = size;
        initCube();
        anglePoint = new Point();
        layerHelper = new LayerHelper(this);
        rotation = new MouseRotationHelper(this);
    }

    public Cube(SmallCube cubeArray[][][]) {
        this.cubeArray = cubeArray;
        this.size = cubeArray.length;
        anglePoint = new Point();
        layerHelper = new LayerHelper(this);
        rotation = new MouseRotationHelper(this);
    }


    
    public void drawRandom() {
        int numOfRotations = (int) (Math.random() * 6) + 6;
        for (int i = 0; i < numOfRotations; i++) {
            int randX = (int) (Math.random() * 3);
            int randFunc = (int) (Math.random() * 6);
            switch (randFunc) {
                case 0:
                    layerHelper.rotateLeftY(layerHelper.getLayerY(randX), randX);
                    break;
                case 1:
                    layerHelper.rotateLeftZ(layerHelper.getLayerZ(randX), randX);
                    break;
                case 2:
                    layerHelper.rotateLeftX(layerHelper.getLayerX(randX), randX);
                    break;
                case 3:
                    layerHelper.rotateRightX(layerHelper.getLayerX(randX), randX);
                    break;
                case 4:
                    layerHelper.rotateRightY(layerHelper.getLayerY(randX), randX);
                    break;
                case 5:
                    layerHelper.rotateRightZ(layerHelper.getLayerZ(randX), randX);
                    break;

            }
        }

    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public void incNumberOfMoves() {
        this.numberOfMoves++;
    }

    public void setNumberOfMoves(int n) {
        this.numberOfMoves = n;
    }

    public Point getAnglePoint() {
        return anglePoint;
    }

    public void setAnglePoint(Point anglePoint) {
        this.anglePoint = anglePoint;
    }

    public LayerHelper getAxis() {
        return layerHelper;
    }

    public void initCube() {
        cubeArray = new SmallCube[size][size][size];
        for (int _x = 0; _x < cubeArray.length; _x++) {

            for (int _y = 0; _y < cubeArray.length; _y++) {

                for (int _z = 0; _z < cubeArray.length; _z++) {
                    int[] arr = {0, 1, 2, 3, 4, 5, 6};
                    cubeArray[_x][_y][_z] = new SmallCube(_x, _y, _z);
                    cubeArray[_x][_y][_z].initArrayOfColors(arr);
                    cubeArray[_x][_y][_z].initFaces();
                    cubeArray[_x][_y][_z].setBlackColors();

                }
            }

        }
    }

    public SmallCube[][][] getArray() {

        return cubeArray;
    }

    public SmallCube getSmallCube(int x, int y, int z) {
        return cubeArray[x][y][z];
    }

    public int getSize() {
        return cubeArray.length;
    }

    public void drawCube(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        for (x = 0; x < size; x++) {
            for (y = 0; y < size; y++) {
                for (z = 0; z < size; z++) {
                    gl.glPushMatrix();
                    SmallCube c = getSmallCube(x, y, z);

                    if (hint) {
                        rotation.rotateAxisHint(gl, c);
                    } else if (!hint && isRotateAxis()) {
                        rotation.rotateAxisMouse(gl, c);
                    } else if (isCubeRotate) {
                        rotation.rotateCube(gl, c);
                    }
                    c = getSmallCube(x, y, z);
                    gl.glTranslatef(((x - 1) * 2f), ((y - 1) * 2f), (-(z - 1) * 2f));////moves the object from its "private world" to the "greal world"
                    c.drawCube(drawable);                               //for exemple Rotem's phone on Lev's Mac
                    gl.glPopMatrix();
                }
            }
        }

    }

    public int getFaceToRotate() {
        return faceToRotate;
    }

    public void setFaceToRotate(int faceToRotate) {
        this.faceToRotate = faceToRotate;
    }

    public void rotateXLeftCube(GL2 gl, SmallCube sc) {

        if ((sc.angle >= 90)) {
            setAxisRotate(false);
            layerHelper.rotateLeftX(layerHelper.getLayerX(0), 0);
            layerHelper.rotateLeftX(layerHelper.getLayerX(1), 1);
            layerHelper.rotateLeftX(layerHelper.getLayerX(2), 2);
            layerHelper.getLayerX(0).setZero();
            layerHelper.getLayerX(1).setZero();
            layerHelper.getLayerX(2).setZero();
            rotation.stopMove();
            isCubeRotate = false;
            mouseReleased = true;
            x = y = z = 0;
        } else if (!mouseReleased) {
            sc.angle = -(float) anglePoint.getY();
            gl.glRotatef(sc.angle, 1, 0, 0);
        } else if (hint) {

            setAxisRotate(true);
            sc.angle += AXIS_ROTATION_SPEED;
            gl.glRotatef(sc.angle, 1, 0, 0);
            isCubeRotate = true;

        } else {
            if (sc.angle > 15) {
                sc.angle += AXIS_ROTATION_SPEED;
                gl.glRotatef(sc.angle, 1, 0, 0);
            } else {
                if (sc.angle <= 0) {

                    setAxisRotate(false);
                    isCubeRotate = false;
                    mouseReleased = true;
                    layerHelper.getLayerX(rotation.x).setZero();
                }
                sc.angle -= AXIS_ROTATION_SPEED;
                gl.glRotatef(sc.angle, 1, 0, 0);

            }
        }
    }

    @Override
    public void rotateXRightCube(GL2 gl, SmallCube sc) {

        if (sc.angle <= -90) {
            setAxisRotate(false);

            layerHelper.rotateRightX(layerHelper.getLayerX(0), 0);
            layerHelper.rotateRightX(layerHelper.getLayerX(1), 1);
            layerHelper.rotateRightX(layerHelper.getLayerX(2), 2);
            layerHelper.getLayerX(0).setZero();
            layerHelper.getLayerX(1).setZero();
            layerHelper.getLayerX(2).setZero();
            rotation.stopMove();
            isCubeRotate = false;
            mouseReleased = true;
            rotation.stopMove();
            x = y = z = 0;

        } else if (!mouseReleased) {
            sc.angle = -(float) anglePoint.getY();
            gl.glRotatef(sc.angle, 1, 0, 0);
        } else if (hint) {

            setAxisRotate(true);
            sc.angle -= AXIS_ROTATION_SPEED;
            gl.glRotatef(sc.angle, 1, 0, 0);
            isCubeRotate = true;
        } else {
            if (sc.angle < -15) {
                sc.angle -= AXIS_ROTATION_SPEED;
                gl.glRotatef(sc.angle, 1, 0, 0);
            } else {
                if (sc.angle >= 0) {
                    setAxisRotate(false);

                    isCubeRotate = false;
                    mouseReleased = true;
                    layerHelper.getLayerX(rotation.x).setZero();
                }
                sc.angle += AXIS_ROTATION_SPEED;
                gl.glRotatef(sc.angle, 1, 0, 0);

            }

        }
    }

    @Override
    public void rotateYRightCube(GL2 gl, SmallCube sc) {

        if ((float) sc.angle >= 90) {
            setAxisRotate(false);

            layerHelper.rotateRightY(layerHelper.getLayerY(0), 0);
            layerHelper.rotateRightY(layerHelper.getLayerY(1), 1);
            layerHelper.rotateRightY(layerHelper.getLayerY(2), 2);
            layerHelper.getLayerY(0).setZero();
            layerHelper.getLayerY(1).setZero();
            layerHelper.getLayerY(2).setZero();
            rotation.stopMove();
            isCubeRotate = false;
            mouseReleased = true;
            rotation.stopMove();
            x = y = z = 0;
        } else if (!mouseReleased) {
            sc.angle = (float) anglePoint.getX();
            gl.glRotatef(sc.angle, 0, 1, 0);
        } else if (hint) {

            setAxisRotate(true);
            sc.angle += AXIS_ROTATION_SPEED;
            gl.glRotatef(sc.angle, 0, 1, 0);
            isCubeRotate = true;
        } else {
            if (sc.angle > 15) {
                sc.angle += AXIS_ROTATION_SPEED;
                gl.glRotatef(sc.angle, 0, 1, 0);

            } else {
                if (sc.angle <= 0) {
                    setAxisRotate(false);

                    isCubeRotate = false;
                    mouseReleased = true;
                    layerHelper.getLayerY(rotation.y).setZero();
                }
                sc.angle -= AXIS_ROTATION_SPEED;
                gl.glRotatef(sc.angle, 0, 1, 0);

            }
        }
    }

    @Override
    public void rotateYLeftCube(GL2 gl, SmallCube sc) {
        if ((float) sc.angle <= -90) {
            setAxisRotate(false);

            layerHelper.rotateLeftY(layerHelper.getLayerY(0), 0);
            layerHelper.rotateLeftY(layerHelper.getLayerY(1), 1);
            layerHelper.rotateLeftY(layerHelper.getLayerY(2), 2);
            layerHelper.getLayerY(0).setZero();
            layerHelper.getLayerY(1).setZero();
            layerHelper.getLayerY(2).setZero();
            rotation.stopMove();
            isCubeRotate = false;
            mouseReleased = true;
            x = y = z = 0;

        } else if (!mouseReleased) {
            sc.angle = (float) anglePoint.getX();
            gl.glRotatef(sc.angle, 0, 1, 0);
        } else if (hint) {
            isCubeRotate = true;
            setAxisRotate(true);
            sc.angle -= AXIS_ROTATION_SPEED;
            gl.glRotatef(sc.angle, 0, 1, 0);
        } else {
            if (sc.angle < -15) {
                sc.angle -= AXIS_ROTATION_SPEED;
                gl.glRotatef(sc.angle, 0, 1, 0);
            } else {
                if (sc.angle >= 0) {
                    setAxisRotate(false);

                    isCubeRotate = false;
                    mouseReleased = true;
                    layerHelper.getLayerY(rotation.y).setZero();
                }
                sc.angle += AXIS_ROTATION_SPEED;
                gl.glRotatef(sc.angle, 0, 1, 0);

            }

        }
    }

    @Override
    public void rotateZRightCube(GL2 gl, SmallCube sc) {
        if ((float) sc.angle <= -90) {
            setAxisRotate(false);

            layerHelper.rotateRightZ(layerHelper.getLayerZ(0), 0);
            layerHelper.rotateRightZ(layerHelper.getLayerZ(1), 1);
            layerHelper.rotateRightZ(layerHelper.getLayerZ(2), 2);
            layerHelper.getLayerZ(0).setZero();
            layerHelper.getLayerZ(1).setZero();
            layerHelper.getLayerZ(2).setZero();
            rotation.stopMove();
            isCubeRotate = false;
            mouseReleased = true;
            rotation.stopMove();
            x = y = z = 0;

        } else if (!mouseReleased) {
            sc.angle = (float) anglePoint.getY();
            gl.glRotatef(sc.angle, 0, 0, 1);
        } else if (hint) {
            setAxisRotate(true);
            isCubeRotate = true;
            sc.angle -= AXIS_ROTATION_SPEED;
            gl.glRotatef(sc.angle, 0, 0, 1);
        } else {
            if (sc.angle < -15) {
                sc.angle -= AXIS_ROTATION_SPEED;

                gl.glRotatef(sc.angle, 0, 0, 1);
            } else {
                if (sc.angle >= 0) {
                    mouseReleased = true;
                    setAxisRotate(false);

                    isCubeRotate = false;
                    layerHelper.getLayerZ(rotation.z).setZero();
                }

                sc.angle += AXIS_ROTATION_SPEED;
                gl.glRotatef(sc.angle, 0, 0, 1);

            }

        }

    }

    @Override
    public void rotateZLeftCube(GL2 gl, SmallCube sc) {

        if ((float) sc.angle >= 90) {
            setAxisRotate(false);

            layerHelper.rotateLeftZ(layerHelper.getLayerZ(0), 0);
            layerHelper.rotateLeftZ(layerHelper.getLayerZ(1), 1);
            layerHelper.rotateLeftZ(layerHelper.getLayerZ(2), 2);
            layerHelper.getLayerZ(0).setZero();
            layerHelper.getLayerZ(1).setZero();
            layerHelper.getLayerZ(2).setZero();
            rotation.stopMove();
            isCubeRotate = false;
            mouseReleased = true;
            rotation.stopMove();
//            x = y = z = 0;
        } else if (!mouseReleased) {

            sc.angle = (float) anglePoint.getY();
            gl.glRotatef(sc.angle, 0, 0, 1);
        } else {

            if (hint) {

                sc.angle += AXIS_ROTATION_SPEED;
                gl.glRotatef(sc.angle, 0, 0, 1);
                setAxisRotate(true);
                isCubeRotate = true;
            } else if (sc.angle > 15) {
                sc.angle += AXIS_ROTATION_SPEED;

                gl.glRotatef(sc.angle, 0, 0, 1);
            } else {
                if (sc.angle <= 0) {
                    setAxisRotate(false);
                    isCubeRotate = false;
                    mouseReleased = true;
                    layerHelper.getLayerZ(rotation.z).setZero();
                }

                sc.angle -= AXIS_ROTATION_SPEED;
                gl.glRotatef(sc.angle, 0, 0, 1);

            }
        }

    }

///////////////////////////////////////////////////////////
    @Override
    public void rotateXRightAnimation(GL2 gl, SmallCube sc) {
        if (sc.angle <= -90) {
            layerHelper.rotateRightX(layerHelper.getLayerX(rotation.x), rotation.x);
            isWin = CubeLogic.CheckLayers(this);
            layerHelper.getLayerX(rotation.x).setZero();
            mouseReleased = true;
            isCubeRotate = false;
            setAxisRotate(false);

            incNumberOfMoves();
            rotation.stopMove();
            x = y = z = 0;

        } else if (!mouseReleased) {
            sc.angle = -(float) anglePoint.getY();
            gl.glRotatef(sc.angle, 1, 0, 0);
        } else if (hint) {

            setAxisRotate(true);
            sc.angle -= AXIS_ROTATION_SPEED + 1;
            gl.glRotatef(sc.angle, 1, 0, 0);

        } else {
            if (sc.angle < -15) {
                sc.angle -= AXIS_ROTATION_SPEED;
                gl.glRotatef(sc.angle, 1, 0, 0);
            } else {
                if (sc.angle >= 0) {
                    setAxisRotate(false);

                    isCubeRotate = false;
                    mouseReleased = true;
                    layerHelper.getLayerX(rotation.x).setZero();
                }
                sc.angle += AXIS_ROTATION_SPEED;
                gl.glRotatef(sc.angle, 1, 0, 0);

            }

        }
    }

    @Override
    public void rotateXLeftAnimation(GL2 gl, SmallCube sc) {

        if ((sc.angle >= 90)) {
            setAxisRotate(false);

            isCubeRotate = false;
            mouseReleased = true;

            layerHelper.rotateLeftX(layerHelper.getLayerX(rotation.x), rotation.x);
            layerHelper.getLayerX(rotation.x).setZero();
            isWin = CubeLogic.CheckLayers(this);
            incNumberOfMoves();
            rotation.stopMove();
            x = y = z = 0;

        } else if (!mouseReleased) {
            sc.angle = -(float) anglePoint.getY();
            gl.glRotatef(sc.angle, 1, 0, 0);
        } else if (hint) {

            setAxisRotate(true);
            sc.angle += AXIS_ROTATION_SPEED + 1;
            gl.glRotatef(sc.angle, 1, 0, 0);

        } else {
            if (sc.angle > 15) {
                sc.angle += AXIS_ROTATION_SPEED;
                gl.glRotatef(sc.angle, 1, 0, 0);
            } else {
                if (sc.angle <= 0) {
                    setAxisRotate(false);
                    isCubeRotate = false;
                    mouseReleased = true;
                    layerHelper.getLayerX(rotation.x).setZero();
                }
                sc.angle -= AXIS_ROTATION_SPEED;
                gl.glRotatef(sc.angle, 1, 0, 0);

            }
        }
    }

    @Override
    public void rotateYRightAnimation(GL2 gl, SmallCube sc) {

        if (sc.angle >= 90) {
            layerHelper.rotateRightY(layerHelper.getLayerY(rotation.y), rotation.y);
            layerHelper.getLayerY(rotation.y).setZero();
            isWin = CubeLogic.CheckLayers(this);
            mouseReleased = true;
            isCubeRotate = false;
            setAxisRotate(false);

            incNumberOfMoves();
            rotation.stopMove();
            x = y = z = 0;

        } else if (!mouseReleased) {
            sc.angle = (float) anglePoint.getX();
            gl.glRotatef(sc.angle, 0, 1, 0);
        } else if (hint) {

            setAxisRotate(true);
            sc.angle += AXIS_ROTATION_SPEED + 1;
            gl.glRotatef(sc.angle, 0, 1, 0);

        } else {
            if (sc.angle > 15) {
                sc.angle += AXIS_ROTATION_SPEED;
                gl.glRotatef(sc.angle, 0, 1, 0);

            } else {
                if (sc.angle <= 0) {
                    setAxisRotate(false);

                    isCubeRotate = false;
                    mouseReleased = true;
                    layerHelper.getLayerY(rotation.y).setZero();
                }
                sc.angle -= AXIS_ROTATION_SPEED;
                gl.glRotatef(sc.angle, 0, 1, 0);

            }
        }
    }

    @Override
    public void rotateYLeftAnimation(GL2 gl, SmallCube sc) {
        Layer axis = layerHelper.getLayerY(rotation.y);

        if (sc.angle <= -90) {
            layerHelper.rotateLeftY(axis, rotation.y);
            axis.setZero();
            isWin = CubeLogic.CheckLayers(this);
            mouseReleased = true;
            isCubeRotate = false;
            setAxisRotate(false);

            incNumberOfMoves();
            rotation.stopMove();
            x = y = z = 0;

        } else if (!mouseReleased) {
            sc.angle = (float) anglePoint.getX();
            gl.glRotatef(sc.angle, 0, 1, 0);
        } else if (hint) {
            setAxisRotate(true);
            sc.angle -= AXIS_ROTATION_SPEED + 1;
            gl.glRotatef(sc.angle, 0, 1, 0);
        } else {
            if (sc.angle < -15) {
                sc.angle -= AXIS_ROTATION_SPEED;
                gl.glRotatef(sc.angle, 0, 1, 0);
            } else {
                if (sc.angle >= 0) {
                    setAxisRotate(false);

                    isCubeRotate = false;
                    mouseReleased = true;
                    axis.setZero();
                }
                sc.angle += AXIS_ROTATION_SPEED;
                gl.glRotatef(sc.angle, 0, 1, 0);

            }

        }
    }

    @Override
    public void rotateZRightAnimation(GL2 gl, SmallCube sc) {

        if (sc.angle <= -90) {
            layerHelper.rotateRightZ(layerHelper.getLayerZ(rotation.z), rotation.z);
            isWin = CubeLogic.CheckLayers(this);
            mouseReleased = true;
            setAxisRotate(false);
            isCubeRotate = false;
            layerHelper.getLayerZ(rotation.z).setZero();
            incNumberOfMoves();
            rotation.stopMove();
            x = y = z = 0;

        } else if (!mouseReleased) {
            sc.angle = (float) anglePoint.getY();
            gl.glRotatef(sc.angle, 0, 0, 1);
        } else if (hint) {

            setAxisRotate(true);

            sc.angle -= AXIS_ROTATION_SPEED + 1;
            gl.glRotatef(sc.angle, 0, 0, 1);
        } else {
            if (sc.angle < -15) {
                sc.angle -= AXIS_ROTATION_SPEED;

                gl.glRotatef(sc.angle, 0, 0, 1);
            } else {
                if (sc.angle >= 0) {
                    mouseReleased = true;
                    setAxisRotate(false);

                    isCubeRotate = false;
                    layerHelper.getLayerZ(rotation.z).setZero();
                }

                sc.angle += AXIS_ROTATION_SPEED;
                gl.glRotatef(sc.angle, 0, 0, 1);

            }

        }

    }

    @Override
    public void rotateZLeftAnimation(GL2 gl, SmallCube sc) {
        if (sc.angle >= 90) {

            layerHelper.rotateLeftZ(layerHelper.getLayerZ(rotation.z), rotation.z);
            layerHelper.getLayerZ(rotation.z).setZero();
            mouseReleased = true;
            setAxisRotate(false);
            isWin = CubeLogic.CheckLayers(this);
            incNumberOfMoves();
            rotation.stopMove();
            x = y = z = 0;

        } else if (!mouseReleased) {

            sc.angle = (float) anglePoint.getY();
            gl.glRotatef(sc.angle, 0, 0, 1);
        } else {

            if (hint) {

                sc.angle += AXIS_ROTATION_SPEED + 1;
                gl.glRotatef(sc.angle, 0, 0, 1);
                setAxisRotate(true);

            } else if (sc.angle > 15) {
                sc.angle += AXIS_ROTATION_SPEED;

                gl.glRotatef(sc.angle, 0, 0, 1);
            } else {
                if (sc.angle <= 0) {
                    setAxisRotate(false);
                    isCubeRotate = false;
                    mouseReleased = true;
                    layerHelper.getLayerZ(rotation.z).setZero();
                }

                sc.angle -= AXIS_ROTATION_SPEED;
                gl.glRotatef(sc.angle, 0, 0, 1);

            }
        }

    }

    public boolean isWin() {
        return isWin;
    }
}
