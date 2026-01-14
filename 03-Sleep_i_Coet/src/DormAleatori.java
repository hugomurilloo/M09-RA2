import java.util.Random;

public class DormAleatori extends Thread {
    private long instantCreacio;
    private Random rand;
    
    public DormAleatori(String nom) {
        super(nom);
        this.instantCreacio = System.currentTimeMillis();
        this.rand = new Random();
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int intervalAleatori = rand.nextInt(1000); 
            long tempsActual = System.currentTimeMillis();
            long tempsDesDeCreacio = tempsActual - instantCreacio;
            
            System.out.printf("%s(%d) a dormir %4dms total %5dms%n", 
                              getName(), i, intervalAleatori, tempsDesDeCreacio);
            
            try {
                Thread.sleep(intervalAleatori);
            } catch (InterruptedException e) {
                System.out.println(getName() + " interromput!");
                return;
            }
        }
    }
    
    public static void main(String[] args) {
        DormAleatori joan = new DormAleatori("Joan");
        DormAleatori pep = new DormAleatori("Pep");
        
        joan.start();
        pep.start();
        
        System.out.println("-- Fi de main ---");
    }
}