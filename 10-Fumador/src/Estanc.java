
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Estanc {
    private List<Tabac> tabac = new ArrayList<>();
    private List<Paper> paper = new ArrayList<>();
    private List<Llumi> llumins = new ArrayList<>();
    private boolean estancObert = true;

    public synchronized void nouSubministrament() {
        Random rand = new Random();
        System.out.println("Estanc obert");
        while (estancObert) {
            int producte = rand.nextInt(3);
            switch (producte) {
                case 0 -> addTabac();
                case 1 -> addPaper();
                case 2 -> addLlumi();
            }
            notifyAll();
            try {
                Thread.sleep(500 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void addTabac() {
        tabac.add(new Tabac());
        System.out.println("Afegint Tabac");
    }

    public synchronized void addPaper() {
        paper.add(new Paper());
        System.out.println("Afegint Paper");
    }

    public synchronized void addLlumi() {
        llumins.add(new Llumi());
        System.out.println("Afegint Llumí");
    }

    public synchronized Tabac venTabac() throws InterruptedException {
        while (tabac.isEmpty() && estancObert) wait();
        if (!tabac.isEmpty()) return tabac.remove(0);
        return null;
    }

    public synchronized Paper venPaper() throws InterruptedException {
        while (paper.isEmpty() && estancObert) wait();
        if (!paper.isEmpty()) return paper.remove(0);
        return null;
    }

    public synchronized Llumi venLlumi() throws InterruptedException {
        while (llumins.isEmpty() && estancObert) wait();
        if (!llumins.isEmpty()) return llumins.remove(0);
        return null;
    }

    public synchronized void tancarEstanc() {
        estancObert = false;
        notifyAll();
    }
}