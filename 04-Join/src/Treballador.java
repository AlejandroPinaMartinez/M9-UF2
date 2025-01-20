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

    public void cobra() {
        cobrat += sou_anual_brut / 12;
    }

    public void pagaImpostos() {
        cobrat -= cobrat * 0.24;
    }

    @Override
    public void run() {
        while (edat_actual < edat_fi_treball) {
            cobra();          // incrementa diners cobrats
            pagaImpostos();   // redueix els impostos
            edat_actual++;    //incrementa la edat
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
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
