import java.util.Random;

public class Soci extends Thread {
    private Compte compte;
    private float aportacio = 10f;
    private int esperarMax = 100;
    private Random random;
    private int maxAnys = 10;
    private int id;


    public Soci(int id) {
        this.id = id;
        this.compte = Compte.getInstance();
        this.random = new Random();
    }

    public Compte getCompte() {
        return compte;
    }

    @Override
    public void run(){
        try{
            for(int any = 0; any < maxAnys; any++){
                for(int mes = 1; mes <= 12; mes++){
                    if(mes%2==0){
                        compte.ingresar(aportacio);
                    } else{
                        compte.retirar(aportacio);
                    }

                    int espera = random.nextInt(esperarMax);
                    Thread.sleep(espera);
                }
            }
        } catch (InterruptedException e){
            System.out.println("Soci " + id + " interromput: " + e.getMessage());
        }
    
    }
    
}
