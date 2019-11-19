package ua.nure.cs.galayda.usermanagement.db;

import ua.nure.cs.galayda.usermanagement.entity.User;

import java.util.Calendar;

public class TestMain {
    public static void main(String[] args) {
        User user = new User();
        user.setFirstName("Galayda");
        user.setLastName("Alexander");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.MARCH, 27);
        user.setDateOfBirth(calendar.getTime());
        DAO<User> systemUserDAO = DAOFactory.getInstance().getUserDao();
        try {
            User userCreating = systemUserDAO.create(user);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }


}
