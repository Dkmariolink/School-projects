
import edu.uncc.nbad.Product;
import edu.uncc.nbad.ProductTable;
import edu.uncc.nbad.User;
import edu.uncc.nbad.UserTable;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hunter
 */
public class Testing {
    public static void main(String args[]) throws SQLException, IOException {
        TestUserCRUD();
    }
    
    
    public static void TestProductCRUD() throws SQLException{
        //assumes teachers loaded Shop.sql file
        List<Product> selectedProducts = ProductTable.selectProducts();        
        assertEquals(selectedProducts.get(0).getCode(), "lp214");
        assertEquals(selectedProducts.get(1).getCode(), "nm2345g");
        
        Product selectedProduct = ProductTable.selectProduct("lp214");
        assertEquals(selectedProduct.getCode(), "lp214");
        assertEquals(selectedProduct.getDescription(), "Laptop");
        
        boolean exists = ProductTable.exists("nm2345g");
        assertTrue(exists);
        exists = ProductTable.exists("sdafsd");
        assertFalse(exists);
        
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setCode("codey");
        product1.setDescription("descript");
        product1.setPrice(10.0);
        products.add(product1);
        ProductTable.saveProducts(products);
        Product selectedProduct1 = ProductTable.selectProduct("codey");
        assertEquals(selectedProduct1.getCode(), "codey");
        assertEquals(selectedProduct1.getDescription(), "descript");
        
        Product product2 = new Product();
        product2.setCode("Iamnumbahtwo");
        product2.setDescription("descript");
        product2.setPrice(10.0);
        ProductTable.insertProduct(product2);
        assertTrue(ProductTable.exists("Iamnumbahtwo"));
        
        Product product3 = new Product();
        product3.setCode("codey");
        product3.setDescription("meow");
        product3.setPrice(10.0);
        ProductTable.updateProduct(product3);
        Product selectedProduct4 = ProductTable.selectProduct("codey");
        assertEquals(selectedProduct4.getDescription(), "meow");
        
        ProductTable.deleteProduct(product3);
        ProductTable.deleteProduct(product2);
        assertEquals(2, ProductTable.selectProducts().size());
    }
    
    public static void TestUserCRUD() throws SQLException, IOException{
        //assumes teachers loaded Shop.sql file
        User user = new User();
        user.setEmail("mail@mal.com");
        user.setFirstName("fgname");
        user.setLastName("lName");
        user.setPassword("password");
        UserTable.addRecord(user);
        
        User user1 = UserTable.getUser("jsmith@gmail.com");
        
        ArrayList<User> users = new ArrayList<>();
        users = UserTable.getUsers();
        
        HashMap<String, User> map = new HashMap<>();
        map = UserTable.getUsersMap();
    }
}
