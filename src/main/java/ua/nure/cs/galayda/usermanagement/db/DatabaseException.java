package ua.nure.cs.galayda.usermanagement.db;

public class DatabaseException extends Exception {

    public DatabaseException(Exception e) {
        super(e);
    }

    public DatabaseException(String s) {
        super(s);
    }
}
