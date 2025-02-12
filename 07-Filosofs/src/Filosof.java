
import java.util.Random;

class Filòsof extends Thread {
    private final int id;
    private final Forquilla esquerra;
    private final Forquilla dreta;
    private final Random random = new Random();

    public Filòsof(int id, Forquilla esquerra, Forquilla dreta) {
        this.id = id;
        this.esquerra = esquerra;
        this.dreta = dreta;
    }

}