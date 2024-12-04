public class Principal {
    public static void main(String[] args) {
        System.out.println("Termina thread main");

        Thread filJuan = new Thread(new Fil("Juan"));
        Thread filPepe = new Thread(new Fil("Pepe"));

        filJuan.start();
        filPepe.start();
    }
}
