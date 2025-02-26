import java.util.Random;

public class Filosof extends Thread {
    private final int id;
    private final Forquilla esquerra;
    private final Forquilla dreta;
    private long iniciGana;
    private long fiGana;
    private long gana;
    private final Random random = new Random();

    public Filosof(int id, Forquilla esquerra, Forquilla dreta) {
        this.id = id;
        this.esquerra = esquerra;
        this.dreta = dreta;
    }

    private void pensar() throws InterruptedException {
        iniciGana = System.currentTimeMillis();
        System.out.println("Fil" + id + " pensant");
        Thread.sleep(random.nextInt(1000) + 1000); 
    }

    private void menjar() throws InterruptedException {
        while (true) {
            agafarForquilles();
            fiGana = System.currentTimeMillis();
            gana = (fiGana - iniciGana) / 1000;
            System.out.println("Fil" + id + " menja amb gana " + gana);
            Thread.sleep(random.nextInt(1000) + 1000); 
            resetGana();
            deixarForquilles();
            break;
        }
    }

    private void agafarForquilles() {
        if (esquerra.getNum() < dreta.getNum()) {
            agafarForquillaEsquerra();
            agafarForquillaDreta();
        } else {
            agafarForquillaDreta();
            agafarForquillaEsquerra();
        }
        System.out.println("Fil" + id + " té forquilles esq(" + esquerra.getNum() + ") dreta(" + dreta.getNum() + ")");
    }

    private void agafarForquillaEsquerra() {
        esquerra.agafar();
    }

    private void agafarForquillaDreta() {
        dreta.agafar();
    }

    private void deixarForquilles() {
        dreta.deixar();
        esquerra.deixar();
        System.out.println("Fil" + id + " ha acabat de menjar");
        System.out.println("Fil" + id + " deixa les forquilles");
    }

    private void resetGana() {
        iniciGana = System.currentTimeMillis();
        gana = 0;
    }

    @Override
    public void run() {
        try {
            iniciGana = System.currentTimeMillis();
            while (true) {
                menjar();
                pensar();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
