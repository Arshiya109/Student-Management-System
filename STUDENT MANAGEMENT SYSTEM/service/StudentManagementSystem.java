package service;

import model.Student;
import java.util.*;

public class StudentManagementSystem {
    private final List<Student> students = new ArrayList<>();

    public boolean addStudent(Student s) {
        if (findByRoll(s.getRoll()) != null) return false;
        students.add(s);
        return true;
    }

    public boolean updateStudent(String oldRoll, Student updated) {
        Student existing = findByRoll(oldRoll);
        if (existing == null) return false;
        if (!oldRoll.equals(updated.getRoll()) && findByRoll(updated.getRoll()) != null) return false;
        existing.setName(updated.getName());
        existing.setRoll(updated.getRoll());
        existing.setGrade(updated.getGrade());
        existing.setEmail(updated.getEmail());
        existing.setPhone(updated.getPhone());
        return true;
    }

    public boolean removeStudent(String roll) {
        Student s = findByRoll(roll);
        if (s == null) return false;
        return students.remove(s);
    }

    public Student findByRoll(String roll) {
        for (Student s : students) if (s.getRoll().equalsIgnoreCase(roll)) return s;
        return null;
    }

    public List<Student> search(String q) {
        String ql = q == null ? "" : q.trim().toLowerCase();
        if (ql.isEmpty()) return new ArrayList<>(students);
        List<Student> results = new ArrayList<>();
        for (Student s : students) {
            if (s.getName().toLowerCase().contains(ql) ||
                s.getRoll().toLowerCase().contains(ql) ||
                s.getGrade().toLowerCase().contains(ql) ||
                s.getEmail().toLowerCase().contains(ql) ||
                s.getPhone().toLowerCase().contains(ql)) {
                results.add(s);
            }
        }
        return results;
    }

    public List<Student> all() {
        return new ArrayList<>(students);
    }

    public void clear() {
        students.clear();
    }
}
