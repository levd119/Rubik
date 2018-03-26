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
public class LayerHelper implements Serializable{

    public static int DIRECTION,LAYER;
    private final SmallCube cubeArray[][][];
    private final Cube cube;
    private SmallCube c[][];

    public LayerHelper(Cube cube) {
        this.cube = cube;
        cubeArray = cube.getArray();
    }

    public Layer getLayerX(int n) {
        c = new SmallCube[3][3];
        for (int x = 0; x < 3; x++) {
            for (int z = 0; z < 3; z++) {

                c[x][z] = cubeArray[n][x][z];

            }
        }
        return new Layer(c);
    }

    public Layer getLayerY(int n) {
        c = new SmallCube[3][3];

        for (int x = 0; x < 3; x++) {
            for (int z = 0, l = 0; z < 3; z++, l++) {

                c[x][z] = cubeArray[x][n][z];

            }
        }


        return new Layer(c);
    }

    public Layer getLayerZ(int n) {
        c = new SmallCube[3][3];

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {

                c[x][y] = cubeArray[x][y][n];

            }
        }
        return new Layer(c);
    }

    public void rotateLeftX(Layer axis, int n) {//rotate counter-clockwise
        SmallCube helpMat[][] = new SmallCube[3][3];
        c = axis.getAxis();
        for (int j = c.length - 1, l = 0; j >= 0; j--, l++) {
            for (int i = 0, k = 0; i < 3; i++, k++) {

                helpMat[k][l] = c[j][i];

            }

        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                c[i][j] = helpMat[i][j];
                c[i][j].rotateDown();

            }

        }

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                cubeArray[n][x][y] = c[x][y];
                cubeArray[n][x][y].setX(n);//keeps the oroginal positions of the smallcubes in the big cube
                cubeArray[n][x][y].setY(x);
                cubeArray[n][x][y].setZ(y);
            }
        }

    }

    public void rotateLeftY(Layer axis, int n) {//rotate counter-clockwise
        SmallCube helpMat[][] = new SmallCube[3][3];
        c = axis.getAxis();
        for (int j = c.length - 1, l = 0; j >= 0; j--, l++) {
            for (int i = 0, k = 0; i < 3; i++, k++) {

                helpMat[k][l] = c[j][i];
            }

        }
        for (int x = 0; x < 3; x++) {
            for (int z = 0; z < 3; z++) {

                c[x][z] = helpMat[x][z];
                c[x][z].rotateLeft();
            }
        }

        for (int x = 0; x < 3; x++) {
            for (int z = 0; z < 3; z++) {
                cubeArray[x][n][z] = c[x][z];
                cubeArray[x][n][z].setX(x);//keeps the oroginal positions of the smallcubes in the big cube
                cubeArray[x][n][z].setY(n);
                cubeArray[x][n][z].setZ(z);

            }
        }

    }

    public void rotateRightY(Layer axis, int n) {//rotate counter-clockwise
        SmallCube helpMat[][] = new SmallCube[3][3];
        c = axis.getAxis();
        for (int i = 0, k = 0; i < 3; i++, k++) {
            for (int j = c.length - 1, l = 0; j >= 0; j--, l++) {
                helpMat[l][k] = c[i][j];

            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                c[i][j] = helpMat[i][j];
                c[i][j].rotateRight();
            }

        }

        for (int x = 0; x < 3; x++) {
            for (int z = 0; z < 3; z++) {
                cubeArray[x][n][z] = c[x][z];
                cubeArray[x][n][z].setX(x);//keeps the oroginal positions of the smallcubes in the big cube
                cubeArray[x][n][z].setY(n);
                cubeArray[x][n][z].setZ(z);

            }
        }

    }

    public void rotateRightX(Layer axis, int n)//rotate clockwise
    {
        SmallCube helpMat[][] = new SmallCube[3][3];
        c = axis.getAxis();
        for (int i = 0, k = 0; i < 3; i++, k++) {
            for (int j = c.length - 1, l = 0; j >= 0; j--, l++) {
                helpMat[l][k] = c[i][j];

            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                c[i][j] = helpMat[i][j];
                c[i][j].rotateUp();
            }

        }

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                cubeArray[n][x][y] = c[x][y];
                cubeArray[n][x][y].setX(n);//keeps the oroginal positions of the smallcubes in the big cube
                cubeArray[n][x][y].setY(x);
                cubeArray[n][x][y].setZ(y);

            }
        }

    }

    public void rotateRightZ(Layer axis, int n)//rotate clockwise
    {

        SmallCube helpMat[][] = new SmallCube[3][3];
        c = axis.getAxis();
        for (int j = c.length - 1, l = 0; j >= 0; j--, l++) {
            for (int i = 0, k = 0; i < 3; i++, k++) {

                helpMat[k][l] = c[j][i];

            }

        }
        for (int x = 0; x < 3; x++) {
            for (int z = 0; z < 3; z++) {

                c[x][z] = helpMat[x][z];
                c[x][z].rotateZRight();
            }
        }

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                cubeArray[x][y][n] = c[x][y];
                cubeArray[x][y][n].setX(x);//keeps the oroginal positions of the smallcubes in the big cube
                cubeArray[x][y][n].setY(y);
                cubeArray[x][y][n].setZ(n);

            }
        }

    }

    public void rotateLeftZ(Layer axis, int n) {//rotate counter-clockwise

        SmallCube helpMat[][] = new SmallCube[3][3];
        c = axis.getAxis();
        for (int i = 0, k = 0; i < 3; i++, k++) {
            for (int j = c.length - 1, l = 0; j >= 0; j--, l++) {
                helpMat[l][k] = c[i][j];

            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                c[i][j] = helpMat[i][j];
                c[i][j].rotateZLeft();
            }

        }

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                cubeArray[x][y][n] = c[x][y];
                cubeArray[x][y][n].setX(x);//keeps the oroginal positions of the smallcubes in the big cube
                cubeArray[x][y][n].setY(y);
                cubeArray[x][y][n].setZ(n);

            }
        }
    }

}
