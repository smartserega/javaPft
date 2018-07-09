package ru.stqa.addressbook.model;

import java.util.Objects;

public class ContactsData {


    private int id = Integer.MAX_VALUE;
    private String test_first_name;
    private String test_middle_name;
    private String test_last_name;
    private String test_nickname;
    private String test_title;
    private String test_compane;
    private String test_address;
    private String homePhone;
    private String mobile;
    private String email;
    private String group;

    @Override
    public String toString() {
        return "ContactsData{" +
                "id='" + id + '\'' +
                ", test_first_name='" + test_first_name + '\'' +
                ", test_last_name='" + test_last_name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactsData that = (ContactsData) o;
        return Objects.equals(test_first_name, that.test_first_name) &&
                Objects.equals(test_last_name, that.test_last_name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(test_first_name, test_last_name);
    }

    public void withId(int id) {
        this.id = id;
    }

    public ContactsData withFirstName(String test_first_name) {
        this.test_first_name = test_first_name;
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
        this.email = email;
        return this;
    }

    public ContactsData withGroup(String group) {
        this.group = group;
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

    public String getTest_address() {
        return test_address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }
}
