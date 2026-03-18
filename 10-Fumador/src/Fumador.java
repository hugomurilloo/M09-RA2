import java.util.Random;

public class Fumador extends Thread {
    private Estanc estanc;
    private int id;
    private Tabac tabac = null;
    private Paper paper = null;
    private Llumi llumi = null;
    private int fumades = 0;
    private Random random = new Random();

    public Fumador(Estanc estanc, int id) {
        this.estanc = estanc;
        this.id = id;
    }

    public void compraTabac() {
        tabac = estanc.venTabac();
        if (tabac != null) {
            System.out.println("Fumador " + id + " comprant Tabac");
        }
    }

    public void compraPaper() {
        paper = estanc.venPaper();
        if (paper != null) {
            System.out.println("Fumador " + id + " comprant Paper");
        }
    }

    public void compraLlumi() {
        llumi = estanc.venLlumi();
        if (llumi != null) {
            System.out.println("Fumador " + id + " comprant Llumi");
        }
    }

    public void fuma() {
        if (tabac != null && paper != null && llumi != null) {
            System.out.println("Fumador " + id + " fumant");
            try {
                Thread.sleep(random.nextInt(500) + 500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            tabac = null;
            paper = null;
            llumi = null;
            fumades++;
            System.out.println("Fumador " + id + " ha fumat " + fumades + " vegades");
        }
    }

    @Override
    public void run() {
        while (fumades < 3) {
            if (tabac == null) compraTabac();
            if (paper == null) compraPaper();
            if (llumi == null) compraLlumi();
            if (tabac != null && paper != null && llumi != null) {
                fuma();
            }
        }
    }
}