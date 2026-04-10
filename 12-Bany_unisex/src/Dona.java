import java.util.concurrent.ThreadLocalRandom;

public class Dona extends Thread {
    private final String nom;
    private final BanyUnisex bany;

    public Dona(String nom, BanyUnisex bany) {
        this.nom = nom;
        this.bany = bany;
    }

    @Override
    public void run() {
        bany.entraDona(nom);
        utilitzaLavabo();
        bany.surtDona(nom);
        System.out.println(nom + " ha acabat d'usar el bany");
    }

    private void utilitzaLavabo() {
        int temps = ThreadLocalRandom.current().nextInt(2000, 3001);
        try {
            Thread.sleep(temps);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
