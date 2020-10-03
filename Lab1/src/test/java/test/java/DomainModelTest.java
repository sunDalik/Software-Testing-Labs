package test.java;

import lab1.domainModel.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DomainModelTest {

    private Sirius sirius;
    private GreatGalaxyEncyclopedia greatGalaxyEncyclopedia;

    private final String greatGalaxyEncyclopediaRobotDefinition = "Mechanical apparatus designed to do human work";
    private final String siriusCyberneticCorporationMarketingDepartmentRobotDefinition = "Your plastic friend that will entertain you!";

    @BeforeEach
    public void setUpDomainModel() {
        sirius = new Sirius();
        greatGalaxyEncyclopedia = new GreatGalaxyEncyclopedia();
    }

    @Test
    public void testGreatGalaxyEncyclopediaRobotEntry() {
        Assertions.assertEquals(greatGalaxyEncyclopediaRobotDefinition, greatGalaxyEncyclopedia.getDefinition("Robot"), "Robot definition from Great Galaxy Encyclopedia is incorrect");
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
        Assertions.assertNotNull(cyberneticsCorporation, "No Cybernetics Corporation was found on Sirius");

        Department marketingDepartment = null;
        for (Department dep : cyberneticsCorporation.departments) {
            if (dep.id == DepartmentId.MARKETING) {
                marketingDepartment = dep;
                break;
            }
        }
        Assertions.assertNotNull(marketingDepartment, "No Marketing Department was found in Sirius's Cybernetics Corporation");

        Assertions.assertEquals(siriusCyberneticCorporationMarketingDepartmentRobotDefinition, marketingDepartment.slogans.getDefinition("Robot"), "Robot definition from Marketing Department is incorrect");
    }

    @Test
    public void testEncyclopediaCaseInsensitivity() {
        Assertions.assertEquals(greatGalaxyEncyclopediaRobotDefinition, greatGalaxyEncyclopedia.getDefinition("robot"), "Encyclopedia entries are not case insensitive");
        Assertions.assertEquals(greatGalaxyEncyclopediaRobotDefinition, greatGalaxyEncyclopedia.getDefinition("ROBOT"), "Encyclopedia entries are not case insensitive");
        Assertions.assertEquals(greatGalaxyEncyclopediaRobotDefinition, greatGalaxyEncyclopedia.getDefinition("rObOT"), "Encyclopedia entries are not case insensitive");
    }

    @Test
    public void testEncyclopediaAddEntry() {
        Encyclopedia encyclopedia = new Encyclopedia();
        String key = "cat";
        String definition = "fluffy animal";
        encyclopedia.addEntry(key, definition);
        Assertions.assertEquals(definition, encyclopedia.getDefinition(key), "Got wrong definition by key after adding entry to encyclopedia");
    }

    @Test
    public void testEncyclopediaEmpty() {
        Encyclopedia encyclopedia = new Encyclopedia();
        Assertions.assertEquals(0, encyclopedia.entries.size(), "New encyclopedia should have 0 entries");
    }

    @Test
    public void testEncyclopediaNullEntry() {
        Encyclopedia encyclopedia = new Encyclopedia();
        Assertions.assertNull(encyclopedia.getDefinition("anything"), "If encyclopedia doesn't have an entry by given key, it must return null");
    }
}
