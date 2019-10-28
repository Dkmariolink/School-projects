/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncc.nbad;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
public class MembershipServlet extends HttpServlet {

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
            out.println("<title>Servlet MembershipServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MembershipServlet at " + request.getContextPath() + "</h1>");
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

        //Make a big decision
        switch (action) {
            case "login":
                getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                break;
            case "signup":
                getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
                break;
            case "logout":
                 getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                 HttpSession session = request.getSession();
                 session.invalidate();            
                break;
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

        //Get the action and session
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MembershipControllerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Membership Servlet</h1>");

            //********** LOGIN **********
            //If action is login, login the user
            if ("login".equals(action)) {
                //Get the parameters
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                
                User user = (User) session.getAttribute("user");
                
                if(user == null) {
                    String pathActualDelete = getServletContext().getRealPath("/WEB-INF/Users.txt");
                    user = UserIO.getUser(username, pathActualDelete);
                    session.setAttribute("user", user);
                }
                
                //Validate the login
                if (username.isEmpty() || password.isEmpty()) {
                    out.println("<p>All fields are required.</p>");
                } else if (username == null ||!username.equals(user.getEmail())) {
                    //user didn't match any user in the text file
                    out.println("User does not exist");
                } else if (password == null || !password.equals(user.getPassword())) {
                    //password didn't match
                    out.println("<p>Incorrect Password.</p>");
                } else {
                    //valid user, so go to display products                                      
                    getServletContext().getRequestDispatcher("/products.jsp").forward(request, response);
                    out.println("<p>Successfully logged in " + user.getFirstName() + " " + user.getLastName() + "</p>");
                    out.println("<p><a href=\"index.jsp\">Return to Index</a></p>");
                }
                //********** SIGNUP **********
                //If action is signup, signup the user
            } else if ("signup".equals(action)) {
                                
                //Get the parameters
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String email = request.getParameter("e-mail");
                String password = request.getParameter("password");

                //Validate Method will print out error if any of the input from parameters is invalid
                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    out.println("<p>All fields are Required, Please make sure you enter a value in each field.</p>");
                } else if (password.length() <= 8) {
                    out.println("Password must be greater than 8 characters."); 
                } else {
                    //Create new user object and put the information in it
                    User user = new User();
                    user.setEmail(email);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setPassword(password);

                    //Put user object into session attribute
                    session.setAttribute("user", user); 
                    
                    String path = getServletContext().getRealPath("/WEB-INF/Users.txt");
                    UserIO.addRecord(user, path);
                    
                    out.println("<p>Successfully created new user " + user.getFirstName() + "</p>");
                    out.println("<p><a href=\"index.jsp\">Return to Index</a></p>");
                }                
            }
            out.println("</body>");
            out.println("</html>");
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

}
