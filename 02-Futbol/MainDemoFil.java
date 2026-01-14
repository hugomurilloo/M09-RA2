
public class MainDemoFil {
    public static void main(String[] args) {
        System.out.println("Inici dels xuts ---");
        
        Futbol[] jugadors = new Futbol[Futbol.NUM_JUGADORS];
        String[] noms = {"Ronaldo", "Levan", "Bell1", "Arnau", "Aspas", 
                         "Messi", "MBapé", "Piqué", "Vinicius", "Torres", "Ramos"};
        
        for (int i = 0; i < Futbol.NUM_JUGADORS; i++) {
            jugadors[i] = new Futbol(noms[i]);
        }
        
        for (Futbol jugador : jugadors) {
            jugador.start();
        }
        
        for (Futbol jugador : jugadors) {
            try {
                jugador.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("Fi dels xuts ---");
        System.out.println("--- Estadístiques ---");
        
        for (Futbol jugador : jugadors) {
            System.out.println(jugador.getName() + " -> " + jugador.getGols() + " gols");
        }
    }
}
