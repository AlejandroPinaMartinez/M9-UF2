public class Barber implements Runnable {
    private final String nom;
    
    public Barber(String nom) {
        this.nom = nom;
    }
    
    @Override
    public void run() {
        while (true) {
            Client client;
            synchronized (Barberia.getInstance().getCondBarber()) {
                while ((client = Barberia.getInstance().seguentClient()) == null) {
                    try {
                        System.out.println("Ningú en espera");
                        System.out.println("Barber " + nom + " dormint");
                        Barberia.getInstance().getCondBarber().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Li toca al client " + client.getNom());
            client.tallarseElCabell();
        }
    }
}