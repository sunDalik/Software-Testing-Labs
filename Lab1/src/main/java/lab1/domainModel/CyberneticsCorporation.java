package lab1.domainModel;

public class CyberneticsCorporation extends Corporation {

    public CyberneticsCorporation() {
        id = CorporationId.CYBERNETICS;
        departments.add(new MarketingDepartment());
        departments.add(new EducationDepartment());
    }
}
