package equipment;

import estgconstroi.Equipment;
import estgconstroi.Equipments;
import estgconstroi.enums.EquipmentStatus;
import estgconstroi.enums.EquipmentType;
import estgconstroi.exceptions.ConstructionSiteException;

public class EquipmentsImpl implements Equipments {
    private static final int INITIAL_EQUIPMENTS = 10;
    private Equipment[] equipment;
    private int equipmentCount;

    public EquipmentsImpl() {
        equipment = new EquipmentImpl[INITIAL_EQUIPMENTS];
        equipmentCount = 0;
    }

    @Override
    public void addEquipment(Equipment equipment) throws ConstructionSiteException {
        for (int i = 0; i < equipmentCount; i++) {
            if (this.equipment[i].equals(equipment)) {
                throw new ConstructionSiteException("Equipment already added");
            }
        }

        if (equipmentCount == this.equipment.length) increaseEquipmentArray();
        this.equipment[equipmentCount] = equipment;
        equipmentCount++;
    }

    @Override
    public void removeEquipment(Equipment equipment) throws ConstructionSiteException {
        for (int i = 0; i < equipmentCount; i++) {
            if (this.equipment[i].equals(equipment)) {
                for (int j = i; j < equipmentCount - 1; j++) {
                    this.equipment[j] = this.equipment[j + 1];
                }
                this.equipment[equipmentCount - 1] = null;
                equipmentCount--;
                return;
            }
        }

        throw new ConstructionSiteException("Equipment not found");
    }

    @Override
    public Equipment[] getEquipment(String name) {
        Equipment[] result = new EquipmentImpl[equipmentCount];
        int resultCount = 0;
        for (int i = 0; i < equipmentCount; i++) {
            if (equipment[i].getName().equals(name)) {
                result[resultCount] = equipment[i];
                resultCount++;
            }
        }

        return result;
    }

    @Override
    public Equipment[] getEquipment(EquipmentStatus status) {
        Equipment[] result = new EquipmentImpl[equipmentCount];
        int resultCount = 0;
        for (int i = 0; i < equipmentCount; i++) {
            if (equipment[i].getStatus().equals(status)) {
                result[resultCount] = equipment[i];
                resultCount++;
            }       
        }
        Equipment[] result2 = new EquipmentImpl[resultCount];
        for (int i = 0; i < resultCount; i++) {
            result2[i] = result[i]; 
        }
        
        return result2;
    }

    @Override
    public Equipment[] getEquipment(EquipmentType type) {
        Equipment[] result = new EquipmentImpl[equipmentCount];
        int resultCount = 0;
        for (int i = 0; i < equipmentCount; i++) {
            if (equipment[i].getType().equals(type)) {
                result[resultCount] = equipment[i];
                resultCount++;
            }       
        }
        Equipment[] result2 = new EquipmentImpl[resultCount];
        for (int i = 0; i < resultCount; i++) {
            result2[i] = result[i]; 
        }
        
        return result2;
    }

    @Override
    public Equipment[] getEquipment() {
        Equipment[] result = new EquipmentImpl[equipmentCount];
        for (int i = 0; i < equipmentCount; i++) {
            result[i] = equipment[i];
        }

        return result;
    }

    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < equipmentCount; i++) {
            result += equipment[i].toString();
            if (i < equipmentCount - 1) result += ", ";
        }

        return result + "]";
    }

    private void increaseEquipmentArray() {
        Equipment[] equipment = new EquipmentImpl[this.equipment.length * 2];
        for (int i = 0; i < equipmentCount; i++) {
            equipment[i] = this.equipment[i];
        }

        this.equipment = equipment;
    }
}
