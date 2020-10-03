package test.java;

import lab1.domainModel.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DomainModelTest {

    private Sirius sirius;
    private GreatGalaxyEncyclopedia greatGalaxyEncyclopedia;

    private final String greatGalaxyEncyclopediaRobotDefinition = "Mechanical apparatus designed to do human work";
    private final String siriusCyberneticCorporationMarketingDepartmentRobotDefinition = "Your plastic friend that will entertain you!";

    @Before
    public void setUpDomainModel() {
        sirius = new Sirius();
        greatGalaxyEncyclopedia = new GreatGalaxyEncyclopedia();
    }

    @Test
    public void testGreatGalaxyEncyclopediaRobotEntry() {
        Assert.assertEquals("Robot definition from Great Galaxy Encyclopedia is incorrect", greatGalaxyEncyclopediaRobotDefinition, greatGalaxyEncyclopedia.getDefinition("Robot"));
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
        Assert.assertEquals("Encyclopedia entries are not case insensitive", greatGalaxyEncyclopediaRobotDefinition, greatGalaxyEncyclopedia.getDefinition("robot"));
        Assert.assertEquals("Encyclopedia entries are not case insensitive", greatGalaxyEncyclopediaRobotDefinition, greatGalaxyEncyclopedia.getDefinition("ROBOT"));
        Assert.assertEquals("Encyclopedia entries are not case insensitive", greatGalaxyEncyclopediaRobotDefinition, greatGalaxyEncyclopedia.getDefinition("rObOT"));
    }

    @Test
    public void testEncyclopediaAddEntry() {
        Encyclopedia encyclopedia = new Encyclopedia();
        String key = "cat";
        String definition = "fluffy animal";
        encyclopedia.addEntry(key, definition);
        Assert.assertEquals("Got wrong definition by key after adding entry to encyclopedia", definition, encyclopedia.getDefinition(key));
    }

    @Test
    public void testEncyclopediaEmpty() {
        Encyclopedia encyclopedia = new Encyclopedia();
        Assert.assertEquals("New encyclopedia should have 0 entries", 0, encyclopedia.entries.size());
    }

    @Test
    public void testEncyclopediaNullEntry() {
        Encyclopedia encyclopedia = new Encyclopedia();
        Assert.assertNull("If encyclopedia doesn't have an entry by given key, it must return null", encyclopedia.getDefinition("anything"));
    }
}
