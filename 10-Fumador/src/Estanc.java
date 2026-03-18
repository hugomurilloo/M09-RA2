import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Estanc extends Thread {
    private List<Tabac> tabac = new ArrayList<>();
    private List<Paper> paper = new ArrayList<>();
    private List<Llumi> llumins = new ArrayList<>();
    private boolean obert = true;
    private Random random = new Random();

    public Estanc() {
    }

    public synchronized void addTabac() {
        tabac.add(new Tabac());
        System.out.println("Afegint Tabac");
        notifyAll();
    }

    public synchronized void addPaper() {
        paper.add(new Paper());
        System.out.println("Afegint Paper");
        notifyAll();
    }

    public synchronized void addLlumi() {
        llumins.add(new Llumi());
        System.out.println("Afegint Llumi");
        notifyAll();
    }

    public void nouSubministrament() {
        int opcio = random.nextInt(3);
        switch (opcio) {
            case 0: addTabac(); break;
            case 1: addPaper(); break;
            case 2: addLlumi(); break;
        }
    }

    public synchronized Tabac venTabac() {
        while (tabac.isEmpty() && obert) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (!tabac.isEmpty()) {
            return tabac.remove(0);
        }
        return null;
    }

    public synchronized Paper venPaper() {
        while (paper.isEmpty() && obert) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (!paper.isEmpty()) {
            return paper.remove(0);
        }
        return null;
    }

    public synchronized Llumi venLlumi() {
        while (llumins.isEmpty() && obert) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (!llumins.isEmpty()) {
            return llumins.remove(0);
        }
        return null;
    }

    public synchronized void tancarEstanc() {
        obert = false;
        notifyAll();
    }

    @Override
    public void run() {
        System.out.println("Estanc obert");
        while (obert) {
            nouSubministrament();
            try {
                Thread.sleep(random.nextInt(1000) + 500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Estanc tancat");
    }
}