import java.util.Random;

public class Assistent extends Thread {
    String nom;
    Esdeveniment esde;
    
    public Assistent(String nom, Esdeveniment esde) {
        this.nom = nom;
        this.esde = esde;
    }
    
    @Override
    public void run() {
        Random rand = new Random();
        while(true) {
            if(rand.nextInt(2) == 1) {
                esde.ferReserva(this);
            } else {
                esde.cancelaReserva(this);
            }
            try {
                sleep(rand.nextInt(1000));
            } catch (Exception e) {
            }
        }
    }
}