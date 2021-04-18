import org.junit.jupiter.api.Assertions;
import health.PatientView;
import javax.swing.JButton;
import javax.swing.JList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PatientTests {

    private PatientView patientView;

    @BeforeEach
    public void setup() {
        patientView = new PatientView("patient");
        patientView.setUserType("Patient");
    }

    @Test
    public void checkPatientCanViewRequests() {
        JButton newButton =  patientView.getNewButton();
        JList requestsList = patientView.getRequestsList();
        JButton openButton = patientView.getOpenButton();

        newButton.doClick();
        requestsList.setSelectedIndex(1);
        openButton.doClick();

        Assertions.assertFalse(patientView.isVisible());
    }
}
