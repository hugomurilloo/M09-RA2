import java.util.Random;

class Motor extends Thread {
    private int potenciaActual = 0;
    private int potenciaObjectiu = 0;
    private final int id;
    private final Random random = new Random();
    private boolean actiu = true;
    private boolean enObjectiu = true;
    private boolean solicitadoApagar = false;

    public Motor(int id) {
        this.id = id;
    }

    public synchronized void setPotencia(int p) {
        if (p < 0 || p > 10) {
            System.out.println("Error: Potència ha d'estar entre 0 i 10");
            return;
        }
        potenciaObjectiu = p;
        enObjectiu = false;
        if (p == 0) {
            solicitadoApagar = true;
        }
    }

    @Override
    public void run() {
        while (actiu) {
            synchronized(this) {
                if (potenciaActual < potenciaObjectiu) {
                    potenciaActual++;
                    enObjectiu = false;
                    System.out.println("Motor " + id + ": Incre. Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
                } else if (potenciaActual > potenciaObjectiu) {
                    potenciaActual--;
                    enObjectiu = false;
                    System.out.println("Motor " + id + ": Decre. Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
                } else if (!enObjectiu) {
                    enObjectiu = true;
                    if (potenciaObjectiu == 0 && solicitadoApagar) {
                        actiu = false;
                    } else if (potenciaObjectiu > 0) {
                        System.out.println("Motor " + id + ": FerRes Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
                    }
                }
            }
            
            if (actiu) {
                try {
                    Thread.sleep(random.nextInt(1000) + 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}