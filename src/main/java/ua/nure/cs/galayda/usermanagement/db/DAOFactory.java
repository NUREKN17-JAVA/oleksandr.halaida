package ua.nure.cs.galayda.usermanagement.db;

import ua.nure.cs.galayda.usermanagement.entity.User;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public abstract class DAOFactory {

    protected static final String USER_DAO = "dao.UserDao";
    private static final String DAO_FACTORY = "dao.Factory";

    protected static Properties properties;
    protected static DAOFactory instance;

    static { //позволяет запускать фрагмент кода, когда жвм загружает файл класса в память, тем самым подготавливаем нашу фабрику к работе
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

    public static void init(
            Properties properties) { // позволяет заменить свойства, чтобы подложить mock-объект
        DAOFactory.properties = properties;
        instance = null; //пересоздаём фабрику уже с подлженными свойствами новыми
    }

    protected ConnectionFactory getConnectionFactory() {
        return new ConnectionFactoryImpl(properties);
    }

    public abstract DAO<User> getUserDao();
}
