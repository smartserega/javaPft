package ru.stqa.addressbook.Generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.addressbook.model.ContactsData;
import ru.stqa.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;


    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        }catch (ParameterException ex){
            jCommander.usage();
            return;
        }

        generator.run();
    }

    private void run() throws IOException {
        List<ContactsData> contacts = generateContacts(count);
        save(contacts, new File(file));
    }

    private void save(List<ContactsData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactsData contact : contacts) {
            writer.write(String.format("%s;%s;%s\n", contact.getTest_first_name(), contact.getTest_last_name(), contact.getTest_middle_name()));
        }
        writer.close();
    }

    private  List<ContactsData> generateContacts(int count) {
        List<ContactsData> contact = new ArrayList<ContactsData>();
        for (int i =0; i < count; i++) {
            contact.add(new ContactsData().withFirstName(String.format("firstName %s",i)).withLastName(String.format("lastName %s",i)).withMiddleName(String.format("middleName %s",i)));
        } return contact;
    }
}