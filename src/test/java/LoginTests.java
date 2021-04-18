import health.NewJFrame;
import javax.swing.JButton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTests {

    private NewJFrame testNewJFrame;

    @BeforeEach
    public void setup() {
        testNewJFrame = new NewJFrame();
    }

    @Test
    public void validPatientCanLogIn() {
        testNewJFrame.setUsername("patient");
        testNewJFrame.setPassword("asdf");

        JButton loginButton = testNewJFrame.getLoginAsPatientButton();

        loginButton.doClick();
        Assertions.assertNotEquals("Incorrect username or password.  Please try again.", testNewJFrame.getSuccessPane().getMessage().toString());
    }

    @Test
    public void invalidPatientCanLogIn() {
        testNewJFrame.setUsername("");
        testNewJFrame.setPassword("");

        JButton loginButton = testNewJFrame.getLoginAsPatientButton();

        loginButton.doClick();
        Assertions.assertEquals("Incorrect username or password.  Please try again.", testNewJFrame.getSuccessPane().getMessage().toString());
    }

    @Test
    public void invalidPatientCanLogInInvalidPassword() {
        testNewJFrame.setUsername("patient");
        testNewJFrame.setPassword("");

        JButton loginButton = testNewJFrame.getLoginAsPatientButton();

        loginButton.doClick();
        Assertions.assertEquals("Incorrect username or password.  Please try again.", testNewJFrame.getSuccessPane().getMessage().toString());
    }

    @Test
    public void invalidPatientCanLogInInvalidUsername() {
        testNewJFrame.setUsername("12345");
        testNewJFrame.setPassword("123");

        JButton loginButton = testNewJFrame.getLoginAsPatientButton();

        loginButton.doClick();
        Assertions.assertEquals("Incorrect username or password.  Please try again.", testNewJFrame.getSuccessPane().getMessage().toString());
    }

    @Test
    public void invalidPatientCanLogInSQLInjection() {
        testNewJFrame.setUsername("\" or \"\"=\"");
        testNewJFrame.setPassword("\" or \"\"=\"");

        JButton loginButton = testNewJFrame.getLoginAsPatientButton();

        loginButton.doClick();
        Assertions.assertEquals("Incorrect username or password.  Please try again.", testNewJFrame.getSuccessPane().getMessage().toString());
    }

    @Test
    public void validDoctorCanLogIn() {
        testNewJFrame.setUsername("doctor");
        testNewJFrame.setPassword("asdf");

        JButton loginButton = testNewJFrame.getLoginAsDoctorButton();

        loginButton.doClick();
        Assertions.assertNotEquals("Incorrect username or password.  Please try again.", testNewJFrame.getSuccessPane().getMessage().toString());
    }

    @Test
    public void invalidDoctorCanLogIn() {
        testNewJFrame.setUsername("");
        testNewJFrame.setPassword("");

        JButton loginButton = testNewJFrame.getLoginAsDoctorButton();

        loginButton.doClick();
        Assertions.assertEquals("Incorrect username or password.  Please try again.", testNewJFrame.getSuccessPane().getMessage().toString());
    }

    @Test
    public void invalidDoctorCanLogInInvalidPassword() {
        testNewJFrame.setUsername("doctor");
        testNewJFrame.setPassword("");

        JButton loginButton = testNewJFrame.getLoginAsDoctorButton();

        loginButton.doClick();
        Assertions.assertEquals("Incorrect username or password.  Please try again.", testNewJFrame.getSuccessPane().getMessage().toString());
    }

    @Test
    public void invalidDoctorCanLogInInvalidUsername() {
        testNewJFrame.setUsername("12345");
        testNewJFrame.setPassword("abcdef");

        JButton loginButton = testNewJFrame.getLoginAsDoctorButton();

        loginButton.doClick();
        Assertions.assertEquals("Incorrect username or password.  Please try again.", testNewJFrame.getSuccessPane().getMessage().toString());
    }

    @Test
    public void invalidDoctorCanLogInSQLInjection() {
        testNewJFrame.setUsername("\" or \"\"=\"");
        testNewJFrame.setPassword("\" or \"\"=\"");

        JButton loginButton = testNewJFrame.getLoginAsDoctorButton();

        loginButton.doClick();
        Assertions.assertEquals("Incorrect username or password.  Please try again.", testNewJFrame.getSuccessPane().getMessage().toString());
    }

    @AfterEach
    public void teardown() {
        testNewJFrame = null;
    }
}
