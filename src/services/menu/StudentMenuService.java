package services.menu;

import entities.Student;
import entities.Teacher;
import services.StudentService;
import services.TeacherService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StudentMenuService implements BaseMenuService {
    private final StudentService studentService;
    private final TeacherService teacherService;

    public StudentMenuService(StudentService studentService, TeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @Override
    public void create() throws SQLException {
        System.out.print("Enter the ID: ");
        long id = new Scanner(System.in).nextLong();
        Student student = studentService.findById(id);

        if (student != null) {
            System.out.println("Student already exists with this ID!");
            return;
        }

        System.out.print("Enter the first name: ");
        String firstName = new Scanner(System.in).nextLine();
        System.out.print("Enter the last name: ");
        String lastName = new Scanner(System.in).nextLine();
        System.out.print("Enter the GPA: ");
        double gpa = new Scanner(System.in).nextDouble();
        System.out.print("Enter the teacher ID: ");
        long teacherId = new Scanner(System.in).nextLong();
        Teacher foundTeacher = teacherService.findById(teacherId);

        if (foundTeacher != null) {
            Student newStudent = new Student(id, firstName, lastName, gpa, teacherId);
            studentService.insert(newStudent);
            System.out.println("Student was added successfully!");
        } else {
            System.out.println("Teacher not found!");
        }
    }

    @Override
    public void getById() throws SQLException {
        System.out.print("Enter the ID: ");
        long id = new Scanner(System.in).nextLong();
        Student student = studentService.findById(id);

        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found!");
        }
    }

    @Override
    public void getList() throws SQLException {
        List<Student> students = studentService.findAll();

        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Override
    public void update() throws SQLException {
        System.out.print("Enter the ID: ");
        long id = new Scanner(System.in).nextLong();
        Student student = studentService.findById(id);

        if (student != null) {
            System.out.print("Which field do you want to change: ");
            String fieldName = new Scanner(System.in).nextLine();

            if (fieldName.equalsIgnoreCase("first name")) {
                System.out.print("Enter the new first name: ");
                String newFirstName = new Scanner(System.in).nextLine();
                student.setFirstName(newFirstName);
                studentService.update(student);
                System.out.println("Student was updated successfully!");
            } if (fieldName.equalsIgnoreCase("last name")) {
                System.out.print("Enter the new last name: ");
                String newLastName = new Scanner(System.in).nextLine();
                student.setFirstName(newLastName);
                studentService.update(student);
                System.out.println("Student was updated successfully!");
            } if (fieldName.equalsIgnoreCase("gpa")) {
                System.out.print("Enter the new GPA: ");
                double newGpa = new Scanner(System.in).nextDouble();
                student.setGpa(newGpa);
                studentService.update(student);
                System.out.println("Student was updated successfully!");
            } else {
                System.out.println("Invalid field name!");
            }
        } else {
            System.out.println("Student not found!");
        }
    }

    @Override
    public void delete() throws SQLException {
        System.out.print("Enter the ID: ");
        long id = new Scanner(System.in).nextLong();
        Student student = studentService.findById(id);

        if (student != null) {
            studentService.delete(student);
            System.out.println("Student was deleted successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }
}
