package ua.nure.cs.galayda.usermanagement.web;


import ua.nure.cs.galayda.usermanagement.db.DAOFactory;
import ua.nure.cs.galayda.usermanagement.db.DatabaseException;
import ua.nure.cs.galayda.usermanagement.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;

public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = -477633379571031102L;

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getParameter("okButton") != null) {
            doOk(req, resp);
        } else if (req.getParameter("calncelButton") != null) {
            doCancel(req, resp);
        } else {
            showPage(req, resp);
        }
    }

    private void doOk(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = null;
        try {
            user = getUser(req);
        } catch (ValidationException e) {
            req.setAttribute("error", e.getMessage());
            showPage(req, resp);
            return;
        }
        try {
            processUser(user);
        } catch (DatabaseException e) {
            new ServletException(e);
        }
        req.getRequestDispatcher("/browse").forward(req, resp);
    }

    private User getUser(HttpServletRequest req)
            throws ValidationException {
        User user = new User();
        String idStr = req.getParameter("id");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String dateStr = req.getParameter("date");

        if (firstName == null) {
            throw new ValidationException("First name is empty");
        }
        if (lastName == null) {
            throw new ValidationException("Last name is empty");
        }
        if (dateStr == null) {
            throw new ValidationException("Date is empty");
        }
        if (idStr != null) {
            user.setId(new Long(idStr));
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);
        try {
            user.setDateOfBirth(DateFormat.getDateInstance().parse(dateStr));
        } catch (ParseException e) {
            throw new ValidationException("Date format is incorrect");
        }

        return user;
    }

    protected void processUser(User user) throws DatabaseException {
        DAOFactory.getInstance().getUserDao().update(user);
    }

    private void doCancel(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/browse").forward(req, resp);

    }

    protected void showPage(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/edit.jsp").forward(req, resp);
    }

}