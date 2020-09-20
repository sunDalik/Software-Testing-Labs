package lab1.domainModel;

public class InformaticsCorporation extends Corporation {

    public InformaticsCorporation() {
        id = CorporationId.INFORMATICS;
        departments.add(new MarketingDepartment());
    }
}
