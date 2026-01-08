public class Futbol extends Thread {
    public static final int NUM_JUGADORS = 11;
    public static final int NUM_TIRADES = 20;
    public static final float PROBABILITAT = 0.5f;
    
    private int ngolsintirades;
    
    public Futbol(String nom) {
        super(nom);
        this.ngolsintirades = 0;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < NUM_TIRADES; i++) {
            if (Math.random() < PROBABILITAT) {
                ngolsintirades++;
            }
        }
    }
    
    public int getGols() {
        return ngolsintirades;
    }
}