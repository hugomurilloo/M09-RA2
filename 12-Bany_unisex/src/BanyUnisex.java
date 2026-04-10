import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BanyUnisex {
    public static final int BANY_BUIT = 0;
    public static final int BANY_AMB_HOMES = 1;
    public static final int BANY_AMB_DONES = 2;

    public static final int CAPACITAT_MAX = 3;

    private int estatActual = BANY_BUIT;
    private int ocupants = 0;
    private final Semaphore capacitat = new Semaphore(CAPACITAT_MAX, true);
    private final ReentrantLock lockEstat = new ReentrantLock(true);

    public void entraHome(String nom) {
        System.out.println(nom + " vol entrar al bany");
        while (true) {
            lockEstat.lock();
            try {
                if (estatActual == BANY_BUIT || estatActual == BANY_AMB_HOMES) {
                    if (capacitat.tryAcquire()) {
                        ocupants++;
                        if (estatActual == BANY_BUIT) {
                            estatActual = BANY_AMB_HOMES;
                        }
                        System.out.println(nom + " entra al bany. Ocupants: " + ocupants);
                        return;
                    }
                }
            } finally {
                lockEstat.unlock();
            }
            sleepMillis(10);
        }
    }

    public void entraDona(String nom) {
        System.out.println(nom + " vol entrar al bany");
        while (true) {
            lockEstat.lock();
            try {
                if (estatActual == BANY_BUIT || estatActual == BANY_AMB_DONES) {
                    if (capacitat.tryAcquire()) {
                        ocupants++;
                        if (estatActual == BANY_BUIT) {
                            estatActual = BANY_AMB_DONES;
                        }
                        System.out.println(nom + " entra al bany. Ocupants: " + ocupants);
                        return;
                    }
                }
            } finally {
                lockEstat.unlock();
            }
            sleepMillis(10);
        }
    }

    public void surtHome(String nom) {
        lockEstat.lock();
        try {
            ocupants--;
            capacitat.release();
            System.out.println(nom + " surt del bany. Ocupants: " + ocupants);
            if (ocupants == 0) {
                estatActual = BANY_BUIT;
                System.out.println("El bany està buit");
            }
        } finally {
            lockEstat.unlock();
        }
    }

    public void surtDona(String nom) {
        lockEstat.lock();
        try {
            ocupants--;
            capacitat.release();
            System.out.println(nom + " surt del bany. Ocupants: " + ocupants);
            if (ocupants == 0) {
                estatActual = BANY_BUIT;
                System.out.println("El bany està buit");
            }
        } finally {
            lockEstat.unlock();
        }
    }

    private void sleepMillis(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BanyUnisex bany = new BanyUnisex();

        Thread[] homes = new Thread[5];
        Thread[] dones = new Thread[5];

        for (int i = 0; i < 5; i++) {
            homes[i] = new Home("Home-" + i, bany);
            homes[i].start();
        }
        for (int i = 0; i < 5; i++) {
            dones[i] = new Dona("Dona-" + i, bany);
            dones[i].start();
        }

        for (Thread home : homes) {
            home.join();
        }
        for (Thread dona : dones) {
            dona.join();
        }
    }
}
