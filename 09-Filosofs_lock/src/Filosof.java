public class Filosof extends Thread {
    private int gana;
    private long iniciGana;
    private long fiGana;
    private Forquilla forquillaDreta;
    private Forquilla forquillaEsquerra;
    private String nom;
    
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
        System.out.println(nom + " pensant");
        iniciGana = System.currentTimeMillis();
        
        try {
            int temps = (int)(Math.random() * 1000) + 1000;
            Thread.sleep(temps);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private void agafarForquilles() {
        agafarForquillaEsquerra();
        agafarForquillaDreta();
        System.out.println(nom + " té forquilles esq(" + forquillaEsquerra.getNumero() + 
                         ") dreta(" + forquillaDreta.getNumero() + ")");
    }
    
    private void agafarForquillaEsquerra() {
        forquillaEsquerra.agafar();
    }
    
    private void agafarForquillaDreta() {
        forquillaDreta.agafar();
    }
    
    private void deixarForquilles() {
        forquillaDreta.deixar();
        forquillaEsquerra.deixar();
        System.out.println(nom + " deixa les forquilles");
    }
    
    public void menjar() {
        agafarForquilles();
        
        fiGana = System.currentTimeMillis();
        calcularGana();
        System.out.println(nom + " menja amb gana " + gana);
        
        try {
            int tempsMenjar = (int)(Math.random() * 1000) + 1000;
            Thread.sleep(tempsMenjar);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println(nom + " ha acabat de menjar");
        deixarForquilles();
        resetGana();
    }
    
    public int calcularGana() {
        gana = (int)((fiGana - iniciGana) / 1000);
        return gana;
    }
    
    public void resetGana() {
        iniciGana = 0;
        fiGana = 0;
        gana = 0;
    }
    
    @Override
    public void run() {
        while (true) {
            pensar();
            menjar();
        }
    }
}