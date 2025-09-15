package ui;


import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Student;


public class StudentTableModel extends AbstractTableModel {
    private final String[] cols = {"Name", "Roll", "Grade", "Email", "Phone"};
    private final List<Student> data;


    public StudentTableModel(List<Student> data) {
        this.data = data;
    }


    @Override public int getRowCount() { return data.size(); }
    @Override public int getColumnCount() { return cols.length; }
    @Override public String getColumnName(int col) { return cols[col]; }
    @Override public Object getValueAt(int row, int col) {
        Student s = data.get(row);
        switch (col) {
            case 0: return s.getName();
            case 1: return s.getRoll();
            case 2: return s.getGrade();
            case 3: return s.getEmail();
            case 4: return s.getPhone();
        }
        return null;
    }
}
