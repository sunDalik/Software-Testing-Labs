package lab1.domainModel;

public class MarketingDepartment extends Department {
    public MarketingDepartment() {
        id = DepartmentId.MARKETING;
        slogans.addEntry("Robot", "Your plastic friend that will entertain you!");
        slogans.addEntry("Car", "Innovative way to move in the city!");
        slogans.addEntry("Our department", "The best department you will ever find!");
    }
}
