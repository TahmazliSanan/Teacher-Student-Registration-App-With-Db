package entities;

public class Subject {
    private Long id;
    private String name;
    private Long teacherId;

    public Subject(Long id, String name, Long teacherId) {
        this.id = id;
        this.name = name;
        this.teacherId = teacherId;
    }

    public Long getId() {
        return id;
    }

    public Subject setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Subject setName(String name) {
        this.name = name;
        return this;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public Subject setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nName: " + name;
    }
}
