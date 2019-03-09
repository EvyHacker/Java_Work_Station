package usa.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import usa.stqa.pft.addressbook.model.ContactData;
import usa.stqa.pft.addressbook.model.Contacts;
import usa.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

    public class DeleteContactFromGroupTest extends TestBase {

        @BeforeMethod
        public void ensurePreconditions() {
            if (app.db().contacts().size() == 0) {
                app.goTo().returnToGroupPage();
                app.contact().create(new ContactData()
                        .withFirstName("Evy").withLastName("Klimovich").withPhoneNumber(
                                "571-241-6524").withEmailAddress("gaidarenko1241@gmail.com")
                                .inGroup(app.db().groups().iterator().next()), true);
            }
            if (app.db().groups().size() == 0) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("GroupForDeletionContactFrom"));
            }

            if (app.db().contacts().iterator().next().getGroups().size() == 0) {
                app.contact().addToGroup(app.db().contacts().iterator().next(), app.db().groups().iterator().next().getId());
            }
        }

        @Test
        public void testDeleteContactFromGroup() throws Exception {

            Contacts contactsDb = app.db().contacts();
            ContactData removedContact = contactsDb.iterator().next();
            GroupData groupsContact = removedContact.getGroups().iterator().next();

            app.goTo().returnToGroupPage();
            app.contact().deleteFromGroup(removedContact, groupsContact.getId());

            Integer idContact = removedContact.getId();
            ContactData checkContact = app.db().contacts().stream()
                    .filter(c -> (idContact == c.getId()))
                    .findAny()
                    .orElse(null);

            assertThat((removedContact.getGroups().size()-1), equalTo(checkContact.getGroups().size()));
        }

    }

