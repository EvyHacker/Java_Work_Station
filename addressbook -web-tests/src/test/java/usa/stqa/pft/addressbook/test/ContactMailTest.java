package usa.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import usa.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactMailTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().goToContactPage();
        if (app.contact().all().size() == 0) {//Добавлена проверка и обеспечение предусловий выполнения GroupModificationTests и GroupDeletionTest
            app.contact().create(new ContactData().withFirstName("Evy").withLastName("Klimovich")
                            .withAllAddresses("1575 Anderson Rd, Apt1214, Mclean, VA, 22102")
                            .withPhoneNumber("571-241-6524").withEmailAddress("gaidarenko1241@gmail.com").withGroup("[none]"),
                    true);
        }
    }

    @Test
            public void testContactAddresses() {
        app.goTo().goToContactPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(cleaned(contact.getAllAddresses()), equalTo(cleaned(contactInfoFromEditForm.getFirstAddress())));
        if (contact.getAllAddresses() == String.valueOf(0)){
            assertThat(cleaned(contact.getAllAddresses()), equalTo(cleaned(contactInfoFromEditForm.getSecondAddress())));
        }
    }

    public static String cleaned(String address){
        return address.replaceAll("\n", "");
    }
}
