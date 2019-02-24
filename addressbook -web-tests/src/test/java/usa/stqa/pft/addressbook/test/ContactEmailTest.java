package usa.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import usa.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTest extends TestBase {
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
    public void testContactEmails() {
        app.goTo().goToContactPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return  Arrays.asList(contact.getEmailAddress(), contact.getEmailAddress2(), contact.getEmailAddress3())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactEmailTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String emails){
        return emails.replaceAll("\n", "");
    }
}


