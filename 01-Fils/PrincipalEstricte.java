public class PrincipalEstricte {
    public static void main(String[] args) {
        System.out.println("Termina thread main");
        
        Fil juan = new Fil("Juan", 11, Thread.NORM_PRIORITY, 1);
        Fil pepe = new Fil("Pepe", 11, Thread.NORM_PRIORITY, 1);
        
        juan.start();
        
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        pepe.start();
    }
}