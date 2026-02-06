package utils;

import model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    private static final String PATH = "contacts.csv";

    private static void writeToFile(List<Contact> contacts) {
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
            System.out.println("Lưu file thành cng");
        } catch (IOException e){
            System.out.println("Lỗi ghi file" + e.getMessage());
        }
    }

    public static List<Contact> readFromFile(){
        List<Contact> contacts = new ArrayList<Contact>();
        File file = new File(PATH);

        if (!file.exists()){
            System.out.println("File không tồn tại");
            return contacts;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null){
                if (line.trim().isEmpty() || line.startsWith("phone")){
                    continue;
                }

                String[] data = line.split(",");
                if (data.length != 7){
                    System.out.println("Dữ liệu định sai sai dòng thứ " + line);
                    continue;
                }

                Contact contact = new Contact(
                        data[0], data[1], data[2], data[3],
                        data[4], data[5], data[6]
                );
            }
        } catch (IOException e){
            System.out.println("Lỗi khi đọc file." + e.getMessage());
        }

        return contacts;
    }
}
