package ua.nure.cs.galayda.usermanagement;
import junit.framework.TestCase;
import ua.nure.cs.galayda.usermanagement.db.DAO;
import ua.nure.cs.galayda.usermanagement.db.DAOFactory;
import ua.nure.cs.galayda.usermanagement.entity.User;

public class DAOFactoryTest extends TestCase {

    public void testGetUserDAO(){
        DAOFactory daoFactory = DAOFactory.getInstance();
        assertNotNull("DAOFactory instance is null", daoFactory);
        DAO<User> result = daoFactory.getUserDao();
        assertNotNull("UserDao instance is null", result);
    }
}
