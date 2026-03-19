public class Client {
    private String nom;
    private int id;
    
    public Client(int id) {
        this.id = id;
        this.nom = "Client-" + id;
    }
    
    public void tallarseElCabell() {
    }
    
    public String getNom() {
        return nom;
    }
}