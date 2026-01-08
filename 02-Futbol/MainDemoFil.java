package 02-Futbol;

public class MainDemoFil {
    public static void main(String[] args) {
        System.out.println("Inici dels xuts ---");
        
        Futbol[] jugadors = new Futbol[Futbol.NUM_JUGADORS];
        String[] noms = {"Ronaldo", "Levan", "Bell1", "Arnau", "Aspas", 
                         "Messi", "MBapé", "Piqué", "Vinicius", "Torres", "Ramos"};
        
        for (int i = 0; i < Futbolista.NUM_JUGADORS; i++) {
            jugadors[i] = new Futbolista(noms[i]);
        }
        
        for (Futbolista jugador : jugadors) {
            jugador.start();
        }
        
        for (Futbolista jugador : jugadors) {
            try {
                jugador.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("Fi dels xuts ---");
        System.out.println("--- Estadístiques ---");
        
        for (Futbolista jugador : jugadors) {
            System.out.println(jugador.getName() + " -> " + jugador.getGols() + " gols");
        }
    }
}
