package usa.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import usa.stqa.pft.addressbook.model.ContactData;
import usa.stqa.pft.addressbook.model.Contacts;

import java.sql.*;

public class DbConnectionContactTest {

    @Test
    public void testDbConnectionContacts(){
        Connection conn = null;
        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select id, firstname, lastname, email from addressbook ");
            Contacts contacts = new Contacts();
            while (rs.next()){
                contacts.add(new ContactData().withId(rs.getInt("id")).withFirstName(rs.getString("firstname"))
                        .withLastName(rs.getString("lastname")).withEmailAddress(rs.getString("email")));
            }
            rs.close();
            st.close();
            conn.close();

            System.out.println(contacts);
            // Do something with the Connection
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
