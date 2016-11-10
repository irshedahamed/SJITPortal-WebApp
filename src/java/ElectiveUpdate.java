/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dbconnection.dbcon;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Home
 */
public class ElectiveUpdate extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ElectiveUpdate</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ElectiveUpdate at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
        processRequest(request, response);
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
       // processRequest(request, response);
       String dept=request.getParameter("dept");
       String regulation=request.getParameter("regulation");
       String sem=request.getParameter("sem");
       String subcode=request.getParameter("subject");
       String ayear=request.getParameter("ayear");
       String year=null;
       Connection conn=null;
       Statement stmt=null;
       try{
       conn=new dbcon().getConnection(dept);
       stmt=conn.createStatement();
       ResultSet rs=stmt.executeQuery("select ayear from subject_sem_table where sem='"+sem+"' and subcode='"+subcode+"' and regulation='"+regulation+"'");
       if(rs.next())
       {
       year=rs.getString("ayear");
       ayear=year+","+ayear;
       stmt.executeUpdate("update subject_sem_table set ayear='"+ayear+"' where sem='"+sem+"' and subcode='"+subcode+"' and regulation='"+regulation+"'");
       response.sendRedirect("dept/ElectiveAdded.jsp?msg=Elective Assigned Successfully!!");
       }
       else{
       response.sendRedirect("dept/ElectiveAdded.jsp?msg=Some Error Occured!!");
       
       }
       
       }catch(Exception e){
       response.sendRedirect("dept/ElectiveAdded.jsp?msg=Some Error Occured!!");
       
           e.printStackTrace();
       }finally{
           try {
               if(stmt!=null)
                   stmt.close();
               if(conn!=null)
                   conn.close();
           } catch (SQLException ex) {
           ex.printStackTrace();
           }
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
