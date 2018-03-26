/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cubeserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author lev
 */
public class MultiPlayerServer implements Runnable {

    private static Queue<ConnectionThreadMultiPlayer> array;
    private final ServerSocket serverSocket;
    private boolean isConnected = false;

    public MultiPlayerServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        isConnected = true;
        array = new LinkedList<>();

    }

    public boolean connectUsers(ConnectionThreadMultiPlayer ctm1) {

        for (int i = 0; i < array.size(); i++) {
            ConnectionThreadMultiPlayer ctm = array.poll();
            if (!ctm.isClosed()) {

                ctm1.setUserSocket(ctm);
                ctm.setUserSocket(ctm1);
                return true;
            }
        }

        return false;
    }

    // this method currently handles a single client
    // to be updated to a loop with thread
    @Override
    public void run() {
        while (isConnected) {

            try {
                Socket singleSocket = serverSocket.accept();
                System.out.println("accept");
                ConnectionThreadMultiPlayer singleConnection = new ConnectionThreadMultiPlayer(singleSocket);
                singleConnection.start();
                if (!connectUsers(singleConnection)) {
                    System.out.println(singleConnection.toString() + " is waiting");
                    array.add(singleConnection);

                }

            } catch (Exception ex) {

                ex.printStackTrace();
            }

        }

    }

}
