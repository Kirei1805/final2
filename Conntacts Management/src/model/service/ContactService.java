package model.service;

import model.entity.Contact;
import model.repository.ContactRepository;
import java.io.IOException;
import java.util.List;

public class ContactService {
    private ContactRepository repository = new ContactRepository();

    public List<Contact> getAll() {
        return repository.getAll();
    }

    public void add(Contact contact) {
        repository.add(contact);
    }

    public void update(int index, Contact contact) {
        repository.update(index, contact);
    }

    public void delete(int index) {
        repository.delete(index);
    }

    public List<Contact> search(String keyword) {
        return repository.search(keyword);
    }

    public void readFromFile(String filePath) throws IOException {
        repository.readFromFile(filePath);
    }

    public void writeToFile(String filePath) throws IOException {
        repository.writeToFile(filePath);
    }

    public boolean updateByPhone(String phone, Contact contact) {
        return repository.updateByPhone(phone, contact);
    }

    public boolean deleteByPhone(String phone) {
        return repository.deleteByPhone(phone);
    }
} 