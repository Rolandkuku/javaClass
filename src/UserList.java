import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by roland on 22/03/2017.
 */
@WebServlet(name = "UserList")
public class UserList extends HttpServlet {

    protected UserManager userManager = null;

    @Override
    public void init() {
        try {
            this.userManager = new UserManager();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            ArrayList<String> userList = this.userManager.getUserList();
            req.setAttribute("userList", userList);
            // System.out.println(session.getAttribute("name"));
            // req.setAttribute("username", session.getAttribute("name"));
            this.getServletContext().getRequestDispatcher("/user_list.jsp").forward(req, res);
            // res.sendRedirect("/user_list.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
