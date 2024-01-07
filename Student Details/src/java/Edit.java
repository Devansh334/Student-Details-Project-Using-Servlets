/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DEVANSH GOYAL
 */
public class Edit extends HttpServlet {

   //roll no coming by Fetch in rolll variable
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       PrintWriter out = response.getWriter();
       String roll=request.getParameter("rolll");
       String name=" ";
       String per=" ";
       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Connect","root","Dev_123goyal");
           Statement smt=cn.createStatement();
           
           ResultSet rs =smt.executeQuery("select * from student where roll ="+roll);
           
           if(rs.next()){
               name =rs.getString("name");
                per =rs.getString("per");
           }
           
           out.print("<h1>Update Details</h1><br>");
           out.print("<form action = 'Update' >");
           out.print("<h3>Roll Number : </h3>");
           out.print("<input type='text' name='roll' value='"+roll+"' readonly><br>");
           out.print("<h3>Edit Name : </h3>");
           out.print("<input type='text' name='name' value='"+name+"' required ><br>");
           out.print("<h3>Edit percentage : </h3>");
           out.print("<input type='text' name='per' value='"+per+"' ><br><br>");
           out.println("<input type='submit' value='Save'>");
           
            out.print("</form>");
           
           cn.close();
       }
       catch(ClassNotFoundException | SQLException e){
           //e.printStackTrace();
           out.println(e.getMessage());
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
        processRequest(request, response);
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
