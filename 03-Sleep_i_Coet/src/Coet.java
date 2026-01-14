import java.util.Scanner;

class Coet {
    private final Motor[] motors = new Motor[4];
    private boolean finalitzat = false;

    public Coet() {
        for (int i = 0; i < 4; i++) {
            motors[i] = new Motor(i);
        }
    }

    public void arranca() {
        for (Motor motor : motors) {
            motor.start();
        }
    }

    public void passaAPotencia(int p) {
        if (p < 0 || p > 10) {
            System.out.println("Error: Potència ha d'estar entre 0 i 10");
            return;
        }

        System.out.println("\nPassant a potència " + p);
        for (Motor motor : motors) {
            motor.setPotencia(p);
        }
        
        if (p == 0) {
            finalitzat = true;
        }
    }

    public void esperarFin() {
        for (Motor motor : motors) {
            try {
                motor.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Coet coet = new Coet();
        coet.arranca();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduiu potència (0-10), 0 per apagar:");

        Thread hiloEntrada = new Thread(() -> {
            while (!coet.finalitzat) {
                try {
                    if (System.in.available() > 0) {
                        if (scanner.hasNextInt()) {
                            int potencia = scanner.nextInt();
                            coet.passaAPotencia(potencia);
                        }
                    } else {
                        Thread.sleep(100);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            scanner.close();
        });

        hiloEntrada.start();

        coet.esperarFin();

        try {
            hiloEntrada.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nCoet apagat completament");
    }
}
