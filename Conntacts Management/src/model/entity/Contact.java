package model.entity;

public class Contact {
    private String name;
    private String phoneNumber;
    private String group;
    private String gender;
    private String address;
    private String email;

    public Contact(String name, String phoneNumber, String group, String gender, String address, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.group = group;
        this.gender = gender;
        this.address = address;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return phoneNumber + ", " + group + ", " + name + ", " + gender + ", " + address + ", " + email;
    }
}
