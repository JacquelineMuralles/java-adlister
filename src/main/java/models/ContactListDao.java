package models;

import java.util.ArrayList;
import java.util.List;

public class ContactListDao implements Contacts{

    // We need a List variable to hold all models.Contacts
    private List<Contact> contacts = new ArrayList<>();// This is the array that will hold our contacts

    //We could have seeded our DB table THIS way, but we already did with the stuff-seeder.sql, so no need to do this
    // initially seed contacts
    public ContactListDao(){
        contacts.add(new Contact(1, "Billy", "Smith", "2105551111"));
        contacts.add(new Contact(2, "Zed", "Smith", "2105552222"));
        contacts.add(new Contact(3, "Calvin", "Smith", "2105553333"));
        contacts.add(new Contact(4, "Melvin", "Smith", "2105554444"));
        contacts.add(new Contact(5, "Darlene", "Smith", "2105555555"));
    }


    //List all the contacts (List<models.Contact>)
    @Override
    public List<Contact> getContacts(){
        return contacts;
    }


    // method for saving contacts
    // will essentially get the length of List<models.Contacts> and +1
    @Override
    public long saveContact(Contact contact){
        // check to see if this is the first contact, and if so, add 1
        if(contact.getId() == 0) {
            // If we're setting up a contact that hasn't been assigned an ID (so it must have been constructed
                //using just 'first', 'last', and 'phone' - NO id, then we want to generate an ID and
                //set it for this contact.
            contact.setId(contacts.size() + 1);
            contacts.add(contact);
        }else {
            //we already have the correct ID coming from the MySQL table, so let's just set that contact in
                //the ArrayList(contacts) with the ID that was passed in
            contacts.set((int) (contact.getId() - 1), contact);
        }

        // return the ID of the newly saved contact
        return contact.getId();
    }


    // method for deleting contacts
    @Override
    public void deleteContactById(long id){
        // don't need a return value
                // [1] -> Billy Smith
                // [2] -> Dax Smith
                // [3] -> Webb Smith
                // [4] -> 1337 Smith
        // do a .remove on the passed in ID to remove that contact from the ArrayList
        contacts.remove((int) id - 1);//sending the index for the ARRAY LIST
    }

    //method for deleting a contact by their ID
    @Override
    public Contact getContactById(long id){
       return contacts.get((int) id - 1);

    }

    // Let's test out our DAO to ensure everything is working properly
    public static void main(String[] args) {

        Contacts contactDao = new ContactListDao();

        //test the getContacts() method
        System.out.println("\n === Testing getContacts()");
        List<Contact> allContacts = contactDao.getContacts();

        for(Contact contact : allContacts) {
            System.out.println(contact.getFirstName());
        }
    }


}
