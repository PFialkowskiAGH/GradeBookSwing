import java.util.ArrayList;

public class DataGenerator
{
    public static final String[] firstNames = {"Adam", "Paweł", "Patryk", "Kamil", "Joasia", "Anna"};
    public static final String[] lastName = {"Biegała", "Kowalski", "Wiśniewski", "Chory", "Zguba", "Badziewna"};
    public static final String[] Address = {"Siewna 12", "Pokątna 4", "Krakowskie Przedmieście 12", "Krakowska 8", "Mostowa 12/7", "Aleja Pokoju 12"};
    ClassOfStudentContainer container = new ClassOfStudentContainer();
    public DataGenerator()
    {
        Integer numberOfClass = getRandomInteger(10, 1);
        for (int i = 1; i <= numberOfClass; i++)
        {
            ClassOfStudent generatedClass = generateClass(i);
            container.addClass(generatedClass.className, generatedClass);
        }
    }
    ClassOfStudent generateClass(Integer i)
    {
        Integer max = getRandomInteger(10, 1);
        ClassOfStudent classOfStudent = new ClassOfStudent(i.toString(), new ArrayList<>(), max);
        Integer numberOfStudents = max/2;
        for (int j = 0; j <= numberOfStudents; j++)
        {
            Student generatedStudent = generateStudent(j);
            classOfStudent.addStudent(generatedStudent);
        }
        return classOfStudent;
    }
    Student generateStudent(Integer j)
    {
        Student student = new Student(firstNames[j], lastName[j], StudentCondition.Obecny, getRandomInteger(2005, 1997), getRandomInteger(6, 1), Address[j]);
        return student;
    }
    public static int getRandomInteger(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }
}