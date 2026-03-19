import java.util.LinkedList;
import java.util.Queue;

public class Barberia extends Thread {
    private Queue<Client> salaEspera;
    private int maxCadires;
    private Object condBarber;
    private static Barberia instance;
    
    public Barberia(int maxCadires) {
        super("Barberia");
        this.maxCadires = maxCadires;
        this.salaEspera = new LinkedList<>();
        this.condBarber = new Object();
        instance = this;
    }
    
    public static Barberia getInstance() {
        return instance;
    }
    
    public Object getCondBarber() {
        return condBarber;
    }
    
    public synchronized Client seguentClient() {
        return salaEspera.poll();
    }
    
    public synchronized void entrarClient(Client client) {
        if (salaEspera.size() < maxCadires) {
            salaEspera.add(client);
            System.out.println("Client " + client.getNom() + " en espera");
            
            synchronized(condBarber) {
                condBarber.notify();
            }
        } else {
            System.out.println("No queden cadires, client " + client.getNom() + " se'n va");
        }
    }
    
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            Client client = new Client(i);
            entrarClient(client);
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        for (int i = 11; i <= 20; i++) {
            Client client = new Client(i);
            entrarClient(client);
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        Barberia barberia = new Barberia(3);
        Barber barber = new Barber("Pepe");
        
        barber.start();
        barberia.start();
    }
}