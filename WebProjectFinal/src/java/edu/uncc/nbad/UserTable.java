/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncc.nbad;

import static edu.uncc.nbad.ProductTable.connection;
import java.io.*;
import java.util.*;
import java.sql.*;
//import murach.business.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Dkmar
 */
public class UserTable {

    static String url = "jdbc:mysql://localhost:3306/shop";
    static String username = "user";
    static String password = "123";

    static Connection connection = null;
    static PreparedStatement selectProduct = null;
    static ResultSet resultset = null;

    //Static initializer, it runs when the class is intialized (it is executed once)
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    static {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }

    public static void addRecord(User user) throws IOException {
        try {
            String preparedSQL = "INSERT INTO shop.users (firstName,lastName,email,password) VALUES (?,?,?,?);";
            selectProduct = connection.prepareStatement(preparedSQL);
            selectProduct.setString(1, user.getFirstName());
            selectProduct.setString(2, user.getLastName());
            selectProduct.setString(3, user.getEmail());
            selectProduct.setString(4, user.getPassword());
            selectProduct.executeUpdate();
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }

    public static User getUser(String emailAddress) throws IOException {
        User user = new User();
        boolean sqlWork = false;
        try {
            String preparedSQL = "SELECT firstName,lastName,email,password FROM shop.users WHERE email = ?;";
            selectProduct = connection.prepareStatement(preparedSQL);
            selectProduct.setString(1, emailAddress);
            resultset = selectProduct.executeQuery();

            while (resultset.next()) {
                user.setFirstName(resultset.getString("firstName"));
                user.setLastName(resultset.getString("lastName"));
                user.setEmail(resultset.getString("email"));
                user.setPassword(resultset.getString("password"));
            }
            sqlWork = true;
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
        if (!sqlWork) {
            user = null;
        }
        return user;
        //throw new NotImplementedException(); // remove this line and implement the logic

    }

    public static ArrayList<User> getUsers() throws IOException {
        User user = new User();
        ArrayList<User> userList = new ArrayList<>();
        boolean sqlWork = false;
        try {
            String preparedSQL = "SELECT firstName,lastName,email,password FROM users";
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery(preparedSQL);

            while (rs.next()) {
                user = new User();
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                userList.add(user);
            }
            sqlWork = true;
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
        if (!sqlWork) {
            user = null;
        }
        
        return userList;
    }

    public static HashMap<String, User> getUsersMap() throws IOException {
        User user = new User();
        HashMap<String, User> userList = new HashMap<>();
        boolean sqlWork = false;
        try {
            String preparedSQL = "SELECT firstName,lastName,email,password FROM users";
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery(preparedSQL);

            while (rs.next()) {
                user = new User();
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                userList.put(rs.getString("email"), user);
            }
            sqlWork = true;
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
        if (!sqlWork) {
            user = null;
        }
        
        return userList;
    }
}
