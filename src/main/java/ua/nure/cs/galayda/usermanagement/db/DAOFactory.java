package ua.nure.cs.galayda.usermanagement.db;

import ua.nure.cs.galayda.usermanagement.entity.User;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public abstract class DAOFactory {

    protected static final String USER_DAO = "dao.UserDao";
    private static final String DAO_FACTORY = "dao.Factory";
    protected static Properties properties;

    private static DAOFactory instance;

    static {
        properties = new Properties();
        try {
            properties.load(Objects.requireNonNull(
                    DAOFactory.class.getClassLoader()
                            .getResourceAsStream("settings.properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized DAOFactory getInstance() {
        if (instance == null) {
            Class<?> factoryClass;
            try {
                factoryClass = Class
                        .forName(properties.getProperty(DAO_FACTORY));
                instance = (DAOFactory) factoryClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    public static void init(Properties prop) {
        properties = prop;
        instance = null;
    }

    protected ConnectionFactory getConnectionFactory() {
        return new ConnectionFactoryImpl(properties);
    }

    public abstract DAO<User> getUserDao();
}
