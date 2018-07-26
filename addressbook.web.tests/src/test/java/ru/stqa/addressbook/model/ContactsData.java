package ru.stqa.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contacts")
@Entity
@Table(name = "addressbook")
public class ContactsData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstname")
    private String test_first_name;

    @Expose
    @Column(name = "middlename")
    private String test_middle_name;

    @Expose
    @Column(name = "lastname")
    private String test_last_name;

    @Column(name = "nickname")
    private String test_nickname;

    @Column(name = "title")
    private String test_title;

    @Column(name = "company")
    private String test_compane;

    @Column(name = "address")
    @Type(type = "text")
    private String test_address;

    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;

    @Type(type = "text")
    @Column(name = "mobile")
    private String mobile;

    @Type(type = "text")
    @Column(name = "email")
    private String email1;

    @Type(type = "text")
    @Column(name = "email2")
    private String email2;

    @Type(type = "text")
    @Column(name = "email3")
    private String email3;

    @Column(name = "work")
    @Type(type = "text")
    private String work_phone;

    @Transient
    private String allPhones;

    @Type(type = "text")
    @Column(name = "photo")
    private String photo;

    @Transient
    private String allEmails;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable (name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name =  "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();


    public File getPhoto() {
        return new File(photo);
    }

    public ContactsData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }


    public String getAllEmails() {
        return allEmails;
    }

    public ContactsData withEmail1(String email1) {
        this.email1 = email1;
        return this;
    }

    public String getEmail2() {
        return email2;
    }

    public ContactsData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public String getEmail3() {
        return email3;
    }

    public ContactsData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactsData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }


    public String getAllPhones() {
        return allPhones;
    }

    public ContactsData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }


    @Override
    public String toString() {
        return "ContactsData{" +
                "id='" + id + '\'' +
                ", test_first_name='" + test_first_name + '\'' +
                ", test_last_name='" + test_last_name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactsData that = (ContactsData) o;
        return id == that.id &&
                Objects.equals(test_first_name, that.test_first_name) &&
                Objects.equals(test_last_name, that.test_last_name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, test_first_name, test_last_name);
    }

    public int getId() {
        return id;
    }

    public ContactsData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactsData withFirstName(String test_first_name) {
        this.test_first_name = test_first_name;
        return this;
    }

    public ContactsData withWorkPhone(String work_phone) {
        this.work_phone = work_phone;
        return this;
    }

    public ContactsData withMiddleName(String test_middle_name) {
        this.test_middle_name = test_middle_name;
        return this;
    }

    public ContactsData withLastName(String test_last_name) {
        this.test_last_name = test_last_name;
        return this;
    }

    public ContactsData withNickname(String test_nickname) {
        this.test_nickname = test_nickname;
        return this;
    }

    public ContactsData withTitle(String test_title) {
        this.test_title = test_title;
        return this;
    }

    public ContactsData withCompane(String test_compane) {
        this.test_compane = test_compane;
        return this;
    }

    public ContactsData withAddress(String test_address) {
        this.test_address = test_address;
        return this;
    }

    public ContactsData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactsData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactsData withEmail(String email) {
        this.email1 = email;
        return this;
    }

    public String getTest_first_name() {
        return test_first_name;
    }


    public String getTest_middle_name() {
        return test_middle_name;
    }

    public String getTest_last_name() {
        return test_last_name;
    }

    public String getTest_nickname() {
        return test_nickname;
    }

    public String getTest_title() {
        return test_title;
    }

    public String getTest_compane() {
        return test_compane;
    }

    public String getAddress() {
        return test_address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getWorkPhone() {
        return work_phone;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail1() {
        return email1;
    }

    public Groups getGroups() {
        return new Groups(groups);
    }

    public ContactsData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }

}
