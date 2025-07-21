package util;

import services.menu.BaseMenuService;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuUtil {
    private final Scanner scanner;

    public MenuUtil(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showOperationMenu(String title, BaseMenuService menuService) throws SQLException {
        while (true) {
            System.out.println("=== " + title.toUpperCase() + " MENU ===");
            System.out.println("1.Create");
            System.out.println("2.Get by ID");
            System.out.println("3.Get list");
            System.out.println("4.Update");
            System.out.println("5.Delete");
            System.out.println("6.Back");
            System.out.print("Choose an operation: ");

            int operationNumber = scanner.nextInt();

            switch (operationNumber) {
                case 1:
                    menuService.create();
                    break;
                case 2:
                    menuService.getById();
                    break;
                case 3:
                    menuService.getList();
                    break;
                case 4:
                    menuService.update();
                    break;
                case 5:
                    menuService.delete();
                    break;
                case 6:
                    return;
                default: System.out.println("Invalid option!");
            }
        }
    }

    public void showServiceMenu(BaseMenuService subjectService,
                                BaseMenuService teacherService,
                                BaseMenuService studentService) throws SQLException {
        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1.Subject Menu");
            System.out.println("2.Teacher Menu");
            System.out.println("3.Student Menu");
            System.out.println("4.Exit");
            System.out.print("Choose an menu: ");

            int serviceNumber = scanner.nextInt();

            switch (serviceNumber) {
                case 1:
                    showOperationMenu("Subject", subjectService);
                    break;
                case 2:
                    showOperationMenu("Teacher", teacherService);
                    break;
                case 3:
                    showOperationMenu("Student", studentService);
                    break;
                case 4:
                    System.out.println("Exit system");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}

