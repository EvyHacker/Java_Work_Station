package usa.stqa.pft.addressbook;

public class GroupContactData {
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String emailAddress;

	public GroupContactData(String firstName, String lastName, String mobileNumber, String emailAddress) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
}