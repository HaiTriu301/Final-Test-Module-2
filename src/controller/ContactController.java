package controller;

import exception.InvalidEmailException;
import exception.InvalidPhoneException;
import service.ContactService;
import model.Contact;
import utils.ValidationUtils;

import java.util.Scanner;

public class ContactController {

    private final Scanner sc;
    private final ContactService service;

    public ContactController(Scanner sc, ContactService service) {
        this.sc = sc;
        this.service = service;
    }

    public void showList() {
        int count = 0;
        for (Contact c : service.getAllContacts()) {
            System.out.println(c);
            if (++count % 5 == 0) {
                System.out.print("Nhấn Enter để tiếp tục...");
                sc.nextLine();
            }
        }
    }

    public void addNew() {
        String phone, group, name,gender,address,birthday,email;

        while (true){
            try{
                System.out.print("SĐT: ");
                phone = sc.nextLine();
                if (!ValidationUtils.validatePhone(phone)) {
                    throw new InvalidPhoneException("Số điện thoại không hợp lệ");
                }
                if (service.findByPhone(phone) != null) {
                    throw new InvalidPhoneException("SĐT đã tồn tại");
                }
                break;
            } catch (InvalidPhoneException e){
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.print("Nhóm: ");
        group = sc.nextLine();
        System.out.print("Tên: ");
        name = sc.nextLine();
        System.out.print("Giới tính: ");
        gender = sc.nextLine();
        System.out.print("Địa chỉ: ");
        address = sc.nextLine();
        System.out.print("Ngày sinh: ");
        birthday = sc.nextLine();

        while (true) {
            try {
                System.out.print("Email: ");
                email = sc.nextLine();

                if (!ValidationUtils.validateEmail(email)) {
                    throw new InvalidEmailException("Email không hợp lệ");
                }
                break;
            } catch (InvalidEmailException e) {
                System.out.println("Error " + e.getMessage());
            }
        }

        service.add(new Contact(phone, group, name, gender, address, birthday, email));
        System.out.println("Thêm thành công");
    }

    public void update() {
        try {
            System.out.print("Nhập SĐT cần sửa: ");
            String phone = sc.nextLine();
            if (phone.isBlank()) return;

            Contact c = service.findByPhone(phone);
            if (c == null) {
                throw new Exception("Không tìm thấy danh bạ");
            }

            System.out.print("Tên mới: "); c.setName(sc.nextLine());
            System.out.print("Email mới: ");
            String email = sc.nextLine();

            if (!ValidationUtils.validateEmail(email)) {
                throw new Exception("Email không hợp lệ");
            }

            c.setEmail(email);
            System.out.println("Cập nhật thành công");

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void delete() {
        System.out.print("Nhập SĐT cần xóa: ");
        String phone = sc.nextLine();
        Contact c = service.findByPhone(phone);

        if (c == null) {
            System.out.println("Không tìm thấy danh bạ");
            return;
        }

        System.out.print("Xác nhận xóa (Y/N): ");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            service.delete(c);
            System.out.println("Đã xóa");
        }
    }

    public void search() {
        System.out.print("Nhập từ khóa: ");
        String key = sc.nextLine();
        service.search(key).forEach(System.out::println);
    }

    public void readFromFile() {
        System.out.print("Ghi đè bộ nhớ? (Y/N): ");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            service.loadFromFile();
            System.out.println("Đã đọc file");
        }
    }

    public void saveToFile() {
        System.out.print("Ghi đè file? (Y/N): ");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            service.saveToFile();
        }
    }
}