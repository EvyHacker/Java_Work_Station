package usa.stqa.pft.addressbook.model;

public class ContactData {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String group;

    public ContactData(String firstName, String lastName, String phoneNumber, String emailAddress, String group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.group = group;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getGroup() {
        return group;
    }
}