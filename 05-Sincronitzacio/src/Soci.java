import java.util.Random;

class Soci implements Runnable {
    private static final float APORTACIO = 10f;
    private static final int ESPERA_MAX = 100;
    private static final int MAX_ANYS = 10;

    private final Compte compte;
    private final Random random;

    // Constructor 
    public Soci() {
        this.compte = Compte.getInstance();
        this.random = new Random();
    }

    // Obté la instància del compte
    public Compte getCompte() {
        return compte;
    }

    // Comportament del soci durant els anys i mesos
    @Override
    public void run() {
        for (int any = 0; any < MAX_ANYS; any++) {
            for (int mes = 1; mes <= 12; mes++) {
                synchronized(compte) {
                    // Ingressa o retira diners segons el mes
                    if (mes % 2 == 0) {
                        compte.ingressar(APORTACIO);
                    } else {
                        compte.retirar(APORTACIO);
                    }
                }
                try {
                    // Espera un temps aleatori abans de la següent acció
                    Thread.sleep(random.nextInt(ESPERA_MAX));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
