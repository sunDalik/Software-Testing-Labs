package lab1.domainModel;

import java.util.ArrayList;

public class Planet {
    public String name;
    public ArrayList<Corporation> corporations = new ArrayList<>();

    public Planet(String planetName) {
        name = planetName;
    }

    public Corporation findCorporation(CorporationId id) {
        for (Corporation corp : corporations) {
            if (corp.id == id) {
                return corp;
            }
        }
        return null;
    }
}
