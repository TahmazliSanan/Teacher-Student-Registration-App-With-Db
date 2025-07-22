package app;

import services.StudentService;
import services.SubjectService;
import services.TeacherService;
import services.menu.BaseMenuService;
import services.menu.StudentMenuService;
import services.menu.SubjectMenuService;
import services.menu.TeacherMenuService;
import util.MenuUtil;

import java.sql.SQLException;
import java.util.Scanner;

public class App {
    private static final BaseMenuService subjectMenuService = new SubjectMenuService(new SubjectService());
    private static final BaseMenuService teacherMenuService =
            new TeacherMenuService(new TeacherService(), new SubjectService());
    private static final BaseMenuService studentMenuService =
            new StudentMenuService(new StudentService(), new TeacherService());

    public static void main(String[] args) throws SQLException {
        MenuUtil menuUtil = new MenuUtil(new Scanner(System.in));
        menuUtil.showServiceMenu(subjectMenuService, teacherMenuService, studentMenuService);
    }
}
