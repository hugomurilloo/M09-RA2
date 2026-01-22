public class Compte {
    private static Compte instancia;
    private float saldo;

    private Compte(){
        this.saldo = 0f;
    }


    public synchronized float getSaldo() {
        return saldo;
    }

    public synchronized void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public static synchronized Compte getInstance(){
        if(instancia == null){
            instancia = new Compte();
        }
        return instancia;
    }

    public synchronized float ingresar(float quantitat){
        saldo += quantitat;
        return saldo;
    }

    public synchronized float retirar(float quantitat){
        saldo -= quantitat;
        return saldo;
    }



}
