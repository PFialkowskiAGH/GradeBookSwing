import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        Student firstStudent = new Student("Paweł", "Ogonek", StudentCondition.Chory, 2000, 3.0, "Krakowska 15" );
        Student secondStudent = new Student("Michał", "Ogórek", StudentCondition.Chory, 2000, 3.0, "Krakowska 15" );
        Student thirdStudent = new Student("Józef", "Ogonek", StudentCondition.Chory, 2000, 3.0, "Krakowska 15" );
        List studentsList = new ArrayList<Student>();
        studentsList.add(firstStudent);
        studentsList.add(secondStudent);
        studentsList.add(thirdStudent);
        ClassOfStudent firstClass = new ClassOfStudent("1a", studentsList, 4);
        ClassOfStudentContainer firstContainer = new ClassOfStudentContainer();
        firstContainer.addClass(firstClass.className, firstClass);
        //////////////////////////////////////////////////////////////////////
        ClassContainerTableModel table = new ClassContainerTableModel(firstContainer.mapOfClasses);
        WindowUI gradebookWindow = new WindowUI(table);
        gradebookWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gradebookWindow.setVisible(true);
    }
}