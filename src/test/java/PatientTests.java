import org.junit.jupiter.api.Assertions;
import health.PatientView;
import health.RequestConversation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PatientTests {

    private PatientView patientView;
    private static Connection conn;
            
    @BeforeAll
    public static void setupAll(){
        try{
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:bin\\health.sqlite");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    @AfterAll
    public static void teadDownAll() throws SQLException{
        conn.close();
    }

    @BeforeEach
    public void setup() {
        patientView = new PatientView("patient");
        patientView.setUserType("Patient");
        String sql = "insert into Request (RID, PUsername, Date, Status) values (555, \"patient\", 5-5-2000, \"New\");";
        
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PatientTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterEach
    public void tearDown() {
        String sql = "delete from Request where RID = 555;";
        String sql2 = "delete from Message where RID = 555;";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PatientTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql2);
        } catch (SQLException ex) {
            Logger.getLogger(PatientTests.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    @Test
    public void ensurePatientCanUpdateRequest(){
        RequestConversation r = new RequestConversation(555, "patient", "Patient");
        r.setVisible(true);        
        javax.swing.JTextArea ta = r.getAddToRequestTA();
        ta.setText("I need help");
        javax.swing.JButton addBtn = r.getAddButton();
        addBtn.doClick();
        String msg = "Are you sure you want to add your message to the request?";
        Assertions.assertEquals(msg, r.getNotifyMsg());
        ta.setText("I need help");
        addBtn.doClick();
        Assertions.assertEquals(msg, r.getNotifyMsg());
    }
}
