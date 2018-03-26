package cubeserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import testjogl.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lev
 */
public class DBUtil {

    public static final String DB_URL = "jdbc:derby://localhost:1527/RUBIKSCUBE_DB";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "root";

    public static final String NEW_USER_ST = "insert into SCORES (USER_NAME,MOVES,TIME,HINTS)values(?,?,?,?)";
    public static final String GET_ALL_USERS = "select * from SCORES order by TIME asc,MOVES asc ";

    public static void addNewUSER(User user) {
        Date date = new Date(user.getTime().getTimeInMillis());
        Time time=new Time(date.getTime());
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            PreparedStatement statement
                    = connection.prepareStatement(NEW_USER_ST);
            statement.setString(1, user.getName());
            statement.setTime(3,time);
            statement.setInt(2, user.getMoves());
            statement.setInt(4, user.getHints());
            statement.execute();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static ArrayList<User> loadUsers() {
        ArrayList<User> allUsers = new ArrayList<>();
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_USER);
            System.out.println("Loading Users");
            Statement statement = connection.createStatement();
            statement.execute(GET_ALL_USERS);
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                String name = resultSet.getString(2);
                int moves = resultSet.getInt(3);
                GregorianCalendar time =new GregorianCalendar();
                time.setTime(resultSet.getTime(4));
                int hints=resultSet.getInt(5);
                User newUser = new User(name, time, moves,hints);

                allUsers.add(newUser);
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return allUsers;
    }

}
