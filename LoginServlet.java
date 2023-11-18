import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (validateUser(email, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            response.sendRedirect("welcome.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    private boolean validateUser(String email, String password) {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            String url = "jdbc:mysql://localhost:3306/mydata";
            Connection connection = DriverManager.getConnection(url);

            // Check if the user exists in the database
            String selectQuery = "SELECT * FROM auth WHERE email = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean isValidUser = resultSet.next();

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();

            return isValidUser;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
