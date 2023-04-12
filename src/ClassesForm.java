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
                ClassOfStudent newClass = new ClassOfStudent(addClassName.getText(), new ArrayList<>(), Integer.parseInt(classMaxNumberOfStudents.getText()));
                String msg = container.addClass(newClass.className, newClass);
                if (msg != "") JOptionPane.showMessageDialog(null, msg);
                createClassesTable(container);
                addClassName.setText("");
                classMaxNumberOfStudents.setText("");
            }
        });
        removeClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = container.removeClass(removeClassName.getText());
                if (msg != "") JOptionPane.showMessageDialog(null, msg);
                createClassesTable(container);
                removeClassName.setText("");
            }
        });
        findStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Student> searchedStudent = container.searchStudent(classFindStudent.getText());
                String msg = "";
                System.out.print(searchedStudent.size());
                for(Student currentStudent : searchedStudent)
                {
                    msg += currentStudent.toString();
                }
                if (msg.isEmpty()) msg = "No class have student with this Lastname";
                JOptionPane.showMessageDialog(null, msg);
            }
        });
        classesTable.addMouseListener(new MouseAdapter() {
            public void mousePressed (MouseEvent mouseEvent){
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    String clickedClass = classesTable.getValueAt(classesTable.getSelectedRow(), 0).toString();
                    container.mapOfClasses.forEach((key, value) ->
                    {
                        if (key.equals(clickedClass)) {
                            JFrame gbf = new StudentsForm(key, value);
                            gbf.setVisible(true);
                            gbf.setSize(800, 500);
                        }
                    });
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
    }
}
