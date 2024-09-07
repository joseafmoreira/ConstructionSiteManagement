package dev.joseafmoreira.incident;

import estgconstroi.Equipment;
import estgconstroi.Failure;

public class FailureImpl extends IncidentImpl implements Failure {
    private final Equipment equipment;

    public FailureImpl(String details, String notificationMessage, Equipment equipment) {
        super(details, notificationMessage);
        this.equipment = equipment;
    }

    @Override
    public Equipment getEquipment() {
        return equipment;
    }
}
