package dev.joseafmoreira.incident;

import dev.joseafmoreira.incident.interfaces.AccidentWithEquipment;
import estgconstroi.Employee;
import estgconstroi.Equipment;

public class AccidentWithEquipmentImpl extends IncidentImpl implements AccidentWithEquipment {
    private final Employee employee;
    private final Equipment equipment;

    public AccidentWithEquipmentImpl(String details, String notificationMessage, Employee employee, Equipment equipment) {
        super(details, notificationMessage);
        this.employee = employee;
        this.equipment = equipment;
    }

    @Override
    public Employee getEmployee() {
        return employee;
    }

    @Override
    public Equipment getEquipment() {
        return equipment;
    }
}
