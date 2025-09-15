package model;

public class Student {
    private String name;
    private String roll;
    private String grade;
    private String email;
    private String phone;

    public Student(String name, String roll, String grade, String email, String phone) {
        this.name = name;
        this.roll = roll;
        this.grade = grade;
        this.email = email;
        this.phone = phone;
    }

    public String getName() { return name; }
    public String getRoll() { return roll; }
    public String getGrade() { return grade; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    public void setName(String name) { this.name = name; }
    public void setRoll(String roll) { this.roll = roll; }
    public void setGrade(String grade) { this.grade = grade; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }

    public String[] toRow() {
        return new String[]{name, roll, grade, email, phone};
    }
}
