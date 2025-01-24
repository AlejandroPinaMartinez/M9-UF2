class Compte {
    private static Compte instance;
    private float saldo;

    // Constructors
    private Compte() {
        this.saldo = 0;
    }

    public static synchronized Compte getInstance() {
        if (instance == null) {
            instance = new Compte();
        }
        return instance;
    }

    // Getters i setters
    public synchronized float getSaldo() {
        return saldo;
    }

    public  synchronized void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public synchronized void ingressar(float quantitat) {
        saldo += quantitat;
    }

    public synchronized void retirar(float quantitat) {
        saldo -= quantitat;
    }
}