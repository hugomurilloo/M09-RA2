public class Associacio {
    private int numSocis = 1000;
    private Soci[] socis;
    
    public Associacio() {
        socis = new Soci[numSocis];
    }

    public void iniciaCompteTempsSocis(){
        for(int i = 0; i < numSocis; i++){
            socis[i] = new Soci(i + 1);
        }
    }

    public void esperaPeriodeSocis(){
        for(int i = 0; i < numSocis; i++){
            socis[i].start();
        }

        for(int i = 0; i < numSocis; i++){
            try{
                socis[i].join();
            } catch(InterruptedException e){
                System.out.println("Error esperant soci " + i + ": " + e.getMessage());
            }
        }

    }

    public void mostraBalancComptes(){
        Compte compte = Compte.getInstance();
        System.out.println("Saldo final: " + compte.getSaldo() + " euros");
    }



    public static void main(String[] args) {
        Associacio associacio = new Associacio();

        associacio.iniciaCompteTempsSocis();

        associacio.esperaPeriodeSocis();
        
        associacio.mostraBalancComptes();
    }
}
