import java.util.Random;

public class Filosof extends Thread {
    private final int id;
    private final Forquilla esquerra;
    private final Forquilla dreta;
    private final Random random = new Random();

    public Filosof(int id, Forquilla esquerra, Forquilla dreta) {
        this.id = id;
        this.esquerra = esquerra;
        this.dreta = dreta;
    }

    public long getId() {
        return id;
    }

    public Forquilla getEsquerra() {
        return esquerra;
    }

    public Forquilla getDreta() {
        return dreta;
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filòsof " + id + " pensant");
        Thread.sleep(random.nextInt(1000) + 1000);
    }

    private void menjar() throws InterruptedException {
        int gana = 0;

        while (true) {
            if (esquerra.agafar()) {
                System.out.println("Filòsof " + id + " agafa la forquilla esquerra " + esquerra.getId());

                if (dreta.agafar()) {
                    System.out.println("Filòsof " + id + " agafa la forquilla dreta " + dreta.getId());
                    System.out.println("Filòsof " + id + " menja");
                    
                    Thread.sleep(random.nextInt(1000) + 1000);

                    dreta.deixar();
                    esquerra.deixar();
                    System.out.println("Filòsof " + id + " ha acabat de menjar.");
                    break;
                } else {
                    esquerra.deixar();
                    gana++;
                    System.out.println("Filòsof " + id + " deixa l'esquerra (" + esquerra.getId() + 
                                       ") i espera (dreta ocupada)");
                    System.out.println("Filòsof " + id + " gana=" + gana);
                }
            }
            Thread.sleep(random.nextInt(500) + 500);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                menjar();
                pensar();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}