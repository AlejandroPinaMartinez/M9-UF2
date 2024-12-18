public class Futbolista extends Thread {
    private int ngols;
    private int ntirades;

    private static final int NUM_JUGADORS = 11;
    private static final int NUM_TIRADES = 20;
    private static final float PROBABILITAT = 0.5f;

    // utilitzo el super pq no puc crear una variable de classe String nova per guardar el nom del jugador
    public Futbolista(String name) {
        super(name);
        this.ngols = 0;
        this.ntirades = 0;
    }

    public static void main(String[] args) {
        String[] noms = {"Piqué", "Vinicius", "Torres", "Ramos", "Ronaldo", "Lewan", "Belli", "Arnau", "Aspas", "Messi", "MBapé"};
        Futbolista[] jugadors = new Futbolista[NUM_JUGADORS];

        System.out.println("Inici dels xuts --------------------");
        // crea i inicia els fiks
        for (int i = 0; i < NUM_JUGADORS; i++) {
            jugadors[i] = new Futbolista(noms[i]);
            jugadors[i].start();
        }

        //espera  a que tots els fils acabin
        for (int i = 0; i < NUM_JUGADORS; i++) {
            try {
                jugadors[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Fi dels xuts -----------------------");
        System.out.println("---------- Estadístiques -----------");
        // mostra les estadístiques
        for (Futbolista jugador : jugadors) {
            System.out.println(jugador.getName() + " -> " + jugador.getNgols() + " gols");
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < NUM_TIRADES; i++) {
            ntirades++;
            if (Math.random() < PROBABILITAT) {
                ngols++;
            }
        }
    }

    public int getNgols() {
        return ngols;
    }

    public int getNtirades() {
        return ntirades;
    }
}
