import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class AuthFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession(false);

        if (session == null || session.getAttribute("email") == null) {
            ((HttpServletResponse) response).sendRedirect("login.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
    }
}
