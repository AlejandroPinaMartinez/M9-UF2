package src;
class Taula {
    private final Filosof[] filosofs;
    private final Forquilla[] forquilles;

    public Taula(int numFilosofs) {
        filosofs = new Filosof[numFilosofs];
        forquilles = new Forquilla[numFilosofs];
        for (int i = 0; i < numFilosofs; i++) {
            forquilles[i] = new Forquilla(i);
        }
        for (int i = 0; i < numFilosofs; i++) {
            filosofs[i] = new Filosof(i, forquilles[i], forquilles[(i + 1) % numFilosofs]);
        }
    }

    public void showTaula() {
        for (int i = 0; i < filosofs.length; i++) {
            System.out.println("Filòsof " + filosofs[i].getName() + " - Forquilla esquerra: " + forquilles[i].getId() + " | Forquilla dreta: " + forquilles[(i + 1) % forquilles.length].getId());
        }
    }

    public void cridarATaula() {
        for (Filosof f : filosofs) {
            f.start();
        }
    }

    public static void main(String[] args) {
        int numFilosofs = 4; 
        Taula taula = new Taula(numFilosofs);
        taula.showTaula(); 
        taula.cridarATaula(); 
    }
}