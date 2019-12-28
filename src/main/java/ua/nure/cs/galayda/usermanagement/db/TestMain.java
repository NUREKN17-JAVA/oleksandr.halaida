package ua.nure.cs.galayda.usermanagement.db;



import ua.nure.cs.galayda.usermanagement.entity.User;

import java.util.Calendar;

public class TestMain {
    public static void main(String[] args) {
        User systemUser = new User();
        systemUser.setFirstName("Nadechka");
        systemUser.setLastName("Malitskaya");
        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, Calendar.OCTOBER, 9);
        systemUser.setDateOfBirth(calendar.getTime());
        DAO<User> systemUserDAO = DAOFactory.getInstance().getUserDao();
        try {
            User systemUser1 = systemUserDAO.create(systemUser);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }


}
