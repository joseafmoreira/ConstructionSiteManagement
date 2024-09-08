package incident;

import estgconstroi.Employee;
import estgconstroi.Equipment;

/**
 * Nome: Ricardo Afonso Saavedra Ferreira
 * Número: 8220132
 * Turma: LSIRC
 */

public class AccidentMachine extends IncidentImpl{
    private final Employee employee;
    private final Equipment equipment;

    public AccidentMachine(String details, String notificationMessage, Employee employee, Equipment equipment){
        super(details, notificationMessage);
        this.employee = employee;
        this.equipment = equipment;
    }

    public Employee getEmployee() {
        return employee;
    }
    
    public Equipment getEquipment() {
        return equipment;
    }
}
