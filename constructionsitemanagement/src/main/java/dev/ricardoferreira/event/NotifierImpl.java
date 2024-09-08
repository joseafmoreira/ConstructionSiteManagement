package event;

import estgconstroi.Event;
import estgconstroi.Notifier;

/**
 * Nome: Ricardo Afonso Saavedra Ferreira
 * Número: 8220132
 * Turma: LSIRC
 */

public class NotifierImpl implements Notifier{

    @Override
    public boolean notify(Event event) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Método para notificar na consola
    private boolean notifyConsole(Event event) {
        System.out.println("Notification to console:");
        System.out.println(event.getNotificationMessage());
        return true;
    }

}
