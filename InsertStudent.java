import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class InsertStudent extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("student_id");
        String name = request.getParameter("student_name");
        String course = request.getParameter("course");
        String branch = request.getParameter("branch");
        String year = request.getParameter("year");
        String email = request.getParameter("studentemailid");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mydb", "root", "your_password");

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO students VALUES (?, ?, ?, ?, ?, ?)");

            ps.setInt(1, Integer.parseInt(id));
            ps.setString(2, name);
            ps.setString(3, course);
            ps.setString(4, branch);
            ps.setInt(5, Integer.parseInt(year));
            ps.setString(6, email);

            int i = ps.executeUpdate();
            if (i > 0) {
                out.println("<h3>Student inserted successfully!</h3>");
            } else {
                out.println("<h3>Insertion failed.</h3>");
            }

            con.close();

        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }

        out.close();
    }
}
