import javax.swing.*;
import ui.StudentFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentFrame().setVisible(true));
    }
}
