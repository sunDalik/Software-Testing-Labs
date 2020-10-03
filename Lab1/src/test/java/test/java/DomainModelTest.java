package test.java;

import lab1.domainModel.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DomainModelTest {

    private Sirius sirius;
    private GreatGalaxyEncyclopedia greatGalaxyEncyclopedia;

    @BeforeEach
    public void setUpDomainModel() {
        sirius = new Sirius();
        greatGalaxyEncyclopedia = new GreatGalaxyEncyclopedia();
    }

    @Test
    public void testGreatGalaxyEncyclopediaRobotEntry() {
        String greatGalaxyEncyclopediaRobotDefinition = "Mechanical apparatus designed to do human work";
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

        String siriusCyberneticCorporationMarketingDepartmentRobotDefinition = "Your plastic friend that will entertain you!";
        Assertions.assertEquals(siriusCyberneticCorporationMarketingDepartmentRobotDefinition, marketingDepartment.slogans.getDefinition("Robot"), "Robot definition from Marketing Department is incorrect");
    }

    @Test
    public void testSiriusName() {
        Assertions.assertEquals("Sirius", sirius.name, "Sirius's name is not Sirius");
    }

    @Nested
    class EncyclopediaTest {
        Encyclopedia encyclopedia;

        @BeforeEach
        public void setUpEncyclopedia() {
            encyclopedia = new Encyclopedia();
        }

        @Test
        public void testEncyclopediaAddEntry() {
            String key = "cat";
            String definition = "fluffy animal";
            encyclopedia.addEntry(key, definition);
            Assertions.assertEquals(definition, encyclopedia.getDefinition(key), "Got wrong definition by key after adding entry to encyclopedia");
        }

        @Test
        public void testEncyclopediaEmpty() {
            Assertions.assertEquals(0, encyclopedia.entries.size(), "New encyclopedia should have 0 entries");
        }

        @Test
        public void testEncyclopediaNullEntry() {
            Assertions.assertNull(encyclopedia.getDefinition("anything"), "If encyclopedia doesn't have an entry by given key, it must return null");
        }

        @Test
        public void testEncyclopediaCaseInsensitivity() {
            encyclopedia.addEntry("Hello", "world");
            Assertions.assertEquals("world", encyclopedia.getDefinition("Hello"), "Encyclopedia entries are not case insensitive");
            Assertions.assertEquals("world", encyclopedia.getDefinition("hello"), "Encyclopedia entries are not case insensitive");
            Assertions.assertEquals("world", encyclopedia.getDefinition("HELLO"), "Encyclopedia entries are not case insensitive");
            Assertions.assertEquals("world", encyclopedia.getDefinition("hElLo"), "Encyclopedia entries are not case insensitive");
        }
    }
}
