/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjogl;

import java.io.Serializable;

/**
 *
 * @author lev
 */
public class LayerRotationData implements Serializable{

    private Layer layer;
    private int direction;
    public static final int CLOCKWISE = 1, COUNTERCLOCKWISE = 2;
// public RotationData(int direction,int rounds){
//     this. direction= direction;
//     this.rounds=rounds;
// }

    public LayerRotationData(Layer layer, int direction) {
        this.layer = layer;
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public Layer getLayer() {
        return layer;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setLayer(Layer layer) {
        this.layer = layer;
    }

    @Override
    public String toString() {
        String str = "";

        switch (layer.getId()) {
            case Layer.X:
                if (layer.getAxisNum() == 0) {
                    str += "L";
                } else if (layer.getAxisNum() == 2) {
                    str += "R";
                }
                break;
            case Layer.Y:
                if (layer.getAxisNum() == 2) {
                    str += "T";
                } else if (layer.getAxisNum() == 0) {
                    str += "B";
                }

                break;
            case Layer.Z:
                if (layer.getAxisNum() == 0) {
                    str += "F";
                } else if (layer.getAxisNum() == 2) {
                    str += "B";
                }

                break;

        }

        switch (direction) {
            case CLOCKWISE:
                str += "";

                break;
            case COUNTERCLOCKWISE:
                str += "I";

                break;
        }

        return str;
    }

}
