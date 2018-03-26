/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjogl;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author lev
 */
public class ConnectionUtil {
//    public static final String HOST_ADDRESS = "68.169.48.170";

    public static final String HOST_ADDRESS = "LOCALHOST";
    public static final int HOST_PORT_MULTI = 12345;
    public static final int HOST_PORT_SCORE = 54321;

//    public static final int HOST_PORT_MULTI = 11112;
//    public static final int HOST_PORT_SCORE = 21111;
    public static Socket clientSocket;
    public static OutputStream output;
    public static ObjectOutputStream oos;
    public static InputStream input;
    public static ObjectInputStream ois;

    public static void closeConnection() {
        try {
            System.out.println("Close Connection");
            if (ois != null) {
                System.out.println("ois.close();");
//                    ois.reset();
                ois.close();
            }
            if (input != null) {
                System.out.println("input.close();");
//                    input.reset();
                input.close();
            }
            if (oos != null) {
                System.out.println("oos.close();");
//                    oos.reset();
                oos.close();
            }
            if (output != null) {
                System.out.println("output.close();");
                output.close();
            }
            if (clientSocket != null) {
                System.out.println("clientSocket.close();");

                clientSocket.close();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void openConnectionScore() throws IOException {
        clientSocket = new Socket(HOST_ADDRESS, HOST_PORT_SCORE);
        output = clientSocket.getOutputStream();

        oos = new ObjectOutputStream(output);

        oos.flush();

        input = clientSocket.getInputStream();

        ois = new ObjectInputStream(input);
    }

    public static void openConnectionMultiPlayer() throws IOException {
        System.out.println("1");
        clientSocket = new Socket(HOST_ADDRESS, HOST_PORT_MULTI);
        System.out.println("2");

        output = clientSocket.getOutputStream();

        oos = new ObjectOutputStream(output);

        oos.flush();

        input = clientSocket.getInputStream();

        ois = new ObjectInputStream(input);

    }

    public static Serializable receiveData() {

        try {
            return (Serializable) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {

            System.out.println("Disconnected");

        }
        return null;

    }

    public static void sendData(Serializable c) throws IOException {

        oos.reset();
        oos.flush();

        oos.writeObject(c);

    }

    public void addUser(User newUser) {
        try {
            ConnectionData theData = new ConnectionData();
            theData.setNewUser(newUser);

            theData.setRequestCode(ConnectionData.ADD_USER);
            oos.writeObject(theData);
            ConnectionData dataResponse = (ConnectionData) ois.readObject();
            if (dataResponse.getResponseStatus()) {
                System.out.println("added User succesfully!");
            } else {
                System.out.println(":(");
            }

        } catch (IOException ex) {
            ex.printStackTrace();

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();

        } finally {

        }
    }

    public ArrayList<User> getUserLists() {
        ArrayList<User> arrayOfUsers = null;
        try {
            ConnectionData theData = new ConnectionData();

            theData.setRequestCode(ConnectionData.GET_USER);
            oos.writeObject(theData);

            ConnectionData dataResponse = (ConnectionData) ois.readObject();
            if (dataResponse.getResponseStatus()) {
                arrayOfUsers = dataResponse.getListOfUsers();
                System.out.println("Users recieved" + arrayOfUsers.size());

            } else {
                System.out.println(":(");
                arrayOfUsers = new ArrayList<>();
            }

        } catch (IOException ex) {
            ex.printStackTrace();

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();

        } finally {
            closeConnection();
        }

        return arrayOfUsers;
    }

}
