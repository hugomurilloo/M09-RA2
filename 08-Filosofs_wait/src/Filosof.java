public class Filosof extends Thread {
    private int id;
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;
    private int gana;
    
    public Filosof(int id, Forquilla esquerra, Forquilla dreta) {
        this.id = id;
        this.forquillaEsquerra = esquerra;
        this.forquillaDreta = dreta;
        this.gana = 0;
    }
    
    public String getNom() {
        return "filo" + id;
    }
    
    public Forquilla getForquillaEsquerra() {
        return forquillaEsquerra;
    }
    
    public Forquilla getForquillaDreta() {
        return forquillaDreta;
    }
    
    public void pensar() {
        System.out.println("Filòsof: " + getNom() + " pensant");
        try {
            int temps = (int)(Math.random() * 1000) + 1000;
            Thread.sleep(temps);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private synchronized void agafarForquilles() {
        while (true) {
            if (forquillaEsquerra.getPropietari() == Forquilla.LLIURE) {
                forquillaEsquerra.setPropietari(id);
                System.out.println("Filòsof: " + getNom() + " agafa la forquilla esquerra " + 
                                 forquillaEsquerra.getNumero());
                
                if (forquillaDreta.getPropietari() == Forquilla.LLIURE) {
                    forquillaDreta.setPropietari(id);
                    System.out.println("Filòsof: " + getNom() + " agafa la forquilla dreta " + 
                                     forquillaDreta.getNumero());
                    return;
                } else {
                    forquillaEsquerra.setPropietari(Forquilla.LLIURE);
                    System.out.println("Filòsof: " + getNom() + " deixa l'esquerra(" + 
                                     forquillaEsquerra.getNumero() + ") i espera (dreta ocupada)");
                }
            }
            
            gana++;
            System.out.println("Filòsof: " + getNom() + " gana=" + gana);
            
            try {
                wait((int)(Math.random() * 500) + 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private synchronized void deixarForquilles() {
        forquillaEsquerra.setPropietari(Forquilla.LLIURE);
        forquillaDreta.setPropietari(Forquilla.LLIURE);
        System.out.println("Filòsof: " + getNom() + " ha acabat de menjar");
        notifyAll();
    }
    
    public void menjar() {
        agafarForquilles();
        
        System.out.println("Filòsof: " + getNom() + " menja");
        try {
            int tempsMenjar = (int)(Math.random() * 1000) + 1000;
            Thread.sleep(tempsMenjar);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        deixarForquilles();
    }
    
    @Override
    public void run() {
        while (true) {
            pensar();
            synchronized(forquillaEsquerra) {
                menjar();
            }
        }
    }
}