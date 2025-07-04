package controller;

import model.entity.Contact;
import model.service.ContactService;
import java.io.IOException;
import java.util.List;

public class ContactController {
    private ContactService service = new ContactService();

    public List<Contact> getAll() {
        return service.getAll();
    }

    public void add(Contact contact) {
        service.add(contact);
    }

    public void update(int index, Contact contact) {
        service.update(index, contact);
    }

    public void delete(int index) {
        service.delete(index);
    }

    public List<Contact> search(String keyword) {
        return service.search(keyword);
    }

    public void readFromFile(String filePath) throws IOException {
        service.readFromFile(filePath);
    }

    public void writeToFile(String filePath) throws IOException {
        service.writeToFile(filePath);
    }

    public boolean updateByPhone(String phone, Contact contact) {
        return service.updateByPhone(phone, contact);
    }

    public boolean deleteByPhone(String phone) {
        return service.deleteByPhone(phone);
    }
} 