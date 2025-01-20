package src;

import java.util.Random;

class Treballador extends Thread {
    private float sou_anual_brut;
    private int edat_inici_treball;
    private int edat_fi_treball;
    private int edat_actual;
    private float cobrat;
    private Random rnd;

    public Treballador(String nom, float nou_anual_brut, int edat_inici_treball, int edat_fi_treball) {
        super(nom);
        this.sou_anual_brut = nou_anual_brut;
        this.edat_inici_treball = edat_inici_treball;
        this.edat_fi_treball = edat_fi_treball;
        this.edat_actual = edat_inici_treball;
        this.cobrat = 0.0f;
        this.rnd = new Random();
    }

    private void cobra() {
        cobrat += (sou_anual_brut / 12.0f); 
    }

    private void pagaImpostos() {
        cobrat -= (sou_anual_brut / 12.0f) * 0.24f;
    }

    @Override
    public void run() {
        while (edat_actual < edat_fi_treball) { 
            if (edat_actual >= edat_inici_treball) { 
                for (int i = 0; i < 12; i++) { 
                    cobra();
                    pagaImpostos();
                }
            }
            edat_actual++; 
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                System.err.println(getName() + " interrumput.");
            }
        }
    }

    public float getCobrat() {
        return cobrat;
    }

    public int getEdat() {
        return edat_actual;
    }
}
