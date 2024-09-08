package event;
import estgconstroi.Event;
import estgconstroi.EventManager;
import estgconstroi.Notifier;
import estgconstroi.enums.EventPriority;
import estgconstroi.exceptions.EventManagerException;

import java.time.LocalDate;

/**
 * Nome: Ricardo Afonso Saavedra Ferreira
 * Número: 8220132
 * Turma: LSIRC
 */


public class EventManagerImpl implements EventManager {

    private Notifier[] notifiers;
    private Event[] events;
    private int notifierCount;
    private int eventCount;

    public EventManagerImpl() {
        this.notifiers = new Notifier[10]; 
        this.events = new Event[10];       
        this.notifierCount = 0;
        this.eventCount = 0;
    }

    @Override
    public void addNotifier(Notifier ntfr) throws EventManagerException {
        if (ntfr == null) {
            throw new EventManagerException("Notifier cannot be null.");
        }
        if (containsNotifier(ntfr)) {
            throw new EventManagerException("Notifier already exists.");
        }
        if (notifierCount == notifiers.length) {
            expandNotifierArray();
        }
        notifiers[notifierCount++] = ntfr;
    }

    @Override
    public void removeNotifier(Notifier ntfr) throws EventManagerException {
        if (ntfr == null || !containsNotifier(ntfr)) {
            throw new EventManagerException("Notifier does not exist.");
        }
        int index = indexOfNotifier(ntfr);
        if (index != -1) {
            for (int i = index; i < notifierCount - 1; i++) {
                notifiers[i] = notifiers[i + 1];
            }
            notifiers[--notifierCount] = null;
        }
    }

    @Override
    public void reportEvent(Event event) throws EventManagerException {
        if (event == null) {
            throw new EventManagerException("Event cannot be null.");
        }
        if (eventCount == events.length) {
            expandEventArray();
        }
        events[eventCount] = event;
        System.out.println("Event inserted\nTittle: " + event.getTitle() + "\n" + event.getDetails() + "\n");
        
        notifyAllNotifiers(event);
        
        eventCount++;
    }

    @Override
    public void removeAllEvents() {
        for (int i = 0; i < eventCount; i++) {
            events[i] = null;
        }
        eventCount = 0;
    }

    @Override
    public void removeEvent(Event event) throws EventManagerException {
        int index = indexOfEvent(event);
        if (index == -1) {
            throw new EventManagerException("Event not found.");
        }
        for (int i = index; i < eventCount - 1; i++) {
            events[i] = events[i + 1];
        }
        events[--eventCount] = null;
    }

    @Override
    public Event[] getEvent(EventPriority ep) {
        Event[] result = new EventImpl[eventCount];
        int resultCount = 0;
        for (int i = 0; i < eventCount; i++) {
            if (events[i].getPriority().equals(ep)) {
                result[resultCount] = events[i];
                resultCount++;
            }       
        }
        Event[] result2 = new EventImpl[resultCount];
        System.out.println("\nEvents of priority " + ep + ":");
        for (int i = 0; i < resultCount; i++) {
            result2[i] = result[i]; 
            System.out.println(result2[i]);
        }
        return result2;
    }

    @Override
    public Event[] getEvent(Class type) {
        Event[] result = new Event[eventCount];
        int resultCount = 0;
        for (int i = 0; i < eventCount; i++) {
            if (type.isInstance(events[i])) {
                result[resultCount++] = events[i];
            }
        }
        return ajustArray(result, resultCount);
    }

    @Override
    public Event[] getEvent(LocalDate ld) {
        Event[] result = new EventImpl[eventCount];
        int resultCount = 0;
        for (int i = 0; i < eventCount; i++) {
            if (events[i].getDate().equals(ld)) {
                result[resultCount] = events[i];
                resultCount++;
            }       
        }
        Event[] result2 = new EventImpl[resultCount];
        System.out.println("\nEvents in " + ld + ":");
        for (int i = 0; i < resultCount; i++) {
            result2[i] = result[i]; 
            System.out.println(result2[i]);
        }
        return result2;
    }

    @Override
    public Event[] getEvent(LocalDate start, LocalDate end) {
        Event[] result = new Event[eventCount];
        int resultCount = 0;
        for (int i = 0; i < eventCount; i++) {
            LocalDate eventDate = events[i].getDate();
            if ((eventDate.isEqual(start) || eventDate.isAfter(start)) &&
                (eventDate.isEqual(end) || eventDate.isBefore(end))) {
                result[resultCount++] = events[i];
            }
        }
        Event[] result2 = new EventImpl[resultCount];
        System.out.println("\nEvents between " + start + " and " + end + ":");
        for (int i = 0; i < resultCount; i++) {
            result2[i] = result[i]; 
            System.out.println(result2[i]);
        }
        return result2;
    }

    private void notifyAllNotifiers(Event event) {
        for (int i = 0; i < notifierCount; i++) {
            notifiers[i].notify(event);
        }
    }

    private boolean containsNotifier(Notifier ntfr) {
        return indexOfNotifier(ntfr) != -1;
    }

    private int indexOfNotifier(Notifier ntfr) {
        for (int i = 0; i < notifierCount; i++) {
            if (notifiers[i].equals(ntfr)) {
                return i;
            }
        }
        return -1;
    }

    private int indexOfEvent(Event event) {
        for (int i = 0; i < eventCount; i++) {
            if (events[i].equals(event)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < eventCount; i++) {
            result += events[i].toString();
            if (i < eventCount - 1) result += ", ";
        }

        return result + "]";
    }
    private void expandNotifierArray() {
        Notifier[] newArray = new Notifier[notifiers.length * 2];
        System.arraycopy(notifiers, 0, newArray, 0, notifiers.length);
        notifiers = newArray;
    }

    private void expandEventArray() {
        Event[] newArray = new Event[events.length * 2];
        System.arraycopy(events, 0, newArray, 0, events.length);
        events = newArray;
    }

    private Event[] ajustArray(Event[] array, int size) {
        Event[] result = new Event[size];
        System.arraycopy(array, 0, result, 0, size);
        return result;
    }
}
