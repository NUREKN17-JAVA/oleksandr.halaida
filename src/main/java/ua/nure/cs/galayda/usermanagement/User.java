package ua.nure.cs.galayda.usermanagement;

import java.io.Serializable;
import java.util.*;

public class User implements Serializable {

    private static final long serialVersionUID = 2604980348026818061L;
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects
                .equals(firstName, user.firstName) && Objects
                .equals(lastName, user.lastName) && Objects
                .equals(dateOfBirth, user.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dateOfBirth);
    }

    public String getFullName() {
        return getLastName() + ", " + getFirstName();
    }

    public int getAge() {
        int current = new Date().getYear();
        int birthYear = getDateOfBirth().getYear();
        return current - birthYear;
    }
}
