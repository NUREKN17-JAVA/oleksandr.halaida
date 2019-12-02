package ua.nure.cs.galayda.usermanagement.db;

import ua.nure.cs.galayda.usermanagement.entity.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MockUserDAO implements DAO<User> {
    private long id = 0;
    private Map<Long, User> users = new HashMap<>();

    public User create(User user) throws DatabaseException {
        System.out.println("create " + user);
        Long currentId = ++id;
        user.setId(currentId);
        users.put(currentId, user);
        return user;
    }

    public void update(User user) throws DatabaseException {
        Long currentId = user.getId();
        users.remove(currentId);
        users.put(currentId, user);
    }

    public void delete(User user) throws DatabaseException {
        Long currentId = user.getId();
        users.remove(currentId);
    }

    public User find(Long id) throws DatabaseException {
        return users.get(id);
    }

    public Collection<User> findAll() throws DatabaseException {
        return users.values();
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
    }

    public Collection<User> find(String firstName, String lastName)
            throws DatabaseException {
        throw new UnsupportedOperationException();
    }
}
