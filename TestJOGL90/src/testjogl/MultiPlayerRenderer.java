/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjogl;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author lev
 */
public class MultiPlayerRenderer extends Renderer {

    ListenFromServer ListenFromServer;
    private boolean isPlaying;
    public static int[] TEXTURES_MULTI;

    public MultiPlayerRenderer(MultiPlayerFrame context) {
        super(context);
        System.out.println("MultiPlayerRenderer()");

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        super.reshape(drawable, x, y, width, height); //To change body of generated methods, choose Tools | Templates.
        score.setWidth((int) width);
        score.setHeight((int) height);

    }

    @Override
    void initCube() {
        System.out.println("initCube() multiplayer");
        score = new Score();
        ListenFromServer = new ListenFromServer();
        ListenFromServer.start();
        isPlaying = true;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void init(GLAutoDrawable drawable) {
        System.out.println("initMultiplayer");
        super.init(drawable);
        final GL2 gl = drawable.getGL().getGL2();
        initTexture(gl);

    }

    @Override
    public void display(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();
        if (isPlaying) {

            checkCamera();
            drawScene(drawable);
        } else {
            stopPlaying();
        }
    }

    @Override
    public void drawScene(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glClearColor(0f, 0, 0, 1f);
        setCamera(gl);
        if (cube != null) {
            if (!cube.isWin()) {
                cube.drawCube(drawable);
                score.drawMoves(cube.getNumberOfMoves());
                score.setMessage(cube.isRotateAxis() + "5");

            } else {
                ((MultiPlayerFrame) context).sendMessage("Loose");

            }
        } else {

//            ((MultiPlayerFrame)context).dispose();
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
//        cmd = SELECT;
//        selectedMouse_x = e.getX();
//        selectedMouse_y = e.getY();

    }

    @Override
    public void mouseReleased(MouseEvent e) {

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

    public void stopPlaying() {
        isPlaying = false;
        System.out.println("Stop play");
        ((MultiPlayerFrame) context).setMultiplayer(false);

    }

    class ListenFromServer extends Thread {

        public boolean runing;

        ListenFromServer() {
            runing = true;
        }

        @Override
        public void run() {

            while (runing) {

                Serializable ser = ConnectionUtil.receiveData();
                System.out.println("receiveData");
                if (ser instanceof Cube) {
                    MultiPlayerRenderer.this.cube = (Cube) ser;
                    cube.mouseReleased = true;

                } else if (ser instanceof ConnectionData) {
                    System.out.println("Check isAlive");
                    ConnectionData cd = (ConnectionData) ser;
                    if (cd.getRequestCode() == ConnectionData.DISCONNECT) {
                        System.out.println("cd.getRequestCode() == ConnectionData.DISCONNECT");
                        ConnectionUtil.closeConnection();
                        isPlaying = false;
                        runing = false;
//                            cube=null;
                    } else if (cd.getRequestCode() == ConnectionData.IS_ALIVE) {
                        System.out.println("else");
                        cd = new ConnectionData();
                        cd.setResponseStatus(true);
                        try {
                            ConnectionUtil.sendData(cd);
                        } catch (IOException ex) {
                            System.out.println("Cant Send data");
                        }
                    }

                } else {
                    runing = false;
                }

            }
        }
    }

    @Override
    protected void initTexture(GL2 gl) {
        System.out.println("init texture");
        ClassLoader cl = getClass().getClassLoader();
        TEXTURES_MULTI = new int[8];
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
                TEXTURES_MULTI[fileName] = text.getTextureObject(gl);
                System.out.println("Textures" + TEXTURES_MULTI[fileName]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
