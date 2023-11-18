import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            String url = "jdbc:mysql://localhost:3306/mydata";
            Connection connection = DriverManager.getConnection(url);

            // Insert user data into the database
            String insertQuery = "INSERT INTO auth (email, fullname, password) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, fullName);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();

            // Close resources
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("login.jsp");
    }
}
