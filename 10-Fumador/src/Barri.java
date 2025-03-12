public class Barri {
    public static void main(String[] args) {
        Estanc estanc = new Estanc();
        Fumador[] fumadors = new Fumador[3];

        for (int i = 0; i < 3; i++) {
            fumadors[i] = new Fumador(estanc, i);
        }

        Thread estancThread = new Thread(() -> estanc.executar());
        estancThread.start();

        Thread[] fumadorThreads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            fumadorThreads[i] = new Thread(fumadors[i]);
            fumadorThreads[i].start();
        }

        for (Thread fumadorThread : fumadorThreads) {
            try {
                fumadorThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        estanc.tancarEstanc();
        System.out.println("Estanc tencat");
    }
}