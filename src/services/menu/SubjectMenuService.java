package services.menu;

import entities.Subject;
import services.SubjectService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SubjectMenuService implements BaseMenuService {
    private final SubjectService subjectService;

    public SubjectMenuService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void create() throws SQLException {
        System.out.print("Enter the ID: ");
        long id = new Scanner(System.in).nextLong();
        Subject subject = subjectService.findById(id);

        if (subject != null) {
            System.out.println("Subject already exists with this ID!");
            return;
        }

        System.out.print("Enter the name: ");
        String name = new Scanner(System.in).nextLine();
        Subject newSubject = new Subject(id, name);
        subjectService.insert(newSubject);
        System.out.println("Subject was added successfully!");
    }

    @Override
    public void getById() throws SQLException {
        System.out.print("Enter the ID: ");
        long id = new Scanner(System.in).nextLong();
        Subject subject = subjectService.findById(id);

        if (subject != null) {
            System.out.println(subject);
        } else {
            System.out.println("Subject not found!");
        }
    }

    @Override
    public void getList() throws SQLException {
        List<Subject> subjects = subjectService.findAll();

        for (Subject subject : subjects) {
            System.out.println(subject);
        }
    }

    @Override
    public void update() throws SQLException {
        System.out.print("Enter the ID: ");
        long id = new Scanner(System.in).nextLong();
        Subject subject = subjectService.findById(id);

        if (subject != null) {
            System.out.print("Which field do you want to change: ");
            String fieldName = new Scanner(System.in).nextLine();

            if (fieldName.equalsIgnoreCase("name")) {
                System.out.print("Enter the new name: ");
                String newName = new Scanner(System.in).nextLine();
                subject.setName(newName);
                subjectService.update(subject);
                System.out.println("Subject was updated successfully!");
            } else {
                System.out.println("Invalid field name!");
            }
        } else {
            System.out.println("Subject not found!");
        }
    }

    @Override
    public void delete() throws SQLException {
        System.out.print("Enter the ID: ");
        long id = new Scanner(System.in).nextLong();
        Subject subject = subjectService.findById(id);

        if (subject != null) {
            subjectService.delete(subject);
            System.out.println("Subject was deleted successfully!");
        } else {
            System.out.println("Subject not found!");
        }
    }
}
