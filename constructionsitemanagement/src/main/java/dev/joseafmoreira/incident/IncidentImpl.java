package dev.joseafmoreira.incident;

import estgconstroi.Incident;

public class IncidentImpl implements Incident {
    private final String details;
    private final String notificationMessage;

    public IncidentImpl(String details, String notificationMessage) {
        this.details = details;
        this.notificationMessage = notificationMessage;
    }

    @Override
    public String getDetails() {
        return details;
    }

    @Override
    public String getNotificationMessage() {
        return notificationMessage;
    }
}
