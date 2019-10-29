package ua.nure.cs.galayda.usermanagement;

import junit.framework.TestCase;
import ua.nure.cs.galayda.usermanagement.User;

import java.util.Calendar;

public class UserTest extends TestCase {
    public static final int YEAR = 2000;
    private static final String ETALON_FULL_NAME = "Doe, John";
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    public static final int MONTH = 03;
    public static final int DATE = 27;
    Calendar calendar = Calendar.getInstance();
    private User user;

    public void testGetFullName() {
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        assertEquals(ETALON_FULL_NAME, user.getFullName());
    }

    public void testGetAgeBirthDateInTheFuture() {

        calendar.set(YEAR, MONTH, DATE);
        user.setDateOfBirth(calendar.getTime());
        assertEquals(19, user.getAge());
    }

    public void testGetNotEqualsAge() {
        calendar.set(YEAR, MONTH, DATE);
        user.setDateOfBirth(calendar.getTime());
        assertNotSame(20, user.getAge());
    }

    public void testNewAgeOfUser() {
        int ageExpected = 0;
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        user.setDateOfBirth(calendar.getTime());
        int ageActual = user.getAge();
        assertEquals(ageExpected, ageActual);
    }

    public void testGetIncorrectFullName() {
        user.setFirstName("FIRST_NAME");
        user.setLastName(LAST_NAME);
        assertNotSame(ETALON_FULL_NAME, user.getFullName());
    }

    public void testGetNullUser() {
        user.setFirstName("not Null");
        assertNotNull(user.getFirstName());
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        user = new User();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
