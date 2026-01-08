public class DormAleatori extends Thread {
    
    private final long instantCreacio;
    private final int NUM_ITERACIONS = 10;
    
    public DormAleatori(String nom) {
        super(nom);
        this.instantCreacio = System.currentTimeMillis();
    }
    
    @Override
    public void run() {
        java.util.Random rand = new java.util.Random();
        
        for (int i = 0; i < NUM_ITERACIONS; i++) {
            int intervalAleatori = rand.nextInt(1000) + 1;
            long totalMs = System.currentTimeMillis() - instantCreacio;
            
            System.out.println(getName() + " (" + i + ") a dormir " + 
                              intervalAleatori + "ms total " + totalMs + "ms");
            
            try {
                Thread.sleep(intervalAleatori);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("F1 de main");
        
        DormAleatori joan = new DormAleatori("Joan");
        DormAleatori pep = new DormAleatori("Pep");
        
        joan.start();
        pep.start();
        
        try {
            joan.join();
            pep.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("S'ha acabat el main");
    }
}