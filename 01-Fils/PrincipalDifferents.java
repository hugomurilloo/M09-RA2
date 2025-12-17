public class PrincipalDifferents {
    public static void main(String[] args) {
        Fil pepe = new Fil("Pepe", 9, Thread.MIN_PRIORITY, 0);
        Fil juan = new Fil("Juan", 9, Thread.MAX_PRIORITY, 0);
        
        pepe.start();
        
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        juan.start();
        
        System.out.println("Acaba thread main");
    }
}