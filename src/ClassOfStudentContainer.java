import java.util.ArrayList;
import java.util.HashMap;

public class ClassOfStudentContainer {
    String nameContainer;
    HashMap<String, ClassOfStudent> mapOfClasses = new HashMap<String, ClassOfStudent>() {};

    String addClass(String className, ClassOfStudent classOfStudent)
    {
        String message;
        if(mapOfClasses.containsKey(className))
        {
            System.out.println("Taka klasa jest już w kontenerze");
            message = "Taka klasa jest już w kontenerze";
        }
        else
        {
            mapOfClasses.put(className, classOfStudent);
            System.out.println("Dodano klasę %s do konetenera".formatted(className));
            message = "";
        }
        return message;
    }
    String removeClass(String className)
    {
        String message;
        if(mapOfClasses.containsKey(className))
        {
            mapOfClasses.remove(className);
            System.out.println("Usunięto klasę z konetenra");
            message = "";
        }

        else
        {
            System.out.println("Taka klasa nie jest w kontenerze");
            message = "Taka klasa nie jest w kontenerze";
        }
        return message;
    }
    ArrayList findEmptyGroups()
    {
        ArrayList emptyClasses = new ArrayList<>();
        mapOfClasses.forEach((key, value) ->
        {
            if(value.students.size() == 0)
            {
                System.out.println("Klasa %s jest pusta".formatted(key));
                emptyClasses.add(value);
            }
        });
        return emptyClasses;
    }
    void summary()
    {
        mapOfClasses.forEach((key, value) ->
        {
            double percentage = ((float) value.students.size()) / value.maxNumberOfStudents;
            System.out.println("Nazwa klasy: %s".formatted(key));
            System.out.println("Zapełnienie klasy: %.2f\n".formatted(percentage));
        });
    }
    void isEmpty()
    {
        if (mapOfClasses.isEmpty()) System.out.println("Ten konetner jest pusty");
        else System.out.println("Ten kontener nie jest pusty");
    }

    ArrayList searchStudent(String lastname)
    {
        ArrayList searchedStudent = new ArrayList<Student>();
        mapOfClasses.forEach((key, value) ->
        {
            searchedStudent.add(value.search(lastname));
        });
        return searchedStudent;
    }
}
