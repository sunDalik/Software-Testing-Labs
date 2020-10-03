package test.java;

import lab1.domainModel.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DomainModelTest {

    private Sirius sirius;
    private GreatGalaxyEncyclopedia encyclopedia;

    private final String greatGalaxyEncyclopediaRobotDefinition = "Mechanical apparatus designed to do human work";
    private final String siriusCyberneticCorporationMarketingDepartmentRobotDefinition = "Your plastic friend that will entertain you!";

    @Before
    public void setUpDomainModel() {
        sirius = new Sirius();
        encyclopedia = new GreatGalaxyEncyclopedia();
    }

    @Test
    public void testGreatGalaxyEncyclopediaRobotEntry() {
        Assert.assertEquals("Robot definition from Great Galaxy Encyclopedia is incorrect", greatGalaxyEncyclopediaRobotDefinition, encyclopedia.getDefinition("Robot"));
    }

    @Test
    public void testSiriusCyberneticCorporationMarketingDepartmentRobotEntry() {
        Corporation cyberneticsCorporation = null;
        for (Corporation corp : sirius.corporations) {
            if (corp.id == CorporationId.CYBERNETICS) {
                cyberneticsCorporation = corp;
                break;
            }
        }
        Assert.assertNotNull("No Cybernetics Corporation was found on Sirius", cyberneticsCorporation);

        Department marketingDepartment = null;
        for (Department dep : cyberneticsCorporation.departments) {
            if (dep.id == DepartmentId.MARKETING) {
                marketingDepartment = dep;
                break;
            }
        }
        Assert.assertNotNull("No Marketing Department was found in Sirius's Cybernetics Corporation", marketingDepartment);

        Assert.assertEquals("Robot definition from Marketing Department is incorrect", siriusCyberneticCorporationMarketingDepartmentRobotDefinition, marketingDepartment.slogans.getDefinition("Robot"));
    }

    @Test
    public void testEncyclopediaCaseInsensitivity() {
        Assert.assertEquals("Encyclopedia entries are not case insensitive", greatGalaxyEncyclopediaRobotDefinition, encyclopedia.getDefinition("robot"));
        Assert.assertEquals("Encyclopedia entries are not case insensitive", greatGalaxyEncyclopediaRobotDefinition, encyclopedia.getDefinition("ROBOT"));
        Assert.assertEquals("Encyclopedia entries are not case insensitive", greatGalaxyEncyclopediaRobotDefinition, encyclopedia.getDefinition("rObOT"));
    }
}
