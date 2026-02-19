public class Filosof extends Thread {
    private String nom;
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;
    private int gana;
    
    public Filosof(String nom, Forquilla esquerra, Forquilla dreta) {
        this.nom = nom;
        this.forquillaEsquerra = esquerra;
        this.forquillaDreta = dreta;
        this.gana = 0;
    }
    
    public String getNom() {
        return nom;
    }
    
    public Forquilla getForquillaEsquerra() {
        return forquillaEsquerra;
    }
    
    public Forquilla getForquillaDreta() {
        return forquillaDreta;
    }
    
    public void pensar() {
        System.out.println("Filósof: " + nom + " pensant");
        try {
            int temps = (int)(Math.random() * 1000) + 1000;
            Thread.sleep(temps);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void menjar() {
        while (true) {
            if (!forquillaEsquerra.isEnUs()) {
                forquillaEsquerra.setEnUs(true);
                System.out.println("Filósof: " + nom + " agafa la forquilla esquerra " + forquillaEsquerra.getNumero());
                
                if (!forquillaDreta.isEnUs()) {
                    forquillaDreta.setEnUs(true);
                    System.out.println("Filósof: " + nom + " agafa la forquilla dreta " + forquillaDreta.getNumero());
                    
                    System.out.println("Filósof: " + nom + " menja");
                    try {
                        int tempsMenjar = (int)(Math.random() * 1000) + 1000;
                        Thread.sleep(tempsMenjar);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    forquillaEsquerra.setEnUs(false);
                    forquillaDreta.setEnUs(false);
                    System.out.println("Filósof: " + nom + " ha acabat de menjar");
                    gana = 0;
                    break;
                    
                } else {
                    forquillaEsquerra.setEnUs(false);
                    System.out.println("Filósof: " + nom + " deixa l'esquerra(" + forquillaEsquerra.getNumero() + ") i espera (dreta ocupada)");
                    gana++;
                    System.out.println("Filósof: " + nom + " gana=" + gana);
                    
                    try {
                        int tempsEspera = (int)(Math.random() * 500) + 500;
                        Thread.sleep(tempsEspera);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    int tempsEspera = (int)(Math.random() * 500) + 500;
                    Thread.sleep(tempsEspera);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    @Override
    public void run() {
        while (true) {
            pensar();
            menjar();
        }
    }
}