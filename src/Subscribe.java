import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

/**
 * Created by roland on 22/03/2017.
 */
public class Subscribe extends HttpServlet {

    private UserManager userManager = null;

    @Override
    public void init() {
        try {
            this.userManager = new UserManager();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res)
            throws javax.servlet.ServletException, IOException {
        String name = "";
        String pwd = "";
        name = req.getParameter("name");
        pwd = req.getParameter("pwd");
        try {
            this.userManager.createUser(name, pwd);
            HttpSession session = req.getSession();
            session.setAttribute("username", name);
            session.setAttribute("connected", "yes");
            this.getServletContext().getRequestDispatcher("/user-list").forward(req, res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res)
            throws javax.servlet.ServletException, IOException {

    }
}
