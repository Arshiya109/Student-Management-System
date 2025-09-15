package ui;

import model.Student;
import javax.swing.*;
import java.awt.*;

public class StudentDialog extends JDialog {
    private boolean saved = false;
    private final JTextField nameField = new JTextField(25);
    private final JTextField rollField = new JTextField(12);
    private final JTextField gradeField = new JTextField(6);
    private final JTextField emailField = new JTextField(25);
    private final JTextField phoneField = new JTextField(15);

    public StudentDialog(Window owner, String title, Student prefill) {
        super(owner, title, ModalityType.APPLICATION_MODAL);
        init(prefill);
    }

    private void init(Student p) {
        JPanel main = new JPanel(new GridLayout(5, 2, 8, 8));
        main.add(new JLabel("Name:")); main.add(nameField);
        main.add(new JLabel("Roll:")); main.add(rollField);
        main.add(new JLabel("Grade:")); main.add(gradeField);
        main.add(new JLabel("Email:")); main.add(emailField);
        main.add(new JLabel("Phone:")); main.add(phoneField);

        if (p != null) {
            nameField.setText(p.getName());
            rollField.setText(p.getRoll());
            gradeField.setText(p.getGrade());
            emailField.setText(p.getEmail());
            phoneField.setText(p.getPhone());
        }

        JButton save = new JButton("Save");
        JButton cancel = new JButton("Cancel");
        JPanel btns = new JPanel();
        btns.add(save); btns.add(cancel);

        save.addActionListener(e -> {
            if (!nameField.getText().trim().isEmpty() && !rollField.getText().trim().isEmpty()) {
                saved = true;
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Name and Roll are required.");
            }
        });
        cancel.addActionListener(e -> setVisible(false));

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(main, BorderLayout.CENTER);
        getContentPane().add(btns, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(getOwner());
    }

    public boolean isSaved() { return saved; }
    public Student getStudent() {
        return new Student(nameField.getText(), rollField.getText(), gradeField.getText(), emailField.getText(), phoneField.getText());
    }
}
