public class Administracio {
    private int num_poblacio_activa = 50;
    private Treballador[] poblacio_activa;

    public Administracio() {
        poblacio_activa = new Treballador[num_poblacio_activa];
        for (int i = 0; i < num_poblacio_activa; i++) {
            poblacio_activa[i] = new Treballador(
                "Ciutadà-" + i,
                25000,
                20,
                65
            );
        }
    }

    public void iniciar() {
        for (int i = 0; i < num_poblacio_activa; i++) {
            poblacio_activa[i].start();
        }

        for (int i = 0; i < num_poblacio_activa; i++) {
            try {
                poblacio_activa[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < num_poblacio_activa; i++) {
            System.out.printf("Ciutadà-%d -> edat: %d / total: %.2f%n",
                i,
                poblacio_activa[i].getEdat(),
                poblacio_activa[i].getCobrat()
            );
        }
    }

    public static void main(String[] args) {
        Administracio admin = new Administracio();
        admin.iniciar();
    }
}
