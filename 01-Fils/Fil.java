public class Fil extends Thread {
    private String nom;
    private int maxComptador;
    private int prioritat;
    private int delay;
    
    public Fil(String nom, int maxComptador, int prioritat, int delay) {
        this.nom = nom;
        this.maxComptador = maxComptador;
        this.prioritat = prioritat;
        this.delay = delay;
    }
    
    @Override
    public void run() {
        this.setPriority(prioritat);
        
        for (int i = 1; i <= maxComptador; i++) {
            System.out.println(nom + " " + i);
                        for (int j = 0; j < 1000; j++) {
            }   
            if (delay > 0) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Acaba el fil " + nom);
    }
}