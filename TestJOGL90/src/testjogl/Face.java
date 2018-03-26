/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjogl;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.awt.GLJPanel;
import java.io.Serializable;

/**
 *
 * @author lev&Rotem
 */
public final class Face implements Serializable {

    public static final int ROTATE_LEFT = -90, ROTATE_RIGHT = 90, ROTATE_DOWN = 180, ROTATE_UP = 0;
    public static final int FRONT = 0;
    public static final int BACK = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int TOP = 4;
    public static final int BOTTOM = 5;

    public static final int ORANGE = 0;
    public static final int RED = 1;
    public static final int GREEN = 2;
    public static final int BLUE = 3;
    public static final int YELLOW = 4;
    public static final int WHITE = 5;
    public static final int BLACK = 6;

    private final int face;
    private int id;
    private static int ID = 0;
    private int color;
    private boolean isVisiable = false;

    public void setVisiable(boolean isVisiable) {
        this.isVisiable = isVisiable;
    }

    public boolean isIsVisiable() {
        return isVisiable;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public int getFace() {
        return face;
    }

    public Face(int f) {
        this.face = f;
        setColor(f);
        id = Face.ID++;//uniq id to every cube face! 
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public void drawFace(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glColor4f(1.0f, 1.0f, 1.0f, 0.0f);
        gl.glLoadName(id);
                if (drawable instanceof GLCanvas) {
        gl.glBindTexture(GL2.GL_TEXTURE_2D, MultiPlayerRenderer.TEXTURES_MULTI[color]);
        }
        if (drawable instanceof GLJPanel) {
        gl.glBindTexture(GL2.GL_TEXTURE_2D, Renderer.TEXTURES[color]);
        }


        gl.glDrawArrays(GL2.GL_QUADS, face * 4, 4);
    }

    @Override
    public String toString() {
        switch (color) {
            case ORANGE:
                return "Orange";
            case RED:
                return "Red";
            case GREEN:
                return "Green";
            case BLUE:
                return "blue";
            case YELLOW:
                return "Yellow";
            case WHITE:
                return "White";
        }
        return "Black";
    }

}
