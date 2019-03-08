package usa.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import usa.stqa.pft.addressbook.model.ContactData;
import usa.stqa.pft.addressbook.model.Contacts;
import usa.stqa.pft.addressbook.model.GroupData;
import usa.stqa.pft.addressbook.model.Groups;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


    public class AddContactToGroupTest extends TestBase {

        @BeforeMethod
        public void ensurePreconditions() {
            if (app.db().contacts().size() == 0) {
                app.goTo().returnToGroupPage();
                app.contact().create(new ContactData().withFirstName("Evy").withLastName("Klimovich").withPhoneNumber(
                        "571-241-6524").withEmailAddress("gaidarenko1241@gmail.com")
                        .inGroup(app.db().groups().iterator().next()), true);
            }
            if (app.db().groups().size() == 0) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("test0"));
            }
        }

        @Test
        public void testAddContactToGroup() throws Exception {

            Groups groupsDb = app.db().groups();
            Contacts contactsDb = app.db().contacts();
            ContactData addedContact = contactsDb.iterator().next();

            while (contactsDb.size() != 0) {
                if (addedContact.getGroups().size() == groupsDb.size()) {
                    contactsDb.remove(addedContact);
                    if (contactsDb.size() == 0) {
                        app.goTo().returnToGroupPage();
                        app.group().create(new GroupData().withName(String.format("GroupForContact %s", addedContact.getLastName())));
                    } else
                        addedContact = contactsDb.iterator().next();
                } else break;
            }

            List<GroupData> listGroupWithoutAddedContact = new ArrayList<GroupData>(groupsDb);
            for (GroupData groupContact : addedContact.getGroups()) {
                for (GroupData groupDb : groupsDb) {
                    if (groupContact.getId() == groupDb.getId()) {
                        listGroupWithoutAddedContact.remove(groupDb);
                    }
                }
            }

            for (GroupData li : listGroupWithoutAddedContact) {
                app.goTo().returnToGroupPage();
                app.contact().addToGroup(addedContact, li.getId());
            }

            assertThat((addedContact.getGroups().size()+listGroupWithoutAddedContact.size()), equalTo(groupsDb.size()));

            Integer idContact = addedContact.getId();
            addedContact = app.db().contacts().stream()
                    .filter(c -> (idContact == c.getId()))
                    .findAny()
                    .orElse(null);
            

        }
    }

