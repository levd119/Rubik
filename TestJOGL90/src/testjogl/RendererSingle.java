package testjogl;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JFrame;
import testjogl.MouseRotationHelper.MouseRotation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lev&Rotem
 */
public class RendererSingle extends Renderer {

    private boolean isCustom = false, isMultiplayer = false;
    private Room room;
    private Cursor cursorNotClicked;
    private Cursor cursorClicked;

    public void getHint() {
        cube.enableHint();
        score.incHints();
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        System.out.println("initSingle");
        final GL2 gl = drawable.getGL().getGL2();
        super.init(drawable); //To change body of generated methods, choose Tools | Templates.
        initTexture(gl);
        rotation = cube.getRotation();

        room = new Room(Renderer.TEXTURES);
        if (!isCustom) {
            cube.drawRandom();
        }
        score.startTimer();
//        String message = "Click on the cube button to get more information!";
//        score.setMessage(message);
        if (isMultiplayer) {
            try {
                ConnectionUtil.sendData(RendererSingle.this.cube);
            } catch (IOException ex) {

                setIsMultiplayer(false);

            }
        }

    }

    public RendererSingle(JFrame frame) {

        super(frame);
        System.out.println("RendererSingle()");
        setCursors();
        context.setCursor(cursorNotClicked);
    }

    public void setIsMultiplayer(boolean isMultiplayer) {
        this.isMultiplayer = isMultiplayer;
    }

//    public void refresh() {
//        cube = new Cube(3);
//        rotation = cube.getRotation();
//        cube.drawRandom();
//        score = new Score();
//        score.startTimer();
//        score.setWidth((int) width);
//        score.setHeight((int) height);
//        String message = "Refresh";
//        score.setMessage(message);
//
//    }
    public Score getScore() {
        return score;
    }

    public void setCursors() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage(getClass().getResource("/icons/cursor_hand.png"));
        Point point = new Point(5, 5);
        cursorNotClicked = toolkit.createCustomCursor(img, point, "cursor_hand");
        img = toolkit.getImage(getClass().getResource("/icons/cursor_drag_hand.png"));
        cursorClicked = toolkit.createCustomCursor(img, point, "cursor_hand");

    }

    @Override
    void initCube() {
        cube = new Cube(3);
        score = new Score();

    }

    void initCube(SmallCube cubeArray[][][]) {

        cube = new Cube(cubeArray);
        score = new Score();
        isCustom = true;

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        if (!cube.isWin()) {
            switch (cmd) {
                case UPDATE:
                    checkCamera();
                    gl.glRenderMode(GL2.GL_RENDER_MODE);
                    drawScene(drawable);
                    score.drawAllData(isMultiplayer,cube.getNumberOfMoves());
                    break;
                case SELECT:
                    pickCube(drawable);
                    break;
                case ROTATE_CAMERA_LEFT:
                    gl.glRenderMode(GL2.GL_RENDER_MODE);
                    rotateCameraLeft();
                    drawScene(drawable);
                    score.drawAllData(isMultiplayer,cube.getNumberOfMoves());

                    break;
                case ROTATE_CAMERA_RIGHT:
                    gl.glRenderMode(GL2.GL_RENDER_MODE);
                    rotateCameraRight();
                    drawScene(drawable);
                    score.drawAllData(isMultiplayer,cube.getNumberOfMoves());

                    break;
                case ROTATE_CAMERA_DOWN:
                    gl.glRenderMode(GL2.GL_RENDER_MODE);
                    rotateCameraDown();
                    drawScene(drawable);
                    score.drawAllData(isMultiplayer,cube.getNumberOfMoves());

                    break;

            }

            if (rotation.isMovesExists()) {
                score.setMessage(rotation.toString());
                rotation.setMovesExists(false);
            }

            gl.glFlush();
        } else {
            if (!isCustom) {
                setEndGameDialog("Win");
            } else {

                new PracticeFinishDialog(context, true).setVisible(true);
            }

        }
    }

    @Override
    public void drawScene(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glClearColor(01f, 1, 1, 1f);

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
            if (isHit) {
                cube.setAxisRotate(true);
            } else {
                rotation.setMousePoint(e.getPoint());
                cube.isCubeRotate = true;
            }
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

    public void setEndGameDialog(String status) {

        if (status.equalsIgnoreCase("Win")) {
            score.stopTimer();
            ((CubeFrame) context).finishGameDialog(score, "win");

        }
        if (status.equalsIgnoreCase("Loose")) {
            ((CubeFrame) context).finishGameDialog(score, "loose");

            score.stopTimer();

        }

        refresh();
        context.setCursor(cursorNotClicked);
        score.startTimer();

//        dispose(drawable);
    }

    @Override
    public void refresh() {
        super.refresh(); //To change body of generated methods, choose Tools | Templates.
        rotation = cube.getRotation();
        score = new Score();
        score.startTimer();
        score.setWidth((int) width);
        score.setHeight((int) height);
        String message = "Refresh";
        score.setMessage(message);

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        super.reshape(drawable, x, y, width, height);
        score.setWidth(width);
        score.setHeight(height);

    }

    @Override
    public void mouseMoved(MouseEvent e
    ) {

    }

    @Override
    public void mouseClicked(MouseEvent e
    ) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        context.setCursor(cursorClicked);
        cmd = SELECT;
        cube.mouseReleased = false;
//        isReleased = true;
        selectedMouse_x = e.getX();
        selectedMouse_y = e.getY();
        score.setMessage("");

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        context.setCursor(cursorNotClicked);
        cube.mouseReleased = true;
        isReleased = true;

        score.setMessage("");
        if (isMultiplayer) {
            try {
                ConnectionUtil.sendData(RendererSingle.this.cube);
            } catch (IOException ex) {

                setIsMultiplayer(false);
            }

        }
    }

    @Override
    public void mouseEntered(MouseEvent e
    ) {

    }

    @Override
    public void mouseExited(MouseEvent e
    ) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

        if (e.getKeyChar() == 'h') {
            getHint();
        }

    }

    @Override
    public void keyPressed(KeyEvent ev
    ) {

        int key = ev.getKeyCode();

        switch (key) {
            case KeyEvent.VK_UP:

                break;
            case KeyEvent.VK_DOWN:
                // down 
                cmd = ROTATE_CAMERA_DOWN;

                break;
            case KeyEvent.VK_LEFT:
                // left
                cmd = ROTATE_CAMERA_LEFT;

                break;
            case KeyEvent.VK_RIGHT:
                // right
                cmd = ROTATE_CAMERA_RIGHT;

                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        cmd = UPDATE;

    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        System.out.println("safasdfsdf");
    }

}
