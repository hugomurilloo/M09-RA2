public class Organitzador {
    static Esdeveniment esde = new Esdeveniment(5);
    static Assistent asis[] = new Assistent[10];
    
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            asis[i] = new Assistent("Assistent-" + i, esde);
            asis[i].start();
        }
    }
}