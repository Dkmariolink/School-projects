package edu.uncc.nbad;

import java.io.*;
import java.util.*;

public class ProductIO {

    public static void addRecord(Product product, String filename) throws IOException {
        File file = new File(filename);
        PrintWriter out = new PrintWriter(
                new FileWriter(file, true));
                out.println(product.getCode() + "|"              
                + product.getDescription() + "|"
                + product.getPrice());
        out.close();
    }      

    public static Product getProduct(String productCode, String filename) throws IOException {
        File file = new File(filename);
        BufferedReader in = new BufferedReader(
                new FileReader(file));
        Product product = new Product();
        String line = in.readLine();
        while (line != null) {
            if(!line.equals("")) {
                StringTokenizer t = new StringTokenizer(line, "|");
                String productCodeFromFile = t.nextToken();
                if (productCodeFromFile.equalsIgnoreCase(productCode)) {
                    String description = t.nextToken();
                    String price = t.nextToken();
                
                    product.setCode(productCode);
                    product.setDescription(description);
                    product.setPrice( Double.valueOf(price));
                }
            }           
            line = in.readLine();
        }
        in.close();
        return product;
    }

    public static ArrayList<Product> getProducts(String filename) throws IOException {
        ArrayList<Product> products = new ArrayList<>();
        BufferedReader in = new BufferedReader(
                new FileReader(filename));
        String line = in.readLine();
        while (line != null) {
            try {
                StringTokenizer t = new StringTokenizer(line, "|");
                String productCode = t.nextToken();
                String description = t.nextToken();
                String price = t.nextToken();
                
                Product product = new Product();
                product.setCode(productCode);
                product.setDescription(description);
                product.setPrice(Double.valueOf(price));
                products.add(product);
                
                line = in.readLine();
            } catch (NoSuchElementException e) {
                line = in.readLine();
            }
        }
        in.close();
        
        return products;
    }

    public static HashMap<String, Product> getUsersMap(String filename) throws IOException {
        HashMap<String, Product> users = new HashMap<>();
        BufferedReader in = new BufferedReader(
                new FileReader(filename));
        String line = in.readLine();
        while (line != null) {
            try {
                StringTokenizer t = new StringTokenizer(line, "|");
                String productCode = t.nextToken();
                String description = t.nextToken();
                String price = t.nextToken();
                
                Product product = new Product();
                product.setCode(productCode);
                product.setDescription(description);
                product.setPrice(Double.valueOf(price));
                
                users.put(productCode, product);
                line = in.readLine();
            } catch (NoSuchElementException e) {
                line = in.readLine();
            }
        }
        in.close();
        return users;
    } 
    
    public static String GetLineTextFromProduct(Product product) {
        return product.getCode() + "|" + product.getDescription() + "|"+ product.getPrice();
    }
}
