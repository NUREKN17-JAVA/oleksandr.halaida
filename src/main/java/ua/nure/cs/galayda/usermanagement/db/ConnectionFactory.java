package ua.nure.cs.galayda.usermanagement.db;

import java.sql.Connection;

public interface ConnectionFactory {
    Connection createConnection() throws
           ua.nure.cs.galayda.usermanagement.db.DatabaseException;
}
