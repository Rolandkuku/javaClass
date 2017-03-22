import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by roland on 22/03/2017.
 */
@WebServlet(name = "Login")
public class Login extends HttpServlet {

    private UserManager userManager;

    @Override
    public void init() {
        try {
            this.userManager = new UserManager();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("name");
        String pwd = req.getParameter("pwd");
        try {
            User user = this.userManager.getUser(name);
            if (pwd.compareTo(user.getPwd()) == 0 && pwd.compareTo("") != 0) {
                // HttpSession session = req.getSession();
                req.getSession().setAttribute("name", user.getName());
                req.getSession().setAttribute("connected", "yes");
                res.sendRedirect("/user-list");
            } else {
                res.sendRedirect("/login");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if ( session.getAttribute("connected") != null && ((String) (session.getAttribute("connected"))).compareTo("yes")==0) {
            this.getServletContext().getRequestDispatcher("/user-list").forward(req, res);
        }
        this.getServletContext().getRequestDispatcher("/login.jsp").forward(req, res);
    }
}
