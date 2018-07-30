package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.ContactsData;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import java.util.Objects;
import java.util.Set;

public class ContactAddToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondtions() {
        app.db().groups();

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("TestAddContactGroup"));
        }

        app.goTo().contactPage();
        Groups groups = app.db().groups();
        if (app.db().contacts().size() == 0) {
            app.contacts().create(new ContactsData().withFirstName("FirstName").withMiddleName("MiddleName").
                    withLastName("LastName").withNickname("nickname").withTitle("Title").withCompane("company").
                    withAddress("address").withMobile("111").withHomePhone("222").withEmail("E-mail@E-mail.ru").withWorkPhone("333").
                    inGroup(groups.iterator().next()));
        }
    }

    @Test
    public void addContactToGroupTests() {
        int allContactSize = app.db().contacts().size();
        System.out.println("Подходящий контакт для группы " + findGoodContactForAdd(allContactSize));
        Set goodContact = findGoodContactForAdd(allContactSize);
         findGoodGroupForContact(goodContact);
    }


    public int findGoodGroupForContact(Set goodContact) {
        ContactsData contact = new ContactsData();
        Groups allgroups = app.db().groups(); //список всех групп
        GroupData oneOfGroupWithContact = allgroups.iterator().next();
        for (allgroups : oneOfGroupWithContact){
            if (Objects.equals(oneOfGroupWithContact, goodContact);
        }
//            if (Objects.equals(group.getId(), goodContact)) {
//                System.out.println("подходящая группа group.getId()");
//                return group.getId();
//            } else System.out.println("группа не найдена");
//
//        return groups.iterator().next().getId();
    }


    public Contacts findGoodContactForAdd(int allContactSize) {
        Contacts contacts = app.db().contacts();
        for (ContactsData contact : contacts) {
            if (contact.getGroups().size() == allContactSize) {

            }
            if (contact.getGroups().size() != allContactSize) {

                System.out.println("id подходящего контакта " + contact.getId());
                return contact;
            }
        }
        return contacts;
    }
}

//        Contacts contacts = app.db().contacts();
//        int contactID = contacts.iterator().next().getId();
//        findContactInGroup(contactID);
//    }
//
//
//    public ContactsData findContactInGroup(int contactID) {
//        ContactsData contact = new ContactsData();
//        contact = contact.withId(contactID);
//        for (GroupData group : contact.getGroups())
//                if (Objects.equals(group.getId(), contactID)) {
//                    System.out.println("группа найдена");
//                } else System.out.println("группа не найдена");
//
//        System.out.println(contact);
//        return contact;
//    }
//}


//
//            System.out.println("groupsOfCurrentContact" + groupsOfCurrentContact);
//            GroupData oneOfGroupWithId = groupsOfCurrentContact.iterator().next();
//            GroupData groupWithAmendedId = oneOfGroupWithId.withId(id);
////            System.out.println("!!!" + groupWithAmendedId);
//        }


//    давайте я ещё раз повторю ту схему, которую я предлагаю
//      +  1) сначала узнаем общее количество групп в приложении
//      +  2) потом получаем полный список контактов
//      +  3) в цикле проходим по всем контактам, и для каждого из них проверяем размер списка групп,
//          в которые этот контакт входит.
//          - если размер совпадает с размером полного списка групп -- нам такой контакт не нужен.
//          - если размер меньше -- значит этот контакт "хороший", подходит для наших целей, его можно в какую-то группу добавить
//        описанное выше можно реализовать в виде метода с именем, скажем
//        "найти контакт, который входит не во все группы"
//        затем реализуем второй метод
//        "найти группу, в которую не входит ЭТОТ контакт"
//        внутри него
//        +1) получаем полный список групп
//        +2) получает список групп, в которые входит этот контакт
//        3) в цикле проходим по полному списку и для каждой группы проверяем, входит она во второй
//// список или нет
//        как только находится группа, которая этому условию не удовлетворяет -- мы нашли подходящую группу
//
//    Groups a = contact.getGroups(); // список всех групп куда входит случайный контакт
//
//    GroupData b = a..iterator().next(); // возвращается одна из групп, куда входит случайный контакт
//
//    GroupData c = b.withId(id); // возаращается id контакта включенного в группу