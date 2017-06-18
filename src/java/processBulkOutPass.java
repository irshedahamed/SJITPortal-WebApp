/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Actor.Parent;
import Actor.Student;
import Forms.OutPass;
import com.action.SMSTemplate;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Irshed
 */
public class processBulkOutPass extends HttpServlet {

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
            out.println("<title>Servlet processBulkOutPass</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet processBulkOutPass at " + request.getContextPath() + "</h1>");
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
      //  processRequest(request, response);
    String[] id = request.getParameterValues("outpass");
    if(id!=null)
    {
        for(int i = 0 ; i < id.length ; i++)
        {
                 OutPass p=new OutPass();
     p.setRollno(id[i]);
     p.setReason(request.getParameter("reason"));
     p.setFrom(request.getParameter("from"));
     p.setTill(request.getParameter("till"));
     p.setStatus(request.getParameter("status"));    
        boolean res=p.insert();
        //System.err.println(p.getStatus().equals("Accepted"));
        
      //  System.err.println(res);
        if(p.getStatus().equals("Accepted")&&res){
        General.OutPass op=new General.OutPass(p.getRollno());
     
        if(op.insert(p.getRequestid())){
            
            if(Student.getById(p.getRollno()).getAccomodation().equalsIgnoreCase("hostel"))
            SMSTemplate.send(Parent.getNumber(p.getRollno()),p.getSMSContent());
            response.getWriter().write("<b>");
            response.getWriter().print(i+1);
            response.getWriter().write(".");
            response.getWriter().write(Student.getById(id[i]).getName());
            response.getWriter().write("</b><br>");
            response.setContentType("text/html;charset=UTF-8");
         response.getWriter().println("<link href='css/bootstrap.min.css' rel='stylesheet'>");
        }
        }
        
  
        }response.getWriter().write("<center><h1>Out Pass Generated and valid for 6 hours</h1>");
        response.getWriter().write("<a href='hostel/bulkOutPass.jsp'><input type='button' id='submit' value='Back' /></a></center>");
           
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
