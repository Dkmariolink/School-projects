package edu.uncc.nbad;

import java.io.*;
import java.util.*;

public class UserIO {

    public static void addRecord(User user, String filename) throws IOException {
        File file = new File(filename);
        PrintWriter out = new PrintWriter(
                new FileWriter(file, true));
        out.println(user.getEmail() + "|"              
                + user.getFirstName() + "|"
                + user.getLastName() + "|"
                + user.getPassword());
        out.close();
    }

    public static User getUser(String emailAddress, String filename) throws IOException {
        File file = new File(filename);
        BufferedReader in = new BufferedReader(
                new FileReader(file));
        User user = new User();
        String line = in.readLine();
        while (line != null) {
            if(!line.equals("")) {
                StringTokenizer t = new StringTokenizer(line, "|");
                String email = t.nextToken();
                if (email.equalsIgnoreCase(emailAddress)) {
                    String firstName = t.nextToken();
                    String lastName = t.nextToken();
                    String password = t.nextToken();
                    user.setEmail(emailAddress);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setPassword(password);
                }
            }
            line = in.readLine();
        }
        in.close();
        return user;
    }

    public static ArrayList<User> getUsers(String filename) throws IOException {
        ArrayList<User> users = new ArrayList<>();
        BufferedReader in = new BufferedReader(
                new FileReader(filename));
        String line = in.readLine();
        while (line != null) {
            try {
                StringTokenizer t = new StringTokenizer(line, "|");
                String emailAddress = t.nextToken();
                String firstName = t.nextToken();
                String lastName = t.nextToken();
                String password = t.nextToken();
                
                User user = new User();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(emailAddress);
                user.setPassword(password);
                users.add(user);
                
                line = in.readLine();
            } catch (NoSuchElementException e) {
                line = in.readLine();
            }
        }
        in.close();
        
        return users;
    }

    public static HashMap<String, User> getUsersMap(String filename) throws IOException {
        HashMap<String, User> users = new HashMap<>();
        BufferedReader in = new BufferedReader(
                new FileReader(filename));
        String line = in.readLine();
        while (line != null) {
            try {
                StringTokenizer t = new StringTokenizer(line, "|");
                String emailAddress = t.nextToken();
                String firstName = t.nextToken();
                String lastName = t.nextToken();
                String password = t.nextToken();

                User user = new User();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(emailAddress);
                user.setPassword(password);
                
                users.put(emailAddress, user);
                line = in.readLine();
            } catch (NoSuchElementException e) {
                line = in.readLine();
            }
        }
        in.close();
        return users;
    }
}
