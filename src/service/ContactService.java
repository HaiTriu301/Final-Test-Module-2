package service;

import model.Contact;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class ContactService {
    private List<Contact> contacts = new ArrayList<Contact>();

    public List<Contact> getAllContacts() {
        return contacts;
    }

    public Contact findByPhone(String phone){
        for (Contact c : contacts) {
            if (c.getPhone().equals(phone)){
                return c;
            }
        }

        return null;
    }

    public void add (Contact c){
        contacts.add(c);
    }

    public void delete(Contact c){
        contacts.remove(c);
    }

    public List<Contact> search(String keyword) {
        List<Contact> search = new ArrayList<>();

        for (Contact c : contacts) {
            if (c.getPhone().contains(keyword)
                    || c.getName().toLowerCase().contains(keyword.toLowerCase())) {
                search.add(c);
            }
        }

        return search;
    }

    public void loadFromFile(){
        contacts = FileUtils.readFromFile();
    }

    public void saveToFile(){
        FileUtils.writeToFile(contacts);
    }
}
