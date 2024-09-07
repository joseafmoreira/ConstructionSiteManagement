package dev.joseafmoreira.equipment;

import estgconstroi.Equipment;
import estgconstroi.enums.EquipmentStatus;
import estgconstroi.enums.EquipmentType;

public class EquipmentImpl implements Equipment {
    private final String name;
    private EquipmentStatus status;
    private final EquipmentType type;

    public EquipmentImpl(String name, EquipmentStatus status, EquipmentType type) {
        this.name = name;
        setStatus(status);
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public EquipmentStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(EquipmentStatus status) {
        this.status = status;
    }

    @Override
    public EquipmentType getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        EquipmentImpl other = (EquipmentImpl) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (status != other.status) {
            return false;
        }
        if (type != other.type) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Equipment[name=" + getName() + ", status=" + getStatus() + ", type=" + getType() + "]";
    }
}
