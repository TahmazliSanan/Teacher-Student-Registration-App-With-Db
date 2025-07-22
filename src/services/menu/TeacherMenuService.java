package services.menu;

import entities.Subject;
import entities.Teacher;
import services.SubjectService;
import services.TeacherService;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class TeacherMenuService implements BaseMenuService {
    private final TeacherService teacherService;
    private final SubjectService subjectService;

    public TeacherMenuService(TeacherService teacherService, SubjectService subjectService) {
        this.teacherService = teacherService;
        this.subjectService = subjectService;
    }

    @Override
    public void create() throws SQLException {
        System.out.print("Enter the ID: ");
        long id = new Scanner(System.in).nextLong();
        Teacher teacher = teacherService.findById(id);

        if (teacher != null) {
            System.out.println("Teacher already exists with this ID!");
            return;
        }

        System.out.print("Enter the first name: ");
        String firstName = new Scanner(System.in).nextLine();
        System.out.print("Enter the last name: ");
        String lastName = new Scanner(System.in).nextLine();
        System.out.print("Enter the count of subjects: ");
        long countOfSubjects = new Scanner(System.in).nextLong();
        long subjectId;
        Subject foundSubject;
        Set<Long> addedSubjectIds = new HashSet<>();

        Teacher newTeacher = new Teacher(id, firstName, lastName, null);
        teacherService.insert(newTeacher);

        for (int i = 0; i < countOfSubjects; i++) {
            System.out.print("Enter the " + (i + 1) + ".book's ID: ");
            subjectId = new Scanner(System.in).nextLong();

            if (addedSubjectIds.contains(subjectId)) {
                System.out.println("You already entered this subject ID!");
                i--;
                continue;
            }

            foundSubject = subjectService.findById(subjectId);

            if (foundSubject != null) {
                teacherService.registerSubjectToTeacher(id, subjectId);
                addedSubjectIds.add(subjectId);
            } else {
                System.out.println("Subject not found!");
                i--;
            }
        }

        System.out.println("Teacher was added successfully!");
    }

    @Override
    public void getById() throws SQLException {
        System.out.print("Enter the ID: ");
        long id = new Scanner(System.in).nextLong();
        Teacher teacher = teacherService.findById(id);

        if (teacher != null) {
            System.out.println(teacher);
        } else {
            System.out.println("Teacher not found!");
        }
    }

    @Override
    public void getList() throws SQLException {
        List<Teacher> teachers = teacherService.findAll();

        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
    }

    @Override
    public void update() throws SQLException {
        System.out.print("Enter the ID: ");
        long id = new Scanner(System.in).nextLong();
        Teacher teacher = teacherService.findById(id);

        if (teacher != null) {
            System.out.print("Which field do you want to change: ");
            String fieldName = new Scanner(System.in).nextLine();

            if (fieldName.equalsIgnoreCase("first name")) {
                System.out.print("Enter the new first name: ");
                String newFirstName = new Scanner(System.in).nextLine();
                teacher.setFirstName(newFirstName);
                teacherService.update(teacher);
                System.out.println("Teacher was updated successfully!");
            } if (fieldName.equalsIgnoreCase("last name")) {
                System.out.print("Enter the new last name: ");
                String newLastName = new Scanner(System.in).nextLine();
                teacher.setFirstName(newLastName);
                teacherService.update(teacher);
                System.out.println("Teacher was updated successfully!");
            } else {
                System.out.println("Invalid field name!");
            }
        } else {
            System.out.println("Teacher not found!");
        }
    }

    @Override
    public void delete() throws SQLException {
        System.out.print("Enter the ID: ");
        long id = new Scanner(System.in).nextLong();
        Teacher teacher = teacherService.findById(id);

        if (teacher != null) {
            teacherService.delete(teacher);
            System.out.println("Teacher was deleted successfully!");
        } else {
            System.out.println("Teacher not found!");
        }
    }
}
