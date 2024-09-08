import constructionsite.ConstructionSiteImpl;
import equipment.EquipmentImpl;
import equipment.EquipmentsImpl;
import employee.EmployeeImpl;
import estgconstroi.enums.EquipmentStatus;
import estgconstroi.enums.EquipmentType;
import estgconstroi.exceptions.ConstructionSiteException;
import estgconstroi.enums.EmployeeType;
import estgconstroi.enums.EventPriority;
import event.EventImpl;
import event.EventManagerImpl;
import java.io.IOException;
import team.TeamImpl;
import java.time.LocalDate;
import java.util.Arrays;
import reporter.InsuranceReporterImpl;


/**
 * Nome: Ricardo Afonso Saavedra Ferreira
 * Número: 8220132
 * Turma: LSIRC
 */
public class Main {

    
    public static void main(String[] args) throws ConstructionSiteException, IOException, InterruptedException {
        
        // Equipamentos
        EquipmentsImpl equipamentos = new EquipmentsImpl();
        
        // Equipamento
        EquipmentImpl equip1 = new EquipmentImpl("Martelo", EquipmentStatus.OPERATIVE, EquipmentType.TOOLS);
        equipamentos.addEquipment(equip1);
        EquipmentImpl equip4 = new EquipmentImpl("Chave de fendas", EquipmentStatus.INOPERATIVE, EquipmentType.HEAVY_DUTY);
        equipamentos.addEquipment(equip4);
        
        EquipmentImpl equip2 = new EquipmentImpl("Martelo2", EquipmentStatus.OPERATIVE, EquipmentType.HEAVY_DUTY);
        equipamentos.addEquipment(equip2);
        
//        System.out.println(Arrays.toString(equipamentos.getEquipment(EquipmentType.HEAVY_DUTY)));
//        System.out.println(Arrays.toString(equipamentos.getEquipment(EquipmentStatus.OPERATIVE)));
        
      
        
        //Employees
        EmployeeImpl employee11 = new EmployeeImpl("ZE Nando", EmployeeType.MANAGER);
        EmployeeImpl employee12 = new EmployeeImpl("Pacheco", EmployeeType.MANAGER);
        
        EmployeeImpl employee21 = new EmployeeImpl("Ricardo", EmployeeType.TEAM_LEADER);
        EmployeeImpl employee22 = new EmployeeImpl("Ana", EmployeeType.TEAM_LEADER);
        
        EmployeeImpl employee300 = new EmployeeImpl("Afonso", EmployeeType.WORKER);
        EmployeeImpl employee301 = new EmployeeImpl("Miguel", EmployeeType.WORKER);
        EmployeeImpl employee302 = new EmployeeImpl("Gustavo", EmployeeType.WORKER);
        EmployeeImpl employee303 = new EmployeeImpl("Gabriel", EmployeeType.WORKER);
        
        //Team
        TeamImpl team1 = new TeamImpl("Team Boas");
        team1.addEmployees(employee11);
//        team1.addEmployees(employee21);
        team1.addEmployees(employee300);
        team1.addEmployees(employee301);
        team1.addEmployees(employee302);

//        System.out.println(Arrays.toString(team1.getEmployees(EmployeeType.WORKER)));
        
        //Constrution Site
        ConstructionSiteImpl estaleiro1 = new ConstructionSiteImpl("Estaleiro 1",  "Porto",  "aaabbbccc",  LocalDate.of(2024, 9, 6),  LocalDate.of(2023, 9, 1),  LocalDate.of(2024, 12, 31), equipamentos);
        
        estaleiro1.addTeam(team1);
        estaleiro1.setResponsible(employee11);
        
//        System.out.println(Arrays.toString(team1.getEmployees()));
//        System.out.println(estaleiro1.getResponsible()); 
        
        //Event
        EventImpl event1 = new EventImpl(EventPriority.LOW, "caiu pedra", employee300, estaleiro1, "apenas caiu");
        EventImpl event2 = new EventImpl(EventPriority.IMMEDIATE, "caiu madeira", employee300, estaleiro1, "apenas caiu");
        EventImpl event3 = new EventImpl(EventPriority.LOW, "caiu parede", employee300, estaleiro1, "apenas caiu");

        //Event Managerx
        EventManagerImpl manager = new EventManagerImpl(); 
        manager.reportEvent(event1);
        manager.reportEvent(event2);
        manager.reportEvent(event3);

        System.out.println(manager.getEvent(LocalDate.of(2024, 9, 7),LocalDate.of(2024, 9, 9)));
//        System.out.println(manager.getEvent(LocalDate.of(2024, 9, 8)));

        //Insurance 
//        System.out.println(InsuranceReporterImpl.addEvent(InsuranceReporterImpl.formaterJSON(event1)));
//        System.out.println(InsuranceReporterImpl.addEvent(InsuranceReporterImpl.formaterJSON(event2)));
//        System.out.println(InsuranceReporterImpl.addEvent(InsuranceReporterImpl.formaterJSON(event3)));
        
//        System.out.println(InsuranceReporterImpl.resetEvents("LEETLEETLEETLEET", "Grupo1337"));
//        System.out.println(InsuranceReporterImpl.getEvents("LEETLEETLEETLEET", "Grupo1337"));
        
    }

}