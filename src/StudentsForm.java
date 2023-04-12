import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentsForm extends JFrame{
    private JPanel studentsPanel;
    private JTable studentsTable;
    private JButton addStudent;
    private JTextField addStudentFirstname;
    private JTextField addStudentLastname;
    private JComboBox addStudentCondition;
    private JTextField addStudentYear;
    private JTextField addStudentPoints;
    private JTextField addStudentAddress;

    public StudentsForm(String title, ClassOfStudent classOfStudent)
    {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(studentsPanel);
        this.pack();
        createStudentsTable(classOfStudent);
        addStudentCondition.setModel(new DefaultComboBoxModel<>(StudentCondition.values()));
        addStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentCondition comboItem = (StudentCondition) addStudentCondition.getSelectedItem();
                Student newStudent = new Student(addStudentFirstname.getText(), addStudentLastname.getText(), comboItem, Integer.parseInt(addStudentYear.getText()), Double.parseDouble(addStudentPoints.getText()), addStudentAddress.getText());
                String msg = classOfStudent.addStudent(newStudent);
                createStudentsTable(classOfStudent);
                if (msg != "") JOptionPane.showMessageDialog(null, msg);
                addStudentFirstname.setText("");
                addStudentLastname.setText("");
                addStudentYear.setText("");
                addStudentPoints.setText("");
                addStudentAddress.setText("");
            }
        });
    }

    private void createStudentsTable(ClassOfStudent classOfStudent)
    {
        ClassTableModel table = new ClassTableModel(classOfStudent.students);
        studentsTable.setModel(table);
    }
}
