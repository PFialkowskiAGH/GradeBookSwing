import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
    private JButton removeStudent;
    private JTextField removeStudentLastname;
    private JButton showSummary;
    private JButton sortByLastnameButton;
    private JButton sortByPointsButton;
    private JButton findStudentByLastname;
    private JTextField searchedLastname;
    private JButton findStudentByPartial;
    private JTextField searchedPart;

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
                studentsTable.repaint();
                createStudentsTable(classOfStudent);
                if (msg != "") JOptionPane.showMessageDialog(null, msg);
                addStudentFirstname.setText("");
                addStudentLastname.setText("");
                addStudentYear.setText("");
                addStudentPoints.setText("");
                addStudentAddress.setText("");
            }
        });
        removeStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = classOfStudent.removeStudent(removeStudentLastname.getText());
                if (msg != "") JOptionPane.showMessageDialog(null, msg);
                removeStudentLastname.setText("");
                createStudentsTable(classOfStudent);
            }
        });
        showSummary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = classOfStudent.summary();
                if (msg != "") JOptionPane.showMessageDialog(null, msg);
                else JOptionPane.showMessageDialog(null, "Klasa jest pusta");
            }
        });
        sortByLastnameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classOfStudent.sortByLastName();
                createStudentsTable(classOfStudent);
            }
        });
        sortByPointsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classOfStudent.sortByPoints();
                createStudentsTable(classOfStudent);
            }
        });
        findStudentByLastname.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg;
                Student searchedStudent = classOfStudent.search(searchedLastname.getText());
                msg = searchedStudent.toString();
                if (msg.isEmpty()) msg = "Class dont have student with that Lastname";
                JOptionPane.showMessageDialog(null, msg);
                searchedLastname.setText("");
            }
        });
        findStudentByPartial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Student> searchedStudent = classOfStudent.searchPartial(searchedPart.getText());
                String msg = "";
                for(Student currentStudent : searchedStudent)
                {
                    msg += currentStudent.toString();
                }
                if (msg.isEmpty()) msg = "No class have student with this part in Firstname or Lastname";
                JOptionPane.showMessageDialog(null, msg);
                searchedPart.setText("");
            }
        });
    }

    private void createStudentsTable(ClassOfStudent classOfStudent)
    {
        ClassTableModel table = new ClassTableModel(classOfStudent.students);
        studentsTable.setModel(table);
    }
}
