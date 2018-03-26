/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjogl;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author lev
 */
public class TestJOGL {


    public static ArrayList<LayerRotationData> getRandom(Cube c) {
        ArrayList arrayOfMoves = new ArrayList();
        for (int i = 0; i < 4; i++) {
            LayerHelper lh = new LayerHelper(c);
            LayerRotationData sc = null;
            Layer layer = null;
            int direction;

            Random rand = new Random();

            int n = rand.nextInt(9) + 1;
//            int n=6;
            switch (n) {
                case 1:
                    layer = lh.getLayerX(0);
                    layer.setId(Layer.X);
                    layer.setAxisNum(0);
                    break;
                case 2:
                    layer = lh.getLayerX(1);
                    layer.setId(Layer.X);
                    layer.setAxisNum(1);
                    break;

                case 3:
                    layer = lh.getLayerX(2);
                    layer.setId(Layer.X);
                    layer.setAxisNum(2);

                    break;

                case 4:
                    layer = lh.getLayerY(0);
                    layer.setId(Layer.Y);
                    layer.setAxisNum(0);
                    break;

                case 5:
                    layer = lh.getLayerY(1);
                    layer.setId(Layer.Y);
                    layer.setAxisNum(1);
                    break;

                case 6:
                    layer = lh.getLayerY(2);
                    layer.setId(Layer.Y);
                    layer.setAxisNum(2);

                    break;

                case 7:
                    layer = lh.getLayerZ(0);
                    layer.setId(Layer.Z);
                    layer.setAxisNum(0);
                    break;

                case 8:
                    layer = lh.getLayerZ(1);
                    layer.setId(Layer.Z);
                    layer.setAxisNum(1);
                    break;

                case 9:
                    layer = lh.getLayerZ(2);
                    layer.setId(Layer.Z);
                    layer.setAxisNum(2);
                    break;

            }
//            direction = rand.nextInt(2)+1;
            direction=LayerRotationData.CLOCKWISE;
            sc = new LayerRotationData(layer, direction);
            arrayOfMoves.add(sc);
        }
        return arrayOfMoves;
    }

}
