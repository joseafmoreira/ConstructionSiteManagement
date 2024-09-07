package event;

import estgconstroi.ConstructionSite;
import estgconstroi.Employee;
import estgconstroi.Event;
import estgconstroi.enums.EventPriority;

public class EventImpl extends Event {

    private final String incidentDetails;

    public EventImpl(EventPriority priority, String title, Employee reporter, ConstructionSite constructionSite, String incidentDetails) {
        super(priority, title, reporter, constructionSite);
        this.incidentDetails = incidentDetails;
    }

    @Override
    public String getDetails() {
        return "Incident Details: " + this.incidentDetails;
    }

    @Override
    public String getNotificationMessage() {
        return "Notification: Incident occurred at " + this.getConstructionSite().getName() + 
               " reported by " + this.getReporter().getName() + 
               " on " + this.getDate().toString() + ". \nPriority: " + this.getPriority().name();
    }
}

