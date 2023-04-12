import java.util.ArrayList;

public class DataGenerator
{
    public static final String[] classes = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};
    public static final String[] firstNames = {"Adam", "Paweł", "Patryk", "Kamil", "Joasia", "Anna"};
    public static final String[] lastName = {"Biegała", "Kowalski", "Wiśniewski", "Chory", "Zguba", "Badziewna"};
    public static final String[] addresses = {"Siewna 12", "Pokątna 4", "Krakowskie Przedmieście 12", "Krakowska 8", "Mostowa 12/7", "Aleja Pokoju 12"};
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
        Integer classLetter = getRandomInteger(10, 1);
        ClassOfStudent classOfStudent = new ClassOfStudent(i.toString() + classes[classLetter], new ArrayList<>(), max);
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
        Integer firstname = getRandomInteger(5, 1);
        Integer lastname = getRandomInteger(5, 1);
        Integer address = getRandomInteger(5, 1);
        Student student = new Student(firstNames[firstname], lastName[lastname], StudentCondition.Obecny, getRandomInteger(2005, 1997), getRandomInteger(6, 1), addresses[address]);
        return student;
    }
    public static int getRandomInteger(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }
}