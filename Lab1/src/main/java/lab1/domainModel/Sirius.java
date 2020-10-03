package lab1.domainModel;

import java.util.ArrayList;

public class Sirius {
    public String name = "Sirius";
    public ArrayList<Corporation> corporations = new ArrayList<>();

    public Sirius() {
        corporations.add(new CyberneticsCorporation());
    }
}
