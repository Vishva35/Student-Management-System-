
public class Student {

    private int id;
    private String name;
    private int age;
    private String course;

    // Constructor
    public Student(int id, String name, int age, String course) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCourse() {
        return course;
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    // Display student details
    public void displayStudent() {

        System.out.println("Student ID     : " + id);
        System.out.println("Student Name   : " + name);
        System.out.println("Student Age    : " + age);
        System.out.println("Student Course : " + course);
    }
}
