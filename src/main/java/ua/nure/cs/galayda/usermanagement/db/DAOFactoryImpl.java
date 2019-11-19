package ua.nure.cs.galayda.usermanagement.db;

import ua.nure.cs.galayda.usermanagement.entity.User;

public class DAOFactoryImpl extends DAOFactory {

    @SuppressWarnings("unchecked")
    public DAO<User> getUserDao() {
        DAO<User> result = null;
        try {
            Class<?> clazz = Class.forName(properties.getProperty(USER_DAO));
            result = (DAO<User>) clazz.newInstance();
            result.setConnectionFactory(getConnectionFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}