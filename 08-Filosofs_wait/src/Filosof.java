package src;

import java.util.Random;

class Filosof extends Thread {
    private final int id;
    private final Forquilla esquerra;
    private final Forquilla dreta;
    private int gana = 0;
    private final Random random = new Random();

    public Filosof(int id, Forquilla esquerra, Forquilla dreta) {
        this.id = id;
        this.esquerra = esquerra;
        this.dreta = dreta;
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filòsof: fil" + id + " pensant");
        Thread.sleep(random.nextInt(1000) + 1000);
    }

    private void menjar() throws InterruptedException {
        while (true) {
            agafarForquilles();

            if (esquerra.getPropietari() == id && dreta.getPropietari() == id) {
                System.out.println("Filòsof: fil" + id + " menja");
                Thread.sleep(random.nextInt(1000) + 1000); 
                deixarForquilles();
                break;
            } else {
                System.out.println("Filòsof: fil" + id + " no pot menjar, esperant...");
                gana++;
                deixarForquilles();
                Thread.sleep(random.nextInt(500) + 500); 
            }
        }
    }

    private boolean agafarForquilles() throws InterruptedException {
        agafarForquillaEsquerra();
        
        if (dreta.getPropietari() != -1) {  
            System.out.println("Filòsof: fil" + id + " deixa l'esquerra (" + esquerra.getId() + ") i espera (dreta ocupada)");
            esquerra.deixar();
            gana++;
            System.out.println("Filòsof: fil" + id + " gana=" + gana);
            Thread.sleep(random.nextInt(500) + 500);
            return false;
        }

        agafarForquillaDreta();
        return true;
    }

    private void agafarForquillaEsquerra() throws InterruptedException {
        esquerra.agafar(id);
        System.out.println("Filòsof: fil" + id + " agafa la forquilla esquerra " + esquerra.getId());
    }

    private void agafarForquillaDreta() throws InterruptedException {
        dreta.agafar(id); 
        System.out.println("Filòsof: fil" + id + " agafa la forquilla dreta " + dreta.getId());
    }

    private void deixarForquilles() {
        esquerra.deixar();
        dreta.deixar();
        System.out.println("Filòsof: fil" + id + " ha acabat de menjar.");
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
