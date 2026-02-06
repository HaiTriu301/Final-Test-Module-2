package utils;

import model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    private static final String PATH = "data/contacts.csv";

    public static void writeToFile(List<Contact> contacts) {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH))) {
            bw.write("Số điện thoại,Nhóm,Họ tên,Giới tính,Địa chỉ,Ngày sinh,Email");
            bw.newLine();

            for (Contact contact : contacts) {
                bw.write(contact.toCSV());
                bw.newLine();
            }
            System.out.println("Lưu file thành công");
        } catch (IOException e){
            System.out.println("Lỗi ghi file" + e.getMessage());
        }
    }

    public static List<Contact> readFromFile() {
        List<Contact> contacts = new ArrayList<>();
        File file = new File(PATH);

        if (!file.exists()) {
            System.out.println("File chưa tồn tại: " + PATH);
            return contacts;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // bỏ dòng header
                }

                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");
                if (data.length != 7) {
                    System.out.println("Dòng dữ liệu sai định dạng: " + line);
                    continue;
                }

                Contact c = new Contact(
                        data[0], data[1], data[2],
                        data[3], data[4], data[5], data[6]
                );
                contacts.add(c);
            }
        }
        catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }

        return contacts;
    }
}
