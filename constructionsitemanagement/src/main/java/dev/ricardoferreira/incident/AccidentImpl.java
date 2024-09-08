package incident;

import estgconstroi.Accident;
import estgconstroi.Employee;

public class AccidentImpl extends IncidentImpl implements Accident {
    private final Employee employee;

    public AccidentImpl(String details, String notificationMessage, Employee employee) {
        super(details, notificationMessage);
        this.employee = employee;
    }

    @Override
    public Employee getEmployee() {
        return employee;
    }
}
