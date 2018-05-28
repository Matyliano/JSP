package web.jdbc;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "TestServlet")
public class TestServlet extends HttpServlet {

    @Resource(name = "jdbc/web_student_tracker")
    private DataSource dataSource;

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {


    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        //set up the printwriter
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");

        //Get a connection to the database
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();
            //Create a SQL statements
            String sql = "select * from students";
            myStmt = myConn.createStatement();
            //Execute SQL query
            myRs = myStmt.executeQuery(sql);

            //Process the result set
            while (myRs.next()) {
                String email = myRs.getString("email");
                out.println(email);
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
