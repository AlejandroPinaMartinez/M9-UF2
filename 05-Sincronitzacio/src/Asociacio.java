class Associacio {
    private static final int NUM_SOCIS = 1000;
    private final Soci[] socis;

    public static void main(String[] args) {
        Associacio associacio = new Associacio();
        associacio.iniciaCompteTempsSocis();
        associacio.mostraBalancComptes();
    }

    // Inicialitza un array de socis
    public Associacio() {
        this.socis = new Soci[NUM_SOCIS];
        for (int i = 0; i < NUM_SOCIS; i++) {
            socis[i] = new Soci();
        }
    }

    // inicia els fils per gestionar el temps dels socis
    public void iniciaCompteTempsSocis() {
        Thread[] fils = new Thread[NUM_SOCIS];
        for (int i = 0; i < NUM_SOCIS; i++) {
            fils[i] = new Thread(socis[i], "Soci-" + i);
            fils[i].start();
        }

        for (Thread fil : fils) {
            try {
                fil.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Mostra el saldo
    public void mostraBalancComptes() {
        Compte compte = Compte.getInstance();
        System.out.printf("Saldo: %.2f", compte.getSaldo());
    }
}
