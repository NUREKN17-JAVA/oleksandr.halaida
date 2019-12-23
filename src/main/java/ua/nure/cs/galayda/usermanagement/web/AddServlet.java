package ua.nure.cs.galayda.usermanagement.web;

import ua.nure.cs.galayda.usermanagement.db.DAOFactory;
import ua.nure.cs.galayda.usermanagement.db.DatabaseException;
import ua.nure.cs.galayda.usermanagement.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends EditServlet {

    @Override
    protected void processUser(User user) throws DatabaseException {
        DAOFactory.getInstance().getUserDao().create(user);
    }

    @Override
    protected void showPage(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/add.jsp").forward(req, resp);
    }
}
