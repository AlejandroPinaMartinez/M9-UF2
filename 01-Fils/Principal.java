public class Principal {
    public static void main(String[] args) {
        System.out.println("Termina thread main");

        // Crear els fils
        Thread filJuan = new Thread(new Fil("Juan"));
        Thread filPepe = new Thread(new Fil("Pepe"));

            filPepe.start();
            filJuan.start();
    }
}

