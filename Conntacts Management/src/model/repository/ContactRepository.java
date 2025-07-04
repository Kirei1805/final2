package model.repository;

import model.entity.Contact;
import java.util.*;
import java.io.*;

public class ContactRepository {
    private List<Contact> contacts = new ArrayList<>();

    public List<Contact> getAll() {
        return contacts;
    }

    public void add(Contact contact) {
        contacts.add(contact);
    }

    public void update(int index, Contact contact) {
        if (index >= 0 && index < contacts.size()) {
            contacts.set(index, contact);
        }
    }

    public void delete(int index) {
        if (index >= 0 && index < contacts.size()) {
            contacts.remove(index);
        }
    }

    public List<Contact> search(String keyword) {
        List<Contact> result = new ArrayList<>();
        for (Contact c : contacts) {
            if (c.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                c.getPhoneNumber().contains(keyword)) {
                result.add(c);
            }
        }
        return result;
    }

    public void readFromFile(String filePath) throws IOException {
        contacts.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    contacts.add(new Contact(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
                }
            }
        }
    }

    public void writeToFile(String filePath) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Contact c : contacts) {
                bw.write(c.toString());
                bw.newLine();
            }
        }
    }

    public boolean updateByPhone(String phone, Contact contact) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getPhoneNumber().equals(phone)) {
                contacts.set(i, contact);
                return true;
            }
        }
        return false;
    }

    public boolean deleteByPhone(String phone) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getPhoneNumber().equals(phone)) {
                contacts.remove(i);
                return true;
            }
        }
        return false;
    }
} 