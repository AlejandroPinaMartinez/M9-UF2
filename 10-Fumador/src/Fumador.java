
import java.util.Random;

class Fumador extends Thread {
    private final Estanc estanc;
    private final int id;
    private int fumades = 0;
    private Tabac t;
    private Paper p;
    private Llumi l;

    public Fumador(Estanc estanc, int id) {
        this.estanc = estanc;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (fumades < 3) {
                compraTabac();
                compraPaper();
                compraLlumi();
                
                System.out.println("Fumador " + id + " fumant");
                Thread.sleep(500 + new Random().nextInt(500));
                fumades++;
                System.out.println("Fumador " + id + " ha fumat " + fumades + " vegades");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void compraTabac() throws InterruptedException {
        System.out.println("Fumador " + id + " comprant Tabac");
        t = estanc.venTabac();
    }

    private void compraPaper() throws InterruptedException {
        System.out.println("Fumador " + id + " comprant Paper");
        p = estanc.venPaper();
    }

    private void compraLlumi() throws InterruptedException {
        System.out.println("Fumador " + id + " comprant Llumí");
        l = estanc.venLlumi();
    }
}