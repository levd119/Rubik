/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjogl;

import com.jogamp.opengl.GL2;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author lev&Rotem
 */
public class MouseRotationHelper implements Serializable {

    private static final String YELLOW_TOP = "Yellow Top&";
    private static final String WHITE_FRONT = "White Front&";
    private static final String WHITE_TOP = "White TOP&";
    private int cubeRotate;
    private StringBuilder message;

    private String cubeRotateString() {
        switch (cubeRotate) {
            case 1:
                return "";
            case 2:
                return "";

            case 3:
                return "";

            case 4:
                return "";

            case 5:
                return "";

        }
        return "";

    }

    public void setMovesExists(boolean b) {
        this.isMovesExists = b;
    }

    public enum MouseRotation {

        MOUSE_DRAG_LEFT_UP, MOUSE_DRAG_LEFT_DOWN, MOUSE_DRAG_RIGHT_UP,
        MOUSE_DRAG_RIGHT_DOWN, MOUSE_DRAG_LEFT, MOUSE_DRAG_RIGHT,
        MOUSE_DRAG_DOWN, MOUSE_DRAG_UP

    };
    public boolean hint = false, isMovesExists = false;
    private boolean started = false;
    private boolean isFirstTime;

    private int level = 0;
    private LayerRotationData sc = null;

    private ArrayList<LayerRotationData> arrayOfMoves;
    private MouseRotation mouseRotationDirection;
    private int faceToRotate;
    private Cube cube;
    private Point mousePoint;
    public int x;
    public int y;
    public int z;
    public int axis;
    private Iterator iterator;
    private Stack<LayerRotationData> stack;

    public boolean isMovesExists() {
        return isMovesExists;
    }

    void setLevel() {
        level = getLevel();
    }

    public void setFirstTime(boolean isFirstTime) {
        this.isFirstTime = isFirstTime;
    }

    public boolean isFirstTime() {
        return isFirstTime;
    }

    private int getLevel() {
        switch (CubeLogic.whichLevel(cube)) {
            case CubeLogic.FIRST:
                System.out.println("CubeLogic.FIRST");
                level = CubeLogic.FIRST;

                break;
            case CubeLogic.SECOND:
                System.out.println("CubeLogic.SECOND");

                level = CubeLogic.SECOND;
                break;
            case CubeLogic.THIRD:
                System.out.println("CubeLogic.THIRD");

                level = CubeLogic.THIRD;

                break;
        }

        return level;
    }

    public MouseRotationHelper() {
        started = false;
    }

    public void stopMove() {
        started = false;
    }

    public void startMove() {
        started = true;
    }

    public void setXYZ(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point getMousePoint() {
        return mousePoint;
    }

    public void setMousePoint(Point mousePoint) {
        this.mousePoint = mousePoint;
    }

    public MouseRotationHelper(Rotatble cube) {
        this.cube = (Cube) cube;
    }

    public MouseRotation getMouseRotationDirection() {
        return mouseRotationDirection;
    }

    public void setMouseRotationDirection(MouseRotation mouseRotationDirection) {
        this.mouseRotationDirection = mouseRotationDirection;
    }

    public int getFaceToRotate() {
        return faceToRotate;
    }

    public void setFaceToRotate(int faceToRotate) {
        this.faceToRotate = faceToRotate;
    }

    public Cube getCube() {
        return cube;
    }

    public void setCube(Cube cube) {
        this.cube = cube;
    }

    public void setArrayOfMoves() {
        isMovesExists = true;
        message = new StringBuilder();
        switch (level) {
            case CubeLogic.FIRST:
                System.out.println("_______ First Cross________");
                arrayOfMoves = CubeLogic.getYellowPlus(cube);

                if (arrayOfMoves.isEmpty()) {
                    arrayOfMoves = CubeLogic.getYellowLayer(cube);
                System.out.println("_______ First edge________");

                }
                message.append(YELLOW_TOP);

                break;
            case CubeLogic.SECOND:
                System.out.println("Second");
                arrayOfMoves = CubeLogic.getSecondLayer(cube);
                message.append(WHITE_FRONT);
                break;
            case CubeLogic.THIRD:
                arrayOfMoves = CubeLogic.getWhitePlus(cube);

                if (arrayOfMoves.isEmpty()) {
                    arrayOfMoves = CubeLogic.whiteEdges(cube);

                }

                if (arrayOfMoves.isEmpty()) {
                    arrayOfMoves = CubeLogic.positionCorners(cube);
                }
                if (arrayOfMoves.isEmpty()) {
                    arrayOfMoves = CubeLogic.flipCorners(cube);

                }
                message.append(WHITE_TOP);

                break;

        }

        Collections.reverse(arrayOfMoves);
//        System.out.println(arrayOfMoves.toString());
        stack = new Stack();
        stack.addAll(arrayOfMoves);

    }

    public boolean isIsSmallCubeColorOnTop(int color) {
        return cube.getArray()[1][2][1].getFaces()[Face.TOP].getColor() == color;
    }

    public boolean isIsSmallCubeColorOnFront(int color) {
        return cube.getArray()[1][1][0].getFaces()[Face.FRONT].getColor() == color;
    }

    public void rotateSmallCubeTop(GL2 gl, SmallCube c, int color) {

        if (cube.getArray()[1][1][0].getFaces()[Face.FRONT].getColor() == color) {
            cube.rotateXRightCube(gl, c);
            cubeRotate = 1;
        }
        if (cube.getArray()[0][1][1].getFaces()[Face.LEFT].getColor() == color) {
            cube.rotateZRightCube(gl, c);
            cubeRotate = 2;

        }
        if (cube.getArray()[1][1][2].getFaces()[Face.BACK].getColor() == color) {
            cube.rotateXLeftCube(gl, c);
            cubeRotate = 3;

        }
        if (cube.getArray()[2][1][1].getFaces()[Face.RIGHT].getColor() == color) {
            cube.rotateZLeftCube(gl, c);
            cubeRotate = 4;

        }
        if (cube.getArray()[1][0][1].getFaces()[Face.BOTTOM].getColor() == color) {
            cube.rotateXRightCube(gl, c);
            cubeRotate = 5;

        }

    }

    public void rotateSmallCubeFront(GL2 gl, SmallCube c, int color) {

        if (cube.getArray()[1][2][1].getFaces()[Face.TOP].getColor() == color) {
            cube.rotateXLeftCube(gl, c);
        }
        if (cube.getArray()[0][1][1].getFaces()[Face.LEFT].getColor() == color) {
            cube.rotateYRightCube(gl, c);
        }
        if (cube.getArray()[1][1][2].getFaces()[Face.BACK].getColor() == color) {
            cube.rotateXLeftCube(gl, c);
        }
        if (cube.getArray()[2][1][1].getFaces()[Face.RIGHT].getColor() == color) {
            cube.rotateYLeftCube(gl, c);
        }
        if (cube.getArray()[1][0][1].getFaces()[Face.BOTTOM].getColor() == color) {
            cube.rotateXRightCube(gl, c);

        }

    }

    public void rotateAxisHint(GL2 gl, SmallCube c) {

        switch (level) {
            case CubeLogic.FIRST:
                if (isIsSmallCubeColorOnTop(Face.YELLOW)) {
                    if (isFirstTime()) {
                        setArrayOfMoves();
                        setFirstTime(false);
                    }

                    rotate(gl, c);
                } else {
                    rotateSmallCubeTop(gl, c, Face.YELLOW);
                }
                break;
            case CubeLogic.SECOND:

                if (isIsSmallCubeColorOnFront(Face.WHITE)) {
                    if (isFirstTime()) {
                        setArrayOfMoves();
                        setFirstTime(false);
                    }

                    rotate(gl, c);
                } else {
                    rotateSmallCubeFront(gl, c, Face.WHITE);
                }

                break;
            case CubeLogic.THIRD:
                if (isIsSmallCubeColorOnTop(Face.WHITE)) {

                    if (isFirstTime()) {
                        setArrayOfMoves();
                        setFirstTime(false);
                    }

                    rotate(gl, c);
                } else {
                    rotateSmallCubeTop(gl, c, Face.WHITE);
                }

                break;

        }

    }

    public void rotate(GL2 gl, SmallCube c) {

        if (!cube.isRotateAxis() && !started) {
            if (!stack.empty()) {
                started = true;
                sc = (LayerRotationData) stack.pop();
                x = sc.getLayer().getAxisNum();
                y = sc.getLayer().getAxisNum();
                z = sc.getLayer().getAxisNum();
            } else {
                hint = false;
                cube.hint = false;
                started = false;

            }
        }

        if (hint && started) {

            switch (sc.getLayer().getId()) {
                case Layer.X:
                    if (c.getX() == sc.getLayer().getAxisNum()) {

                        //
                        if (sc.getLayer().getAxisNum() == 0) {
                            //Left
                            if (sc.getDirection() == LayerRotationData.CLOCKWISE) {

                                cube.rotateXLeftAnimation(gl, c);
                            } else {
                                cube.rotateXRightAnimation(gl, c);
                            }

                        } ///
                        else {
                            //Right
                            if (sc.getDirection() == LayerRotationData.COUNTERCLOCKWISE) {
                                cube.rotateXLeftAnimation(gl, c);

                            } else {

                                cube.rotateXRightAnimation(gl, c);
                            }
                        }
                    }

                    break;
                case Layer.Y:
                    if (c.getY() == sc.getLayer().getAxisNum()) {

                        //
                        if (sc.getLayer().getAxisNum() == 2) {
                            //TOP
                            if (sc.getDirection() == LayerRotationData.CLOCKWISE) {
                                cube.rotateYLeftAnimation(gl, c);
                            } else {

                                cube.rotateYRightAnimation(gl, c);
                            }

                        } else {
                            //BOTTOM
                            if (sc.getDirection() == LayerRotationData.COUNTERCLOCKWISE) {
                                cube.rotateYLeftAnimation(gl, c);
                            } else {
                                cube.rotateYRightAnimation(gl, c);
                            }

                        }
                    }
                    break;
                case Layer.Z:

                    if (c.getZ() == sc.getLayer().getAxisNum()) {

                        if (sc.getLayer().getAxisNum() == 2) {
                            //Back
                            if (sc.getDirection() == LayerRotationData.CLOCKWISE) {

                                cube.rotateZLeftAnimation(gl, c);
                            } else {
                                cube.rotateZRightAnimation(gl, c);
                            }

                        } else {
                            //Front
                            if (sc.getDirection() == LayerRotationData.COUNTERCLOCKWISE) {

                                cube.rotateZLeftAnimation(gl, c);
                            } else {
                                cube.rotateZRightAnimation(gl, c);
                            }
                        }
                    }
                    break;

            }
        }
    }

    public void rotateAxisMouse(GL2 gl, SmallCube c) {

        switch (mouseRotationDirection) {

            case MOUSE_DRAG_LEFT_UP:
                if (c.getZ() == z && faceToRotate == Face.RIGHT) {
                    cube.rotateZLeftAnimation(gl, c);

                } else if (c.getY() == y && faceToRotate == Face.FRONT) {
                    cube.rotateYLeftAnimation(gl, c);

                } else if (c.getZ() == z && faceToRotate == Face.TOP) {
                    cube.rotateZLeftAnimation(gl, c);

                }

                break;
            case MOUSE_DRAG_LEFT_DOWN:
                if (c.getX() == x && faceToRotate == Face.FRONT) {
                    cube.rotateXLeftAnimation(gl, c);

                } else if (c.getY() == y && faceToRotate == Face.RIGHT) {
                    cube.rotateYLeftAnimation(gl, c);

                } else if (c.getX() == x && faceToRotate == Face.TOP) {
                    cube.rotateXLeftAnimation(gl, c);

                }
                break;
            case MOUSE_DRAG_RIGHT_UP:
                if (c.getX() == x && faceToRotate == Face.FRONT) {
                    cube.rotateXRightAnimation(gl, c);

                } else if (c.getY() == y && faceToRotate == Face.RIGHT) {
                    cube.rotateYRightAnimation(gl, c);

                } else if (c.getX() == x && faceToRotate == Face.TOP) {
                    cube.rotateXRightAnimation(gl, c);

                }
                break;
            case MOUSE_DRAG_RIGHT_DOWN:

                if (c.getY() == y && faceToRotate == Face.FRONT) {
                    cube.rotateYRightAnimation(gl, c);

                } else if (c.getZ() == z && faceToRotate == Face.RIGHT) {
                    cube.rotateZRightAnimation(gl, c);

                } else if (c.getZ() == z && faceToRotate == Face.TOP) {
                    cube.rotateZRightAnimation(gl, c);

                }
                break;

            case MOUSE_DRAG_DOWN:
                if (c.getX() == x && faceToRotate == Face.FRONT) {
                    cube.rotateXLeftAnimation(gl, c);

                } else if (c.getZ() == z && faceToRotate == Face.RIGHT) {
                    cube.rotateZRightAnimation(gl, c);

                }

                break;
            case MOUSE_DRAG_LEFT:
                if (c.getY() == y && faceToRotate == Face.FRONT) {
                    cube.rotateYLeftAnimation(gl, c);

                } else if (c.getY() == y && faceToRotate == Face.RIGHT) {
                    cube.rotateYLeftAnimation(gl, c);

                } else if (c.getZ() == z && faceToRotate == Face.TOP) {
                    cube.rotateZLeftAnimation(gl, c);

                }
                break;
            case MOUSE_DRAG_UP:
                if (c.getX() == x && faceToRotate == Face.FRONT) {
                    cube.rotateXRightAnimation(gl, c);

                } else if (c.getZ() == z && faceToRotate == Face.RIGHT) {
                    cube.rotateZLeftAnimation(gl, c);

                }

                break;
            case MOUSE_DRAG_RIGHT:
                if (c.getY() == y && faceToRotate == Face.FRONT) {
                    cube.rotateYRightAnimation(gl, c);

                } else if (c.getY() == y && faceToRotate == Face.RIGHT) {
                    cube.rotateYRightAnimation(gl, c);

                } else if (c.getZ() == z && faceToRotate == Face.TOP) {
                    cube.rotateZRightAnimation(gl, c);

                }
                break;

        }

    }

    public void rotateCube(GL2 gl, SmallCube c) {

        switch (mouseRotationDirection) {
            case MOUSE_DRAG_LEFT_DOWN:
            case MOUSE_DRAG_DOWN:
//                if (c.getX() == 0 || c.getX() == 1 || c.getX() == 2) {
//                    smallCubeToRotate = c;
                cube.rotateXLeftCube(gl, c);
//                }

                break;

            case MOUSE_DRAG_UP:
            case MOUSE_DRAG_RIGHT_UP:
//                if (c.getX() == 0 || c.getX() == 1 || c.getX() == 2) {
//                    smallCubeToRotate = c;
                cube.rotateXRightCube(gl, c);
//                }

                break;
            case MOUSE_DRAG_LEFT_UP:
//            case MOUSE_DRAG_LEFT:
//                if (c.getZ() == 0 || c.getZ() == 1 || c.getZ() == 2) {
//                    smallCubeToRotate = c;
                cube.rotateZLeftCube(gl, c);
//                }

                break;
//            case MOUSE_DRAG_RIGHT:
            case MOUSE_DRAG_RIGHT_DOWN:
//                if (c.getZ() == 0 || c.getZ() == 1 || c.getZ() == 2) {
//                    smallCubeToRotate = c;
                cube.rotateZRightCube(gl, c);
//                }

                break;
            case MOUSE_DRAG_RIGHT:
//                if (c.getY() == 0 || c.getY() == 1 || c.getY() == 2) {
//                    smallCubeToRotate = c;
                cube.rotateYRightCube(gl, c);
//                }

                break;
            case MOUSE_DRAG_LEFT:
//                if (c.getY() == 0 || c.getY() == 1 || c.getY() == 2) {
//                    smallCubeToRotate = c;
                cube.rotateYLeftCube(gl, c);
//                }

                break;

        }
    }

    @Override
    public String toString() {

        try {
            Collections.reverse(arrayOfMoves);
            
            String str=arrayOfMoves.toString();
//            System.out.println("+++++++++++");
//            System.out.println(str);
//                        System.out.println("+++++++++++");

            return message + arrayOfMoves.toString();
        } catch (Exception e) {
            return "No Moves";
        }
    }
//   class MYArrayList extends ArrayList{
//
//        @Override
//        public String toString() {
//            String str
//            
//            return super.toString(); //To change body of generated methods, choose Tools | Templates.
//        }
//       
//       
//   }
}
