/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cubeserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import testjogl.ConnectionData;
import testjogl.Cube;

/**
 *
 * @author lev
 */
public class ConnectionThreadMultiPlayer extends Thread {

    private final Socket singleSocket;
    private InputStream input;
    private ObjectInputStream ois;
    private OutputStream output;
    private ObjectOutputStream oos;
    private ConnectionThreadMultiPlayer userConnection;
    private boolean running = true, isConnected = false;

    public synchronized void setUserSocket(ConnectionThreadMultiPlayer singleConnection) {
        userConnection = singleConnection;
        isConnected = true;
        notifyAll();
    }

    public void sendCube(Serializable userCube) throws IOException {
        oos.flush();
        oos.writeObject(userCube);

    }

    public void disConnect() {
        System.out.println("Disconnect");

        try {

            if (ois != null) {
                ois.close();
            }
            if (input != null) {
                input.close();
            }

            if (oos != null) {
                oos.close();
            }

            if (output != null) {
                output.close();
            }

            if (singleSocket != null) {
                singleSocket.close();

            }
//            checkIsAlive.stopTimer();
        } catch (IOException ex) {

            ex.printStackTrace();

        }
    }

    public boolean isClosed() {

        try {
            ConnectionData cd = new ConnectionData();
            cd.setRequestCode(ConnectionData.IS_ALIVE);
            oos.reset();
            oos.writeObject(cd);
        } catch (IOException ex) {
            System.out.println("Exception");
            return true;

        }
        return false;
    }

    public ConnectionThreadMultiPlayer(Socket socket) {
        this.singleSocket = socket;

    }

    private void connect() {
        try {
            input = singleSocket.getInputStream();
            output = singleSocket.getOutputStream();
            ois = new ObjectInputStream(input);
            oos = new ObjectOutputStream(output);
        } catch (IOException ex) {

            System.out.println("Cant Connect");
        }

    }

    @Override
    public void run() {
        connect();

        synchronized (this) {

            do {
                try {
                    if (!isConnected) {
                        ConnectionData cd = new ConnectionData();
                        cd.setResponseStatus(false);
                        oos.writeObject(cd);
                        wait();
                    }
                } catch (InterruptedException | IOException ex) {
//                    ex.printStackTrace();
                    disConnect();
                }
            } while (!isConnected);
            System.out.println("connected" + getName());

        }

        try {
            System.out.println("try" + getName());
            ConnectionData connectrionData = new ConnectionData();
            connectrionData.setResponseStatus(true);
            oos.writeObject(connectrionData);
            System.out.println("Success");
            Serializable cd;
            do {
                cd = (Serializable) ois.readObject();
                if (cd instanceof Cube) {
                    if (userConnection.isConnected()) {
                        userConnection.sendCube((Cube) cd);
                    }
                } else {
                    connectrionData = (ConnectionData) cd;
                    if (connectrionData.getRequestCode() == ConnectionData.DISCONNECT) {
                        System.out.println("End Game");
                        userConnection.sendCube(connectrionData);
                        System.out.println("running = false");
                        running = false;
                    }

                }

            } while (running);
            System.out.println("Running=false");
            disConnect();

        } catch (IOException ex) {
            System.out.println("Exception");
           ConnectionData connectrionData = new ConnectionData();
            connectrionData.setRequestCode(ConnectionData.DISCONNECT);
            try {
                userConnection.sendCube(connectrionData);
            } catch (IOException ex1) {

            }


            disConnect();

        } catch (ClassNotFoundException ex) {

            System.out.println("ERROR2");
        }

    }

    @Override
    public String toString() {
        return singleSocket.getLocalAddress().toString();
    }

    private boolean isConnected() {
        return singleSocket.isConnected();

    }

}
