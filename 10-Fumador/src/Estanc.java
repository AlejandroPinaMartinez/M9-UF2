
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Estanc {
    private List<Tabac> tabac = new ArrayList<>();
    private List<Paper> paper = new ArrayList<>();
    private List<Llumi> llumins = new ArrayList<>();
    private boolean estancObert = true;
    private final Random random = new Random();

    public Estanc() {
        this.tabac = new ArrayList<>();
        this.paper = new ArrayList<>();
        this.llumins = new ArrayList<>();
    }

    public synchronized void nouSubministrament() {
        if (!estancObert) return;

        int producte = random.nextInt(3);
        switch (producte) {
            case 0:
                addTabac();
                break;
            case 1:
                addPaper();
                break;
            case 2:
                addLlumi();
                break;
        }
        notifyAll();
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
