import java.util.concurrent.ThreadLocalRandom;

public class Home extends Thread {
    private final String nom;
    private final BanyUnisex bany;

    public Home(String nom, BanyUnisex bany) {
        this.nom = nom;
        this.bany = bany;
    }

    @Override
    public void run() {
        bany.entraHome(nom);
        utilitzaLavabo();
        bany.surtHome(nom);
        System.out.println(nom + " ha acabat d'usar el bany");
    }

    private void utilitzaLavabo() {
        int temps = ThreadLocalRandom.current().nextInt(1000, 2001);
        try {
            Thread.sleep(temps);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
