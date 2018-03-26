/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjogl;

import java.io.Serializable;


/**
 *
 * @author lev&Rotem
 */
public final class Layer implements Serializable{

    public static final int X=1,Y=2,Z=3;
    
    
    private SmallCube[][] axis;
    private int face;
    private int id;
    private int axisNum;

    public void setAxisNum(int axisNum) {
        this.axisNum = axisNum;
    }

    public int getAxisNum() {
        return axisNum;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Layer(SmallCube[][] axis) {
        setAxis(axis);
    }

    public SmallCube[][] getAxis() {
        return axis;
    }

    public void setAxis(SmallCube[][] axis) {
        this.axis = axis;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public int getFace() {
        return face;
    }
    
    
    
//    public boolean checkLayerColorComplete(){
//        return true;
//    }

    public void setZero(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                axis[i][j].angle=0;
            }
        }
    }
    
    
}
