package ru.stqa.addressbook.model;

import java.util.Objects;

public class ContactsData {
    private final String id;
    private final String test_first_name;
    private final String test_middle_name;
    private final String test_last_name;
    private final String test_nickname;
    private final String test_title;
    private final String test_compane;
    private final String test_address;
    private final String homePhone;
    private final String mobile;
    private final String email;
    private String group;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactsData that = (ContactsData) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(test_first_name, that.test_first_name) &&
                Objects.equals(test_middle_name, that.test_middle_name) &&
                Objects.equals(test_last_name, that.test_last_name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, test_first_name, test_middle_name, test_last_name);
    }

    @Override
    public String toString() {
        return "ContactsData{" +
                "id='" + id + '\'' +
                ", test_first_name='" + test_first_name + '\'' +
                ", test_middle_name='" + test_middle_name + '\'' +
                ", test_last_name='" + test_last_name + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public ContactsData(String id, String test_first_name, String test_middle_name, String test_last_name, String test_nickname, String test_title, String test_compane, String test_address, String homePhone, String mobile, String email, String group) {
        this.id = id;
        this.test_first_name = test_first_name;
        this.test_middle_name = test_middle_name;
        this.test_last_name = test_last_name;
        this.test_nickname = test_nickname;
        this.test_title = test_title;
        this.test_compane = test_compane;
        this.test_address = test_address;
        this.homePhone = homePhone;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
    }


    public ContactsData(String test_first_name, String test_middle_name, String test_last_name, String test_nickname, String test_title, String test_compane, String test_address, String homePhone, String mobile, String email, String group) {
        this.id = null;
        this.test_first_name = test_first_name;
        this.test_middle_name = test_middle_name;
        this.test_last_name = test_last_name;
        this.test_nickname = test_nickname;
        this.test_title = test_title;
        this.test_compane = test_compane;
        this.test_address = test_address;
        this.homePhone = homePhone;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
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
