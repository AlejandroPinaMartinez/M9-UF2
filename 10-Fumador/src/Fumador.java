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
                if (!compraTabac()) continue;
                if (!compraPaper()) continue;
                if (!compraLlumi()) continue;
                
                System.out.println("Fumador " + id + " fumant");
                Thread.sleep(500 + new Random().nextInt(500));
                fumades++;
                System.out.println("Fumador " + id + " ha fumat " + fumades + " vegades");
                
                if (fumades == 3) {
                    estanc.tancarEstanc();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean compraTabac() throws InterruptedException {
        synchronized (estanc) {
            while (estanc.estaObert() && !estanc.hiHaTabac()) {
                estanc.wait();
            }
            if (!estanc.hiHaTabac()) return false;
            System.out.println("Fumador " + id + " comprant Tabac");
            t = estanc.venTabac();
            return true;
        }
    }

    private boolean compraPaper() throws InterruptedException {
        synchronized (estanc) {
            while (estanc.estaObert() && !estanc.hiHaPaper()) {
                estanc.wait();
            }
            if (!estanc.hiHaPaper()) return false;
            System.out.println("Fumador " + id + " comprant Paper");
            p = estanc.venPaper();
            return true;
        }
    }

    private boolean compraLlumi() throws InterruptedException {
        synchronized (estanc) {
            while (estanc.estaObert() && !estanc.hiHaLlumi()) {
                estanc.wait();
            }
            if (!estanc.hiHaLlumi()) return false;
            System.out.println("Fumador " + id + " comprant Llumí");
            l = estanc.venLlumi();
            return true;
        }
    }
}
