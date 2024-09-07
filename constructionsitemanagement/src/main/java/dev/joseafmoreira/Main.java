import constructionsite.ConstructionSiteImpl;
import equipment.EquipmentImpl;
import equipment.EquipmentsImpl;
import employee.EmployeeImpl;
import estgconstroi.Event;
import estgconstroi.enums.EquipmentStatus;
import estgconstroi.enums.EquipmentType;
import estgconstroi.exceptions.ConstructionSiteException;
import java.util.Arrays;
import estgconstroi.enums.EmployeeType;
import estgconstroi.enums.EventPriority;
import event.EventImpl;
import java.io.IOException;
import team.TeamImpl;
import java.time.LocalDate;
import reporter.InsuranceReporterImpl;


/**
 * Nome: Ricardo Afonso Saavedra Ferreira
 * Número: 8220132
 * Turma: LSIRC
 */
public class Main {

    
    public static void main(String[] args) throws ConstructionSiteException {
        
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
        ConstructionSiteImpl estaleiro1 = new ConstructionSiteImpl("Estaleiro 1",  "Porto",  "aaabbbccc",  LocalDate.of(2024, 9, 6),  LocalDate.of(2023, 9, 1),  LocalDate.of(2024, 12, 31), equipamentos, true );
        
        estaleiro1.addTeam(team1);
        estaleiro1.setResponsible(employee11);
        
//        System.out.println(Arrays.toString(team1.getEmployees()));
//        System.out.println(estaleiro1.getResponsible()); 
        
        //Event
        EventImpl event = new EventImpl(EventPriority.LOW, "caiu pedra", employee300, estaleiro1, "apenas caiu");
        System.out.println("Event Details:");
        System.out.println(event.getDetails());
        System.out.println(event.getNotificationMessage());
        
        
        //Insurance 
        String eventJson = "{"
                + "\"groupname\": \"Grupo89\","
                + "\"groupkey\": \"your-group-key-here\","
                + "\"event\": {"
                + "\"uuid\": \"abcd\","
                + "\"data\": \"2022-05-30\","
                + "\"priority\": \"High\","
                + "\"eventtype\": \"Accident\","
                + "\"title\": \"Avaria de máquina\","
                + "\"constructionsitename\": \"cs1\","
                + "\"details\": \"...\","
                + "\"employeename\": \"João\""
                + "}"
                + "}";
        
        try {
            // Call the addEvent method to communicate the event to the insurer
            String response = InsuranceReporterImpl.addEvent(eventJson);
            System.out.println("Response from insurer: " + response);
        } catch (IOException e) {
            System.err.println("IOException occurred: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("InterruptedException occurred: " + e.getMessage());
        }
        
        
    }

}