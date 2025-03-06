public class Barri {
    public static void main(String[] args) {
        Estanc estanc = new Estanc();
        Fumador[] fumadors = new Fumador[3];

        for (int i = 0; i < 3; i++) {
            fumadors[i] = new Fumador(estanc, i);
        }

        Thread estancThread = new Thread(estanc::nouSubministrament);
        estancThread.start();

        for (Fumador f : fumadors) {
            f.start();
        }

        for (Fumador f : fumadors) {
            try {
                f.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        estanc.tancarEstanc();
        System.out.println("Estanc tancat");
    }
}
