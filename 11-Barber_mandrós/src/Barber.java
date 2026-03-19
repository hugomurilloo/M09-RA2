import java.util.Random;

public class Barber extends Thread {
    private String nom;
    private Random random;
    
    public Barber(String nom) {
        super("Barber-" + nom);
        this.nom = nom;
        this.random = new Random();
    }
    
    @Override
    public void run() {
        Barberia barberia = Barberia.getInstance();
        
        while (true) {
            Client client = barberia.seguentClient();
            
            if (client != null) {
                System.out.println("Li toca al client " + client.getNom());
                System.out.println("Tallant cabell a " + client.getNom());
                
                int tempsTall = 900 + random.nextInt(100);
                try {
                    Thread.sleep(tempsTall);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                client.tallarseElCabell();
            } else {
                System.out.println("Ningú en espera");
                System.out.println("Barber " + nom + " dormint");
                
                synchronized(barberia.getCondBarber()) {
                    try {
                        barberia.getCondBarber().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}