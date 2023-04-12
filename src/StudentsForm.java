import javax.swing.*;

public class StudentsForm extends JFrame{
    private JPanel studentsPanel;
    private JTable studentsTable;

    public StudentsForm(String title, ClassOfStudent classOfStudent)
    {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(studentsPanel);
        this.pack();
        createClassesTable(classOfStudent);
    }

    private void createClassesTable(ClassOfStudent classOfStudent)
    {
        ClassTableModel table = new ClassTableModel(classOfStudent.students);
        studentsTable.setModel(table);
    }
}
