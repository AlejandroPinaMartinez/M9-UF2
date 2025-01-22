class Compte {
    private static Compte instance;
    private float saldo;

    private Compte() {
        this.saldo = 0;
    }

    public static Compte getInstance() {
        if (instance == null) {
            instance = new Compte();
        }
        return instance;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public void ingressar(float quantitat) {
        saldo += quantitat;
    }

    public void retirar(float quantitat) {
        saldo -= quantitat;
    }
}