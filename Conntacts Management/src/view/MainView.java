package view;

import controller.ContactController;
import model.entity.Contact;
import java.util.List;
import java.util.Scanner;

public class MainView {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactController controller = new ContactController();
        String filePath = "src/data/contacts.csv";
        while (true) {
            System.out.println("========== CHUONG TRINH QUAN LY DANH BA ==========");
            System.out.println("1. Xem danh sach danh ba");
            System.out.println("2. Them moi");
            System.out.println("3. Cap nhat");
            System.out.println("4. Xoa");
            System.out.println("5. Tim kiem");
            System.out.println("6. Doc tu file");
            System.out.println("7. Ghi vao file");
            System.out.println("8. Thoat");
            System.out.print("Chon chuc nang: ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                continue;
            }
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so tu 1 den 8!");
                continue;
            }
            try {
                switch (choice) {
                    case 1:
                        List<Contact> list = controller.getAll();
                        if (list.isEmpty()) {
                            System.out.println("Danh ba trong!");
                        } else {
                            for (int i = 0; i < list.size(); i++) {
                                System.out.println((i+1) + ". " + list.get(i));
                            }
                        }
                        break;
                    case 2:
                        System.out.print("Nhap ho ten: ");
                        String name = scanner.nextLine();
                        System.out.print("Nhom: ");
                        String group = scanner.nextLine();
                        String phone;
                        do {
                            System.out.print("So dien thoai: ");
                            phone = scanner.nextLine();
                            if (!isValidPhone(phone)) System.out.println("So dien thoai khong hop le!");
                        } while (!isValidPhone(phone));
                        System.out.print("Gioi tinh: ");
                        String gender = scanner.nextLine();
                        System.out.print("Dia chi: ");
                        String address = scanner.nextLine();
                        String email;
                        do {
                            System.out.print("Email: ");
                            email = scanner.nextLine();
                            if (!isValidEmail(email)) System.out.println("Email khong hop le!");
                        } while (!isValidEmail(email));
                        controller.add(new Contact(name, phone, group, gender, address, email));
                        System.out.println("Da them!");
                        List<Contact> listAfterAdd = controller.getAll();
                        if (listAfterAdd.isEmpty()) {
                            System.out.println("Danh ba trong!");
                        } else {
                            for (int i = 0; i < listAfterAdd.size(); i++) {
                                System.out.println((i+1) + ". " + listAfterAdd.get(i));
                            }
                        }
                        break;
                    case 3:
                        while (true) {
                            System.out.print("Nhap so dien thoai can cap nhat: ");
                            String phoneUpdate = scanner.nextLine();
                            if (phoneUpdate.trim().isEmpty()) {
                                break;
                            }
                            List<Contact> foundUpdate = controller.search(phoneUpdate);
                            boolean exists = false;
                            for (Contact c : foundUpdate) {
                                if (c.getPhoneNumber().equals(phoneUpdate)) {
                                    exists = true;
                                    break;
                                }
                            }
                            if (!exists) {
                                System.out.println("Khong tim thay so dien thoai! Moi nhap lai hoac nhan Enter de huy.");
                                continue;
                            }
                            System.out.print("Nhap ho ten moi: ");
                            String nameU = scanner.nextLine();
                            System.out.print("Nhom moi: ");
                            String groupU = scanner.nextLine();
                            String phoneU;
                            do {
                                System.out.print("So dien thoai moi: ");
                                phoneU = scanner.nextLine();
                                if (!isValidPhone(phoneU)) System.out.println("So dien thoai khong hop le!");
                            } while (!isValidPhone(phoneU));
                            System.out.print("Gioi tinh moi: ");
                            String genderU = scanner.nextLine();
                            System.out.print("Dia chi moi: ");
                            String addressU = scanner.nextLine();
                            String emailU;
                            do {
                                System.out.print("Email moi: ");
                                emailU = scanner.nextLine();
                                if (!isValidEmail(emailU)) System.out.println("Email khong hop le!");
                            } while (!isValidEmail(emailU));
                            boolean updated = controller.updateByPhone(phoneUpdate, new Contact(nameU, phoneU, groupU, genderU, addressU, emailU));
                            if (updated) {
                                System.out.println("Da cap nhat!");
                            } else {
                                System.out.println("Khong tim thay so dien thoai!");
                            }
                            List<Contact> listAfterUpdate = controller.getAll();
                            if (listAfterUpdate.isEmpty()) {
                                System.out.println("Danh ba trong!");
                            } else {
                                for (int i = 0; i < listAfterUpdate.size(); i++) {
                                    System.out.println((i+1) + ". " + listAfterUpdate.get(i));
                                }
                            }
                            break;
                        }
                        break;
                    case 4:
                        while (true) {
                            System.out.print("Nhap so dien thoai can xoa: ");
                            String phoneDel = scanner.nextLine();
                            if (phoneDel.trim().isEmpty()) {
                                break;
                            }
                            List<Contact> foundDelete = controller.search(phoneDel);
                            Contact toDelete = null;
                            for (Contact c : foundDelete) {
                                if (c.getPhoneNumber().equals(phoneDel)) {
                                    toDelete = c;
                                    break;
                                }
                            }
                            if (toDelete == null) {
                                System.out.println("Khong tim thay so dien thoai! Moi nhap lai hoac nhan Enter de huy.");
                                continue;
                            }
                            System.out.println("Ban muon xoa thong tin danh ba sau: ");
                            System.out.println(toDelete);
                            System.out.println("Ban co muon xoa thong tin danh ban nay ");
                            System.out.print("Nhap 'y' de xoa, phim bat ky de huy: ");
                            String confirm = scanner.nextLine();
                            if (!confirm.trim().equalsIgnoreCase("y")) {
                                System.out.println("Huy xoa, quay ve menu.");
                                break;
                            }
                            boolean deleted = controller.deleteByPhone(phoneDel);
                            if (deleted) {
                                System.out.println("Da xoa!");
                                List<Contact> listAfterDelete = controller.getAll();
                                if (listAfterDelete.isEmpty()) {
                                    System.out.println("Danh ba trong!");
                                } else {
                                    for (int i = 0; i < listAfterDelete.size(); i++) {
                                        System.out.println((i+1) + ". " + listAfterDelete.get(i));
                                    }
                                }
                                break;
                            } else {
                                System.out.println("Khong tim thay so dien thoai! Moi nhap lai hoac nhan Enter de huy.");
                            }
                        }
                        break;
                    case 5:
                        System.out.print("Nhap Ho va Ten va so dien thoai de tim kiem: ");
                        String keyword = scanner.nextLine();
                        List<Contact> found = controller.search(keyword);
                        if (found.isEmpty()) {
                            System.out.println("Khong tim thay!");
                        } else {
                            System.out.println("Ket qua tim kiem:");
                            for (Contact c : found) {
                                System.out.println(c);
                            }
                        }
                        break;
                    case 6:
                        System.out.println("Canh bao: Thao tac nay se xoa toan bo danh ba hien tai trong bo nho va cap nhat lai tu file!");
                        System.out.print("Nhap 'y' de tiep tuc, phim bat ky de huy: ");
                        String cr = scanner.nextLine();
                        if (!cr.trim().equalsIgnoreCase("y")) {
                            System.out.println("Huy doc file, quay ve menu.");
                            break;
                        }
                        controller.readFromFile(filePath);
                        System.out.println("Da doc tu file!");
                        List<Contact> listAfterRead = controller.getAll();
                        if (listAfterRead.isEmpty()) {
                            System.out.println("Danh ba trong!");
                        } else {
                            for (int i = 0; i < listAfterRead.size(); i++) {
                                System.out.println((i+1) + ". " + listAfterRead.get(i));
                            }
                        }
                        break;
                    case 7:
                        System.out.println("Canh bao: Thao tac nay se ghi de toan bo file danh ba hien tai bang danh ba trong bo nho!");
                        System.out.print("Nhap 'y' de tiep tuc, phim bat ky de huy: ");
                        String cw = scanner.nextLine();
                        if (!cw.trim().equalsIgnoreCase("y")) {
                            System.out.println("Huy ghi file, quay ve menu.");
                            break;
                        }
                        controller.writeToFile(filePath);
                        System.out.println("Da ghi vao file!");
                        break;
                    case 8:
                        System.out.println("Thoat chuong trinh.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Chuc nang khong hop le!");
                }
            } catch (Exception e) {
                System.out.println("Loi: " + e.getMessage());
            }
        }
    }

    private static boolean isValidPhone(String phone) {
        return phone.matches("0[0-9]{9,10}");
    }

    private static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
}

