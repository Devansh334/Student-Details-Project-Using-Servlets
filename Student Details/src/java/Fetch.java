
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
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
public class Fetch extends HttpServlet {

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
  
        
       try{
           
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Connect","root","Dev_123goyal");
           Statement smt = cn.createStatement();
           ResultSet rs=smt.executeQuery("select * from student order by roll");
           out.print("<table border = '2' cellspacing='5' cellpadding='5'>");
           out.print("<tr><th>Roll no</th><th>Name</th><th>Percentage</th><th>Edit Data</th><th>Delete Data</th></tr>");
           while(rs.next()){
               
               String roll= rs.getString("roll");
               out.print("<tr><td>"+rs.getString("roll")+"</td><td> "+rs.getString("name")+"</td><td> "+rs.getString("per")+"</td><td><a href = 'Edit?rolll="+roll+"'><img alt='edit' width='20' height='20' src='Images/edit.png'></a></td><td><a href = 'Delete?rolll="+roll+"'><img alt='delete' width='20' height='20' src='Images/delete.png'></a></td></tr>");
           }
           
           
           out.print("</table>");
           cn.close();
       }
       catch(ClassNotFoundException | SQLException e){
           out.print(e.getMessage());
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
