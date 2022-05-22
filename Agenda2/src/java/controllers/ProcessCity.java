/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.City;
import DBC.DbConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author user
 */
public class ProcessCity extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        City ct = new City();
        
        try (PrintWriter out = response.getWriter()) {
           
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProcessCity</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form>");
            DbConnection dbc = new DbConnection();
            Connection conn = dbc.getConnected();
            
            ct.setName(request.getParameter("name"));
            ct.setCountryCode(request.getParameter("cc"));
            ct.setDistrict(request.getParameter("dist"));
            ct.setPopulation(Integer.decode(request.getParameter("pop")));
                  
            out.println("<h1>Servlet ProcessCity at " + ct.getName() + "</h1>");
            out.println("<h1>Servlet ProcessCity at " + ct.getCountryCode() + "</h1>");
            out.println("<h1>Servlet ProcessCity at " + ct.getDistrict() + "</h1>");
            out.println("<h1>Servlet ProcessCity  at " + ct.getPopulation() + "</h1>");
            
             Statement st = conn.createStatement();

            // note that i'm leaving "date_created" out of this insert statement
            String qry = "INSERT INTO users (Name, CountryCode, District, Population) "
            +"VALUES ("+ "'" + ct.getName()+ "'," + "'" + ct.getDistrict() + "', " + ct.getPopulation() + ")";
            out.println("<h1>Servlet ProcessCity  at " + qry + "</h1>");
            
            
            st.executeUpdate(qry);

             conn.close();
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProcessCity.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessCity.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessCity.class.getName()).log(Level.SEVERE, null, ex);
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
