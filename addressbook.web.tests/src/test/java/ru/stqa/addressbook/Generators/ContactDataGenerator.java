package ru.stqa.addressbook.Generators;

import ru.stqa.addressbook.model.ContactsData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<ContactsData> groups = generateContacts(count);
        save(groups, file);
    }

    private static void save(List<ContactsData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactsData contact : contacts) {
            writer.write(String.format("%s;%s;%s\n", contact.getTest_first_name(), contact.getTest_last_name(), contact.getTest_middle_name()));
        }
        writer.close();
    }

    private static List<ContactsData> generateContacts(int count) {
        List<ContactsData> contact = new ArrayList<ContactsData>();
        for (int i =0; i < count; i++) {
            contact.add(new ContactsData().withFirstName(String.format("firstName %s",i)).withLastName(String.format("lastName %s",i)).withMiddleName(String.format("middleName %s",i)));
        } return contact;
    }
}