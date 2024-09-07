package employee;

import estgconstroi.Employee;
import estgconstroi.enums.EmployeeType;

public class EmployeeImpl extends Employee {
    private final String name;
    private EmployeeType type;

    public EmployeeImpl(String name, EmployeeType type) {
        super();
        this.name = name;
        setType(type);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public EmployeeType getType() {
        return type;
    }

    @Override
    public void setType(EmployeeType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EmployeeImpl other = (EmployeeImpl) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (type != other.type)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Employee[uuid=" + getUUID() + ", name=" + name + ", type=" + type + "]";
    }
}
