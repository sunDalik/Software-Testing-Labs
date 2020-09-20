package lab1.domainModel;

public class EducationDepartment extends Department {
    public EducationDepartment() {
        id = DepartmentId.EDUCATION;
        slogans.addEntry("Book", "Your paper friend that will educate you");
        slogans.addEntry("Earth", "A flat planet");
    }
}
