import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Estanc {
    private final List<Tabac> tabac = new ArrayList<>();
    private final List<Paper> paper = new ArrayList<>();
    private final List<Llumi> llumins = new ArrayList<>();
    private boolean estancObert = true;
    private final Random random = new Random();

    public synchronized boolean hiHaTabac() {
        return !tabac.isEmpty();
    }

    public synchronized boolean hiHaPaper() {
        return !paper.isEmpty();
    }

    public synchronized boolean hiHaLlumi() {
        return !llumins.isEmpty();
    }

    public synchronized boolean estaObert() {
        return estancObert;
    }

    public synchronized Tabac venTabac() {
        return tabac.isEmpty() ? null : tabac.remove(0);
    }

    public synchronized Paper venPaper() {
        return paper.isEmpty() ? null : paper.remove(0);
    }

    public synchronized Llumi venLlumi() {
        return llumins.isEmpty() ? null : llumins.remove(0);
    }

    public synchronized void nouSubministrament() {
        if (!estancObert) return;
        int producte = random.nextInt(3);
        switch (producte) {
            case 0: tabac.add(new Tabac()); System.out.println("Afegint Tabac"); break;
            case 1: paper.add(new Paper()); System.out.println("Afegint Paper"); break;
            case 2: llumins.add(new Llumi()); System.out.println("Afegint Llumí"); break;
        }
        notifyAll();
    }

    public synchronized void tancarEstanc() {
        estancObert = false;
        notifyAll();
        System.out.println("Estanc tancat");
    }

    public void executar() {
        System.out.println("Estanc obert");
        while (estancObert) {
            try {
                Thread.sleep(500 + random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            nouSubministrament();
        }
    }
}