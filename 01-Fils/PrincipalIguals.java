public class PrincipalIguals {
    public static void main(String[] args) {
        Fil pepe = new Fil("Pepe", 9, Thread.MAX_PRIORITY, 0);
        Fil juan = new Fil("Juan", 10, Thread.MIN_PRIORITY, 0);
        
        pepe.start();
        juan.start();
        
        System.out.println("Acaba thread main");
    }
}