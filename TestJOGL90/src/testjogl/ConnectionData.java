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
public class ConnectionData implements Serializable{

    public static final int ADD_USER = 1;
    public static final int GET_USER = 2;
    public static final int IS_ALIVE=3;
    public static final int DISCONNECT=4;
    public static final int CONNECTED=5;
    private int requestCode;
    private boolean responseStatus;

    private User newUser;
    private ArrayList<User> listOfUsers;
    
    
    

    
    
    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public boolean getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(boolean responseStatus) {
        this.responseStatus = responseStatus;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public ArrayList<User> getListOfUsers() {
        return listOfUsers;
    }

    public void setListOfUsers(ArrayList<User> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

}
