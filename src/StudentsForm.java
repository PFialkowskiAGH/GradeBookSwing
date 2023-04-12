import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class StudentsForm extends JFrame{
    private JPanel studentsPanel;
    private JTable studentsTable;
    private JButton addStudent;
    private JTextField addStudentFirstname;
    private JTextField addStudentLastname;
    private JComboBox<StudentCondition> addStudentCondition;
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
    private JTextField changedPoints;
    private JButton addPoints;
    private JButton removePoints;
    private JButton getPoint;
    private JComboBox<StudentCondition> changeCondition;
    private JButton changedConditionButton;
    private JTextArea messageArea;
    private JButton countConditionButton;
    private JLabel countedConditionLabel;
    private JLabel maxStudent;
    private JButton maxButton;
    private JLabel addStudentLabel;
    private JLabel removeStudentLabel;
    private JPanel removeStudentPannel;

    public StudentsForm(String title, ClassOfStudent classOfStudent)
    {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(studentsPanel);
        this.pack();
        createStudentsTable(classOfStudent);
        addStudentCondition.setModel(new DefaultComboBoxModel<>(StudentCondition.values()));
        changeCondition.setModel(new DefaultComboBoxModel<>(StudentCondition.values()));
        studentsTable.addMouseListener(new MouseAdapter() {
            public void mousePressed (MouseEvent mouseEvent){
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 1 && table.getSelectedRow() != -1)
                {
                    addStudentLabel.setText("Edit Student");
                    removeStudentLabel.setText("Remove selected Student");
                    removeStudentPannel.setVisible(false);
                    addStudent.setText("Edit");
                }
            }
        });
        studentsPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed (MouseEvent mouseEvent){
                Point point = mouseEvent.getPoint();
                if (mouseEvent.getClickCount() == 1)
                {
                    studentsTable.clearSelection();
                    addStudentLabel.setText("Add Student");
                    removeStudentLabel.setText("Remove Student");
                    removeStudentPannel.setVisible(true);
                    addStudent.setText("Add");
                }
            }
        });
        addStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String msg = "";
                if(studentsTable.getSelectionModel().isSelectionEmpty())
                {
                    if (addStudentFirstname.getText().isEmpty() || addStudentLastname.getText().isEmpty() || addStudentYear.getText().isEmpty() || addStudentPoints.getText().isEmpty() || addStudentAddress.getText().isEmpty()) msg = "Fill all necessary field to create student";
                    else
                    {
                        StudentCondition comboItem = (StudentCondition) addStudentCondition.getSelectedItem();
                        Student newStudent = new Student(addStudentFirstname.getText(), addStudentLastname.getText(), comboItem, Integer.parseInt(addStudentYear.getText()), Double.parseDouble(addStudentPoints.getText()), addStudentAddress.getText());
                        msg = classOfStudent.addStudent(newStudent);
                    }
                }
                else
                {
                    if (addStudentFirstname.getText().isEmpty()) addStudentFirstname.setText(studentsTable.getValueAt(studentsTable.getSelectedRow(), 0).toString());
                    if (addStudentLastname.getText().isEmpty()) addStudentLastname.setText(studentsTable.getValueAt(studentsTable.getSelectedRow(), 1).toString());
                    if (addStudentYear.getText().isEmpty()) addStudentYear.setText(studentsTable.getValueAt(studentsTable.getSelectedRow(), 3).toString());
                    if (addStudentPoints.getText().isEmpty()) addStudentPoints.setText(studentsTable.getValueAt(studentsTable.getSelectedRow(), 4).toString());
                    if (addStudentAddress.getText().isEmpty()) addStudentAddress.setText(studentsTable.getValueAt(studentsTable.getSelectedRow(), 5).toString());
                    StudentCondition comboItem = (StudentCondition) addStudentCondition.getSelectedItem();
                    msg = classOfStudent.changeStudent(studentsTable.getValueAt(studentsTable.getSelectedRow(), 1).toString(), addStudentFirstname.getText(), addStudentLastname.getText(), comboItem, Integer.parseInt(addStudentYear.getText()), Double.parseDouble(addStudentPoints.getText()), addStudentAddress.getText());
                }
                createStudentsTable(classOfStudent);
                if (msg != "") JOptionPane.showMessageDialog(null, msg);
                addStudentFirstname.setText("");
                addStudentLastname.setText("");
                addStudentYear.setText("");
                addStudentPoints.setText("");
                addStudentAddress.setText("");
                addStudentLabel.setText("Add Student");
                removeStudentLabel.setText("Remove Student");
                removeStudentPannel.setVisible(true);
                addStudent.setText("Add");
            }
        });
        removeStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String msg = "";
                if(studentsTable.getSelectionModel().isSelectionEmpty())
                {
                    if(removeStudentLastname.getText().isEmpty()) msg = "Write lastname of student that you want delete or select this student in table";
                    else msg = classOfStudent.removeStudent(removeStudentLastname.getText());
                }
                else{
                    msg = classOfStudent.removeStudent(studentsTable.getValueAt(studentsTable.getSelectedRow(), 1).toString());
                }
                if (msg != "") JOptionPane.showMessageDialog(null, msg);
                removeStudentLastname.setText("");
                createStudentsTable(classOfStudent);
                addStudentLabel.setText("Add Student");
                removeStudentLabel.setText("Remove Student");
                removeStudentPannel.setVisible(true);
                addStudent.setText("Add");
            }
        });
        showSummary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = classOfStudent.summary();
                if (msg != "") JOptionPane.showMessageDialog(null, msg);
                else JOptionPane.showMessageDialog(null, "Klasa jest pusta");
                messageArea.setText(msg);
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
                if (!searchedLastname.getText().isEmpty())
                {
                    Student searchedStudent = classOfStudent.search(searchedLastname.getText());
                    msg = searchedStudent.toString();
                    if (msg.isEmpty()) msg = "Class dont have student with that Lastname";
                    searchedLastname.setText("");
                    messageArea.setText(msg);
                }
                else msg = "Write lastname you want searched in appropriate field";
                JOptionPane.showMessageDialog(null, msg);
            }
        });
        findStudentByPartial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = "";
                if (!searchedPart.getText().isEmpty())
                {
                    List<Student> searchedStudent = classOfStudent.searchPartial(searchedPart.getText());
                    for(Student currentStudent : searchedStudent)
                    {
                        msg += currentStudent.toString();
                    }
                    if (msg.isEmpty()) msg = "No class have student with this part in Firstname or Lastname";

                    searchedPart.setText("");
                    messageArea.setText(msg);
                }
                else msg = "Write part of firstname, lastname you want searched in appropriate field";
                JOptionPane.showMessageDialog(null, msg);
            }
        });
        addPoints.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double points = Double.parseDouble(changedPoints.getText());
                String selectedStudentLastname = studentsTable.getValueAt(studentsTable.getSelectedRow(), 1).toString();
                Student selectedStudent = classOfStudent.search(selectedStudentLastname);
                classOfStudent.addPoints(selectedStudent, points);
                createStudentsTable(classOfStudent);
                changedPoints.setText("");
            }
        });
        removePoints.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double points = Double.parseDouble(changedPoints.getText());
                String selectedStudentLastname = studentsTable.getValueAt(studentsTable.getSelectedRow(), 1).toString();
                Student selectedStudent = classOfStudent.search(selectedStudentLastname);
                checkBeforeRemovePoints(selectedStudent, points);
                classOfStudent.removePoints(selectedStudent, points);
                createStudentsTable(classOfStudent);
                changedPoints.setText("");
            }
        });
        getPoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedStudentLastname = studentsTable.getValueAt(studentsTable.getSelectedRow(), 1).toString();
                Student selectedStudent = classOfStudent.search(selectedStudentLastname);
                checkBeforeRemovePoints(selectedStudent, 1.0);
                classOfStudent.getStudent(selectedStudent);
                createStudentsTable(classOfStudent);
                changedPoints.setText("");
            }
        });
        changedConditionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedStudentLastname = studentsTable.getValueAt(studentsTable.getSelectedRow(), 1).toString();
                Student selectedStudent = classOfStudent.search(selectedStudentLastname);
                StudentCondition comboItem = (StudentCondition) changeCondition.getSelectedItem();
                classOfStudent.changeCondition(selectedStudent, comboItem);
                createStudentsTable(classOfStudent);
            }
        });
        countConditionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentCondition comboItem = (StudentCondition) changeCondition.getSelectedItem();
                int countedCondition = classOfStudent.countByCondition(comboItem);
                countedConditionLabel.setText(String.valueOf(countedCondition));
            }
        });
        maxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maxStudent.setText(classOfStudent.max());
            }
        });
    }

    private void createStudentsTable(ClassOfStudent classOfStudent)
    {
        ClassTableModel table = new ClassTableModel(classOfStudent.students);
        studentsTable.setModel(table);
    }

    private void checkBeforeRemovePoints(Student selectedStudent, Double points)
    {
        if (selectedStudent.numberOfPoint - points <= 0.0) JOptionPane.showMessageDialog(null, "Student will be removed becaufe of lack of Points");
    }
}
