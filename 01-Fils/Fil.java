public class Fil extends Thread {
    private String nom;

    public Fil(String nom) {
        this.nom = nom;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 9; i++) {
            System.out.println(nom + " " + i);
            try {
                Thread.sleep(51); // Controla la velocitat del fil
            } catch (InterruptedException e) {
                System.out.println("Error en el fil " + nom);
            }
        }
        System.out.println("Termina el fil " + nom);
    }
}
