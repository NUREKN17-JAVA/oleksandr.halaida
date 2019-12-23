package ua.nure.cs.galayda.usermanagement.web;

import org.junit.Before;
import org.junit.Test;
import ua.nure.cs.galayda.usermanagement.entity.User;

import java.text.DateFormat;
import java.util.Date;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertNotNull;

public class AddServletTest extends MockServletTestCase {
    @Before
    public void setUp() throws Exception {
        super.setUp();
        createServlet(AddServlet.class);
    }

    @Test
    public void testAdd() {
        Date date = new Date();
        User newUser = new User("John", "Doe", date);
        User user = new User(new Long(1000), "John", "Doe", date);
        getMockUserDao().expectAndReturn("create", newUser, user);

        addRequestParameter("firstName", "John");
        addRequestParameter("lastName", "Doe");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
    }

    @Test
    public void testAddEmptyFirstName() {
        Date date = new Date();
        addRequestParameter("lastName", "Doe");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory()
                .getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope",
                errorMessage);
        assertSame("First name is empty", errorMessage);
    }

    @Test
    public void testAddEmptyLastName() {
        Date date = new Date();
        addRequestParameter("firstName", "John");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory()
                .getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope",
                errorMessage);
        assertSame("Last name is empty", errorMessage);
    }

    @Test
    public void testAddEmptyDate() {
        addRequestParameter("firstName", "John");
        addRequestParameter("lastName", "Doe");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory()
                .getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope",
                errorMessage);
        assertSame("Date is empty", errorMessage);
    }

    @Test
    public void testAddEmptyDateIncorrect() {
        addRequestParameter("firstName", "John");
        addRequestParameter("lastName", "Doe");
        addRequestParameter("date", "hsdfkjshdfk");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory()
                .getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope",
                errorMessage);
        assertSame("Date format is incorrect", errorMessage);
    }
}