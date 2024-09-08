package dev.joseafmoreira.constructionsite;

import java.time.LocalDate;

import dev.joseafmoreira.team.TeamImpl;
import estgconstroi.ConstructionSite;
import estgconstroi.Employee;
import estgconstroi.Equipments;
import estgconstroi.Team;
import estgconstroi.enums.EmployeeType;
import estgconstroi.exceptions.ConstructionSiteException;

public class ConstructionSiteImpl implements ConstructionSite {
    private final String name;
    private final String location;
    private String permit;
    private LocalDate permitExpirationDate;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private static final int INITIAL_TEAM = 10;
    private Team[] teams;
    private int teamsCount;
    private final Equipments equipments;

    public ConstructionSiteImpl(String name, String location, String permit, LocalDate permitExpirationDate, LocalDate startDate, LocalDate endDate, Equipments equipments) {
        this.name = name;
        this.location = location;
        setPermit(permit, permitExpirationDate);
        this.startDate = startDate;
        this.endDate = endDate;
        teams = new TeamImpl[INITIAL_TEAM];
        teamsCount = 0;
        this.equipments = equipments;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public String getPermit() {
        return permit;
    }

    @Override
    public void setPermit(String permit, LocalDate permitExpirationDate) {
        this.permit = permit;
        this.permitExpirationDate = permitExpirationDate;
    }

    @Override
    public LocalDate getPermitExpirationDate() {
        return permitExpirationDate;
    }

    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public Employee getResponsible() {
        for (int i = 0; i < teamsCount; i++) {
            Employee[] employees = teams[i].getEmployees();
            for (int j = 0; j < teams[i].getNumberOfEmployees(); j++) {
                if (employees[j].getType().equals(EmployeeType.MANAGER)) return employees[j];
            }
        }

        return null;
    }

    @Override
    public void setResponsible(Employee employee) throws ConstructionSiteException {
        if (!employee.getType().equals(EmployeeType.MANAGER)) throw new ConstructionSiteException("Employee isn't a manager");

        boolean set = false;
        for (int i = 0; (i < teamsCount && !set); i++) {
            Employee[] employees = teams[i].getEmployees();
            for (int j = 0; j < teams[i].getNumberOfEmployees(); j++) {
                if (employees[j].equals(employee)) {
                    employees[j].setType(EmployeeType.MANAGER);
                    for (int k = 0; k < teams[i].getNumberOfEmployees(); k++) {
                        if (employees[k].getType().equals(EmployeeType.MANAGER)) {
                            employees[k].setType(EmployeeType.WORKER);
                            break;
                        }
                    }
                    set = true;
                    break;
                }
            }
        }
    }

    @Override
    public void addTeam(Team team) throws ConstructionSiteException {
        for (int i = 0; i < teamsCount; i++) {
            if (teams[i].equals(team)) throw new ConstructionSiteException("Team is already added");
        }

        if (teamsCount == teams.length) increaseTeamsArray();
        teams[teamsCount] = team;
        teamsCount++;
    }

    @Override
    public void removeTeam(Team team) throws ConstructionSiteException {
        for (int i = 0; i < teamsCount; i++) {
            if (teams[i].equals(team)) {
                for (int j = i; j < teamsCount - 1; j++) {
                    teams[j] = teams[j + 1];
                }
                teams[teamsCount - 1] = null;
                teamsCount--;
                return;
            }
        }

        throw new ConstructionSiteException("Team not found");
    }

    @Override
    public Team[] getTeams(String name) {
        Team[] result = new TeamImpl[teamsCount];
        int resultCount = 0;
        for (int i = 0; i < teamsCount; i++) {
            if (teams[i].getName().equals(name)) {
                result[resultCount] = teams[i];
                resultCount++;
            }
        }

        return result;
    }

    @Override
    public Team[] getTeams() {
        return teams;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public Equipments getEquipments() {
        return equipments;
    }

    private void increaseTeamsArray() {
        Team[] teams = new TeamImpl[this.teams.length * 2];
        for (int i = 0; i < teamsCount; i++) {
            teams[i] = this.teams[i];
        }

        this.teams = teams;
    }
}
