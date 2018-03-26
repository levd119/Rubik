/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cubeserver;

import java.io.IOException;

/**
 *
 * @author lev
 */
public class CubeServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Thread t1 = new Thread(new MultiPlayerServer(12345));
            Thread t2 = new Thread(new ScoresServer(54321));

            t1.start();
            t2.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
