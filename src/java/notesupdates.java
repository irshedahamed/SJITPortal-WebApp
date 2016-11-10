/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.action.Base;
import dbconnection.dbcon;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.System.out;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.Part;

/**
 *
 * @author aravind
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
        maxFileSize = 1024 * 1024 * 50, // 50 MB
        maxRequestSize = 1024 * 1024 * 100)      // 100 MB
public class notesupdates extends HttpServlet {

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
            out.println("<title>Servlet subjectallocupdate</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet subjectallocupdate at " + request.getContextPath() + "</h1>");
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
    private static final long serialVersionUID = 205242440643911308L;

    /**
     * Directory where uploaded files will be saved, its relative to the web
     * application directory.
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
        String ayear ="";
        String course ="";
        String dept = "";
        String batch = "";
        String sem = "";
        String subject = "";
        String subcode = "";
       
        String notes = "";
        String descp="";
String name="";
String UPLOAD_DIRECTORY="hello";
        
       if(ServletFileUpload.isMultipartContent(request)){
            try {
                
                List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(request);
              
                for(FileItem item : multiparts){
                    if (item.isFormField()) {
            if (item.getFieldName().equals("ayear")) {
                ayear = item.getString();
                // Do something with the value
            }
             if (item.getFieldName().equals("course")) {
                course = item.getString();
                // Do something with the value
            }
              if (item.getFieldName().equals("dept")) {
                dept = item.getString();
                // Do something with the value
            }
               if (item.getFieldName().equals("batch")) {
                batch = item.getString();
                // Do something with the value
            }
               if (item.getFieldName().equals("sem")) {
                sem = item.getString();
                // Do something with the value
            }
               if (item.getFieldName().equals("desc")) {
                descp = item.getString();
                // Do something with the value
            }
                if (item.getFieldName().equals("notes")) {
                notes = item.getString();
                // Do something with the value
            }
                if (item.getFieldName().equals("subject")) {
                subcode = item.getString();
                 String[] parts = subcode.split("-");
        subcode = parts[0];
        subject = parts[1];
                // Do something with the value
            }
                     // if(!ayear.equals("")&&!dept.equals("")&&!batch.equals("")&&!sem.equals("")&&!subcode.equals("")&&!notes.equals(""))
                      {
                      UPLOAD_DIRECTORY = Base.path+"/notes/"+ayear+"/"+dept+"/"+batch+"/"+sem+"/"+subcode+"/"+notes+"/";
    File file = new File(UPLOAD_DIRECTORY);
    
 Boolean a = file.mkdirs();
                
        }
                    }
                    if(!item.isFormField()){
                        name = new File(item.getName()).getName();
                        
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                    }
                    
                    
                }
           
               //File uploaded successfully
               request.setAttribute("message", "File Uploaded Successfully "+name );
            } catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex +UPLOAD_DIRECTORY+ayear);
            }          
         
        }else{
            request.setAttribute("message",
                                 "Sorry this Servlet only handles file upload request");
        }
    
        
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection1 = new dbcon().getConnection(dept);
            Statement statement1 = connection1.createStatement();

            statement1.executeUpdate("insert into notes values("+null+",'" + ayear + "','" + sem + "','" + subcode + "','" + notes + "','" + name + "','" + UPLOAD_DIRECTORY + "','"+descp+"')");
             if(statement1!=null)
                            statement1.close();
                              if(connection1!=null)
                                connection1.close();
                              List<String> depts=new ArrayList<String>();
                              depts.add("cse");
                              depts.add("eee");
                              depts.add("ece");
                              depts.add("mech");
                              depts.add("it");
                              depts.add("civil");
                              
                              for(String department:depts){
                                  String newsem;
                                  if(!department.equals(dept)){
                                  ResultSet rs;
                                  connection1 = new dbcon().getConnection(department);
                                  statement1 = connection1.createStatement();
                                  response.getWriter().println(department);
                                  rs=statement1.executeQuery("select * from subject_sem_table where subcode like '"+subcode+"'");
                                  if(rs.next())
                                  {
                                      response.getWriter().println(department+"insert");
                                  newsem=rs.getString("sem");
                                  if(rs!=null)
                                  rs.close();
                                  FileInputStream in=new FileInputStream(new File(UPLOAD_DIRECTORY + File.separator + name));
                                   UPLOAD_DIRECTORY = Base.path+"/notes/"+ayear+"/"+department+"/"+batch+"/"+newsem+"/"+subcode+"/"+notes+"/";
                                   File filecopy = new File(UPLOAD_DIRECTORY);
                                   Boolean a = filecopy.mkdirs();
                                  FileOutputStream out=new FileOutputStream(new File(UPLOAD_DIRECTORY + File.separator + name));
                                   byte data[] = new byte[4096];
                                  while(in.read(data)!=-1){
                                  out.write(data);
                                  }
                                   
                                  
            statement1.executeUpdate("insert into notes values("+null+",'" + ayear + "','" + newsem + "','" + subcode + "','" + notes + "','" + name + "','" + UPLOAD_DIRECTORY + "','"+descp+"')");
                                  
                                  
                                  }
                                  
                                  }
                              }
                                 request.getRequestDispatcher("dept/result.jsp").forward(request, response);
           

        } catch (Exception ex) {
            PrintWriter out = response.getWriter();
            out.println(ex);
        }

    }

    /**
     * Utility method to get file name from HTTP header content-disposition
     */
    
}
