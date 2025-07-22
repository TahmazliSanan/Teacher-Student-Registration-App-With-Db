package entities;

import abstracts.Person;

import java.util.List;

public class Teacher extends Person {
    private List<Subject> subjects;

    public Teacher(Long id, String firstName, String lastName, List<Subject> subjects) {
        super(id, firstName, lastName);
        this.subjects = subjects;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public Teacher setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
        return this;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + "\n" +
                "First name: " + getFirstName() + "\n" +
                "Last name: " + getLastName() + "\n" +
                "Subjects: " + subjects;
    }
}
