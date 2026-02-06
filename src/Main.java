import controller.ContactController;
import service.ContactService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ContactService service = new ContactService();
        ContactController controller = new ContactController(sc, service);

        while (true) {
            System.out.println("==== QUẢN LÝ DANH BẠ ====");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm kiếm");
            System.out.println("6. Đọc từ File");
            System.out.println("7. Lưu vào File");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            switch (sc.nextLine()) {
                case "1" -> controller.showList();
                case "2" -> controller.addNew();
                case "3" -> controller.update();
                case "4" -> controller.delete();
                case "5" -> controller.search();
                case "6" -> controller.readFromFile();
                case "7" -> controller.saveToFile();
                case "0" -> { return; }
                default -> System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }
}