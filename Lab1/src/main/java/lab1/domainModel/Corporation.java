package lab1.domainModel;

import java.util.ArrayList;

public class Corporation {
    public ArrayList<Department> departments = new ArrayList<>();
    public CorporationId id = CorporationId.UNDEFINED;

    public Department findDepartment(DepartmentId id) {
        for (Department dep : departments) {
            if (dep.id == id) {
                return dep;
            }
        }
        return null;
    }
}
