package ru.stqa.addressbook.appManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.ContactsData;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import java.sql.*;
import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();

        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactsData> result = session.createQuery("from ContactsData where deprecated = '0000-00-00'").list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }

//    public Groups getAssignedGroups(String firstname){
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        List<GroupData> result = session.createQuery("select contacts.groups from NewContactData contacts where contacts.firstName = :firstname")
//                .setParameter("firstname", firstname).list();
//        session.getTransaction().commit();
//        session.close();
//        return new Groups (result);
//    }

    public void groupsWithConnection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:/addressbook?user=root&password=&serverTimezone=UTC");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select  id, firstname, lastname from addressbook where deprecated = '0000-00-00' ");

            Contacts contacts = new Contacts();
            while (rs.next()) {
                contacts.add(new ContactsData().withId(rs.getInt("id")).withFirstName(rs.getString("firstname"))
                        .withLastName(rs.getString("lastname")));
            }

            rs.close();
            st.close();
            conn.close();

            System.out.println(contacts);

            // Do something with the Connection


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public void set() throws Exception {
        // A SessionFactory is set up once for an application!
        SessionFactory sessionFactory;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
    public List<ContactsData> connectionContacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactsData> result = session.createQuery("from ContactsData where deprecated = '0000-00-00'").list();
        for (ContactsData contact : result) {
                if (contact.getGroups().size() < 1){
                    return null;
                } else return result;
            }

//            contact.getGroups();
//            System.out.println(contact.getGroups().size());

        System.out.println("РЕЗУЛЬТАТ "+ result);
        return result;
    }
}





