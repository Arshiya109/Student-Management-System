package ui;

import java.awt.*;
import java.nio.file.Path;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Student;
import service.StudentManagementSystem;
import storage.StorageManager;

public class StudentFrame extends JFrame {
    private final StudentManagementSystem backend = new StudentManagementSystem();
    private final DefaultTableModel tableModel;
    private final JTable table;
    private final Path defaultPath = Path.of("students.csv");

    public StudentFrame() {
        super("Student Management System");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] cols = {"Name", "Roll", "Grade", "Email", "Phone"};
        tableModel = new DefaultTableModel(cols, 0);
        table = new JTable(tableModel);

        JButton addBtn = new JButton("Add");
        JButton editBtn = new JButton("Edit");
        JButton delBtn = new JButton("Delete");
        JButton saveBtn = new JButton("Save");
        JButton loadBtn = new JButton("Load");

        JPanel top = new JPanel();
        top.add(addBtn); top.add(editBtn); top.add(delBtn);
        top.add(saveBtn); top.add(loadBtn);

        addBtn.addActionListener(e -> onAdd());
        editBtn.addActionListener(e -> onEdit());
        delBtn.addActionListener(e -> onDelete());
        saveBtn.addActionListener(e -> onSave());
        loadBtn.addActionListener(e -> onLoad());

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void onAdd() {
        StudentDialog d = new StudentDialog(this, "Add Student", null);
        d.setVisible(true);
        if (d.isSaved()) {
            if (backend.addStudent(d.getStudent())) refreshTable();
            else JOptionPane.showMessageDialog(this, "Roll already exists.");
        }
    }

    private void onEdit() {
        int row = table.getSelectedRow();
        if (row < 0) return;
        String roll = (String) tableModel.getValueAt(row, 1);
        Student existing = backend.findByRoll(roll);
        if (existing == null) return;
        StudentDialog d = new StudentDialog(this, "Edit Student", existing);
        d.setVisible(true);
        if (d.isSaved()) {
            backend.updateStudent(roll, d.getStudent());
            refreshTable();
        }
    }

    private void onDelete() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            String roll = (String) tableModel.getValueAt(row, 1);
            backend.removeStudent(roll);
            refreshTable();
        }
    }

    private void onSave() {
        try { StorageManager.saveToFile(backend.all(), defaultPath); }
        catch (Exception e) { JOptionPane.showMessageDialog(this, "Save failed: "+e.getMessage()); }
    }

    private void onLoad() {
        try {
            List<Student> list = StorageManager.loadFromFile(defaultPath);
            backend.clear();
            for (Student s : list) backend.addStudent(s);
            refreshTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Load failed: "+e.getMessage());
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Student s : backend.all()) tableModel.addRow(s.toRow());
    }
}
