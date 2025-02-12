class Taula {
    private final Filòsof[] filòsofs;
    private final Forquilla[] forquilles;

    public Taula(int numFilosofs) {
        filòsofs = new Filòsof[numFilosofs];
        forquilles = new Forquilla[numFilosofs];

        for (int i = 0; i < numFilosofs; i++) {
            forquilles[i] = new Forquilla(i);
        }

        for (int i = 0; i < numFilosofs; i++) {
            filòsofs[i] = new Filòsof(i, forquilles[i], forquilles[(i + 1) % numFilosofs]);
        }
    }

    public void showTaula() {
        for (Filòsof filòsof : filòsofs) {
            System.out.println("Filòsof: " + filòsof.getName());
        }
    }

    public void cridarATaula() {
        for (Filòsof filòsof : filòsofs) {
            filòsof.start();
        }
    }

    public static void main(String[] args) {
        int numFilosofs = 5;
        Taula taula = new Taula(numFilosofs);
        taula.showTaula();
        taula.cridarATaula();
    }
}