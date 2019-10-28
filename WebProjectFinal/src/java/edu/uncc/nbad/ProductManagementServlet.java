/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncc.nbad;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andrew
 */
public class ProductManagementServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductManagementServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductManagementServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Get action parameter
        String action = request.getParameter("action");
      
        System.out.println(" product management get"); 
        HttpSession session = request.getSession();
        ArrayList<Product> productsCheck = (ArrayList<Product>) session.getAttribute("products");
        if(productsCheck == null) {
            //loads session with product text file
            String path = getServletContext().getRealPath("/WEB-INF/Products.txt");
            ArrayList<Product> products = ProductIO.getProducts(path);
            session.setAttribute("products", products);                     
        }

        //User is logged in
        //Make a big decision
        switch (action) {
            case "displayProducts":
                String pathDoubleDisplay = getServletContext().getRealPath("/WEB-INF/Products.txt");
                ArrayList<Product> productsDoubleDisplay = ProductIO.getProducts(pathDoubleDisplay);
                session.setAttribute("products", productsDoubleDisplay);
                //Call getProducts method to grab products from text file and put inside ArrayList of Products to put into session attribute               
                getServletContext().getRequestDispatcher("/products.jsp").forward(request, response);
                break;
            case "addProduct":
                //Just simple redirect to the blank newProduct.jsp
                System.out.println(" forwarding to new product");
                getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
                break;
            case "displayProduct":
                //Just simple redirect to the blank newProduct.jsp
                getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
                break;
            case "deleteProduct":
                //Call getProduct method to grab product from text file and put inside request attribute
                String path = getServletContext().getRealPath("/WEB-INF/Products.txt");
                String productCode = request.getParameter("productCode");
                Product product = ProductIO.getProduct(productCode, path);
                request.setAttribute("product", product);
                getServletContext().getRequestDispatcher("/confirmDelete.jsp").forward(request, response);
                break;
            case "actuallyDelete":
                System.out.println("Requested to delete" + request.getParameter("productCode"));
                String pathActualDelete = getServletContext().getRealPath("/WEB-INF/Products.txt");
                String productCodeForActualDelete = request.getParameter("productCode");
                Product productForDelete = ProductIO.getProduct(productCodeForActualDelete, pathActualDelete);
                String productText = ProductIO.GetLineTextFromProduct(productForDelete);
                IOUtilitites.RemoveMatchingLine(productText, pathActualDelete);
                
                //loads session with product text file
                ArrayList<Product> products = ProductIO.getProducts(pathActualDelete);
                session.removeAttribute("products");
                session.setAttribute("products", products); 
                request.removeAttribute("product");

                getServletContext().getRequestDispatcher("/productManagement?action=displayProducts").forward(request, response);
                break;
            case "updateProduct": {
                String pathToUpdate = getServletContext().getRealPath("/WEB-INF/Products.txt");
                String productCodeGuy = (String)request.getParameter("productCode");
                Product productToUpdate = ProductIO.getProduct(productCodeGuy, pathToUpdate);
                session.setAttribute("productToUpdate", productToUpdate);
                getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
                break;
            }
            default:
                break;
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("product post is here");
        HttpSession sessionForCheck = request.getSession();
        ArrayList<Product> productsCheck = (ArrayList<Product>) sessionForCheck.getAttribute("products");
        if(productsCheck == null) {
            //loads session with product text file
            String path = getServletContext().getRealPath("/WEB-INF/Products.txt");
            ArrayList<Product> products = ProductIO.getProducts(path);
            sessionForCheck.setAttribute("products", products);                     
        }
        
        //Get action parameter
        String action = request.getParameter("action");
          System.out.println("action "+action);

        //If user is not logged in, forward to the login page
        if (action.equals("login")) {
            
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");
            
            if (user.getPassword()==null)
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            else
            {
                getServletContext().getRequestDispatcher("/products.jsp").forward(request, response);
            }
        }

        //User is logged in, proceed to updateProduct if appropriate action parameter
        switch (action) {
            case "addProduct":                                               
                    //Get the the values to put into the new product
                    String code = request.getParameter("code");
                    String desc = request.getParameter("desc");
                    String priceText = request.getParameter("price");
                                         
                    //Validate Method will print out error if any of the input from parameters is invalid
                    if (code.isEmpty() || desc.isEmpty() || priceText.isEmpty()) {
                        try (PrintWriter out = response.getWriter()) {
                            out.println("<!DOCTYPE html>");
                            out.println("<html>");
                            out.println("<head>");
                            out.println("<title>Incorrect Info</title>");
                            out.println("</head>");
                            out.println("<body>");
                            out.println("<p>All fields are Required, Please make sure you enter a value in each field.</p>");
                            out.println("</body>");
                            out.println("</html>");
                        }
                    } else if(!IsValidPrice(priceText)) {
                        try (PrintWriter out = response.getWriter()) {
                            out.println("<!DOCTYPE html>");
                            out.println("<html>");
                            out.println("<head>");
                            out.println("<title>Incorrect Info</title>");
                            out.println("</head>");
                            out.println("<body>");
                            out.println("Price must be a valid Price."); 
                            out.println("</body>");
                            out.println("</html>");
                        }
                    } else {
                        double price = Double.parseDouble(request.getParameter("price"));

                        //Create new product object and put in the values
                        Product newProduct = new Product();
                        newProduct.setCode(code);
                        newProduct.setDescription(desc);
                        newProduct.setPrice(price);
                        
                        String path = getServletContext().getRealPath("/WEB-INF/Products.txt");
                        HttpSession session = request.getSession();

                        //check if update
                        Product productToUpdate = (Product)session.getAttribute("productToUpdate");
                        
                        //get the product list from the session, if any 
                        ArrayList<Product> products = (ArrayList<Product>) session.getAttribute("products");

                        if(products==null)
                        {
                            products =  new ArrayList<>();

                        }

                        if(productToUpdate != null) {
                            Product productForUpdate = ProductIO.getProduct(productToUpdate.getCode(), path);
                            String productText = ProductIO.GetLineTextFromProduct(productForUpdate);
                            IOUtilitites.RemoveMatchingLine(productText, path);
                            session.removeAttribute("productToUpdate");
                            products.remove(productToUpdate);
                        }
                        
                        //add to text file
                        ProductIO.addRecord(newProduct, path);
                        //add product to list
                        products.add(newProduct);

                        // replacing the old list in the session by a the new list (that contains the new product we just added)
                        session.removeAttribute("products");
                        session.setAttribute("products", products);

                        //Redirect back to products page
                        getServletContext().getRequestDispatcher("/productManagement?action=displayProducts").forward(request, response);
                    }                                                      
                break;
            case "displayProducts":
                String pathDoubleDisplay = getServletContext().getRealPath("/WEB-INF/Products.txt");
                ArrayList<Product> productsDoubleDisplay = ProductIO.getProducts(pathDoubleDisplay);
                HttpSession session2 = request.getSession();
                session2.setAttribute("products", productsDoubleDisplay);
                //Call getProducts method to grab products from text file and put inside ArrayList of Products to put into session attribute               
                getServletContext().getRequestDispatcher("/products.jsp").forward(request, response);
                break;
            default:
                System.err.println("He's dead, Jim!");
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private boolean IsValidPrice(String input) {
        double d;
        boolean valid = false;
        try {
            d = Double.parseDouble(input);
            if(d >= 0) {
                valid = true;
            }
        }
        catch (NumberFormatException e) {
            valid = false;
        }
        
        return valid;
    }  
}
