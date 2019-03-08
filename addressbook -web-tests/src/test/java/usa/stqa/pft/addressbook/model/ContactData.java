package usa.stqa.pft.addressbook.model;


import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.*;

@XStreamAlias("contacts")
@Entity
@Table(name="addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name="id")
    private int id  = Integer.MAX_VALUE;
    @Expose
    @Column(name="firstname")
    private String firstName;
    @Expose
    @Column(name="lastname")
    private String lastName;
    @Expose
    @Transient
    private String phoneNumber;
    @Column(name="home")
    @Type(type="text")
    private String homePhone;
    @Column(name="mobile")
    @Type(type="text")
    private String mobilePhone;
    @Column(name="work")
    @Type(type="text")
    private String workPhone;
    @Expose
    @Column(name="email")
    @Type(type="text")
    private String emailAddress;
    @Column(name="email2")
    @Type(type="text")
    private String emailAddress2;
    @Column(name="email3")
    @Type(type="text")
    private String emailAddress3;
    @Transient
    private String allEmails;
    //@Transient
    //private String group;
    @Transient
    private String allPhones;
    @Transient
    private String firstAddress;
    @Transient
    private String secondAddress;
    @Transient
    private String allAddresses;
    @Column(name="photo")
    @Type(type="text")
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="address_in_groups", joinColumns = @JoinColumn(name="id"),
            inverseJoinColumns = @JoinColumn(name="group_id"))

    private Set<GroupData> groups = new HashSet<GroupData>();

    public int getId() { return id; }

    public ContactData withId(int id) { this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ContactData withEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }
    public String getEmailAddress3() { return emailAddress3; }

    public ContactData withEmailAddress3(String emailAddress3) {
        this.emailAddress3 = emailAddress3;
        return this;
    }

    public String getEmailAddress2() { return emailAddress2; }

    public ContactData withEmailAddress2(String emailAddress2) {
        this.emailAddress2 = emailAddress2;
        return this;
    }
    public String getAllEmails() { return allEmails; }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public String getFirstAddress() { return firstAddress; }

    public ContactData withFirstAddress(String firstAddress) {
        this.firstAddress = firstAddress;
        return this;
    }
    public String getSecondAddress() { return secondAddress; }

    public ContactData withAllAddresses(String allAddresses) {
        this.allAddresses = allAddresses;
        return this;
    }

    public String getAllAddresses() { return allAddresses; }

    public ContactData withSecondAddress(String secondAddress) {
        this.secondAddress = secondAddress;
        return this;
    }

    public String getAllPhones(){ return allPhones; }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

//    public ContactData withGroup(String group) {
//        this.group = group;
//        return this;
//    }
    public ContactData inGroup(GroupData group){
        groups.add(group);
        return this;
    }

    public Groups getGroups() {
        return new Groups(groups);
    }

    public void setGroups(Set<GroupData> groups) {
        this.groups = groups;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public File getPhoto() {
        if (photo == null){
            return null;
        }
        return new File (photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    //    public ContactData(int id, String firstName, String lastName) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.phoneNumber = null;
//        this.emailAddress = null;
//        this.group = null;
//    }
//    public ContactData(String firstName, String lastName, String phoneNumber, String emailAddress, String group) {
//        this.id = Integer.MAX_VALUE;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.phoneNumber = phoneNumber;
//        this.emailAddress = emailAddress;
//        this.group = group;
//    }
//
    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getPhoneNumber() { return phoneNumber; }

    public String getHomePhone() { return homePhone; }

    public String getMobilePhone() { return mobilePhone; }

    public String getWorkPhone() { return workPhone; }

    public String getEmailAddress() { return emailAddress; }

   // public String getGroup() { return group; }
}