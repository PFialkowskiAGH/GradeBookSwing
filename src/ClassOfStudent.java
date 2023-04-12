import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClassOfStudent {
    String className;
    List<Student> students;
    int maxNumberOfStudents;
    public ClassOfStudent(String className, List<Student> students, int maxNumberOfStudents)
    {
        this.className = className;
        this.students = students;
        this.maxNumberOfStudents = maxNumberOfStudents;
    }
    String addStudent(Student student)
    {
        String message;
        if (maxNumberOfStudents > students.size())
        {
            boolean isExist = false;
            for (Student element : students)
            {
                if(element.compare(student)) isExist = true;
            }
            if (!isExist) {
                students.add(student);
                System.out.println("Dodano studenta do klasy\n");
                message = "";
            }
            else
            {
                System.out.println("W tej klasie jest już student o tym nazwisku\n");
                message = "W tej klasie jest już student o tym nazwisku";
            }
        }
        else
        {
            //System.err.println("Przekroczono wielkość klasy\n");
            message = "Przekroczono wielkość klasy";
        }
        return message;
    }
    void addPoints(Student student, double point)
    {
        student.numberOfPoint += point;
        System.out.println(student);
        System.out.println("Uzyskał %.2f punktów\n".formatted(point));
    }
    void getStudent(Student student)
    {
        if (student.numberOfPoint > 1)
        {
            student.numberOfPoint -= 1;
            System.out.println(student);
            System.out.println("Stracił punkty\n");
        }
        else
        {
            System.out.println(student);
            System.out.println("Zostaje usunięty\n");
            this.students.remove(student);
        }
    }
    void changeCondition(Student student, StudentCondition studentCondition)
    {
        student.studentCondition = studentCondition;
        System.out.println(student);
        System.out.println("Zmienił status\n");
    }
    void removePoints(Student student, double point)
    {
        student.numberOfPoint -= point-1;
        this.getStudent(student);
    }
    Student search(String lastName)
    {
        Student searchedStudent = new Student(lastName);
        for(Student currentStudent : students)
        {
            if (searchedStudent.compare(currentStudent))
            {
                System.out.println("Znaleniono w klasie %s studenta %s\n".formatted(className, currentStudent));
                return currentStudent;
            }
        }
        System.out.println("Nie znaleziono w klasie %s studenta o nazwisku %s\n".formatted(className,lastName));
        return searchedStudent;
    }
    List<Student> searchPartial(String partOfName)
    {
        List<Student> searchedStudents = new ArrayList<>();
        for(Student currentStudent : students)
        {
            if (currentStudent.firstName.contains(partOfName) || currentStudent.lastName.contains(partOfName)) searchedStudents.add(currentStudent);
        }
        System.out.println("Wyszukani studenci w klasie %s z %s w imieniu lub nazwisku:\n".formatted(className,partOfName));
        if (searchedStudents.size() == 0) System.out.println("Brak wyników\n");
        else
        {
            for(Student currentStudent : searchedStudents)
            {
                currentStudent.print();
            }
        }
        return searchedStudents;
    }
    int countByCondition(StudentCondition searchedCondition)
    {
        int numberOfStudents = 0;
        for(Student currentStudent : students)
        {
            if (currentStudent.studentCondition == searchedCondition) numberOfStudents++;
        }
        return numberOfStudents;
    }
    String summary()
    {
        String message = "";
        for(Student currentStudent : students)
        {
            message += currentStudent.print();
        }
        return message;
    };
    void sortByLastName()
    {
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.lastName.compareTo(o2.lastName);
            }
        });
    }
    void sortByPoints()
    {
        Collections.sort(students, new KomparatorPunkty());
        Collections.reverse(students);
    }
    String max()
    {
        System.out.println(Collections.max(students, new KomparatorPunkty()));
        return Collections.max(students, new KomparatorPunkty()).toString();
    }
    private class KomparatorPunkty implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            if (o1.numberOfPoint > o2.numberOfPoint) return 1;
            else return -1;
        }
    }

    String removeStudent(String lastname)
    {
        String message;
        boolean isExist = false;
        Student removedStudent = new Student(lastname);
        for (Student element : students)
        {
            if (element.compare(removedStudent))
            {
                isExist = true;
                removedStudent = element;
            }
        }
        if(isExist)
        {
            message = "";
            this.students.remove(removedStudent);
        }
        else message = "Nie można usunąć, ponieważ nie ma studenta o takim nazwisku";
        return message;
    }
}
