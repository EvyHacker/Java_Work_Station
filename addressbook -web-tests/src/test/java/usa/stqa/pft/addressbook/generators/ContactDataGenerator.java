package usa.stqa.pft.addressbook.generators;

import usa.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

    public class ContactDataGenerator {

        public static void main (String [] args) throws IOException {
            int count = Integer.parseInt(args[0]);
            File file = new File(args[1]);

            List<ContactData> contacts = generateContacts(count);
            save(contacts, file);
        }

        private static void save(List<ContactData> contacts, File file) throws IOException {
            System.out.println(new File (".").getAbsolutePath());
            Writer writer = new FileWriter(file);
            for (ContactData contact : contacts){
                writer.write((String.format("%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastName(), contact.getEmailAddress()
                        , contact.getMobilePhone())));
            }
            writer.close();
        }

        private static List<ContactData> generateContacts(int count) {
            List<ContactData> contacts = new ArrayList<ContactData>();
            for (int i=0; i<count; i++){
                contacts.add(new ContactData().withFirstName(String.format("name %s", i))
                        .withLastName(String.format("lastName %s", i)).withEmailAddress(String.format("email %s", i))
                .withMobilePhone(String.format("mobilePhone %s", i)));
            }
            return contacts;
        }
    }

