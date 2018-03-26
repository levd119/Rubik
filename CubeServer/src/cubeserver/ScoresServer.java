/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cubeserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author lev
 */
public class ScoresServer implements Runnable{

    ServerSocket serverSocket;
    boolean isConnected = false;

    public ScoresServer(int port) throws IOException {

        serverSocket = new ServerSocket(port);
        
        isConnected = true;
        System.out.println("Connected"+port);
    }

    // this method currently handles a single client
    // to be updated to a loop with thread
    @Override
    public void run() {
        while (isConnected) {
            try {
                Socket singleSocket = serverSocket.accept();

                ConnectionThreadScores singleConnection = new ConnectionThreadScores(singleSocket);
                singleConnection.start();
            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }

    }

    
}
