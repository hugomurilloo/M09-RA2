import java.util.Random;

public class Treballador extends Thread {
    private int edat_inici_treball;
    private int edat_fi_treball;
    private float sou_anual_brut;
    private int edat_actual;
    private float cobrat;
    private Random rnd;

    public Treballador(String nom, float sou_anual_brut, int edat_inici_treball, int edat_fi_treball) {
        super(nom);
        this.sou_anual_brut = sou_anual_brut;
        this.edat_inici_treball = edat_inici_treball;
        this.edat_fi_treball = edat_fi_treball;
        this.edat_actual = 0;
        this.cobrat = 0.0f;
        this.rnd = new Random();
    }

    public void cobra() {
        cobrat += sou_anual_brut / 12.0f;
    }

    public void pagaImpostos() {
        float impostos = cobrat * 0.24f;
        cobrat -= impostos;
    }

    @Override
    public void run() {
        while (edat_actual < edat_fi_treball) {
            if (edat_actual >= edat_inici_treball) {
                cobra();
                pagaImpostos();
            }
            edat_actual++;
            
            try {
                Thread.sleep(rnd.nextInt(10) + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public float getCobrat() {
        return cobrat;
    }

    public int getEdat() {
        return edat_actual;
    }
}
