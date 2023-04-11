import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClassesForm extends JFrame{
    private JPanel classesPanel;
    private JTable classesTable;
    private JButton addClass;
    private JTextField addClassName;
    private JTextField classMaxNumberOfStudents;
    private JButton removeClass;
    private JTextField removeClassName;

    public ClassesForm(String title, ClassOfStudentContainer container)
    {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(classesPanel);
        this.pack();
        ClassContainerTableModel table = new ClassContainerTableModel(container.mapOfClasses);
        createClassesTable(table);
        addClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ClassOfStudent newClass = new ClassOfStudent(addClassName.getText(), new ArrayList<>(), Integer.parseInt(classMaxNumberOfStudents.getText()));
                container.addClass(newClass.className, newClass);
                ClassContainerTableModel table = new ClassContainerTableModel(container.mapOfClasses);
                createClassesTable(table);
                addClassName.setText("");
                classMaxNumberOfStudents.setText("");
            }
        });
        removeClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.removeClass(removeClassName.getText());
                ClassContainerTableModel table = new ClassContainerTableModel(container.mapOfClasses);
                createClassesTable(table);
                removeClassName.setText("");
            }
        });
    }

    private void createClassesTable(ClassContainerTableModel table)
    {
        classesTable.setModel(table);
    }
}
