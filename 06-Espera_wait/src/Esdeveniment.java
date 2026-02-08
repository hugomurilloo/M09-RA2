import java.util.ArrayList;
import java.util.List;

public class Esdeveniment {
    int places;
    int placesDisponibles;
    List<Assistent> assistents;
    
    public Esdeveniment() {
        this(10);
    }
    
    public Esdeveniment(int places) {
        this.places = places;
        placesDisponibles = places;
        this.assistents = new ArrayList<>();
    }
    
    public synchronized void ferReserva(Assistent a) {
        while(placesDisponibles == 0) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
        assistents.add(a);
        placesDisponibles--;
        System.out.printf("%s ha fet una reserva. Places disponibles: %d %n", a.nom, placesDisponibles);
    }
    
    public synchronized void cancelaReserva(Assistent a) {
        if (assistents.contains(a)) {
            assistents.remove(a);
            placesDisponibles++;
            notifyAll();
            System.out.printf("%s ha cancel·lat una reserva. Places disponibles: %d %n", a.nom, placesDisponibles);
        } else {
            System.out.printf("%s no ha pogut cancel·lar una reserva inexistent. Places disponibles: %d %n", a.nom, placesDisponibles);
        }
    }
}