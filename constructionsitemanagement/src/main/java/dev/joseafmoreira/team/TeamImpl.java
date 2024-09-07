package dev.joseafmoreira.team;

import java.util.Arrays;

import dev.joseafmoreira.employee.EmployeeImpl;
import dev.joseafmoreira.equipment.EquipmentsImpl;
import estgconstroi.Employee;
import estgconstroi.Equipments;
import estgconstroi.Team;
import estgconstroi.enums.EmployeeType;
import estgconstroi.exceptions.TeamException;

public class TeamImpl implements Team {
    private static final int INITIAL_EMPLOYEES = 10;
    private final String name;
    private Employee[] employees;
    private int employeeCount;
    private final Equipments equipments;

    public TeamImpl(String name) {
        this.name = name;
        employees = new EmployeeImpl[INITIAL_EMPLOYEES];
        employeeCount = 0;
        equipments = new EquipmentsImpl();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Employee getLeader() {
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getType().equals(EmployeeType.TEAM_LEADER)) return employees[i];
        }

        return null;
    }

    @Override
    public void setLeader(Employee employee) throws TeamException {
        if (!employee.getType().equals(EmployeeType.TEAM_LEADER)) throw new TeamException("Employee not a team leader");

        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getType().equals(EmployeeType.TEAM_LEADER)) employees[i].setType(EmployeeType.WORKER);
            if (employees[i].equals(employee)) employees[i].setType(EmployeeType.TEAM_LEADER);
        }
    }

    @Override
    public int getNumberOfEmployees() {
        return employeeCount;
    }

    @Override
    public void addEmployees(Employee employee) throws TeamException {
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].equals(employee)) throw new TeamException("Employee already in team");
        }

        if (employeeCount == employees.length) increaseEmployeesArray();
        employees[employeeCount] = employee;
        employeeCount++;
    }

    @Override
    public void removeEmployee(Employee employee) throws TeamException {
        for (int i = 0; i < employeeCount; i++) {
            if (this.employees[i].equals(employee)) {
                for (int j = i; j < employeeCount - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[employeeCount - 1] = null;
                employeeCount--;
                return;
            }
        }

        throw new TeamException("Employee not found");
    }

    @Override
    public Employee[] getEmployees(String name) {
        Employee[] result = new EmployeeImpl[employeeCount];
        int resultCount = 0;
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getName().equals(name)) {
                result[resultCount] = employees[i];
                resultCount++;
            }
        }

        return result;
    }

    @Override
    public Employee[] getEmployees(EmployeeType type) {
        Employee[] result = new EmployeeImpl[employeeCount];
        int resultCount = 0;
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getType().equals(type)) {
                result[resultCount] = employees[i];
                resultCount++;
            }
        }

        return result;
    }

    @Override
    public Employee[] getEmployees() {
        return employees;
    }

    @Override
    public Equipments getEquipments() {
        return equipments;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TeamImpl other = (TeamImpl) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (!Arrays.equals(employees, other.employees))
            return false;
        if (employeeCount != other.employeeCount)
            return false;
        if (equipments == null) {
            if (other.equipments != null)
                return false;
        } else if (!equipments.equals(other.equipments))
            return false;
        return true;
    }

    @Override
    public String toString() {
        String result = "Team[name=" + name + ", employees=[";
        for (int i = 0; i < employeeCount; i++) {
            result += employees[i];
            if (i < employeeCount - 1) result += ", ";
        }
        result += "], equipments=" + equipments + "]";

        return result;
    }

    private void increaseEmployeesArray() {
        Employee[] employees = new EmployeeImpl[this.employees.length * 2];
        for (int i = 0; i < employeeCount; i++) {
            employees[i] = this.employees[i];
        }

        this.employees = employees;
    }
}
