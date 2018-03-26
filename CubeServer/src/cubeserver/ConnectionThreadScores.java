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
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import testjogl.ConnectionData;
import testjogl.User;

/**
 *
 * @author lev
 */
public class ConnectionThreadScores extends Thread{
        Socket socket;
    InputStream input;
    ObjectInputStream ois;
    OutputStream output;
    ObjectOutputStream oos;

    public ConnectionThreadScores(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            output = socket.getOutputStream();
            oos = new ObjectOutputStream(output);
            input = socket.getInputStream();
            ois = new ObjectInputStream(input);

            ConnectionData requestData = (ConnectionData) ois.readObject();
            ConnectionData responseData = new ConnectionData();

            switch (requestData.getRequestCode()) {
                case ConnectionData.ADD_USER:
                    User user = requestData.getNewUser();
                    System.out.println("Secondssss"+user.getTime().get(Calendar.SECOND));
                    
                    DBUtil.addNewUSER(user);
                    responseData.setResponseStatus(true);
                    oos.writeObject(responseData);

                    break;
                case ConnectionData.GET_USER:
                    ArrayList<User> Users = DBUtil.loadUsers();
                    responseData.setListOfUsers(Users);
                    responseData.setResponseStatus(true);
                    oos.writeObject(responseData);
                    break;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

}
