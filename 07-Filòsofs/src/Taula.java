public class Taula {
    private Filosof[] comensals;
    private Forquilla[] forquilles;
    
    public Taula(int numComensals) {
        forquilles = new Forquilla[numComensals];
        comensals = new Filosof[numComensals];
        
        for (int i = 0; i < numComensals; i++) {
            forquilles[i] = new Forquilla(i);
        }
        
        for (int i = 0; i < numComensals; i++) {
            Forquilla esquerra = forquilles[i];
            Forquilla dreta = forquilles[(i + 1) % numComensals];
            comensals[i] = new Filosof("filo" + i, esquerra, dreta);
        }
    }
    
    public void showTaula() {
        for (int i = 0; i < comensals.length; i++) {
            System.out.println("Comensal: " + comensals[i].getNom() + 
                             " esq: " + comensals[i].getForquillaEsquerra().getNumero() + 
                             " dret: " + comensals[i].getForquillaDreta().getNumero());
        }
        System.out.println("------------------------");
    }
    
    public void cridarATaula() {
        for (int i = 0; i < comensals.length; i++) {
            comensals[i].start();
        }
    }
    
    public static void main(String[] args) {
        Taula taula = new Taula(4);
        taula.showTaula();
        taula.cridarATaula();
    }
}