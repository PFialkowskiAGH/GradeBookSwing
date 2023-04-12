import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ClassesForm extends JFrame{
    private JPanel classesPanel;
    private JTable classesTable;
    private JButton addClass;
    private JTextField addClassName;
    private JTextField classMaxNumberOfStudents;
    private JButton removeClass;
    private JTextField removeClassName;
    private JButton findStudent;
    private JTextField classFindStudent;
    private JLabel addClassLabel;
    private JLabel removeClassLabel;
    private JPanel removeClassPanel;
    private JPanel removePanel;
    private JPanel findPanel;

    public ClassesForm(String title, ClassOfStudentContainer container)
    {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(classesPanel);
        this.pack();
        createClassesTable(container);
        addClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String msg = "";
                if(classesTable.getSelectionModel().isSelectionEmpty())
                {
                    if (addClassName.getText().isEmpty() || classMaxNumberOfStudents.getText().isEmpty()) msg = "Fill all necessary field to create class";
                    else
                    {
                        try {
                            ClassOfStudent newClass = new ClassOfStudent(addClassName.getText(), new ArrayList<>(), Integer.parseInt(classMaxNumberOfStudents.getText()));
                            msg = container.addClass(newClass.className, newClass);
                        }
                        catch (NumberFormatException error) {
                            msg = "You have to write Ineger number in MaxNumberOfStudents field";
                        }
                    }
                }
                else
                {
                    if (addClassName.getText().isEmpty()) addClassName.setText(classesTable.getValueAt(classesTable.getSelectedRow(), 0).toString());
                    if (classMaxNumberOfStudents.getText().isEmpty()) classMaxNumberOfStudents.setText(classesTable.getValueAt(classesTable.getSelectedRow(), 2).toString());
                    try {
                        msg = container.changeClass(classesTable.getValueAt(classesTable.getSelectedRow(), 0).toString(), addClassName.getText(), Integer.parseInt(classMaxNumberOfStudents.getText()));
                    }
                    catch (NumberFormatException error) {
                        msg = "You have to write Ineger number in MaxNumberOfStudents field";
                    }
                }
                if (msg != "") JOptionPane.showMessageDialog(null, msg);
                createClassesTable(container);
                addClassName.setText("");
                classMaxNumberOfStudents.setText("");
                addClassLabel.setText("Add class");
                removeClassPanel.setVisible(true);
                removeClassLabel.setText("Remove Class");
                addClass.setText("Add");
            }
        });
        removeClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = "";
                if(classesTable.getSelectionModel().isSelectionEmpty())
                {
                    if(removeClassName.getText().isEmpty()) msg = "Write name of class that you want delete or select this class in table";
                    else msg = container.removeClass(removeClassName.getText());
                }
                else
                {
                    msg = container.removeClass(classesTable.getValueAt(classesTable.getSelectedRow(), 0).toString());
                }
                if (msg != "") JOptionPane.showMessageDialog(null, msg);
                createClassesTable(container);
                removeClassName.setText("");
                addClassLabel.setText("Add class");
                removeClassPanel.setVisible(true);
                removeClassLabel.setText("Remove Class");
                addClass.setText("Add");
            }
        });
        findStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!classFindStudent.getText().isEmpty())
                {
                    List<Student> searchedStudent = container.searchStudent(classFindStudent.getText());
                    String msg = "";
                    for(Student currentStudent : searchedStudent)
                    {
                        msg += currentStudent.toString();
                    }
                    if (msg.isEmpty()) msg = "No class have student with this Lastname";
                    JOptionPane.showMessageDialog(null, msg);
                }
                else JOptionPane.showMessageDialog(null, "Write lastname you want searched in appropriate field");
            }
        });
        classesTable.addMouseListener(new MouseAdapter() {
            public void mousePressed (MouseEvent mouseEvent){
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1)
                {
                    String clickedClass = classesTable.getValueAt(classesTable.getSelectedRow(), 0).toString();
                    container.mapOfClasses.forEach((key, value) ->
                    {
                        if (key.equals(clickedClass)) {
                            JFrame gbf = new StudentsForm(key, value);
                            gbf.setVisible(true);
                            gbf.setSize(1000, 900);
                        }
                    });
                }
                if (mouseEvent.getClickCount() == 1 && table.getSelectedRow() != -1)
                {
                    addClassLabel.setText("Edit class");
                    removeClassLabel.setText("Remove selected Class");
                    removeClassPanel.setVisible(false);
                    addClass.setText("Edit");
                }
            }
        });
        classesPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed (MouseEvent mouseEvent){
                Point point = mouseEvent.getPoint();
                if (mouseEvent.getClickCount() == 1)
                {
                    classesTable.clearSelection();
                    addClassLabel.setText("Add class");
                    removeClassPanel.setVisible(true);
                    removeClassLabel.setText("Remove Class");
                    addClass.setText("Add");
                }
            }
        });
        if(this.hasFocus())
        {
            createClassesTable(container);
        }
    }

    private void createClassesTable(ClassOfStudentContainer container)
    {
        ClassContainerTableModel table = new ClassContainerTableModel(container.mapOfClasses);
        classesTable.setModel(table);
        if (container.mapOfClasses.isEmpty())
        {
            removePanel.setVisible(false);
            findPanel.setVisible(false);
        }
        else
        {
            removePanel.setVisible(true);
            findPanel.setVisible(true);
        }
    }
}
