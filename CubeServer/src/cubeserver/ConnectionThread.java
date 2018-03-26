///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package cubeserver;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.io.OutputStream;
//import java.net.Socket;
//import testjogl.Cube;
//
///**
// *
// * @author lev
// */
//public class ConnectionThread extends Thread {
//
//    private static int ID = 0;
//    int id;
//    Socket singleSocket;
//    InputStream input;
//    ObjectInputStream ois;
//    OutputStream output;
//    ObjectOutputStream oos;
//    ConnectionThread userConnection;
//    private boolean isConnected;
//
//
//    
//    public void setUserSocket(ConnectionThread singleConnection) {
//        userConnection = singleConnection;
//    }
//
//    public void sendUserCube(Cube userCube) {
//        try {
//            oos.writeObject(userCube);
//        } catch (IOException ex) {
//            System.out.println("setUserSocket not good");
//
//        }
//
//    }
//
//    public ConnectionThread(Socket socket) {
//        id = ID++;
//        this.singleSocket = socket;
//    }
//
//    @Override
//    public void run() {
//        try {
//            input = singleSocket.getInputStream();
//            output = singleSocket.getOutputStream();
//
//            ois = new ObjectInputStream(input);
//            oos = new ObjectOutputStream(output);
//            // here start the conversation
//            // read multiple messages from this client
//            Cube cube;
//            do {
//                cube = (Cube) ois.readObject();
//
//                userConnection.sendUserCube(cube);
//            } while (singleSocket.isConnected());
//            singleSocket.close();
//
//        } catch (IOException ex) {
//
//            ex.printStackTrace();
//
//        } catch (ClassNotFoundException ex) {
//
//            ex.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public String toString() {
//        return singleSocket.getLocalAddress().toString();
//    }
//    
//
//}
