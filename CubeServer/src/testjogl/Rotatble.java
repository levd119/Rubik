/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjogl;

import com.jogamp.opengl.GL2;
import java.io.Serializable;

/**
 *
 * @author lev
 */
public interface Rotatble extends Serializable{

    public void rotateXRightCube(GL2 gl, SmallCube sc);

     public void rotateYRightCube(GL2 gl, SmallCube sc);

     public void rotateYLeftCube(GL2 gl, SmallCube sc);

     public void rotateZRightCube(GL2 gl, SmallCube sc);

     public void rotateZLeftCube(GL2 gl, SmallCube sc);

     public void rotateXRightAnimation(GL2 gl, SmallCube sc);

     public void rotateXLeftAnimation(GL2 gl, SmallCube sc);

     public void rotateYRightAnimation(GL2 gl, SmallCube sc);

     public void rotateYLeftAnimation(GL2 gl, SmallCube sc);

     public void rotateZRightAnimation(GL2 gl, SmallCube sc);

     public void rotateZLeftAnimation(GL2 gl, SmallCube sc);

}
 