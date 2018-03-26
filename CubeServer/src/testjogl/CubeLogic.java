/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjogl;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author lev&Rotem
 */
public class CubeLogic implements Serializable {

    public static final int FIRST = 1, SECOND = 2, THIRD = 3;
//    public static boolean isWin = false;
    public static Layer[] layerArray = new Layer[6];

    private static int check1(Cube cube) {
        int[] colors = {cube.getCubeFaceColor(Face.FRONT), cube.getCubeFaceColor(Face.BACK), cube.getCubeFaceColor(Face.LEFT), cube.getCubeFaceColor(Face.RIGHT), cube.getCubeFaceColor(Face.TOP), cube.getCubeFaceColor(Face.BOTTOM)};
        SmallCube scarray[][][] = cube.getArray();
        //Front
        if (checkCube(scarray[0][0][0], colors) && checkCube(scarray[0][1][0], colors) && checkCube(scarray[0][2][0], colors)
                && checkCube(scarray[1][0][0], colors) && checkCube(scarray[1][1][0], colors) && checkCube(scarray[1][2][0], colors)
                && checkCube(scarray[2][0][0], colors) && checkCube(scarray[2][1][0], colors) && checkCube(scarray[2][2][0], colors) && scarray[1][1][0].getFaces()[Face.FRONT].getColor() == Face.YELLOW) {
            return Face.FRONT;
        }
        //Back
        if (checkCube(scarray[0][0][2], colors) && checkCube(scarray[0][1][2], colors) && checkCube(scarray[0][2][2], colors)
                && checkCube(scarray[1][0][2], colors) && checkCube(scarray[1][1][2], colors) && checkCube(scarray[1][2][2], colors)
                && checkCube(scarray[2][0][2], colors) && checkCube(scarray[2][1][2], colors) && checkCube(scarray[2][2][2], colors) && scarray[1][1][2].getFaces()[Face.BACK].getColor() == Face.YELLOW) {
            return Face.BACK;

        }
        //Left
        if (checkCube(scarray[0][0][0], colors) && checkCube(scarray[0][0][1], colors) && checkCube(scarray[0][0][2], colors)
                && checkCube(scarray[0][1][0], colors) && checkCube(scarray[0][1][1], colors) && checkCube(scarray[0][1][2], colors)
                && checkCube(scarray[0][2][0], colors) && checkCube(scarray[0][2][1], colors) && checkCube(scarray[0][2][2], colors) && scarray[0][1][1].getFaces()[Face.LEFT].getColor() == Face.YELLOW) {
            return Face.LEFT;
        }
        //Right
        if (checkCube(scarray[2][0][0], colors) && checkCube(scarray[2][0][1], colors) && checkCube(scarray[2][0][2], colors)
                && checkCube(scarray[2][1][0], colors) && checkCube(scarray[2][1][1], colors) && checkCube(scarray[2][1][2], colors)
                && checkCube(scarray[2][2][0], colors) && checkCube(scarray[2][2][1], colors) && checkCube(scarray[2][2][2], colors) && scarray[2][1][1].getFaces()[Face.RIGHT].getColor() == Face.YELLOW) {
            return Face.RIGHT;
        }
        //Top
        if (checkCube(scarray[0][2][0], colors) && checkCube(scarray[0][2][1], colors) && checkCube(scarray[0][2][2], colors)
                && checkCube(scarray[1][2][0], colors) && checkCube(scarray[1][2][1], colors) && checkCube(scarray[1][2][2], colors)
                && checkCube(scarray[2][2][0], colors) && checkCube(scarray[2][2][1], colors) && checkCube(scarray[2][2][2], colors) && scarray[1][2][1].getFaces()[Face.TOP].getColor() == Face.YELLOW) {
            return Face.TOP;
        }
        //Bottom
        if (checkCube(scarray[0][0][0], colors) && checkCube(scarray[0][0][1], colors) && checkCube(scarray[0][0][2], colors)
                && checkCube(scarray[1][0][0], colors) && checkCube(scarray[1][0][1], colors) && checkCube(scarray[1][0][2], colors)
                && checkCube(scarray[2][0][0], colors) && checkCube(scarray[2][0][1], colors) && checkCube(scarray[2][0][2], colors) && scarray[1][0][1].getFaces()[Face.BOTTOM].getColor() == Face.YELLOW) {
            return Face.BOTTOM;
        }

        return -1;
    }
/////////////////////////////////////////////

    private static boolean checkCube(SmallCube sc, int[] colors) {

        for (int i = 0; i < 6; i++) {
            if (sc.getFaces()[i].isIsVisiable()) {
                if (!(sc.getFaces()[i].getColor() == colors[i])) {
                    return false;
                }
            }

        }
        return true;
    }

    public static int whichLevel(Cube cube) {
        SmallCube scarray[][][] = cube.getArray();
        int[] colors = {cube.getCubeFaceColor(Face.FRONT), cube.getCubeFaceColor(Face.BACK), cube.getCubeFaceColor(Face.LEFT), cube.getCubeFaceColor(Face.RIGHT), cube.getCubeFaceColor(Face.TOP), cube.getCubeFaceColor(Face.BOTTOM)};

        switch (check1(cube)) {
            case Face.TOP:
            case Face.BOTTOM:

                if (checkCube(scarray[0][1][0], colors) && checkCube(scarray[1][1][0], colors) && checkCube(scarray[2][1][0], colors)
                        && checkCube(scarray[2][1][1], colors) && checkCube(scarray[2][1][2], colors) && checkCube(scarray[1][1][2], colors)
                        && checkCube(scarray[0][1][2], colors) && checkCube(scarray[0][1][1], colors)) {
                    return 3;
                }
                return 2;
            case Face.BACK:
            case Face.FRONT:
                if (checkCube(scarray[0][2][1], colors) && checkCube(scarray[1][2][1], colors) && checkCube(scarray[2][2][1], colors)
                        && checkCube(scarray[2][1][1], colors) && checkCube(scarray[2][0][1], colors) && checkCube(scarray[1][0][1], colors)
                        && checkCube(scarray[0][0][1], colors) && checkCube(scarray[0][1][1], colors)) {
                    return 3;
                }
                return 2;
            case Face.LEFT:
            case Face.RIGHT:
                if (checkCube(scarray[1][2][0], colors) && checkCube(scarray[1][2][1], colors) && checkCube(scarray[1][2][2], colors)
                        && checkCube(scarray[1][1][2], colors) && checkCube(scarray[1][0][2], colors) && checkCube(scarray[1][0][1], colors)
                        && checkCube(scarray[1][0][0], colors) && checkCube(scarray[1][1][0], colors)) {
                    return 3;
                }
                return 2;
            case -1:
                System.out.println("NON");
                return 1;

        }

        return FIRST;
    }

    public static boolean topYellow022(SmallCube[][][] scArray) {

        return scArray[0][2][2].getFaces()[Face.TOP].getColor() == Face.YELLOW
                && scArray[0][2][2].getFaces()[Face.LEFT].getColor() == scArray[0][1][1].getFaces()[Face.LEFT].getColor()
                && scArray[0][2][2].getFaces()[Face.BACK].getColor() == scArray[1][1][2].getFaces()[Face.BACK].getColor();
    }

    public static boolean topYellow220(SmallCube[][][] scArray) {

        return scArray[2][2][0].getFaces()[Face.TOP].getColor() == Face.YELLOW
                && scArray[2][2][0].getFaces()[Face.FRONT].getColor() == scArray[1][1][0].getFaces()[Face.FRONT].getColor()
                && scArray[2][2][0].getFaces()[Face.RIGHT].getColor() == scArray[2][1][1].getFaces()[Face.RIGHT].getColor();
    }

    public static boolean topYellow222(SmallCube[][][] scArray) {
        return scArray[2][2][2].getFaces()[Face.TOP].getColor() == Face.YELLOW
                && scArray[2][2][2].getFaces()[Face.RIGHT].getColor() == scArray[2][1][1].getFaces()[Face.RIGHT].getColor()
                && scArray[2][2][2].getFaces()[Face.BACK].getColor() == scArray[1][1][2].getFaces()[Face.BACK].getColor();
    }

    public static boolean topYellow020(SmallCube[][][] scArray) {

        return scArray[0][2][0].getFaces()[Face.TOP].getColor() == Face.YELLOW
                && scArray[0][2][0].getFaces()[Face.FRONT].getColor() == scArray[1][1][0].getFaces()[Face.FRONT].getColor()
                && scArray[0][2][0].getFaces()[Face.LEFT].getColor() == scArray[0][1][1].getFaces()[Face.LEFT].getColor();
    }

    public static boolean isYellowLayerComplete(SmallCube scArray[][][]) {
        if (topYellow020(scArray) && topYellow222(scArray) && topYellow220(scArray) && topYellow022(scArray)) {
            System.err.println("all yellow edges done!");
            return true;
        }
        return false;
    }
//**********yellow Layer************//

    public static ArrayList<LayerRotationData> getYellowLayer(Cube cube) {
        ArrayList<LayerRotationData> moves = new ArrayList<>();
        layerArray[Face.FRONT] = cube.getAxis().getLayerZ(0);
        layerArray[Face.FRONT].setId(Layer.Z);
        layerArray[Face.FRONT].setAxisNum(0);

        layerArray[Face.LEFT] = cube.getAxis().getLayerX(0);
        layerArray[Face.LEFT].setId(Layer.X);
        layerArray[Face.LEFT].setAxisNum(0);

        layerArray[Face.RIGHT] = cube.getAxis().getLayerX(2);
        layerArray[Face.RIGHT].setId(Layer.X);
        layerArray[Face.RIGHT].setAxisNum(2);

        layerArray[Face.TOP] = cube.getAxis().getLayerY(2);
        layerArray[Face.TOP].setId(Layer.Y);
        layerArray[Face.TOP].setAxisNum(2);

        layerArray[Face.BACK] = cube.getAxis().getLayerZ(2);
        layerArray[Face.BACK].setId(Layer.Z);
        layerArray[Face.BACK].setAxisNum(2);

        layerArray[Face.BOTTOM] = cube.getAxis().getLayerY(0);
        layerArray[Face.BOTTOM].setId(Layer.Y);
        layerArray[Face.BOTTOM].setAxisNum(0);
        SmallCube scArray[][][] = cube.getArray();
        SmallCube sCcenter = null;
        SmallCube yellowPlusCube = null;
        int moveTo;
        int layer = 0;

//        if (topYellow020(scArray) && topYellow222(scArray) && topYellow220(scArray) && topYellow022(scArray)) {
//            System.out.println("all done!");
//            return moves;
//        }
        if (isYellowLayerComplete(scArray)) {
            return moves;
        }

//*********************************************0|0|0 yellow in front*************************************************************************//
        sCcenter = scArray[0][1][1];
        if (scArray[0][2][2].getFaces()[Face.TOP].getColor() == Face.YELLOW
                && scArray[0][2][2].getFaces()[Face.LEFT].getColor() != scArray[0][1][1].getFaces()[Face.LEFT].getColor()
                && scArray[0][2][2].getFaces()[Face.BACK].getColor() != scArray[1][1][2].getFaces()[Face.BACK].getColor()) {
//            scArray[0][2][2].getFaces()[Face.TOP].startBlink();
            yellowPlusCube = scArray[0][2][2];
            System.out.println("0|2|2 yellow in TOP2");
            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
//            topYellow022 = true;
//            System.out.println("1topYellow022" + topYellow022);
            layer = Face.BACK;
            moves.addAll(yellowInFront000(layer, yellowPlusCube, sCcenter, scArray));
            return moves;
        }
        if (!topYellow220(scArray)) {
            if (scArray[2][0][0].getFaces()[Face.BOTTOM].getColor() == Face.YELLOW) {//check if front Face of the plus is yellow
                yellowPlusCube = scArray[2][0][0];
//                scArray[2][0][0].getFaces()[Face.BOTTOM].startBlink();
                System.out.println("2|0|0 yellow in BOTTOM2");
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                layer = Face.RIGHT;
                moves.addAll(yellowInFront000(layer, yellowPlusCube, sCcenter, scArray));
                return moves;
            }
        }
        if (scArray[2][2][0].getFaces()[Face.RIGHT].getColor() == Face.YELLOW) {
            yellowPlusCube = scArray[2][2][0];
//            scArray[2][2][0].getFaces()[Face.RIGHT].startBlink();
            System.out.println("2|2|0 yellow in RIGHT1");
            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
            layer = Face.TOP;
            moves.addAll(yellowInFront000(layer, yellowPlusCube, sCcenter, scArray));
            return moves;

        }

        yellowPlusCube = scArray[0][0][0];
        if (yellowPlusCube.getFaces()[Face.FRONT].getColor() == Face.YELLOW) {
//            scArray[0][0][0].getFaces()[Face.FRONT].startBlink();
            System.out.println(" yellow in front 0|0|0 3 ");
            layer = Face.LEFT;
            moves.addAll(yellowInFront000(layer, yellowPlusCube, sCcenter, scArray));
            return moves;
        }

//**********************************************************************************************************************//
        sCcenter = cube.getArray()[1][1][0];
        if (scArray[2][2][0].getFaces()[Face.TOP].getColor() == Face.YELLOW
                && scArray[2][2][0].getFaces()[Face.FRONT].getColor() != scArray[1][1][0].getFaces()[Face.FRONT].getColor()
                && scArray[2][2][0].getFaces()[Face.RIGHT].getColor() != scArray[2][1][1].getFaces()[Face.RIGHT].getColor()) {
            yellowPlusCube = scArray[2][2][0];
//            scArray[2][2][0].getFaces()[Face.TOP].startBlink();

            System.out.println("2|2|0 yellow in TOP6");
            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
//            topYellow220 = true;
//            System.out.println("2topYellow220" + topYellow220);
            layer = Face.RIGHT;
            moves.addAll(yellowInLeft000(layer, yellowPlusCube, sCcenter, scArray));
            return moves;

        }

        if (!topYellow022(scArray)) {
            System.out.println("BOTTOM pre1");
            if (scArray[0][0][0].getFaces()[Face.BOTTOM].getColor() == Face.YELLOW) {
                yellowPlusCube = scArray[0][0][0];
//                scArray[0][0][0].getFaces()[Face.BOTTOM].startBlink();
                System.out.println("0|0|0 yellow in BOTTOM pre2");
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
                layer = Face.LEFT;
                moves.addAll(yellowInFront200(layer, yellowPlusCube, sCcenter, scArray));
                return moves;
            }
        }

        if (!topYellow222(scArray)) {
            System.out.println("BOTTOM pre2");

            if (scArray[0][0][0].getFaces()[Face.BOTTOM].getColor() == Face.YELLOW) {
//                scArray[0][0][0].getFaces()[Face.BOTTOM].startBlink();
                System.out.println("0|0|0 yellow in BOTTOM pre3");
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                layer = Face.LEFT;
                moves.addAll(yellowInLeft000(layer, yellowPlusCube, sCcenter, scArray));
                return moves;
            }

        }

        if (!topYellow222(scArray)) {
//                    System.out.println("BOTTOM pre3");

            System.out.println("0|0|2 yellow in BOTTOM4 BEFORE");

            if (scArray[0][0][2].getFaces()[Face.BOTTOM].getColor() == Face.YELLOW) {
                yellowPlusCube = scArray[0][0][2];
//                scArray[0][0][2].getFaces()[Face.BOTTOM].startBlink();
                System.out.println("0|0|2 yellow in BOTTOM4");
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                layer = Face.BACK;
                moves.addAll(yellowInLeft000(layer, yellowPlusCube, sCcenter, scArray));
                return moves;
            }
        }

        if (!topYellow020(scArray)) {
            System.out.println("BOTTOM pre4");
            if (scArray[0][0][2].getFaces()[Face.BOTTOM].getColor() == Face.YELLOW) {
                yellowPlusCube = scArray[0][0][2];
//                scArray[0][0][2].getFaces()[Face.BOTTOM].startBlink();
                System.out.println("0|0|2 yellow in BOTTOM5");
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                layer = Face.BACK;
                moves.addAll(yellowInFront200(layer, yellowPlusCube, sCcenter, scArray));
                return moves;
            }

        }
        if (scArray[0][2][2].getFaces()[Face.BACK].getColor() == Face.YELLOW) {

//            scArray[0][2][2].getFaces()[Face.BACK].startBlink();
            yellowPlusCube = scArray[0][2][2];
            System.out.println("0|2|2 yellow in BACK5");
            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
//            System.out.println("topYellow022" + topYellow022);
            layer = Face.TOP;
            moves.addAll(yellowInLeft000(layer, yellowPlusCube, sCcenter, scArray));
            return moves;

        }

        yellowPlusCube = scArray[0][0][0];
        if (yellowPlusCube.getFaces()[Face.LEFT].getColor() == Face.YELLOW) {//check if front Face of the plus is yellow
            System.out.println("0|0|0 yellow in LEFT7 ");
            layer = Face.FRONT;
            moves.addAll(yellowInLeft000(layer, yellowPlusCube, sCcenter, scArray));
            return moves;
        }

//*************************************************2|0|0 yellow in front*********************************************************************//		
        sCcenter = cube.getArray()[2][1][1];
        if (scArray[2][2][2].getFaces()[Face.TOP].getColor() == Face.YELLOW
                && scArray[2][2][2].getFaces()[Face.RIGHT].getColor() != scArray[2][1][1].getFaces()[Face.RIGHT].getColor()
                && scArray[2][2][2].getFaces()[Face.BACK].getColor() != scArray[1][1][2].getFaces()[Face.BACK].getColor()) {
            System.out.println("2|2|2 yellow in TOP10");
//            scArray[2][2][2].getFaces()[Face.TOP].startBlink();
            yellowPlusCube = scArray[2][2][2];
            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
//            topYellow222 = true;
//            System.out.println("topYellow222" + topYellow222);
            layer = Face.BACK;
            moves.addAll(yellowInFront200(layer, yellowPlusCube, sCcenter, scArray));
            return moves;

        }
        if (!topYellow022(scArray)) {
            if (scArray[2][0][0].getFaces()[Face.BOTTOM].getColor() == Face.YELLOW) {//check if front Face of the plus is yellow

                System.out.println("2|0|0 yellow in BOTTOM3");
//                scArray[2][0][0].getFaces()[Face.BOTTOM].startBlink();
                yellowPlusCube = scArray[2][0][0];
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
                layer = Face.FRONT;
                moves.addAll(yellowInFront200(layer, yellowPlusCube, sCcenter, scArray));
                return moves;
            }

        }
        if (!topYellow022(scArray)) {
            if (scArray[2][0][2].getFaces()[Face.BOTTOM].getColor() == Face.YELLOW) {
                System.out.println("2|0|2 yellow in BOTTOM8");
//                scArray[2][0][2].getFaces()[Face.BOTTOM].startBlink();
                yellowPlusCube = scArray[2][0][2];
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
                layer = Face.RIGHT;
                moves.addAll(yellowInFront200(layer, yellowPlusCube, sCcenter, scArray));
                return moves;
            }
        }
        if (scArray[0][2][0].getFaces()[Face.LEFT].getColor() == Face.YELLOW) {
            System.out.println("0|2|0 yellow in LEFT9");
//            scArray[0][2][0].getFaces()[Face.LEFT].startBlink();
            yellowPlusCube = scArray[0][2][0];
            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
            layer = Face.TOP;
            moves.addAll(yellowInFront200(layer, yellowPlusCube, sCcenter, scArray));
            return moves;
        }

        yellowPlusCube = scArray[2][0][0];
        if (yellowPlusCube.getFaces()[Face.FRONT].getColor() == Face.YELLOW) {//check if front Face of the plus is yellow
            System.out.println(" yellow in front 2|0|0 01");
            layer = Face.RIGHT;
            moves.addAll(yellowInFront200(layer, yellowPlusCube, sCcenter, scArray));
            return moves;
        }

        //***********************************************2|0|0 yellow in RIGHT***********************************************************************//	
        sCcenter = cube.getArray()[1][1][0];
        if (scArray[0][2][0].getFaces()[Face.TOP].getColor() == Face.YELLOW
                && scArray[0][2][0].getFaces()[Face.FRONT].getColor() != scArray[1][1][0].getFaces()[Face.FRONT].getColor()
                && scArray[0][2][0].getFaces()[Face.LEFT].getColor() != scArray[0][1][1].getFaces()[Face.LEFT].getColor()) {
            System.out.println("0|2|0 yellow in TOP14");
//            scArray[0][2][0].getFaces()[Face.TOP].startBlink();
            yellowPlusCube = scArray[0][2][0];
            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
//            topYellow020 = true;
//            System.out.println("topYellow020" + topYellow020);
            layer = Face.LEFT;
            moves.addAll(yellowInRight200(layer, yellowPlusCube, sCcenter, scArray));
            return moves;

        }
        if (!topYellow222(scArray)) {
            if (scArray[2][0][0].getFaces()[Face.BOTTOM].getColor() == Face.YELLOW) {//check if front Face of the plus is yellow
                System.out.println("2|0|0 yellow in BOTTOM4");
//                scArray[2][0][0].getFaces()[Face.BOTTOM].startBlink();
                yellowPlusCube = scArray[2][0][0];
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                layer = Face.BOTTOM;
                moves.addAll(yellowInRight200(layer, yellowPlusCube, sCcenter, scArray));
                return moves;
            }
        }
        if (!topYellow022(scArray)) {
            if (scArray[0][0][2].getFaces()[Face.BOTTOM].getColor() == Face.YELLOW) {
                System.out.println("0|0|2 yellow in BOTTOM11");
//                scArray[0][0][2].getFaces()[Face.BOTTOM].startBlink();
                yellowPlusCube = scArray[0][0][2];
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                layer = Face.LEFT;
                moves.addAll(yellowInRight200(layer, yellowPlusCube, sCcenter, scArray));
                return moves;
            }
        }
        if (!topYellow222(scArray)) {
            if (scArray[2][0][2].getFaces()[Face.BOTTOM].getColor() == Face.YELLOW) {
//                scArray[2][0][2].getFaces()[Face.BOTTOM].startBlink();
                yellowPlusCube = scArray[2][0][2];
                System.out.println("2|0|2 yellow in BOTTOM12");
                moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                layer = Face.BACK;
                moves.addAll(yellowInRight200(layer, yellowPlusCube, sCcenter, scArray));
                return moves;
            }
        } else if (scArray[0][2][2].getFaces()[Face.LEFT].getColor() == Face.YELLOW) {
            System.out.println("0|2|2 yellow in LEFT13");
//            scArray[0][2][2].getFaces()[Face.LEFT].startBlink();
            yellowPlusCube = scArray[0][2][2];
            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
            layer = Face.TOP;
            moves.addAll(yellowInRight200(layer, yellowPlusCube, sCcenter, scArray));
            return moves;

        }
        if (scArray[2][2][2].getFaces()[Face.BACK].getColor() == Face.YELLOW) {
            System.out.println("2|2|2 yellow in BACK15");
//            scArray[2][2][2].getFaces()[Face.BACK].startBlink();
            yellowPlusCube = scArray[2][2][2];
            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
            layer = Face.TOP;
            moves.addAll(yellowInRight200(layer, yellowPlusCube, sCcenter, scArray));
            return moves;
        }
        yellowPlusCube = cube.getArray()[2][0][0];
        if (yellowPlusCube.getFaces()[Face.RIGHT].getColor() == Face.YELLOW) {
            System.out.println("2|0|0 yellow in RIGHT 16");
//            scArray[2][0][0].getFaces()[Face.RIGHT].startBlink();
            layer = Face.FRONT;
            moves.addAll(yellowInRight200(layer, yellowPlusCube, sCcenter, scArray));
            return moves;
        }

//***********************************************2|0|2 yellow in RIGHT*********
        sCcenter = cube.getArray()[1][1][2];
        if (!topYellow020(scArray)) {
            yellowPlusCube = scArray[2][0][2];
            if (yellowPlusCube.getFaces()[Face.BOTTOM].getColor() == Face.YELLOW) {
                System.out.println("2|0|2 yellow in BOTTOM17");
//                yellowPlusCube.getFaces()[Face.BOTTOM].startBlink();
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                layer = Face.RIGHT;
                moves.addAll(yellowInRight202(layer, yellowPlusCube, sCcenter, scArray));
                return moves;
            }
        }
        yellowPlusCube = scArray[2][2][0];
        if (yellowPlusCube.getFaces()[Face.FRONT].getColor() == Face.YELLOW) {
            System.out.println("2|2|0 yellow in FRONT18");
//            yellowPlusCube.getFaces()[Face.FRONT].startBlink();
            moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
//            System.out.println("topYellow220" + topYellow220);
            layer = Face.TOP;
            moves.addAll(yellowInRight202(layer, yellowPlusCube, sCcenter, scArray));
            return moves;
        }
        yellowPlusCube = scArray[2][0][2];
        if (yellowPlusCube.getFaces()[Face.RIGHT].getColor() == Face.YELLOW) {//check if front Face of the plus is yellow
            System.out.println("2|0|2 yellow in RIGHT09 ");
//            yellowPlusCube.getFaces()[Face.RIGHT].startBlink();
            layer = Face.BACK;
            moves.addAll(yellowInRight202(layer, yellowPlusCube, sCcenter, scArray));
            return moves;
        } //**************************************************yellowInBack202********************************************************************//	
        sCcenter = cube.getArray()[2][1][1];
        if (!topYellow020(scArray)) {
            yellowPlusCube = scArray[2][0][0];
            if (yellowPlusCube.getFaces()[Face.BOTTOM].getColor() == Face.YELLOW) {
                System.out.println("2|0|0 yellow in BOTTOM1");
//                yellowPlusCube.getFaces()[Face.BOTTOM].startBlink();
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                layer = Face.RIGHT;
                moves.addAll(yellowInBack202(layer, yellowPlusCube, sCcenter, scArray));
                return moves;
            }
        }
        yellowPlusCube = scArray[2][0][2];
        if (yellowPlusCube.getFaces()[Face.BACK].getColor() == Face.YELLOW) {
            System.out.println("2|0|2 yellow in BACK 20");
            layer = Face.RIGHT;//768657536548767870909898674 FRONT
            moves.addAll(yellowInBack202(layer, yellowPlusCube, sCcenter, scArray));
            return moves;
        } //**************************************************yellowInLeft002********************************************************************//		
        sCcenter = cube.getArray()[1][1][2];
        yellowPlusCube = scArray[0][2][0];
        if (yellowPlusCube.getFaces()[Face.FRONT].getColor() == Face.YELLOW) {
            System.out.println("0|2|0 yellow in FRONT21");
//            yellowPlusCube.getFaces()[Face.FRONT].startBlink();
            moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
//            System.out.println("topYellow020" + topYellow020);
            layer = Face.TOP;
            moves.addAll(yellowInLeft002(layer, yellowPlusCube, sCcenter, scArray));
            return moves;
        }

        yellowPlusCube = scArray[0][0][0];
        if (!topYellow020(scArray)) {
            if (yellowPlusCube.getFaces()[Face.BOTTOM].getColor() == Face.YELLOW) {
                System.out.println("0|0|0 yellow in BOTTOM pre22");
//                yellowPlusCube.getFaces()[Face.BOTTOM].startBlink();
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                layer = Face.RIGHT;
                moves.addAll(yellowInLeft002(layer, yellowPlusCube, sCcenter, scArray));
                return moves;
            }
        }
        yellowPlusCube = scArray[0][0][2];
        if (yellowPlusCube.getFaces()[Face.LEFT].getColor() == Face.YELLOW) {
            System.out.println("0|0|2 yellow in LEFT 23");
            layer = Face.BACK;
            moves.addAll(yellowInLeft002(layer, yellowPlusCube, sCcenter, scArray));
            return moves;
        }
//*******************************************************0|0|2 yellow in BACK***************************************************************//		
        sCcenter = cube.getArray()[0][1][1];
        if (!topYellow220(scArray)) {
            yellowPlusCube = scArray[0][0][0];
            if (yellowPlusCube.getFaces()[Face.BOTTOM].getColor() == Face.YELLOW) {//check if front Face of the plus is yellow
//                yellowPlusCube.getFaces()[Face.BOTTOM].startBlink();
                System.out.println("0|0|0 yellow in BOTTOM pre1 24");
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                layer = Face.LEFT;
                moves.addAll(yellowInBack002(layer, yellowPlusCube, sCcenter, scArray));
                return moves;
            }
        }
        if (!topYellow220(scArray)) {
            yellowPlusCube = scArray[0][0][2];
            if (yellowPlusCube.getFaces()[Face.BOTTOM].getColor() == Face.YELLOW) {
                System.out.println("0|0|2 yellow in BOTTOM25");
//                yellowPlusCube.getFaces()[Face.BOTTOM].startBlink();
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                layer = Face.LEFT;
                moves.addAll(yellowInBack002(layer, yellowPlusCube, sCcenter, scArray));
                return moves;
            }
        }
        if (!topYellow220(scArray)) {
            yellowPlusCube = scArray[2][0][2];
            if (yellowPlusCube.getFaces()[Face.BOTTOM].getColor() == Face.YELLOW) {
//                yellowPlusCube.getFaces()[Face.BOTTOM].startBlink();
                System.out.println("2|0|2 yellow in BOTTOM26");
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                layer = Face.RIGHT;
                moves.addAll(yellowInBack002(layer, yellowPlusCube, sCcenter, scArray));
                return moves;
            }
        }

        yellowPlusCube = scArray[2][2][2];
        if (yellowPlusCube.getFaces()[Face.RIGHT].getColor() == Face.YELLOW) {
            System.out.println("2|2|2 yellow in RIGHT27");
//            yellowPlusCube.getFaces()[Face.RIGHT].startBlink();
            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
//            System.out.println("topYellow222" + topYellow222);
            layer = Face.TOP;
            moves.addAll(yellowInBack002(layer, yellowPlusCube, sCcenter, scArray));
            return moves;

        }
        if (scArray[0][0][2].getFaces()[Face.BACK].getColor() == Face.YELLOW) {
            yellowPlusCube = scArray[0][0][2];
            System.out.println("0|0|2 yellow in BACK 28");
            layer = Face.LEFT;
            moves.addAll(yellowInBack002(layer, yellowPlusCube, sCcenter, scArray));
            return moves;
        }
        return moves;
    }

    public static ArrayList<LayerRotationData> yellowInFront000(int layer, SmallCube sc, SmallCube sCcenter, SmallCube scArray[][][]) {
        ArrayList<LayerRotationData> moves = new ArrayList<>();

        int moveTo;

        System.out.println(" yellow in front 0|0|029 ");

        if (sc.getFaces()[layer].getColor() != sCcenter.getFaces()[Face.LEFT].getColor()) {
            moveTo = findLayerWithReqestedCenter(scArray, sc.getFaces()[layer].getColor());
            switch (moveTo) {

                case Face.FRONT:

                    System.out.println(" yellow in front 0|0|0 FRONT30");
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                    break;

                case Face.BACK:
                    System.out.println(" yellow in front 0|0|0 BACK31");
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
                    break;

                case Face.RIGHT:
                    System.out.println(" yellow in front 0|0|0 RIGHT32");
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                    break;
            }
            return moves;
        } else {
            System.out.println(" yellow in front 0|0|0 LEFT ELSE33");
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
            return moves;

        }

    }

    public static ArrayList<LayerRotationData> yellowInLeft000(int layer, SmallCube sc, SmallCube sCcenter, SmallCube scArray[][][]) {
        ArrayList<LayerRotationData> moves = new ArrayList<>();
        int moveTo;

        if (sc.getFaces()[layer].getColor() != sCcenter.getFaces()[Face.FRONT].getColor()) {
//            scArray[0][0][0].getFaces()[Face.LEFT].startBlink();
            moveTo = findLayerWithReqestedCenter(scArray, sc.getFaces()[layer].getColor());
            switch (moveTo) {

                case Face.LEFT:
                    System.out.println("0|0|0 yellow in LEFT LEFT34");
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                    break;

                case Face.BACK:
                    System.out.println("0|0|0 yellow in LEFT BACK35");
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                    break;

                case Face.RIGHT:
                    System.out.println("0|0|0 yellow in LEFT RIGHT36");
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                    break;
            }
            return moves;

        } else {
            System.out.println(" 0|0|0 yellow in LEFT ELSE37");
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
            return moves;

        }

    }

    public static ArrayList<LayerRotationData> yellowInFront200(int layer, SmallCube yellowPlusCube, SmallCube scCenter, SmallCube scArray[][][]) {
        ArrayList<LayerRotationData> moves = new ArrayList<>();

        int moveTo;

        if (yellowPlusCube.getFaces()[layer].getColor() != scCenter.getFaces()[Face.RIGHT].getColor()) {
//            scArray[2][0][0].getFaces()[Face.FRONT].startBlink();
            System.out.println(" yellow in front 2|0|0 if2");
            moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[layer].getColor());
            switch (moveTo) {

                case Face.LEFT:
                    System.out.println(" yellow in front 2|0|0 LEFT38");
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                    break;

                case Face.BACK:
                    System.out.println(" yellow in front 2|0|0 BACK39");
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                    break;

                case Face.FRONT:
                    System.out.println(" yellow in front 2|0|0 FRONT40");
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                    break;
            }
            return moves;

        } else {

            System.out.println(" yellow in front 2|0|0 RIGHT41");
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
            return moves;

        }
    }

    public static ArrayList<LayerRotationData> yellowInRight200(int layer, SmallCube yellowPlusCube, SmallCube scCenter, SmallCube scArray[][][]) {
        ArrayList<LayerRotationData> moves = new ArrayList<>();

        int moveTo;

        if (yellowPlusCube.getFaces()[layer].getColor() != scCenter.getFaces()[Face.FRONT].getColor()) {

            moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[layer].getColor());

            switch (moveTo) {
                case Face.RIGHT:
                    System.out.println("2|0|0 yellow in RIGHT RIGHT42");
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));

                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                    break;

                case Face.BACK:
                    System.out.println("2|0|0 yellow in RIGHT BACK43");
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
                    break;

                case Face.LEFT:
                    System.out.println("2|0|0 yellow in RIGHT LEFT44");
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                    break;
            }
            return moves;

        } else {
            System.out.println("2|0|0 yellow in RIGHT ELSE FRONT45");
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
            return moves;

        }

    }

    public static ArrayList<LayerRotationData> yellowInRight202(int layer, SmallCube yellowPlusCube, SmallCube scCenter, SmallCube scArray[][][]) {
        ArrayList<LayerRotationData> moves = new ArrayList<>();
        int moveTo;
        if (yellowPlusCube.getFaces()[layer].getColor() != scCenter.getFaces()[Face.BACK].getColor()) {
//            scArray[2][0][2].getFaces()[Face.RIGHT].startBlink();

            moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[layer].getColor());
            switch (moveTo) {

                case Face.FRONT:
                    System.out.println("2|0|2 yellow in RIGHT FRONT46");
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                    break;

                case Face.RIGHT:
                    System.out.println("2|0|2 yellow in RIGHT RIGHT47");
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                    break;

                case Face.LEFT:
                    System.out.println("2|0|2 yellow in RIGHT LEFT48");
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                    break;
            }
            return moves;

        } else {

            System.out.println("2|0|2 yellow in RIGHT ELSE79");
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
            return moves;

        }
    }

    public static ArrayList<LayerRotationData> yellowInLeft002(int layer, SmallCube yellowPlusCube, SmallCube scCenter, SmallCube scArray[][][]) {
        ArrayList<LayerRotationData> moves = new ArrayList<>();
        int moveTo;
        if (yellowPlusCube.getFaces()[layer].getColor() != scCenter.getFaces()[Face.BACK].getColor()) {
//            scArray[0][0][2].getFaces()[Face.LEFT].startBlink();

            moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[layer].getColor());
            switch (moveTo) {

                case Face.FRONT:

                    System.out.println("0|0|2 yellow in LEFT FRONT50");
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                    break;

                case Face.RIGHT:
                    System.out.println("0|0|2 yellow in LEFT RIGHT15");

                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                    break;

                case Face.LEFT:
                    System.out.println("0|0|2 yellow in LEFT LEFT52");
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                    break;
            }
            return moves;

        } else {

            System.out.println("0|0|2 yellow in LEFT ELSE53");
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
            return moves;

        }

    }

    public static ArrayList<LayerRotationData> yellowInBack002(int layer, SmallCube yellowPlusCube, SmallCube scCenter, SmallCube scArray[][][]) {
        ArrayList<LayerRotationData> moves = new ArrayList<>();
        int moveTo;
        System.out.println("0|0|2 yellow in BACK before if");
        if (yellowPlusCube.getFaces()[layer].getColor() != scCenter.getFaces()[Face.LEFT].getColor()) {
//            scArray[0][0][2].getFaces()[Face.BACK].startBlink();
            System.out.println("0|0|2 yellow in BACK after if");
            moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[layer].getColor());

            switch (moveTo) {

                case Face.FRONT:

                    System.out.println("0|0|2 yellow in BACK FRONT54");
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                    break;

                case Face.RIGHT:
                    System.out.println("0|0|2 yellow in BACK RIGHT55");
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));

                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                    break;

                case Face.BACK:
                    System.out.println("0|0|2 yellow in BACK LEFT56");
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));

                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));

                    break;
            }
            return moves;

        } else {

            System.out.println("0|0|2 yellow in BACK ELSE57");
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
            return moves;

        }
    }

    public static ArrayList<LayerRotationData> yellowInBack202(int layer, SmallCube yellowPlusCube, SmallCube scCenter, SmallCube scArray[][][]) {

        ArrayList<LayerRotationData> moves = new ArrayList<>();
        int moveTo;

        if (yellowPlusCube.getFaces()[layer].getColor() != scCenter.getFaces()[Face.RIGHT].getColor()) {
//            scArray[2][0][2].getFaces()[Face.BACK].startBlink();

            moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[layer].getColor());
            switch (moveTo) {

                case Face.FRONT:

                    System.out.println("2|0|2 yellow in BACK FRONT58");
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                    break;

                case Face.BACK:
                    System.out.println("2|0|2 yellow in BACK BACK89");
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
                    break;

                case Face.LEFT:
                    System.out.println("2|0|2 yellow in BACK LEFT60");
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                    break;
            }
            return moves;

        } else {

            System.out.println("2|0|2 yellow in BACK ELSE91");
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
            return moves;

        }
    }

    private static int findLayerWithReqestedCenter(SmallCube sc[][][], int reqestedColor) {
        // x  y  z
        if (sc[0][1][1].getFaces()[Face.LEFT].getColor() == reqestedColor) {
            return Face.LEFT;
        }
        if (sc[2][1][1].getFaces()[Face.RIGHT].getColor() == reqestedColor) {
            return Face.RIGHT;
        }
        if (sc[1][2][1].getFaces()[Face.TOP].getColor() == reqestedColor) {
            return Face.TOP;
        }
        if (sc[1][0][1].getFaces()[Face.BOTTOM].getColor() == reqestedColor) {
            return Face.BOTTOM;
        }
        if (sc[1][1][0].getFaces()[Face.FRONT].getColor() == reqestedColor) {
            return Face.FRONT;
        }
        if (sc[1][1][2].getFaces()[Face.BACK].getColor() == reqestedColor) {
            return Face.BACK;
        }

        return -1;

    }
    //**********yellow Layer************//

    //********yellowPlus*******//
    public static boolean yellowPlus120(SmallCube[][][] scArray) {

        return scArray[1][2][0].getFaces()[Face.TOP].getColor() == Face.YELLOW
                && scArray[1][2][0].getFaces()[Face.FRONT].getColor() == scArray[1][1][0].getFaces()[Face.FRONT].getColor();

    }

    public static boolean yellowPlus221(SmallCube[][][] scArray) {

        return scArray[2][2][1].getFaces()[Face.TOP].getColor() == Face.YELLOW
                && scArray[2][2][1].getFaces()[Face.RIGHT].getColor() == scArray[2][1][1].getFaces()[Face.RIGHT].getColor();

    }

    public static boolean yellowPlus122(SmallCube[][][] scArray) {

        return scArray[1][2][2].getFaces()[Face.TOP].getColor() == Face.YELLOW
                && scArray[1][2][2].getFaces()[Face.BACK].getColor() == scArray[1][1][2].getFaces()[Face.BACK].getColor();

    }

    public static boolean yellowPlus021(SmallCube[][][] scArray) {

        return scArray[0][2][1].getFaces()[Face.TOP].getColor() == Face.YELLOW
                && scArray[0][2][1].getFaces()[Face.LEFT].getColor() == scArray[0][1][1].getFaces()[Face.LEFT].getColor();

    }

    public static boolean isYellowPlusComplete(SmallCube[][][] scArray) {
        return yellowPlus021(scArray) && yellowPlus120(scArray) && yellowPlus122(scArray) && yellowPlus221(scArray);
    }

    public static ArrayList<LayerRotationData> getYellowPlus(Cube cube) {
//        Layer yellowLayer;
        ArrayList<LayerRotationData> moves = new ArrayList<>();
        layerArray[Face.FRONT] = cube.getAxis().getLayerZ(0);
        layerArray[Face.FRONT].setId(Layer.Z);
        layerArray[Face.FRONT].setAxisNum(0);

        layerArray[Face.LEFT] = cube.getAxis().getLayerX(0);
        layerArray[Face.LEFT].setId(Layer.X);
        layerArray[Face.LEFT].setAxisNum(0);

        layerArray[Face.RIGHT] = cube.getAxis().getLayerX(2);
        layerArray[Face.RIGHT].setId(Layer.X);
        layerArray[Face.RIGHT].setAxisNum(2);

        layerArray[Face.TOP] = cube.getAxis().getLayerY(2);
        layerArray[Face.TOP].setId(Layer.Y);
        layerArray[Face.TOP].setAxisNum(2);

        layerArray[Face.BACK] = cube.getAxis().getLayerZ(2);
        layerArray[Face.BACK].setId(Layer.Z);
        layerArray[Face.BACK].setAxisNum(2);

        layerArray[Face.BOTTOM] = cube.getAxis().getLayerY(0);
        layerArray[Face.BOTTOM].setId(Layer.Y);
        layerArray[Face.BOTTOM].setAxisNum(0);
        SmallCube scArray[][][] = cube.getArray();
        int moveTo;
        SmallCube scCenter = null;
        SmallCube scCenterTOP = null;

        SmallCube yellowPlusCube = null;

        boolean doMove = false;

        if (isYellowPlusComplete(scArray)) {
            System.err.println("yellowPlus DONE!");
            return moves;
        }
//*****************************************************************************************************//
//        cube.getArray()[0][1][0].getFaces()[Face.FRONT].startBlink();

        ///////////////////////////0|1|0 front//////////////////////////////
        yellowPlusCube = scArray[0][1][0];//Front Left
        if (yellowPlusCube.getFaces()[Face.FRONT].getColor() == Face.YELLOW) {//check if front Face of the plus is yellow
//            yellowPlusCube.getFaces()[Face.FRONT].startBlink();
            scCenter = cube.getArray()[0][1][1];
            if (yellowPlusCube.getFaces()[Face.LEFT].getColor() != scCenter.getFaces()[Face.LEFT].getColor()) {
                scCenterTOP = cube.getArray()[0][2][1];
                if (scCenterTOP.getFaces()[Face.TOP].getColor() == Face.YELLOW
                        && scCenterTOP.getFaces()[Face.LEFT].getColor() == scCenter.getFaces()[Face.LEFT].getColor()) {
                    doMove = true;
                }
                System.out.println("do move" + doMove);

                moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[Face.LEFT].getColor());
                switch (moveTo) {

                    case Face.RIGHT:

                        System.out.println("inside i == Face.FRONT 0|1|0 RIGHT");
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                        System.out.println("do move" + doMove);

                        if (doMove) {
                            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                        }
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                        break;

                    case Face.FRONT:
                        System.out.println("inside i == Face.FRONT 0|1|0 FRONT");
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                        System.out.println("do move" + doMove);

                        if (doMove) {
                            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                        }
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        break;

                    case Face.BACK:
                        System.out.println("inside i == Face.FRONT 0|1|0 BACK");
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                        System.out.println("do move" + doMove);

                        if (doMove) {
                            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));

                        }
                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));

                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));

                        break;

                }
                doMove = false;
                return moves;
            } else {
                System.out.println("inside i == Face.FRONT 0|1|0 else");

                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                doMove = false;
                return moves;
            }

        }

        ///////////////////////////0|1|0 yellow in the left//////////////////////////////
        if (yellowPlusCube.getFaces()[Face.LEFT].getColor() == Face.YELLOW) {
//            yellowPlusCube.getFaces()[Face.LEFT].startBlink();
            System.out.println("inside i == Face.LEFT 0|1|0");

            scCenter = cube.getArray()[1][1][0];
            if (yellowPlusCube.getFaces()[Face.FRONT].getColor() != scCenter.getFaces()[Face.FRONT].getColor()) {

                scCenterTOP = cube.getArray()[1][2][0];
                if (scCenterTOP.getFaces()[Face.TOP].getColor() == Face.YELLOW
                        && scCenterTOP.getFaces()[Face.FRONT].getColor() == scCenter.getFaces()[Face.FRONT].getColor()) {
                    doMove = true;
                }
                System.out.println("do move" + doMove);

                moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[Face.FRONT].getColor());
                switch (moveTo) {
                    case Face.RIGHT:
                        System.out.println("inside i == Face.LEFT 0|1|0 RIGHT");

                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));

                        System.out.println("do move" + doMove);
                        if (doMove) {
                            moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));

                        }
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));

                        break;
                    case Face.LEFT:
                        System.out.println("inside i == Face.LEFT 0|1|0 LEFT");

                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));

                        System.out.println("do move" + doMove);
                        if (doMove) {
                            moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));

                        }
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));

                        break;
                    case Face.BACK:
                        System.out.println("inside i == Face.LEFT 0|1|0 BACK");

                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));

                        System.out.println("do move" + doMove);
                        if (doMove) {
                            moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));

                        }
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));

                        break;
                }
                doMove = false;
                return moves;
            } else {
                System.out.println("inside i == Face.LEFT 0|1|0 ELSE");

                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                doMove = false;
                return moves;

            }

        }

        ///////////////////////////1|2|0 yellow in the FRONT//////////////////////////////
        yellowPlusCube = scArray[1][2][0];
        if (yellowPlusCube.getFaces()[Face.FRONT].getColor() == Face.YELLOW) {
//            yellowPlusCube.getFaces()[Face.FRONT].startBlink();
            scCenterTOP = cube.getArray()[2][2][1];
            scCenter = cube.getArray()[2][1][1];

            if (scCenterTOP.getFaces()[Face.TOP].getColor() == Face.YELLOW
                    && scCenterTOP.getFaces()[Face.RIGHT].getColor() == scCenter.getFaces()[Face.RIGHT].getColor()) {
                doMove = true;
            }
            System.err.println("do move 7" + doMove);

            System.out.println("inside i == Face.FRONT 1|2|0");

            moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[Face.TOP].getColor());
            switch (moveTo) {
                case Face.RIGHT:
                    System.out.println("inside i == Face.FRONT 1|2|0 RIGHT");

                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));

                    break;
                case Face.LEFT:
                    System.out.println("inside i == Face.FRONT 1|2|0 LEFT");

                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));

                    break;
                case Face.BACK:
                    System.out.println("inside i == Face.FRONT 1|2|0 BACK");

                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                    if (doMove) {
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                    }
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));

                    break;
                case Face.FRONT:
                    System.out.println("inside i == Face.FRONT 1|2|0 FRONT");

                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    if (doMove) {
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                    }

                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));

                    break;

            }
            return moves;
        }

        ///////////////////////////1|2|0 yellow in the TOP//////////////////////////////
        if (!yellowPlus120(scArray)) {
            if (yellowPlusCube.getFaces()[Face.TOP].getColor() == Face.YELLOW) {
//                yellowPlusCube.getFaces()[Face.TOP].startBlink();
                System.out.println("inside i == Face.TOP 1|2|0 ");

                scCenter = cube.getArray()[1][1][0];
                if (yellowPlusCube.getFaces()[Face.FRONT].getColor() != scCenter.getFaces()[Face.FRONT].getColor()) {
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                    switch (findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[Face.FRONT].getColor())) {

                        case Face.BACK:
                            System.out.println("inside i == Face.TOP 1|2|0 BACK");

                            moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                            break;
                        case Face.LEFT:
                            System.out.println("inside i == Face.TOP 1|2|0 LEFT");

                            moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));

                            break;
                        case Face.RIGHT:
                            System.out.println("inside i == Face.TOP 1|2|0 RIGHT");

                            moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                            break;

                    }
//                    top120Done = true;
                    return moves;
                }
//                top120Done = true;

            }
        }

        ///////////////////////////2|1|0 yellow in the FRONT//////////////////////////////
        yellowPlusCube = cube.getArray()[2][1][0];
        if (yellowPlusCube.getFaces()[Face.FRONT].getColor() == Face.YELLOW) {
//            yellowPlusCube.getFaces()[Face.FRONT].startBlink();
            scCenter = cube.getArray()[2][1][1];
            if (yellowPlusCube.getFaces()[Face.RIGHT].getColor() != scCenter.getFaces()[Face.RIGHT].getColor()) {
                scCenterTOP = cube.getArray()[2][2][1];
                if (scCenterTOP.getFaces()[Face.TOP].getColor() == Face.YELLOW
                        && scCenterTOP.getFaces()[Face.RIGHT].getColor() == scCenter.getFaces()[Face.RIGHT].getColor()) {
                    doMove = true;
                }
                System.out.println("do move" + doMove);

                moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[Face.RIGHT].getColor());
                switch (moveTo) {

                    case Face.BACK:
                        System.out.println("inside i == Face.FRONT 2|1|0 BACK");

                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));

                        if (doMove) {
                            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));

                        }
                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                        break;
                    case Face.FRONT:
                        System.out.println("inside i == Face.FRONT 2|1|0 FRONT");

                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));

                        if (doMove) {
                            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));

                        }
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));

                        break;
                    case Face.LEFT://
                        System.out.println("inside i == Face.FRONT 2|1|0 LEFT");

                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                        System.out.println("do move" + doMove);

                        if (doMove) {
                            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));

                        }
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                        break;

                }
                doMove = false;
                return moves;
            } else {
                System.out.println("inside i == Face.FRONT 2|1|0 ELSE");

                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                doMove = false;
                return moves;
            }

        }

        ///////////////////////////2|1|0 yellow in the right//////////////////////////////
        yellowPlusCube = cube.getArray()[2][1][0];
        if (yellowPlusCube.getFaces()[Face.RIGHT].getColor() == Face.YELLOW) {
//            yellowPlusCube.getFaces()[Face.RIGHT].startBlink();
            System.out.println("inside i == Face.RIGHT 2|1|0");

            scCenter = cube.getArray()[1][1][0];
            if (yellowPlusCube.getFaces()[Face.FRONT].getColor() != scCenter.getFaces()[Face.FRONT].getColor()) {
                scCenterTOP = cube.getArray()[1][2][0];
                if (scCenterTOP.getFaces()[Face.TOP].getColor() == Face.YELLOW
                        && scCenterTOP.getFaces()[Face.FRONT].getColor() == scCenter.getFaces()[Face.FRONT].getColor()) {
                    doMove = true;
                }
                System.out.println("do move" + doMove);

                moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[Face.FRONT].getColor());

                switch (moveTo) {

                    case Face.BACK:
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                        System.out.println("do move" + doMove);

                        if (doMove) {
                            moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));

                        }
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));

                        System.out.println("inside i == Face.RIGHT 2|1|0 Face.BACK");
                        break;
                    case Face.LEFT:
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                        System.out.println("do move" + doMove);

                        if (doMove) {
                            moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));

                        }
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));

                        System.out.println("inside i == Face.RIGHT 2|1|0 Face.LEFT");
                        break;
                    case Face.RIGHT:
                        System.out.println("inside i == Face.RIGHT 2|1|0 Face.RIGHT");
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                        System.out.println("do move" + doMove);

                        if (doMove) {
                            moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));

                        }
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));

                        break;

                }
                doMove = false;
                return moves;
            } else {
                System.out.println("inside i == Face.RIGHT 2|1|0 ELSE");

                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                doMove = false;
                return moves;

            }

        }

        ///////////////////////////1|0|0 yellow in the bottom//////////////////////////////
        yellowPlusCube = cube.getArray()[1][0][0];
        if (yellowPlusCube.getFaces()[Face.BOTTOM].getColor() == Face.YELLOW) {
//            yellowPlusCube.getFaces()[Face.BOTTOM].startBlink();
            System.out.println("inside i == Face.TOP 1|0|0 BOTTOM");

            scCenter = cube.getArray()[1][1][0];
            if (yellowPlusCube.getFaces()[Face.FRONT].getColor() != scCenter.getFaces()[Face.FRONT].getColor()) {

                switch (findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[Face.FRONT].getColor())) {

                    case Face.BACK:
                        System.out.println("inside i == Face.TOP 1|0|0 BACK");

                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                        break;
                    case Face.LEFT:
                        System.out.println("inside i == Face.TOP 1|0|0 LEFT");

                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));

                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));

                        break;
                    case Face.RIGHT:
                        System.out.println("inside i == Face.TOP 1|0|0 RIGHT");

                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                        break;

                }
                return moves;
            } else {
                System.out.println("inside i == Face.TOP 1|0|0 ELSE");

                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                return moves;
            }

        }

        ///////////////////////////1|0|0 yellow in the front//////////////////////////////
        scCenter = cube.getArray()[1][1][0];

        if (yellowPlusCube.getFaces()[Face.FRONT].getColor() == Face.YELLOW) {
//            yellowPlusCube.getFaces()[Face.FRONT].startBlink();
            System.out.println("inside i == Face.FRONT 1|0|0 ");
            scCenterTOP = cube.getArray()[1][2][0];

            if (scCenterTOP.getFaces()[Face.TOP].getColor() == Face.YELLOW
                    && scCenterTOP.getFaces()[Face.FRONT].getColor() == scCenter.getFaces()[Face.FRONT].getColor()) {
                doMove = true;
            }
            System.out.println("do move" + doMove);

            scCenterTOP = cube.getArray()[0][2][1];
            scCenter = cube.getArray()[0][1][1];

            if (scCenterTOP.getFaces()[Face.TOP].getColor() == Face.YELLOW
                    && scCenterTOP.getFaces()[Face.LEFT].getColor() == scCenter.getFaces()[Face.LEFT].getColor()) {
                doMove = true;
            }
            System.err.println("newww do move4" + doMove);

            moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[Face.BOTTOM].getColor());
            switch (moveTo) {

                case Face.LEFT:
                    System.out.println("inside i == Face.FRONT 1|0|0 LEFT");

                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));

                    System.out.println("do move" + doMove);
                    if (doMove) {
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));

                    }

                    break;
                case Face.RIGHT:
                    System.out.println("inside i == Face.FRONT 1|0|0 RIGHT");

                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));

                    System.out.println("do move" + doMove);
                    if (doMove) {
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));

                    }

                    break;
                case Face.FRONT:
                    System.out.println("inside i == Face.FRONT 1|0|0 FRONT");

                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));

                    if (doMove) {
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));

                    }
                    break;
                case Face.BACK:
                    System.out.println("inside i == Face.FRONT 1|0|0 BACK");

                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
                    if (doMove) {
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));

                    }
                    break;

            }
            doMove = false;
            return moves;
        }

        ///////////////////////////1|0|0 yellow in the front//////////////////////////////
        yellowPlusCube = cube.getArray()[2][2][1];
        if (yellowPlusCube.getFaces()[Face.RIGHT].getColor() == Face.YELLOW) {
//            yellowPlusCube.getFaces()[Face.RIGHT].startBlink();
            scCenterTOP = cube.getArray()[0][2][1];
            scCenter = cube.getArray()[0][1][1];
            if (scCenterTOP.getFaces()[Face.TOP].getColor() == Face.YELLOW
                    && scCenterTOP.getFaces()[Face.FRONT].getColor() == scCenter.getFaces()[Face.FRONT].getColor()) {
                doMove = true;
            }
            System.out.println("do move 1.1" + doMove);

            scCenterTOP = cube.getArray()[1][2][2];
            scCenter = cube.getArray()[1][1][2];
            if (scCenterTOP.getFaces()[Face.TOP].getColor() == Face.YELLOW
                    && scCenterTOP.getFaces()[Face.BACK].getColor() == scCenter.getFaces()[Face.BACK].getColor()) {
                doMove = true;
            }
            System.err.println("newwwwwwwww do move 1.2" + doMove);

            System.out.println("inside i == Face.LEFT 1|0|0");
            moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[Face.TOP].getColor());
            switch (moveTo) {

                case Face.BACK:
                    System.out.println("inside i == Face.BACK 1|0|0 BACK");
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));

                    break;
                case Face.LEFT:
                    System.out.println("inside i == Face.BACK 1|0|0 LEFT");
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));

                    System.out.println("do move" + doMove);
                    if (doMove) {
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));

                    }

                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));

                    break;
                case Face.RIGHT:
                    System.out.println("inside i == Face.BACK 1|0|0 RIGHT");
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    if (doMove) {
                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));

                    }
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));

                    break;

                case Face.FRONT:
                    System.out.println("inside i == Face.BACK 1|0|0 FRONT");
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));

                    break;
            }
            doMove = false;
            return moves;

        }

        ///////////////////////////2|2|1 yellow in the TOP//////////////////////////////
        if (!yellowPlus221(scArray)) {
            yellowPlusCube = cube.getArray()[2][2][1];
            if (yellowPlusCube.getFaces()[Face.TOP].getColor() == Face.YELLOW) {
//                yellowPlusCube.getFaces()[Face.TOP].startBlink();
                System.out.println("inside i == Face.TOP 2|2|1 TOP");

                scCenter = cube.getArray()[2][2][1];
                if (yellowPlusCube.getFaces()[Face.RIGHT].getColor() != scCenter.getFaces()[Face.RIGHT].getColor()) {

                    moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[Face.RIGHT].getColor());
                    switch (moveTo) {

                        case Face.BACK:
                            System.out.println("inside i == Face.TOP 2|2|1 BACK");

                            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                            break;

                        case Face.LEFT:
                            System.out.println("inside i == Face.TOP 2|2|1 LEFT");

                            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));

                            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));

                            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));

                            break;
                        case Face.FRONT:
                            System.out.println("inside i == Face.TOP 2|2|1 FRONT");

                            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));

                            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));

                            moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));

                            break;

                    }
//                    top221Done = true;
                    return moves;
                }
//                top221Done = true;

            }
        }
        ///////////////////////////2|0|1 yellow in the RIGHT//////////////////////////////

        yellowPlusCube = cube.getArray()[2][0][1];
        if (yellowPlusCube.getFaces()[Face.RIGHT].getColor() == Face.YELLOW) {
//            yellowPlusCube.getFaces()[Face.RIGHT].startBlink();
            System.out.println("inside i == Face.RIGHT 2|0|1 ");
            scCenterTOP = cube.getArray()[2][2][1];
            scCenter = cube.getArray()[2][1][1];
            if (scCenterTOP.getFaces()[Face.TOP].getColor() == Face.YELLOW
                    && scCenterTOP.getFaces()[Face.RIGHT].getColor() == scCenter.getFaces()[Face.RIGHT].getColor()) {
                doMove = true;
            }
            System.out.println("do move" + doMove);
            scCenterTOP = cube.getArray()[1][2][0];
            scCenter = cube.getArray()[1][1][0];
            if (scCenterTOP.getFaces()[Face.TOP].getColor() == Face.YELLOW
                    && scCenterTOP.getFaces()[Face.FRONT].getColor() == scCenter.getFaces()[Face.FRONT].getColor()) {
                doMove = true;
            }

            System.err.println("newwwwwwww do move1" + doMove);
            moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[Face.BOTTOM].getColor());
            switch (moveTo) {
                case Face.BACK:
                    System.out.println("inside i == Face.RIGHT 2|0|1 BACK");

                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));

                    System.out.println("do move" + doMove);
                    if (doMove) {
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));

                    }

                    break;
                case Face.LEFT:
                    System.out.println("inside i == Face.RIGHT 2|0|1 LEFT");

                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));

                    System.out.println("do move" + doMove);
                    if (doMove) {
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));

                    }

                    break;
                case Face.RIGHT:
                    System.out.println("inside i == Face.RIGHT 2|0|1 RIGHT");

                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));

                    System.out.println("do move" + doMove);
                    if (doMove) {
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));

                    }

                    break;

                case Face.FRONT:
                    System.out.println("inside i == Face.RIGHT 2|0|1 FRONT");

                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));

                    System.out.println("do move" + doMove);
                    if (doMove) {
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));

                    }

                    break;

            }
            doMove = false;
            return moves;
        }

        ///////////////////////////2|0|1 yellow in the BOTTOM//////////////////////////////
        yellowPlusCube = cube.getArray()[2][0][1];
        if (yellowPlusCube.getFaces()[Face.BOTTOM].getColor() == Face.YELLOW) {
//            yellowPlusCube.getFaces()[Face.BOTTOM].startBlink();
            System.out.println("inside i == Face.BOTTOM 2|0|1 ");

            scCenter = cube.getArray()[2][1][1];
            if (yellowPlusCube.getFaces()[Face.RIGHT].getColor() != scCenter.getFaces()[Face.RIGHT].getColor()) {

                switch (findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[Face.RIGHT].getColor())) {

                    case Face.LEFT:
                        System.out.println("inside i == Face.BOTTOM 2|0|1 LEFT");

                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                        break;
                    case Face.FRONT:
                        System.out.println("inside i == Face.BOTTOM 2|0|1 FRONT");

                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));

                        break;
                    case Face.BACK:
                        System.out.println("inside i == Face.BOTTOM 2|0|1 BACK");

                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                        break;

                }
                return moves;
            } else {
                System.out.println("inside i == Face.BOTTOM 2|0|1 ELSE");

                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                return moves;
            }

        }

        ///////////////////////////2|1|2 yellow in the RIGHT//////////////////////////////
        yellowPlusCube = cube.getArray()[2][1][2];
        if (yellowPlusCube.getFaces()[Face.RIGHT].getColor() == Face.YELLOW) {
//            yellowPlusCube.getFaces()[Face.RIGHT].startBlink();
            System.out.println("inside i == Face.RIGHT 2|2|2");

            scCenter = cube.getArray()[1][1][2];
            if (yellowPlusCube.getFaces()[Face.BACK].getColor() != scCenter.getFaces()[Face.BACK].getColor()) {
                scCenterTOP = cube.getArray()[1][2][2];
                if (scCenterTOP.getFaces()[Face.TOP].getColor() == Face.YELLOW
                        && scCenterTOP.getFaces()[Face.BACK].getColor() == scCenter.getFaces()[Face.BACK].getColor()) {
                    doMove = true;
                }
                System.out.println("do move" + doMove);

                moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[Face.BACK].getColor());
                switch (moveTo) {
                    case Face.RIGHT:
                        System.out.println("inside i == Face.RIGHT 2|2|2 RIGHT");

                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                        System.out.println("do move" + doMove);

                        if (doMove) {
                            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));

                        }
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));

                        break;
                    case Face.FRONT:
                        System.out.println("inside i == Face.RIGHT 2|2|2 FRONT");

                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                        System.out.println("do move" + doMove);

                        if (doMove) {
                            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));

                        }
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));

                        break;
                    case Face.LEFT:
                        System.out.println("inside i == Face.RIGHT 2|2|2 LEFT");

                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));

                        System.out.println("do move" + doMove);
                        if (doMove) {
                            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));

                        }
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));

                        break;
                }
                doMove = false;
                return moves;
            } else {
                System.out.println("inside i == Face.RIGHT 2|2|2 ELSE");

                moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                doMove = false;
                return moves;

            }

        }

        ///////////////////////////2|1|2 yellow in the BACK//////////////////////////////
        yellowPlusCube = cube.getArray()[2][1][2];
        if (yellowPlusCube.getFaces()[Face.BACK].getColor() == Face.YELLOW) {
//            yellowPlusCube.getFaces()[Face.BACK].startBlink();
            System.out.println("inside i == Face.BACK 2|1|2");
            scCenter = cube.getArray()[2][1][1];
            if (yellowPlusCube.getFaces()[Face.RIGHT].getColor() != scCenter.getFaces()[Face.RIGHT].getColor()) {
                scCenterTOP = cube.getArray()[2][2][1];
                scCenter = cube.getArray()[2][1][1];
                if (scCenterTOP.getFaces()[Face.TOP].getColor() == Face.YELLOW
                        && scCenterTOP.getFaces()[Face.RIGHT].getColor() == scCenter.getFaces()[Face.RIGHT].getColor()) {
                    doMove = true;
                }
                System.out.println("do move" + doMove);

                moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[Face.RIGHT].getColor());

                switch (moveTo) {
                    case Face.FRONT:
                        System.out.println("inside i == Face.BACK 2|2|2 FRONT");
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                        System.out.println("do move" + doMove);

                        if (doMove) {
                            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));

                        }

                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));

                        break;
                    case Face.LEFT:
                        System.out.println("inside i == Face.BACK 2|2|2 LEFT");
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                        System.out.println("do move" + doMove);

                        if (doMove) {
                            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));

                        }
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));

                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));

                        break;
                    case Face.BACK:
                        System.out.println("inside i == Face.BACK 2|2|2 BACK");
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                        System.out.println("do move" + doMove);

                        if (doMove) {
                            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));

                        }
                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));

                        break;
                }
                doMove = false;
                return moves;
            } else {
                System.out.println("inside i == Face.BACK 2|2|2 else");
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                doMove = false;
                return moves;

            }

        }

        ///////////////////////////1|2|2 yellow in the BACK//////////////////////////////
        yellowPlusCube = cube.getArray()[1][2][2];
        if (yellowPlusCube.getFaces()[Face.BACK].getColor() == Face.YELLOW) {
            scCenterTOP = cube.getArray()[0][2][1];
            scCenter = cube.getArray()[0][1][1];
            if (scCenterTOP.getFaces()[Face.TOP].getColor() == Face.YELLOW
                    && scCenterTOP.getFaces()[Face.LEFT].getColor() == scCenter.getFaces()[Face.LEFT].getColor()) {
                doMove = true;
            }
            System.err.println("New domove 6" + doMove);

//            yellowPlusCube.getFaces()[Face.BACK].startBlink();
            System.out.println("inside i == Face.BACK 1|2|2");
            moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[Face.TOP].getColor());
            switch (moveTo) {
                case Face.LEFT:
                    System.out.println("inside i == Face.BACK 1|2|2 LEFT");
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                    break;

                case Face.RIGHT:
                    System.out.println("inside i == Face.BACK 1|2|2 RIGHT");
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));

                    break;
                case Face.FRONT:
                    System.out.println("inside i == Face.BACK 1|2|2 FRONT");
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                    if (doMove) {
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                    }
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                    break;

                case Face.BACK:
                    System.out.println("inside i == Face.BACK 1|2|2 BACK");
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    if (doMove) {
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                    }

                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                    break;
            }
            doMove = false;
            return moves;
        }

        ///////////////////////////1|2|2 yellow in the TOP//////////////////////////////
        if (!yellowPlus122(scArray)) {
            yellowPlusCube = cube.getArray()[1][2][2];
            if (yellowPlusCube.getFaces()[Face.TOP].getColor() == Face.YELLOW) {
//                yellowPlusCube.getFaces()[Face.TOP].startBlink();
                System.out.println("inside i == Face.TOP 1|2 ");
                scCenter = cube.getArray()[1][1][2];
                if (yellowPlusCube.getFaces()[Face.BACK].getColor() != scCenter.getFaces()[Face.BACK].getColor()) {
                    moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[Face.BACK].getColor());
                    switch (moveTo) {

                        case Face.LEFT:
                            System.out.println("inside i == Face.TOP 1|2|2 LEFT");
                            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));

                            break;
                        case Face.FRONT:
                            System.out.println("inside i == Face.TOP 1|2|2 FRONT");
                            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));

                            break;
                        case Face.RIGHT://
                            System.out.println("inside i == Face.TOP 1|2|2 RIGHT ");
                            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                            break;

                    }
//                    top122Done = true;
                    return moves;

                }
//                top122Done = true;

            }
        }

        ///////////////////////////1|0|2 yellow in the FRONT//////////////////////////////
        yellowPlusCube = cube.getArray()[1][0][2];
        if (yellowPlusCube.getFaces()[Face.BACK].getColor() == Face.YELLOW) {
//            yellowPlusCube.getFaces()[Face.BACK].startBlink();
            System.out.println("inside i == Face.BACK 1|0|2 ");
            scCenterTOP = cube.getArray()[1][2][2];
            scCenter = cube.getArray()[1][1][2];
            if (scCenterTOP.getFaces()[Face.TOP].getColor() == Face.YELLOW
                    && scCenterTOP.getFaces()[Face.BACK].getColor() == scCenter.getFaces()[Face.BACK].getColor()) {
                doMove = true;
            }
            System.out.println("do move 1.3" + doMove);

            scCenterTOP = cube.getArray()[2][2][1];
            scCenter = cube.getArray()[2][1][1];
            if (scCenterTOP.getFaces()[Face.TOP].getColor() == Face.YELLOW
                    && scCenterTOP.getFaces()[Face.RIGHT].getColor() == scCenter.getFaces()[Face.RIGHT].getColor()) {
                doMove = true;
            }
            System.err.println("NEWWWW do move 1.3" + doMove);
            moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[Face.BOTTOM].getColor());
            switch (moveTo) {
                case Face.LEFT:
                    System.out.println("inside i == Face.BACK 1|0|2 LEFT");

                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));

                    System.out.println("do move" + doMove);
                    if (doMove) {
                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                    }
                    break;

                case Face.RIGHT:
                    System.out.println("inside i == Face.BACK 1|0|2 RIGHT");

                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));

                    System.out.println("do move" + doMove);
                    if (doMove) {
                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
                    }
                    break;

                case Face.BACK:
                    System.out.println("inside i == Face.BACK 1|0|2 BACK");

                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                    System.out.println("do move" + doMove);

                    if (doMove) {
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                    }
                    break;

                case Face.FRONT:
                    System.out.println("inside i == Face.BACK 1|0|2 FRONT");

                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));

                    if (doMove) {
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                    }
                    break;
            }
            doMove = false;
            return moves;
        }

        ///////////////////////////1|0|2 yellow in the BOTTOM//////////////////////////////
        yellowPlusCube = cube.getArray()[1][0][2];
        if (yellowPlusCube.getFaces()[Face.BOTTOM].getColor() == Face.YELLOW) {
//            yellowPlusCube.getFaces()[Face.BOTTOM].startBlink();
            System.out.println("inside i == Face.BOTTOM 1|0|2 ");

            scCenter = cube.getArray()[1][1][2];
            if (yellowPlusCube.getFaces()[Face.BACK].getColor() != scCenter.getFaces()[Face.BACK].getColor()) {

                switch (findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[Face.BACK].getColor())) {

                    case Face.LEFT:
                        System.out.println("inside i == Face.BOTTOM 1|0|2 LEFT");

                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                        break;
                    case Face.FRONT:
                        System.out.println("inside i == Face.BOTTOM 1|0|2 FRONT");
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        break;
                    case Face.RIGHT:
                        System.out.println("inside i == Face.BOTTOM 1|0|2 right/////////");

                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                        break;

                }
                return moves;

            } else {
                System.out.println("inside i == Face.BOTTOM 1|0|2 ELSE");

                moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                return moves;

            }
        }

        ///////////////////////////0|1|2 yellow in the BACK//////////////////////////////
        yellowPlusCube = cube.getArray()[0][1][2];
        if (yellowPlusCube.getFaces()[Face.BACK].getColor() == Face.YELLOW) {
//            yellowPlusCube.getFaces()[Face.BACK].startBlink();
            System.out.println("inside i == Face.BACK 0|1|2");

            scCenter = cube.getArray()[0][1][1];
            if (yellowPlusCube.getFaces()[Face.LEFT].getColor() != scCenter.getFaces()[Face.LEFT].getColor()) {
                scCenterTOP = cube.getArray()[0][2][1];
                if (scCenterTOP.getFaces()[Face.TOP].getColor() == Face.YELLOW
                        && scCenterTOP.getFaces()[Face.LEFT].getColor() == scCenter.getFaces()[Face.LEFT].getColor()) {
                    doMove = true;
                }
                System.out.println("do move" + doMove);

                moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[Face.LEFT].getColor());
                switch (moveTo) {
                    case Face.BACK:

                        System.out.println("inside i == Face.BACK 0|1|2 BACK");

                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                        System.out.println("do move" + doMove);

                        if (doMove) {
                            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                        }
                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                        break;

                    case Face.FRONT:
                        System.out.println("inside i == Face.BACK 0|1|2 FRONT");

                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                        System.out.println("do move" + doMove);

                        if (doMove) {
                            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                        }
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        break;
                    case Face.RIGHT:
                        System.out.println("inside i == Face.BACK 0|1|2 RIGHT");
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));

                        System.out.println("do move" + doMove);
                        if (doMove) {
                            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                        }
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                        break;
                }
                doMove = false;
                return moves;
            } else {
                System.out.println("inside i == Face.BACK 0|1|2 ELSE");
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                doMove = false;
                return moves;
            }

        }

        ///////////////////////////0|1|2 yellow in the LEFT//////////////////////////////
        yellowPlusCube = cube.getArray()[0][1][2];
        if (yellowPlusCube.getFaces()[Face.LEFT].getColor() == Face.YELLOW) {
//            yellowPlusCube.getFaces()[Face.LEFT].startBlink();
            System.out.println("inside i == Face.LEFT 0|1|2");
            scCenter = cube.getArray()[1][1][2];
            if (yellowPlusCube.getFaces()[Face.BACK].getColor() != scCenter.getFaces()[Face.BACK].getColor()) {
                scCenterTOP = cube.getArray()[1][2][2];
                scCenter = cube.getArray()[1][1][2];
                if (scCenterTOP.getFaces()[Face.TOP].getColor() == Face.YELLOW
                        && scCenterTOP.getFaces()[Face.BACK].getColor() == scCenter.getFaces()[Face.BACK].getColor()) {
                    doMove = true;
                }
                System.out.println("do move" + doMove);

                moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[Face.BACK].getColor());
                switch (moveTo) {
                    case Face.LEFT:
                        System.out.println("inside i == Face.LEFT 0|1|2 LEFT");
                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                        System.out.println("do move" + doMove);

                        if (doMove) {
                            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));

                        }
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));

                        break;
                    case Face.FRONT:
                        System.out.println("inside i == Face.LEFT 0|1|2 FRONT");
                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                        System.out.println("do move" + doMove);

                        if (doMove) {
                            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));

                        }
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));

                        break;

                    case Face.RIGHT:
                        System.out.println("inside i == Face.LEFT 0|1|2 RIGHT");
                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));

                        System.out.println("do move" + doMove);
                        if (doMove) {
                            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));

                        }
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));

                        break;
                }
                doMove = false;
                return moves;
            } else {
                System.out.println("inside i == Face.LEFT 0|1|2 ELSE");

                moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
                doMove = false;
                return moves;

            }

        }

        ///////////////////////////0|2|1 yellow in the LEFT//////////////////////////////
        yellowPlusCube = cube.getArray()[0][2][1];
        if (yellowPlusCube.getFaces()[Face.LEFT].getColor() == Face.YELLOW) {
//            yellowPlusCube.getFaces()[Face.LEFT].startBlink();
            System.out.println("inside i == Face.LEFT 0|2|1");
            scCenter = cube.getArray()[1][1][0];
            scCenterTOP = cube.getArray()[1][2][0];
            if (scCenterTOP.getFaces()[Face.TOP].getColor() == Face.YELLOW
                    && scCenterTOP.getFaces()[Face.BACK].getColor() == scCenter.getFaces()[Face.BACK].getColor()) {
                doMove = true;
            }
            System.out.println("do move" + doMove);

            moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[Face.TOP].getColor());
            switch (moveTo) {
                case Face.RIGHT:
                    System.out.println("inside i == Face.LEFT 0|2|1 RIGHT");

                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                    System.out.println("do move" + doMove);

                    if (doMove) {
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));

                    }
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                    break;
                case Face.LEFT:
                    System.out.println("inside i == Face.LEFT 0|2|1 LEFT");

                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                    System.out.println("do move" + doMove);

                    if (doMove) {
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));

                    }
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                    break;
                case Face.FRONT:

                    System.out.println("inside i == Face.LEFT 0|2|1 FRONT");

                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                    break;
                case Face.BACK:
                    System.out.println("inside i == Face.LEFT 0|2|1 BACK");

                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
                    break;
            }
            doMove = false;
            return moves;
        }
///////////////////////////0|2|1 yellow in the TOP//////////////////////////////
        if (!yellowPlus021(scArray)) {
            yellowPlusCube = cube.getArray()[0][2][1];
            if (yellowPlusCube.getFaces()[Face.TOP].getColor() == Face.YELLOW) {
//                yellowPlusCube.getFaces()[Face.TOP].startBlink();
                System.out.println("inside i == Face.TOP 0|2|1");
                scCenter = cube.getArray()[0][1][1];

                if (yellowPlusCube.getFaces()[Face.LEFT].getColor() != scCenter.getFaces()[Face.LEFT].getColor()) {
                    moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[Face.LEFT].getColor());

                    switch (moveTo) {

                        case Face.BACK:
                            System.out.println("inside i == Face.TOP 0|2|1 Face.BACK");
                            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));

                            break;

                        case Face.FRONT:
                            System.out.println("inside i == Face.TOP 0|2|1 Face.LEFT");
                            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                            break;
                        case Face.RIGHT:
                            System.out.println("inside i == Face.TOP 0|2|1 Face.RIGHT");
                            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                            moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                            break;

                    }
//                    top021Done = true;

                    return moves;

                }
//                top021Done = true;
            }
        }
        ///////////////////////////0|0|1 yellow in the LEFT//////////////////////////////
        yellowPlusCube = cube.getArray()[0][0][1];
        if (yellowPlusCube.getFaces()[Face.LEFT].getColor() == Face.YELLOW) {
//            yellowPlusCube.getFaces()[Face.LEFT].startBlink();
            System.out.println("inside i == Face.LEFT 0|0|1 ");
            scCenterTOP = cube.getArray()[0][2][1];
            scCenter = cube.getArray()[0][1][1];
            if (scCenterTOP.getFaces()[Face.TOP].getColor() == Face.YELLOW
                    && scCenterTOP.getFaces()[Face.LEFT].getColor() == scCenter.getFaces()[Face.LEFT].getColor()) {
                doMove = true;
            }
            System.err.println(" do move1.0" + doMove);

            scCenterTOP = cube.getArray()[1][2][0];
            scCenter = cube.getArray()[1][1][0];
            if (scCenterTOP.getFaces()[Face.TOP].getColor() == Face.YELLOW
                    && scCenterTOP.getFaces()[Face.FRONT].getColor() == scCenter.getFaces()[Face.FRONT].getColor()) {
                doMove = true;
            }

            System.err.println("newwwwwwwwww do move2" + doMove);

            moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[Face.BOTTOM].getColor());
            switch (moveTo) {
                case Face.LEFT:

                    System.out.println("inside i == Face.LEFT 0|0|1 LEFT ");

                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                    System.out.println("do move" + doMove);
                    if (doMove) {
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                    }

                    break;
                case Face.RIGHT:

                    System.out.println("inside i == Face.LEFT 0|0|1 RIGHT ");

                    moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                    System.out.println("do move" + doMove);

                    if (doMove) {
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                    }

                    break;
                case Face.BACK:
                    System.out.println("inside i == Face.LEFT 0|0|1 BACK ");

                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
                    System.out.println("do move" + doMove);

                    if (doMove) {
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                    }

                    break;
                case Face.FRONT:
                    System.out.println("inside i == Face.LEFT 0|0|1 FRONT");

                    moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                    moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                    System.out.println("do move" + doMove);

                    if (doMove) {
                        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                    }

                    break;
            }
            doMove = false;
            return moves;
        }

        ///////////////////////////0|0|1 yellow in the BOTTOM//////////////////////////////
        yellowPlusCube = cube.getArray()[0][0][1];
        if (yellowPlusCube.getFaces()[Face.BOTTOM].getColor() == Face.YELLOW) {
//            yellowPlusCube.getFaces()[Face.BOTTOM].startBlink();
            System.out.println("inside i == Face.BOTTOM 0|0|1");
            scCenter = cube.getArray()[0][1][1];
            if (yellowPlusCube.getFaces()[Face.LEFT].getColor() != scCenter.getFaces()[Face.LEFT].getColor()) {
                moveTo = findLayerWithReqestedCenter(scArray, yellowPlusCube.getFaces()[Face.LEFT].getColor());
                switch (moveTo) {

                    case Face.RIGHT:
                        System.out.println("inside i == Face.BOTTOM 0|0|1 Face.RIGHT");
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                        break;
                    case Face.BACK:
                        System.out.println("inside i == Face.BOTTOM 0|0|1 Face.BACK");
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
                        break;
                    case Face.FRONT:
                        System.out.println("inside i == Face.BOTTOM 0|0|1 Face.FRONT");
                        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        break;

                }
                return moves;

            } else {
                System.out.println("inside i == Face.BOTTOM 0|0|1 ELSE");
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                return moves;

            }
        }
        return moves;

    }
//********yellowPlus*******//
    /////////_______________________________________________________________________________________________________________________________________________________________________________________________________________

    //*************second layer******************//
    public static boolean layerTwo021Done(SmallCube[][][] scArray) {
        System.out.println("021***");
        System.out.println(scArray[0][2][1].getFaces()[Face.TOP].getColor() == scArray[1][2][1].getFaces()[Face.TOP].getColor()
                && scArray[0][2][1].getFaces()[Face.LEFT].getColor() == scArray[0][1][1].getFaces()[Face.LEFT].getColor());
        return scArray[0][2][1].getFaces()[Face.TOP].getColor() == scArray[1][2][1].getFaces()[Face.TOP].getColor()
                && scArray[0][2][1].getFaces()[Face.LEFT].getColor() == scArray[0][1][1].getFaces()[Face.LEFT].getColor();

    }

    public static boolean layerTwo221Done(SmallCube[][][] scArray) {
        System.out.println("221***");
        System.out.println(scArray[2][2][1].getFaces()[Face.TOP].getColor() == scArray[1][2][1].getFaces()[Face.TOP].getColor()
                && scArray[2][2][1].getFaces()[Face.RIGHT].getColor() == scArray[2][1][1].getFaces()[Face.RIGHT].getColor());

        return scArray[2][2][1].getFaces()[Face.TOP].getColor() == scArray[1][2][1].getFaces()[Face.TOP].getColor()
                && scArray[2][2][1].getFaces()[Face.RIGHT].getColor() == scArray[2][1][1].getFaces()[Face.RIGHT].getColor();

    }

    public static boolean layerTwo201Done(SmallCube[][][] scArray) {

        System.out.println("201***");
        System.out.println(scArray[2][0][1].getFaces()[Face.RIGHT].getColor() == scArray[2][1][1].getFaces()[Face.RIGHT].getColor()
                && scArray[2][0][1].getFaces()[Face.BOTTOM].getColor() == scArray[1][0][1].getFaces()[Face.BOTTOM].getColor());

        return scArray[2][0][1].getFaces()[Face.RIGHT].getColor() == scArray[2][1][1].getFaces()[Face.RIGHT].getColor()
                && scArray[2][0][1].getFaces()[Face.BOTTOM].getColor() == scArray[1][0][1].getFaces()[Face.BOTTOM].getColor();

    }

    public static boolean layerTwo001Done(SmallCube[][][] scArray) {

        System.out.println("001***");
        System.out.println(scArray[0][0][1].getFaces()[Face.LEFT].getColor() == scArray[0][1][1].getFaces()[Face.LEFT].getColor()
                && scArray[0][0][1].getFaces()[Face.BOTTOM].getColor() == scArray[1][0][1].getFaces()[Face.BOTTOM].getColor());

        return scArray[0][0][1].getFaces()[Face.LEFT].getColor() == scArray[0][1][1].getFaces()[Face.LEFT].getColor()
                && scArray[0][0][1].getFaces()[Face.BOTTOM].getColor() == scArray[1][0][1].getFaces()[Face.BOTTOM].getColor();

    }

//these functions check if the cube that i want to change with are not white 
    public static boolean isChangeble221(SmallCube[][][] scArray) {
        return (!layerTwo221Done(scArray)) && scArray[2][2][1].getFaces()[Face.TOP].getColor() != Face.WHITE
                && scArray[2][2][1].getFaces()[Face.RIGHT].getColor() != Face.WHITE;

    }

    public static boolean isChangeble021(SmallCube[][][] scArray) {
        return (!layerTwo021Done(scArray)) && scArray[0][2][1].getFaces()[Face.TOP].getColor() != Face.WHITE
                && scArray[0][2][1].getFaces()[Face.LEFT].getColor() != Face.WHITE;

    }

    public static boolean isChangeble201(SmallCube[][][] scArray) {
        return (!layerTwo201Done(scArray)) && scArray[2][0][1].getFaces()[Face.RIGHT].getColor() != Face.WHITE
                && scArray[2][0][1].getFaces()[Face.BOTTOM].getColor() != Face.WHITE;

    }

    public static boolean isChangeble001(SmallCube[][][] scArray) {
        return (!layerTwo001Done(scArray)) && scArray[0][0][1].getFaces()[Face.LEFT].getColor() != Face.WHITE
                && scArray[0][0][1].getFaces()[Face.BOTTOM].getColor() != Face.WHITE;

    }

    public static boolean isSecondLayerComplete(SmallCube[][][] scArray) {
        return layerTwo001Done(scArray) && layerTwo021Done(scArray) && layerTwo201Done(scArray) && layerTwo221Done(scArray);
    }

    public static ArrayList<LayerRotationData> getSecondLayer(Cube cube) {

        ArrayList<LayerRotationData> moves = new ArrayList<>();
        layerArray[Face.FRONT] = cube.getAxis().getLayerZ(0);
        layerArray[Face.FRONT].setId(Layer.Z);
        layerArray[Face.FRONT].setAxisNum(0);

        layerArray[Face.LEFT] = cube.getAxis().getLayerX(0);
        layerArray[Face.LEFT].setId(Layer.X);
        layerArray[Face.LEFT].setAxisNum(0);

        layerArray[Face.RIGHT] = cube.getAxis().getLayerX(2);
        layerArray[Face.RIGHT].setId(Layer.X);
        layerArray[Face.RIGHT].setAxisNum(2);

        layerArray[Face.TOP] = cube.getAxis().getLayerY(2);
        layerArray[Face.TOP].setId(Layer.Y);
        layerArray[Face.TOP].setAxisNum(2);

        layerArray[Face.BACK] = cube.getAxis().getLayerZ(2);
        layerArray[Face.BACK].setId(Layer.Z);
        layerArray[Face.BACK].setAxisNum(2);

        layerArray[Face.BOTTOM] = cube.getAxis().getLayerY(0);
        layerArray[Face.BOTTOM].setId(Layer.Y);
        layerArray[Face.BOTTOM].setAxisNum(0);
        SmallCube scArray[][][] = cube.getArray();
        SmallCube sCcenter = null;
        SmallCube layerTwoCube = null;
        int moveTo;

        sCcenter = cube.getArray()[1][2][1];
        layerTwoCube = scArray[1][2][0];

        if (isSecondLayerComplete(scArray)) {
            System.err.println("Second Layer Complete");
            return moves;
        }

        if (layerTwoCube.getFaces()[Face.TOP].getColor() != Face.WHITE
                && layerTwoCube.getFaces()[Face.FRONT].getColor() != Face.WHITE) {

            if (layerTwoCube.getFaces()[Face.TOP].getColor() != sCcenter.getFaces()[Face.TOP].getColor()) {
                moveTo = findLayerWithReqestedCenter(scArray, layerTwoCube.getFaces()[Face.TOP].getColor());
                System.out.println("120 LAYER2 BEFORE SWICH");
//                scArray[1][2][0].getFaces()[Face.TOP].startBlink();

                switch (moveTo) {

                    case Face.LEFT:

                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                        System.out.println("120 LEFT");
                        moves.addAll(moveCube010(layerTwoCube, scArray, findLayerWithReqestedCenter(scArray, layerTwoCube.getFaces()[Face.FRONT].getColor())));
                        break;
                    case Face.RIGHT:
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        System.out.println("120 RIGHT");
                        moves.addAll(moveCube210(layerTwoCube, scArray, findLayerWithReqestedCenter(scArray, layerTwoCube.getFaces()[Face.FRONT].getColor())));
                        break;
                    case Face.BOTTOM:
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        System.out.println("120 BOTTOM");
                        moves.addAll(moveCube100(layerTwoCube, scArray, findLayerWithReqestedCenter(scArray, layerTwoCube.getFaces()[Face.FRONT].getColor())));
                        break;
                }
                return moves;
            } else {
                moves.addAll(moveCube120(layerTwoCube, scArray, findLayerWithReqestedCenter(scArray, layerTwoCube.getFaces()[Face.FRONT].getColor())));
                System.out.println("120 ELSE");
                return moves;
            }

        }

        if (layerTwoCube.getFaces()[Face.TOP].getColor() == Face.WHITE
                || layerTwoCube.getFaces()[Face.FRONT].getColor() == Face.WHITE) {

            if (isChangeble221(scArray)) {
                System.out.println("120 isChangeble221 ");
//                scArray[1][2][0].getFaces()[Face.TOP].startBlink();

                moves.addAll(moveCube120(layerTwoCube, scArray, Face.RIGHT));
                return moves;

            } else if (isChangeble021(scArray)) {
                System.out.println("120 isChangeble021 ");
//                scArray[1][2][0].getFaces()[Face.TOP].startBlink();

                moves.addAll(moveCube120(layerTwoCube, scArray, Face.LEFT));
                return moves;

            } else if (isChangeble201(scArray)) {
                System.out.println("120 isChangeble201 ");
//                scArray[1][2][0].getFaces()[Face.TOP].startBlink();

                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                moves.addAll(moveCube210(layerTwoCube, scArray, Face.BOTTOM));
                return moves;

            } else if (isChangeble001(scArray)) {
                System.out.println("120 isChangeble001 ");
//                scArray[1][2][0].getFaces()[Face.TOP].startBlink();

                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                moves.addAll(moveCube010(layerTwoCube, scArray, Face.BOTTOM));
                return moves;

            }
        }

        //********************************************************************//
        sCcenter = cube.getArray()[0][1][1];
        layerTwoCube = scArray[0][1][0];

        if (layerTwoCube.getFaces()[Face.LEFT].getColor() != Face.WHITE
                && layerTwoCube.getFaces()[Face.FRONT].getColor() != Face.WHITE) {

            if (layerTwoCube.getFaces()[Face.LEFT].getColor() != sCcenter.getFaces()[Face.LEFT].getColor()) {
                moveTo = findLayerWithReqestedCenter(scArray, layerTwoCube.getFaces()[Face.LEFT].getColor());
                System.out.println("010 LAYER2 BEFORE SWITCH");
//                scArray[0][1][0].getFaces()[Face.FRONT].startBlink();

                switch (moveTo) {
                    case Face.TOP:
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        System.out.println("010 TOP");
                        moves.addAll(moveCube120(layerTwoCube, scArray, findLayerWithReqestedCenter(scArray, layerTwoCube.getFaces()[Face.FRONT].getColor())));
                        break;
                    case Face.RIGHT:
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        System.out.println("010 RIGHT");
                        moves.addAll(moveCube210(layerTwoCube, scArray, findLayerWithReqestedCenter(scArray, layerTwoCube.getFaces()[Face.FRONT].getColor())));
                        break;
                    case Face.BOTTOM:
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                        System.out.println("010 BOTTOM");
                        moves.addAll(moveCube100(layerTwoCube, scArray, findLayerWithReqestedCenter(scArray, layerTwoCube.getFaces()[Face.FRONT].getColor())));
                        break;
                }
                return moves;
            } else {
                moves.addAll(moveCube010(layerTwoCube, scArray, findLayerWithReqestedCenter(scArray, layerTwoCube.getFaces()[Face.FRONT].getColor())));
                System.out.println("010 ELSE");
                return moves;
            }
        }

        if (layerTwoCube.getFaces()[Face.LEFT].getColor() == Face.WHITE
                || layerTwoCube.getFaces()[Face.FRONT].getColor() == Face.WHITE) {

            if (isChangeble021(scArray)) {
                System.out.println("010 isChangeble021 ");
                moves.addAll(moveCube010(layerTwoCube, scArray, Face.TOP));
                return moves;

            } else if (isChangeble001(scArray)) {
                System.out.println("010 isChangeble001 ");
                moves.addAll(moveCube010(layerTwoCube, scArray, Face.BOTTOM));
                return moves;

            } else if (isChangeble221(scArray)) {
                System.out.println("010 isChangeble221 ");
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                moves.addAll(moveCube120(layerTwoCube, scArray, Face.RIGHT));
                return moves;

            } else if (isChangeble201(scArray)) {
                System.out.println("010 isChangeble201 ");

                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                moves.addAll(moveCube120(layerTwoCube, scArray, Face.LEFT));
                return moves;

            }

        }

        //********************************************************************//
        sCcenter = cube.getArray()[1][0][1];
        layerTwoCube = scArray[1][0][0];

        if (layerTwoCube.getFaces()[Face.BOTTOM].getColor() != Face.WHITE
                && layerTwoCube.getFaces()[Face.FRONT].getColor() != Face.WHITE) {

            if (layerTwoCube.getFaces()[Face.BOTTOM].getColor() != sCcenter.getFaces()[Face.BOTTOM].getColor()) {
                moveTo = findLayerWithReqestedCenter(scArray, layerTwoCube.getFaces()[Face.BOTTOM].getColor());
                System.out.println("100 BEFORE SWITCH ");
//                scArray[1][0][0].getFaces()[Face.FRONT].startBlink();

                switch (moveTo) {

                    case Face.TOP:
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        moves.addAll(moveCube120(layerTwoCube, scArray, findLayerWithReqestedCenter(scArray, layerTwoCube.getFaces()[Face.FRONT].getColor())));
                        System.out.println("100 TOP ");

                        break;
                    case Face.RIGHT:
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                        System.out.println("100 RIGHT ");
                        moves.addAll(moveCube210(layerTwoCube, scArray, findLayerWithReqestedCenter(scArray, layerTwoCube.getFaces()[Face.FRONT].getColor())));

                        break;

                    case Face.LEFT:
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        System.out.println("100 LEFT ");
                        moves.addAll(moveCube010(layerTwoCube, scArray, findLayerWithReqestedCenter(scArray, layerTwoCube.getFaces()[Face.FRONT].getColor())));

                        break;

                }

                return moves;
            } else {
                System.out.println("100 ELSE ");

                moves.addAll(moveCube100(layerTwoCube, scArray, findLayerWithReqestedCenter(scArray, layerTwoCube.getFaces()[Face.FRONT].getColor())));
                return moves;

            }
        }

        if (layerTwoCube.getFaces()[Face.BOTTOM].getColor() == Face.WHITE
                || layerTwoCube.getFaces()[Face.FRONT].getColor() == Face.WHITE) {

            if (isChangeble201(scArray)) {
                System.out.println("100 isChangeble201 ");

                moves.addAll(moveCube100(layerTwoCube, scArray, Face.RIGHT));
                return moves;

            } else if (isChangeble001(scArray)) {
                System.out.println("100 isChangeble001 ");

                moves.addAll(moveCube100(layerTwoCube, scArray, Face.LEFT));
                return moves;

            } else if (isChangeble021(scArray)) {
                System.out.println("100 isChangeble021 ");

                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                moves.addAll(moveCube120(layerTwoCube, scArray, Face.LEFT));
                return moves;

            } else if (isChangeble221(scArray)) {
                System.out.println("100 isChangeble221 ");

                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                moves.addAll(moveCube120(layerTwoCube, scArray, Face.RIGHT));
                return moves;

            }

        }

        //********************************************************************//
        sCcenter = cube.getArray()[2][1][1];
        layerTwoCube = scArray[2][1][0];

        if (layerTwoCube.getFaces()[Face.RIGHT].getColor() != Face.WHITE
                && layerTwoCube.getFaces()[Face.FRONT].getColor() != Face.WHITE) {

            if (layerTwoCube.getFaces()[Face.RIGHT].getColor() != sCcenter.getFaces()[Face.RIGHT].getColor()) {
                moveTo = findLayerWithReqestedCenter(scArray, layerTwoCube.getFaces()[Face.RIGHT].getColor());
                System.out.println("210 BEFORE SWITCH ");
//                scArray[2][1][0].getFaces()[Face.FRONT].startBlink();

                switch (moveTo) {

                    case Face.TOP:
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                        System.out.println("210 TOP ");

                        moves.addAll(moveCube120(layerTwoCube, scArray, findLayerWithReqestedCenter(scArray, layerTwoCube.getFaces()[Face.FRONT].getColor())));

                        break;
                    case Face.BOTTOM:
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        System.out.println("210 BOTTOM ");
                        moves.addAll(moveCube100(layerTwoCube, scArray, findLayerWithReqestedCenter(scArray, layerTwoCube.getFaces()[Face.FRONT].getColor())));

                        break;

                    case Face.LEFT:
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                        System.out.println("210 LEFT ");
                        moves.addAll(moveCube010(layerTwoCube, scArray, findLayerWithReqestedCenter(scArray, layerTwoCube.getFaces()[Face.FRONT].getColor())));

                        break;
                }
                return moves;
            } else {
                System.out.println("210 ELSE ");
                moves.addAll(moveCube210(layerTwoCube, scArray, findLayerWithReqestedCenter(scArray, layerTwoCube.getFaces()[Face.FRONT].getColor())));
                return moves;
            }
        }
        if (layerTwoCube.getFaces()[Face.RIGHT].getColor() == Face.WHITE
                || layerTwoCube.getFaces()[Face.FRONT].getColor() == Face.WHITE) {

            if (isChangeble201(scArray)) {
                System.out.println("210 isChangeble201 ");

                moves.addAll(moveCube210(layerTwoCube, scArray, Face.BOTTOM));
                return moves;

            } else if (isChangeble221(scArray)) {
                System.out.println("210 isChangeble221 ");

                moves.addAll(moveCube210(layerTwoCube, scArray, Face.TOP));
                return moves;

            } else if (isChangeble021(scArray)) {
                System.out.println("210 isChangeble021 ");

                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                moves.addAll(moveCube120(layerTwoCube, scArray, Face.RIGHT));
                return moves;

            } else if (isChangeble001(scArray)) {
                System.out.println("210 isChangeble001 ");

                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                moves.addAll(moveCube120(layerTwoCube, scArray, Face.LEFT));
                return moves;

            }
        }

        return moves;
    }

    public static ArrayList<LayerRotationData> moveCube120(SmallCube sc, SmallCube scArray[][][], int moveTo) {

        ArrayList<LayerRotationData> moves = new ArrayList<>();
        switch (moveTo) {

            case Face.LEFT:
                System.out.println("LEFT120 ");

                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                System.out.println("FIX YELLOW LEFT120 ");
                // FIX YELLOW LEFT
                moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));

                break;

            case Face.RIGHT:
                System.out.println("RIGHT120 ");
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                System.out.println("FIX YELLOW RIGHT 120");

                // FIX YELLOW RIGHT 
                moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                break;
        }
        return moves;
    }

    public static ArrayList<LayerRotationData> moveCube010(SmallCube sc, SmallCube scArray[][][], int moveTo) {

        ArrayList<LayerRotationData> moves = new ArrayList<>();

        switch (moveTo) {

//to the left
            case Face.BOTTOM:
                System.out.println("BOTTOM010 ");

                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                System.out.println("FIX YELLOW BOTTOM010 ");
                // FIX YELLOW LEFT
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                break;
//to the right
            case Face.TOP:
                System.out.println("to the TOP010 ");
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                System.out.println("FIX YELLOW  010");

                // FIX YELLOW RIGHT 
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                break;
        }
        return moves;
    }

    public static ArrayList<LayerRotationData> moveCube210(SmallCube sc, SmallCube scArray[][][], int moveTo) {

        ArrayList<LayerRotationData> moves = new ArrayList<>();
        switch (moveTo) {

//to the RIGHT
            case Face.BOTTOM:
                System.out.println("BOTTOM210 ");

                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                System.out.println("FIX YELLOW BOTTOM210 ");
                // FIX YELLOW LEFT
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                break;
//to the LEFT
            case Face.TOP:
                System.out.println("to the TOP210 ");
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                System.out.println("FIX YELLOW  210");

                // FIX YELLOW RIGHT 
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
                break;
        }
        return moves;
    }

    public static ArrayList<LayerRotationData> moveCube100(SmallCube sc, SmallCube scArray[][][], int moveTo) {

        ArrayList<LayerRotationData> moves = new ArrayList<>();
        switch (moveTo) {

            case Face.LEFT:
                System.out.println("LEFT100 ");

                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                System.out.println("FIX YELLOW LEFT100 ");
                // FIX YELLOW LEFT
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));

                break;

            case Face.RIGHT:
                System.out.println("RIGHT100 ");
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                System.out.println("FIX YELLOW RIGHT 100");

                // FIX YELLOW RIGHT 
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
                moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
                break;
        }
        return moves;
    }

    //*************second layer******************//
    //************whitePlus**********// 
    //rotate the cube in a way white will be on top
    public static boolean isCubeWhite122(SmallCube[][][] scArray) {
        return scArray[1][2][2].getFaces()[Face.TOP].getColor() == Face.WHITE;
    }

    public static boolean isCubeWhite221(SmallCube[][][] scArray) {
        return scArray[2][2][1].getFaces()[Face.TOP].getColor() == Face.WHITE;
    }

    public static boolean isCubeWhite120(SmallCube[][][] scArray) {
        return scArray[1][2][0].getFaces()[Face.TOP].getColor() == Face.WHITE;
    }

    public static boolean isCubeWhite021(SmallCube[][][] scArray) {
        return scArray[0][2][1].getFaces()[Face.TOP].getColor() == Face.WHITE;
    }

    public static boolean isWhitePlusComplete(SmallCube[][][] scArray) {
        return isCubeWhite021(scArray) && isCubeWhite120(scArray) && isCubeWhite221(scArray) && isCubeWhite122(scArray);

    }

    public static ArrayList<LayerRotationData> getWhitePlus(Cube cube) {
        ArrayList<LayerRotationData> moves = new ArrayList<>();
        layerArray[Face.FRONT] = cube.getAxis().getLayerZ(0);
        layerArray[Face.FRONT].setId(Layer.Z);
        layerArray[Face.FRONT].setAxisNum(0);

        layerArray[Face.LEFT] = cube.getAxis().getLayerX(0);
        layerArray[Face.LEFT].setId(Layer.X);
        layerArray[Face.LEFT].setAxisNum(0);

        layerArray[Face.RIGHT] = cube.getAxis().getLayerX(2);
        layerArray[Face.RIGHT].setId(Layer.X);
        layerArray[Face.RIGHT].setAxisNum(2);

        layerArray[Face.TOP] = cube.getAxis().getLayerY(2);
        layerArray[Face.TOP].setId(Layer.Y);
        layerArray[Face.TOP].setAxisNum(2);

        layerArray[Face.BACK] = cube.getAxis().getLayerZ(2);
        layerArray[Face.BACK].setId(Layer.Z);
        layerArray[Face.BACK].setAxisNum(2);

        layerArray[Face.BOTTOM] = cube.getAxis().getLayerY(0);
        layerArray[Face.BOTTOM].setId(Layer.Y);
        layerArray[Face.BOTTOM].setAxisNum(0);
        SmallCube scArray[][][] = cube.getArray();
        SmallCube whitePlusCube = null;

        if (isWhitePlusComplete(scArray)) {
            return moves;
        }

// one center white cube or yellow L to the right front or row parallel to the front
        if (!isCubeWhite021(scArray) && !isCubeWhite120(scArray) && !isCubeWhite221(scArray) && !isCubeWhite122(scArray)
                || isCubeWhite120(scArray) && isCubeWhite221(scArray)
                || isCubeWhite021(scArray) && isCubeWhite221(scArray)) {

            System.out.println("WHITE PLUS IF1 ");
            moves.addAll(moveWhitePlusCubeBasic(whitePlusCube, scArray));
            return moves;
        } //yellow L to the left-front or row parallel to the front 
        else if (isCubeWhite021(scArray) && isCubeWhite120(scArray) || isCubeWhite021(scArray) && isCubeWhite221(scArray)) {
            System.out.println("WHITE PLUS IF2 ");

            moves.addAll(LeftFrontL(whitePlusCube, scArray));
            return moves;
        } //yellow L to the left-back or row parallel to the left 
        else if (isCubeWhite021(scArray) && isCubeWhite122(scArray)) {
            System.out.println("WHITE PLUS IF3 ");

            moves.addAll(backLeftL(whitePlusCube, scArray));
            return moves;
        } //yellow L to the right-back 
        else if (isCubeWhite122(scArray) && isCubeWhite221(scArray) || isCubeWhite120(scArray) && isCubeWhite122(scArray)) {
            System.out.println("WHITE PLUS IF4 ");

            moves.addAll(backRightL(whitePlusCube, scArray));
            return moves;
        }
        return moves;
    }

    public static ArrayList<LayerRotationData> moveWhitePlusCubeBasic(SmallCube sc, SmallCube scArray[][][]) {

        ArrayList<LayerRotationData> moves = new ArrayList<>();
        System.out.println("WHITE PLUS moveWhitePlusCubeBasic ");

        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
        return moves;
    }

    public static ArrayList<LayerRotationData> LeftFrontL(SmallCube sc, SmallCube scArray[][][]) {
        System.out.println("WHITE PLUS LeftFrontL ");
        ArrayList<LayerRotationData> moves = new ArrayList<>();
        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.CLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.FRONT], LayerRotationData.COUNTERCLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
        return moves;

    }

    public static ArrayList<LayerRotationData> backLeftL(SmallCube sc, SmallCube scArray[][][]) {
        System.out.println("WHITE PLUS backLeftL ");

        ArrayList<LayerRotationData> moves = new ArrayList<>();
        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.CLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.LEFT], LayerRotationData.COUNTERCLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
        return moves;

    }

    public static ArrayList<LayerRotationData> backRightL(SmallCube sc, SmallCube scArray[][][]) {
        System.out.println("WHITE PLUS backRightL ");

        ArrayList<LayerRotationData> moves = new ArrayList<>();
        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.CLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.BACK], LayerRotationData.COUNTERCLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
        return moves;
    }

    //************whitePlus**********// 
    //************FinalLayerStage1IsComplete*******************//
// replacing the middle cubes to the right centers
    public static boolean isCubeWhite021InTheRightMiddle(SmallCube[][][] scArray) {
        return scArray[0][2][1].getFaces()[Face.LEFT].getColor() == scArray[0][1][1].getFaces()[Face.LEFT].getColor();
    }

    public static boolean isCubeWhite120InTheRightMiddle(SmallCube[][][] scArray) {
        return scArray[1][2][0].getFaces()[Face.FRONT].getColor() == scArray[1][1][0].getFaces()[Face.FRONT].getColor();
    }

    public static boolean isCubeWhite221InTheRightMiddle(SmallCube[][][] scArray) {
        return scArray[2][2][1].getFaces()[Face.RIGHT].getColor() == scArray[2][1][1].getFaces()[Face.RIGHT].getColor();
    }

    public static boolean isCubeWhite122InTheRightMiddle(SmallCube[][][] scArray) {
        return scArray[1][2][2].getFaces()[Face.BACK].getColor() == scArray[1][1][2].getFaces()[Face.BACK].getColor();
    }

    public static boolean isFinalLayerStage1IsComplete(SmallCube[][][] scArray) {
        return isCubeWhite122InTheRightMiddle(scArray) && isCubeWhite221InTheRightMiddle(scArray)
                && isCubeWhite120InTheRightMiddle(scArray) && isCubeWhite021InTheRightMiddle(scArray);
    }

    public static ArrayList<LayerRotationData> whiteEdges(Cube cube) {

        ArrayList<LayerRotationData> moves = new ArrayList<>();
        layerArray[Face.FRONT] = cube.getAxis().getLayerZ(0);
        layerArray[Face.FRONT].setId(Layer.Z);
        layerArray[Face.FRONT].setAxisNum(0);

        layerArray[Face.LEFT] = cube.getAxis().getLayerX(0);
        layerArray[Face.LEFT].setId(Layer.X);
        layerArray[Face.LEFT].setAxisNum(0);

        layerArray[Face.RIGHT] = cube.getAxis().getLayerX(2);
        layerArray[Face.RIGHT].setId(Layer.X);
        layerArray[Face.RIGHT].setAxisNum(2);

        layerArray[Face.TOP] = cube.getAxis().getLayerY(2);
        layerArray[Face.TOP].setId(Layer.Y);
        layerArray[Face.TOP].setAxisNum(2);

        layerArray[Face.BACK] = cube.getAxis().getLayerZ(2);
        layerArray[Face.BACK].setId(Layer.Z);
        layerArray[Face.BACK].setAxisNum(2);

        layerArray[Face.BOTTOM] = cube.getAxis().getLayerY(0);
        layerArray[Face.BOTTOM].setId(Layer.Y);
        layerArray[Face.BOTTOM].setAxisNum(0);
        SmallCube scArray[][][] = cube.getArray();
        int layer;

        if (isFinalLayerStage1IsComplete(scArray)) {
            return moves;
        }

        if (isCubeWhite122InTheRightMiddle(scArray) && isCubeWhite221InTheRightMiddle(scArray)
                || isCubeWhite021InTheRightMiddle(scArray) && isCubeWhite221InTheRightMiddle(scArray)) {
            System.out.println("whiteEdges if1");
            layer = Face.RIGHT;
            moves.addAll(whiteEdgesMoves(layer, scArray));

        } else if (isCubeWhite120InTheRightMiddle(scArray) && isCubeWhite221InTheRightMiddle(scArray)
                || isCubeWhite120InTheRightMiddle(scArray) && isCubeWhite122InTheRightMiddle(scArray)) {
            System.out.println("whiteEdges if2");

            layer = Face.FRONT;
            moves.addAll(whiteEdgesMoves(layer, scArray));

        } else if (isCubeWhite120InTheRightMiddle(scArray) && isCubeWhite021InTheRightMiddle(scArray)
                || isCubeWhite120InTheRightMiddle(scArray) && isCubeWhite122InTheRightMiddle(scArray)) {
            System.out.println("whiteEdges if3");

            layer = Face.LEFT;
            moves.addAll(whiteEdgesMoves(layer, scArray));

        } else if (isCubeWhite021InTheRightMiddle(scArray) && isCubeWhite122InTheRightMiddle(scArray)
                || isCubeWhite021InTheRightMiddle(scArray) && isCubeWhite221InTheRightMiddle(scArray)) {
            System.out.println("whiteEdges if4");

            layer = Face.BACK;
            moves.addAll(whiteEdgesMoves(layer, scArray));

        } else {
            System.out.println("whiteEdges else");

            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
            return moves;
        }
        moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
        System.out.println("whiteEdges 60");

        return moves;
    }

    public static ArrayList<LayerRotationData> whiteEdgesMoves(int layer, SmallCube scArray[][][]) {
        System.out.println("whiteEdges moves");

        ArrayList<LayerRotationData> moves = new ArrayList<>();

        moves.add(new LayerRotationData(layerArray[layer], LayerRotationData.CLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
        moves.add(new LayerRotationData(layerArray[layer], LayerRotationData.COUNTERCLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
        moves.add(new LayerRotationData(layerArray[layer], LayerRotationData.CLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
        moves.add(new LayerRotationData(layerArray[layer], LayerRotationData.COUNTERCLOCKWISE));

        return moves;
    }
	//************FinalLayerStage1IsComplete*******************//

    //************FinalLayerStage2IsComplete*******************//
    public static boolean isWhiteCube020InTheRightPosition(SmallCube[][][] scArray) {
        return (scArray[0][2][0].getFaces()[Face.TOP].getColor() == scArray[1][1][0].getFaces()[Face.FRONT].getColor()
                || scArray[0][2][0].getFaces()[Face.FRONT].getColor() == scArray[1][1][0].getFaces()[Face.FRONT].getColor()
                || scArray[0][2][0].getFaces()[Face.LEFT].getColor() == scArray[1][1][0].getFaces()[Face.FRONT].getColor())
                && (scArray[0][2][0].getFaces()[Face.TOP].getColor() == scArray[0][1][1].getFaces()[Face.LEFT].getColor()
                || scArray[0][2][0].getFaces()[Face.FRONT].getColor() == scArray[0][1][1].getFaces()[Face.LEFT].getColor()
                || scArray[0][2][0].getFaces()[Face.LEFT].getColor() == scArray[0][1][1].getFaces()[Face.LEFT].getColor());
    }

    public static boolean isWhiteCube220InTheRightPosition(SmallCube[][][] scArray) {
        return (scArray[2][2][0].getFaces()[Face.TOP].getColor() == scArray[1][1][0].getFaces()[Face.FRONT].getColor()
                || scArray[2][2][0].getFaces()[Face.FRONT].getColor() == scArray[1][1][0].getFaces()[Face.FRONT].getColor()
                || scArray[2][2][0].getFaces()[Face.RIGHT].getColor() == scArray[1][1][0].getFaces()[Face.FRONT].getColor())
                && (scArray[2][2][0].getFaces()[Face.TOP].getColor() == scArray[2][1][1].getFaces()[Face.RIGHT].getColor()
                || scArray[2][2][0].getFaces()[Face.FRONT].getColor() == scArray[2][1][1].getFaces()[Face.RIGHT].getColor()
                || scArray[2][2][0].getFaces()[Face.RIGHT].getColor() == scArray[2][1][1].getFaces()[Face.RIGHT].getColor());
    }

    public static boolean isWhiteCube222InTheRightPosition(SmallCube[][][] scArray) {
        return (scArray[2][2][2].getFaces()[Face.TOP].getColor() == scArray[2][1][1].getFaces()[Face.RIGHT].getColor()
                || scArray[2][2][2].getFaces()[Face.RIGHT].getColor() == scArray[2][1][1].getFaces()[Face.RIGHT].getColor()
                || scArray[2][2][2].getFaces()[Face.BACK].getColor() == scArray[2][1][1].getFaces()[Face.RIGHT].getColor())
                && (scArray[2][2][2].getFaces()[Face.TOP].getColor() == scArray[1][1][2].getFaces()[Face.BACK].getColor()
                || scArray[2][2][2].getFaces()[Face.RIGHT].getColor() == scArray[1][1][2].getFaces()[Face.BACK].getColor()
                || scArray[2][2][2].getFaces()[Face.BACK].getColor() == scArray[1][1][2].getFaces()[Face.BACK].getColor());
    }

    public static boolean isWhiteCube022InTheRightPosition(SmallCube[][][] scArray) {
        return (scArray[0][2][2].getFaces()[Face.TOP].getColor() == scArray[1][1][2].getFaces()[Face.BACK].getColor()
                || scArray[0][2][2].getFaces()[Face.LEFT].getColor() == scArray[1][1][2].getFaces()[Face.BACK].getColor()
                || scArray[0][2][2].getFaces()[Face.BACK].getColor() == scArray[1][1][2].getFaces()[Face.BACK].getColor())
                && (scArray[0][2][2].getFaces()[Face.TOP].getColor() == scArray[0][1][1].getFaces()[Face.LEFT].getColor()
                || scArray[0][2][2].getFaces()[Face.LEFT].getColor() == scArray[0][1][1].getFaces()[Face.LEFT].getColor()
                || scArray[0][2][2].getFaces()[Face.BACK].getColor() == scArray[0][1][1].getFaces()[Face.LEFT].getColor());
    }

    public static ArrayList<LayerRotationData> positionCorners(Cube cube) {

        ArrayList<LayerRotationData> moves = new ArrayList<>();
        layerArray[Face.FRONT] = cube.getAxis().getLayerZ(0);
        layerArray[Face.FRONT].setId(Layer.Z);
        layerArray[Face.FRONT].setAxisNum(0);

        layerArray[Face.LEFT] = cube.getAxis().getLayerX(0);
        layerArray[Face.LEFT].setId(Layer.X);
        layerArray[Face.LEFT].setAxisNum(0);

        layerArray[Face.RIGHT] = cube.getAxis().getLayerX(2);
        layerArray[Face.RIGHT].setId(Layer.X);
        layerArray[Face.RIGHT].setAxisNum(2);

        layerArray[Face.TOP] = cube.getAxis().getLayerY(2);
        layerArray[Face.TOP].setId(Layer.Y);
        layerArray[Face.TOP].setAxisNum(2);

        layerArray[Face.BACK] = cube.getAxis().getLayerZ(2);
        layerArray[Face.BACK].setId(Layer.Z);
        layerArray[Face.BACK].setAxisNum(2);

        layerArray[Face.BOTTOM] = cube.getAxis().getLayerY(0);
        layerArray[Face.BOTTOM].setId(Layer.Y);
        layerArray[Face.BOTTOM].setAxisNum(0);
        SmallCube scArray[][][] = cube.getArray();
        int layer1 = 0;
        int layer2 = 0;
        if (isWhiteCube022InTheRightPosition(scArray) && isWhiteCube222InTheRightPosition(scArray)
                && isWhiteCube220InTheRightPosition(scArray) && isWhiteCube020InTheRightPosition(scArray)) {
            System.err.println("positionCorners DONE!");

            return moves;
        }

        if (!isWhiteCube022InTheRightPosition(scArray) && !isWhiteCube222InTheRightPosition(scArray)
                && !isWhiteCube220InTheRightPosition(scArray) && !isWhiteCube020InTheRightPosition(scArray)) {
            System.out.println("positionCorners1");
//            scArray[0][2][2].getFaces()[Face.TOP].starftBlink();

            layer1 = Face.RIGHT;
            layer2 = Face.LEFT;
            moves.addAll(positionCornersMoves(layer1, layer2, scArray));
            return moves;

        } else if (isWhiteCube020InTheRightPosition(scArray)) {
            System.out.println("positionCorners2");
//            scArray[0][2][0].getFaces()[Face.TOP].starftBlink();

            layer1 = Face.FRONT;
            layer2 = Face.BACK;
            moves.addAll(positionCornersMoves(layer1, layer2, scArray));
            return moves;

        } else if (isWhiteCube220InTheRightPosition(scArray)) {
            System.out.println("positionCorners3");
//            scArray[2][2][0].getFaces()[Face.TOP].starftBlink();

            layer1 = Face.RIGHT;
            layer2 = Face.LEFT;
            moves.addAll(positionCornersMoves(layer1, layer2, scArray));
            return moves;

        } else if (isWhiteCube222InTheRightPosition(scArray)) {
            System.out.println("positionCorners4");
//            scArray[2][2][2].getFaces()[Face.TOP].starftBlink();

            layer1 = Face.BACK;
            layer2 = Face.FRONT;
            moves.addAll(positionCornersMoves(layer1, layer2, scArray));
            return moves;

        } else if (isWhiteCube022InTheRightPosition(scArray)) {
            System.out.println("positionCorners5");
//            scArray[0][2][2].getFaces()[Face.TOP].starftBlink();

            layer1 = Face.LEFT;
            layer2 = Face.RIGHT;
            moves.addAll(positionCornersMoves(layer1, layer2, scArray));
            return moves;

        }
        System.out.println("positionCorners6");

        return moves;
    }

    public static ArrayList<LayerRotationData> positionCornersMoves(int layer1, int layer2, SmallCube scArray[][][]) {
        ArrayList<LayerRotationData> moves = new ArrayList<>();
        System.out.println("positionCornersMoves");
        moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
        moves.add(new LayerRotationData(layerArray[layer1], LayerRotationData.CLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));
        moves.add(new LayerRotationData(layerArray[layer2], LayerRotationData.COUNTERCLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
        moves.add(new LayerRotationData(layerArray[layer1], LayerRotationData.COUNTERCLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));
        moves.add(new LayerRotationData(layerArray[layer2], LayerRotationData.CLOCKWISE));

        return moves;
    }

    //************FinalLayerStage2IsComplete*******************//
    //************FinalLayerStage3IsComplete*******************//
    public static boolean topWhite022(SmallCube[][][] scArray) {
        return scArray[0][2][2].getFaces()[Face.TOP].getColor() == Face.WHITE
                && scArray[0][2][2].getFaces()[Face.LEFT].getColor() == scArray[0][1][1].getFaces()[Face.LEFT].getColor()
                && scArray[0][2][2].getFaces()[Face.BACK].getColor() == scArray[1][1][2].getFaces()[Face.BACK].getColor();
    }

    public static boolean topWhite220(SmallCube[][][] scArray) {

        return scArray[2][2][0].getFaces()[Face.TOP].getColor() == Face.WHITE
                && scArray[2][2][0].getFaces()[Face.FRONT].getColor() == scArray[1][1][0].getFaces()[Face.FRONT].getColor()
                && scArray[2][2][0].getFaces()[Face.RIGHT].getColor() == scArray[2][1][1].getFaces()[Face.RIGHT].getColor();
    }

    public static boolean topWhite222(SmallCube[][][] scArray) {
        return scArray[2][2][2].getFaces()[Face.TOP].getColor() == Face.WHITE
                && scArray[2][2][2].getFaces()[Face.RIGHT].getColor() == scArray[2][1][1].getFaces()[Face.RIGHT].getColor()
                && scArray[2][2][2].getFaces()[Face.BACK].getColor() == scArray[1][1][2].getFaces()[Face.BACK].getColor();
    }

    public static boolean topWhite020(SmallCube[][][] scArray) {

        return scArray[0][2][0].getFaces()[Face.TOP].getColor() == Face.WHITE
                && scArray[0][2][0].getFaces()[Face.FRONT].getColor() == scArray[1][1][0].getFaces()[Face.FRONT].getColor()
                && scArray[0][2][0].getFaces()[Face.LEFT].getColor() == scArray[0][1][1].getFaces()[Face.LEFT].getColor();
    }

    public static boolean isWhiteLayerComplete(SmallCube scArray[][][]) {
        if (topWhite020(scArray) && topWhite222(scArray) && topWhite220(scArray) && topWhite022(scArray)) {
            System.err.println("all White edges done!");
            return true;
        }
        return false;
    }

    ////////////////////////////////////////////////////////////////////////////
    public static ArrayList<LayerRotationData> flip220(SmallCube scArray[][][]) {
        ArrayList<LayerRotationData> moves = new ArrayList<>();
        System.out.println("!topWhite220 1");
        if (scArray[2][2][0].getFaces()[Face.FRONT].getColor() == Face.WHITE) {

            System.out.println("2");
            moves.addAll(flipCornersMoves());
            System.out.println("3");

            moves.addAll(flipCornersMoves());
        }
        if (scArray[2][2][0].getFaces()[Face.RIGHT].getColor() == Face.WHITE) {
            System.out.println("4");

            moves.addAll(flipCornersMoves());

        }
        return moves;

    }

    public static ArrayList<LayerRotationData> flip222(SmallCube scArray[][][]) {
        ArrayList<LayerRotationData> moves = new ArrayList<>();
        System.out.println("!topWhite222(scArray) 12");
        if (scArray[2][2][2].getFaces()[Face.RIGHT].getColor() == Face.WHITE) {
            System.out.println("13");

            moves.addAll(flipCornersMoves());
            moves.addAll(flipCornersMoves());

        }
        if (scArray[2][2][2].getFaces()[Face.BACK].getColor() == Face.WHITE) {
            System.out.println("14");

            moves.addAll(flipCornersMoves());

        }
        return moves;

    }

    public static ArrayList<LayerRotationData> flip022(SmallCube scArray[][][]) {
        ArrayList<LayerRotationData> moves = new ArrayList<>();
        System.out.println("!topWhite022(scArray) 8");

        if (scArray[0][2][2].getFaces()[Face.BACK].getColor() == Face.WHITE) {
            System.out.println("10");

            moves.addAll(flipCornersMoves());
            moves.addAll(flipCornersMoves());

        }
        if (scArray[0][2][2].getFaces()[Face.LEFT].getColor() == Face.WHITE) {
            System.out.println("11");

            moves.addAll(flipCornersMoves());

        }
        return moves;

    }

    public static ArrayList<LayerRotationData> flip020(SmallCube scArray[][][]) {
        ArrayList<LayerRotationData> moves = new ArrayList<>();
        if (scArray[0][2][0].getFaces()[Face.LEFT].getColor() == Face.WHITE) {
            System.out.println("6");

            moves.addAll(flipCornersMoves());
            moves.addAll(flipCornersMoves());

        }
        if (scArray[0][2][0].getFaces()[Face.FRONT].getColor() == Face.WHITE) {
            System.out.println("7");

            moves.addAll(flipCornersMoves());

        }
        return moves;

    }

    public static ArrayList<LayerRotationData> flipCorners(Cube cube) {

        ArrayList<LayerRotationData> moves = new ArrayList<>();
        layerArray[Face.FRONT] = cube.getAxis().getLayerZ(0);
        layerArray[Face.FRONT].setId(Layer.Z);
        layerArray[Face.FRONT].setAxisNum(0);

        layerArray[Face.LEFT] = cube.getAxis().getLayerX(0);
        layerArray[Face.LEFT].setId(Layer.X);
        layerArray[Face.LEFT].setAxisNum(0);

        layerArray[Face.RIGHT] = cube.getAxis().getLayerX(2);
        layerArray[Face.RIGHT].setId(Layer.X);
        layerArray[Face.RIGHT].setAxisNum(2);

        layerArray[Face.TOP] = cube.getAxis().getLayerY(2);
        layerArray[Face.TOP].setId(Layer.Y);
        layerArray[Face.TOP].setAxisNum(2);

        layerArray[Face.BACK] = cube.getAxis().getLayerZ(2);
        layerArray[Face.BACK].setId(Layer.Z);
        layerArray[Face.BACK].setAxisNum(2);

        layerArray[Face.BOTTOM] = cube.getAxis().getLayerY(0);
        layerArray[Face.BOTTOM].setId(Layer.Y);
        layerArray[Face.BOTTOM].setAxisNum(0);
        SmallCube scArray[][][] = cube.getArray();
        if (isWhiteLayerComplete(scArray)) {
            System.err.println("done!!");
        }

        if (!topWhite020(scArray) && !topWhite022(scArray) && !topWhite220(scArray) && !topWhite222(scArray)) {

            moves.addAll(flip220(scArray));
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));

            moves.addAll(flip020(scArray));
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));

            moves.addAll(flip022(scArray));
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));

            moves.addAll(flip222(scArray));
            System.err.println("111");

            return moves;

        }

        if (!topWhite020(scArray) && !topWhite022(scArray) && !topWhite220(scArray)) {
            moves.addAll(flip220(scArray));
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));

            moves.addAll(flip020(scArray));
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));

            moves.addAll(flip022(scArray));
            System.err.println("222");

            return moves;
        }

        if (!topWhite022(scArray) && !topWhite220(scArray) && !topWhite222(scArray)) {

            moves.addAll(flip220(scArray));
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
            moves.addAll(flip222(scArray));
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
            moves.addAll(flip022(scArray));
            System.err.println("333");

            return moves;

        }

        if (!topWhite020(scArray) && !topWhite022(scArray) && !topWhite222(scArray)) {
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));

            moves.addAll(flip020(scArray));
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));
            moves.addAll(flip022(scArray));
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));
            moves.addAll(flip222(scArray));
            System.err.println("3333542");

            return moves;

        }

        if (!topWhite020(scArray) && !topWhite220(scArray) && !topWhite222(scArray)) {

            moves.addAll(flip220(scArray));
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
            moves.addAll(flip222(scArray));
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));

            moves.addAll(flip022(scArray));
            System.err.println("33345");

            return moves;

        }

        //////////////////////////33333333333333/////////////////
        if (!topWhite020(scArray) && !topWhite222(scArray)) {
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
            moves.addAll(flip222(scArray));
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));

            moves.addAll(flip020(scArray));
            System.err.println("444");

            return moves;
        }

        if (!topWhite220(scArray) && !topWhite022(scArray)) {
            moves.addAll(flip220(scArray));
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
            moves.addAll(flip022(scArray));
            System.err.println("555");

            return moves;
        }
        ////////////////////////
        if (!topWhite220(scArray) && !topWhite020(scArray)) {
            moves.addAll(flip220(scArray));
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));
            moves.addAll(flip020(scArray));
            System.err.println("5554");

            return moves;
        }

        if (!topWhite220(scArray) && !topWhite222(scArray)) {
            moves.addAll(flip220(scArray));
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
            moves.addAll(flip222(scArray));
            System.err.println("5551");

            return moves;
        }

        if (!topWhite022(scArray) && !topWhite020(scArray)) {
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));

            moves.addAll(flip020(scArray));
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));
            moves.addAll(flip022(scArray));
            System.err.println("5552");

            return moves;
        }

        if (!topWhite022(scArray) && !topWhite222(scArray)) {
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));

            moves.addAll(flip222(scArray));
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));
            moves.addAll(flip022(scArray));
            System.err.println("5553");

            return moves;
        }
//////////////////////////////
        if (!topWhite020(scArray)) {
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));
            moves.addAll(flip020(scArray));
            System.err.println("666");

            return moves;

        }
        if (!topWhite022(scArray)) {
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.COUNTERCLOCKWISE));

            moves.addAll(flip022(scArray));
            System.err.println("777");

            return moves;

        }
        if (!topWhite220(scArray)) {

            moves.addAll(flip220(scArray));
            System.err.println("888");

            return moves;

        }

        if (!topWhite222(scArray)) {
            moves.add(new LayerRotationData(layerArray[Face.TOP], LayerRotationData.CLOCKWISE));

            moves.addAll(flip222(scArray));
            System.err.println("999");

            return moves;

        }

        return moves;
    }

    public static ArrayList<LayerRotationData> flipCornersMoves() {
        ArrayList<LayerRotationData> moves = new ArrayList<>();
        System.out.println("flipCornersMoves");
        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));

        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.COUNTERCLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.COUNTERCLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.RIGHT], LayerRotationData.CLOCKWISE));
        moves.add(new LayerRotationData(layerArray[Face.BOTTOM], LayerRotationData.CLOCKWISE));
        return moves;
    }

    //************FinalLayerStage3IsComplete*******************//
    public static final boolean CheckLayers(Cube c) {

        Layer front = c.getAxis().getLayerZ(0);
        front.setFace(Face.FRONT);
        Layer back = c.getAxis().getLayerZ(2);
        back.setFace(Face.BACK);
        Layer top = c.getAxis().getLayerY(2);
        top.setFace(Face.TOP);
        Layer bottom = c.getAxis().getLayerY(0);
        bottom.setFace(Face.BOTTOM);
        Layer left = c.getAxis().getLayerX(0);
        left.setFace(Face.LEFT);
        Layer right = c.getAxis().getLayerX(2);
        right.setFace(Face.RIGHT);

        MyThread m1 = new MyThread(front);
        MyThread m2 = new MyThread(back);
        MyThread m3 = new MyThread(top);
        MyThread m4 = new MyThread(bottom);
        MyThread m5 = new MyThread(left);
        MyThread m6 = new MyThread(right);

        m1.start();
        m2.start();
        m3.start();
        m4.start();
        m5.start();
        m6.start();

        try {
            m1.join();
            m2.join();
            m3.join();
            m4.join();
            m5.join();
            m6.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
//        System.out.println(m1.isLayerComplete() && m2.isLayerComplete() && m3.isLayerComplete() && m4.isLayerComplete() && m5.isLayerComplete() && m6.isLayerComplete());
//        isWin = m1.isLayerComplete() && m2.isLayerComplete() && m3.isLayerComplete() && m4.isLayerComplete() && m5.isLayerComplete() && m6.isLayerComplete();
        return m1.isLayerComplete() && m2.isLayerComplete() && m3.isLayerComplete() && m4.isLayerComplete() && m5.isLayerComplete() && m6.isLayerComplete();

    }

    private static final class MyThread extends Thread {

        private Layer layer;
        private SmallCube sc;
        private Face face;
        private Face facetoCMP;
        private boolean isLayerComplete = true;

        MyThread(Layer layer) {
            setLayer(layer);
        }

        @Override
        public void run() {
            SmallCube[][] scArray = layer.getAxis();
            facetoCMP = scArray[0][0].getFaceByID(layer.getFace());

            for (int x = 0; x < 3; x++) {
                for (int y = 1; y < 3; y++) {

                    face = scArray[x][y].getFaceByID(layer.getFace());
                    if (face.getColor() != facetoCMP.getColor()) {

                        isLayerComplete = false;
                        break;
                    }
                }
            }

        }

        public void setLayer(Layer layer) {
            this.layer = layer;
        }

        public boolean isLayerComplete() {
            return isLayerComplete;
        }

    }
}
