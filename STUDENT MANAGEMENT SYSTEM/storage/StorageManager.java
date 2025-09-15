package storage;

import model.Student;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class StorageManager {
    public static void saveToFile(List<Student> students, Path path) throws IOException {
        try (BufferedWriter w = Files.newBufferedWriter(path)) {
            w.write("name,roll,grade,email,phone\n");
            for (Student s : students) {
                w.write(String.join(",",
                        s.getName(), s.getRoll(), s.getGrade(), s.getEmail(), s.getPhone()));
                w.newLine();
            }
        }
    }

    public static List<Student> loadFromFile(Path path) throws IOException {
        List<Student> students = new ArrayList<>();
        if (!Files.exists(path)) return students;
        try (BufferedReader r = Files.newBufferedReader(path)) {
            String header = r.readLine(); // skip header
            String line;
            while ((line = r.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    students.add(new Student(parts[0], parts[1], parts[2], parts[3], parts[4]));
                }
            }
        }
        return students;
    }
}
