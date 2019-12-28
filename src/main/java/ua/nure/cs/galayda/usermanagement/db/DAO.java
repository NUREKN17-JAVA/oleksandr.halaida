package ua.nure.cs.galayda.usermanagement.db;

import java.util.Collection;

public interface DAO<T> {
    T create(T t) throws DatabaseException;

    void update(T t) throws DatabaseException;

    void delete(T t) throws DatabaseException;

    T find(Long id) throws DatabaseException;

    Collection find(String firstName, String lastName) throws DatabaseException;

    Collection<T> findAll() throws DatabaseException;

    void setConnectionFactory(ConnectionFactory connectionFactory);
}
