package ru.stqa.addressbook.model;

public class ContactsData {
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

    public ContactsData(String test_first_name, String test_middle_name, String test_last_name, String test_nickname, String test_title, String test_compane, String test_address, String homePhone, String mobile, String email) {
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
}
