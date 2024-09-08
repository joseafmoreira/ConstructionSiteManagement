package dev.joseafmoreira.incident.interfaces;

import estgconstroi.Employee;
import estgconstroi.Equipment;
import estgconstroi.Incident;

public interface AccidentWithEquipment extends Incident {
    Employee getEmployee();
    Equipment getEquipment();
}
