/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncc.nbad;
import java.io.*;
import java.sql.*;
import java.util.*;
//import murach.business.Product;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Dkmar
 */
public class ProductTable {
    
    static String url = "jdbc:mysql://localhost:3306/shop";
    static String username = "user";
    static String password = "123";
    
    static Connection connection = null;
    static PreparedStatement selectProduct = null;
    static ResultSet resultset = null;
	
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
    
    static {
        try {
            connection = DriverManager.getConnection (url, username, password);
        }
        catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        }
    }
   
    public static List<Product> selectProducts() throws SQLException {
        Statement stat = null;
        String sql = "Select * FROM products";
        List<Product> products = new ArrayList<>();

        try{
            stat = connection.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
                Product product = new Product();
                product.setCode(rs.getString("code"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                products.add(product);                           
            }
        }
        catch (SQLException e){
            System.out.println("Broken");
        }
        return products;
    }
        
	
    

    public static Product selectProduct(String productCode) {
        Statement stat = null;
        Product product = null;
        String sql = "select * from products where code =" + "'" + productCode + "'";
        try{
            stat = connection.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
                product = new Product();       
                product.setCode(rs.getString("code"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
            }
        }
        catch (SQLException e){
            
        }
        return product;
		
    }

    public static boolean exists(String productCode) {
        Statement stat = null;
        boolean check = false;
        String sql = "select * from products where code =" + "'" + productCode + "'";
        try{
            stat = connection.createStatement();
            ResultSet rs = null;
            rs = stat.executeQuery(sql);
           if(!rs.isBeforeFirst()){
               System.out.print("No Data Found");
           }
           else{
               check = true;
           }
        }
    catch (SQLException e){
        
    }
        return check;
    }
    
    public static void saveProducts(List<Product> products) {
        Statement stat = null;
        String sql = "INSERT INTO products(code,description,price) VALUES (?,?,?)";
        try{
            stat = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement(sql);
            int i =0;
            for (Product item : products){
                ps.setString(1, item.getCode());
                ps.setString(2, item.getDescription());
                ps.setDouble(3, item.getPrice());
                ps.addBatch();
                i++;
                if (i % 1000 == 0 || i == products.size()) {
                    ps.executeBatch();
                }
             
                
            }
        }
        catch (SQLException e){
            
        }
    }

    public static void insertProduct(Product product) {
        Statement stat = null;
        String sql = "INSERT INTO PRODUCTS(code,description,price) VALUES (?,?,?)";
        try{
            stat = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, product.getCode());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.executeUpdate();
        }
        catch (SQLException e){
            
        }
    }

    public static void updateProduct(Product product) {
       Statement stat = null;
       String sql = "UPDATE PRODUCTS SET description = ?, price = ? WHERE code = ?";
       try{
            stat = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, product.getDescription());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getCode());
            ps.executeUpdate();
       }
       catch (SQLException e){
           
       }
    }  
    
    public static void deleteProduct (Product product) {
       Statement stat = null;
       String sqlToGetId = "select id from products where code =" + "'" + product.getCode() + "'";
       int id = -1;
       try{
           stat = connection.createStatement();
            ResultSet rs = stat.executeQuery(sqlToGetId);
            while (rs.next()){
                id = rs.getInt("id");
            }
            
            String sql = "DELETE FROM products where id =" + id;
            stat = connection.createStatement();
            stat.execute(sql);
       }
       catch (SQLException e){
           System.out.println("Error");
       }
    }
}
    
