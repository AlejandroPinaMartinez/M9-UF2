import java.util.LinkedList;
import java.util.Queue;

public class Barberia {
    private final Queue<Client> salaEspera;
    private final int maxCadires;
    private final Object condBarber = new Object();
    private static Barberia instancia;
    
    public Barberia(int maxCadires) {
        this.maxCadires = maxCadires;
        this.salaEspera = new LinkedList<>();
        instancia = this;
    }
    
    public static Barberia getInstance() {
        return instancia;
    }
    
    public Client seguentClient() {
        synchronized (salaEspera) {
            return salaEspera.poll();
        }
    }
    
    public void entrarClient(Client client) {
        synchronized (salaEspera) {
            if (salaEspera.size() < maxCadires) {
                salaEspera.add(client);
                System.out.println("Client " + client.getNom() + " en espera");
                synchronized (condBarber) {
                    condBarber.notify();
                }
            } else {
                System.out.println("No queden cadires, client " + client.getNom() + " se'n va");
            }
        }
    }
    
    public void executar() {
        new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    Client client = new Client(i);
                    entrarClient(client);
                    Thread.sleep(500);
                }
                Thread.sleep(10000);
                for (int i = 11; i <= 20; i++) {
                    Client client = new Client(i);
                    entrarClient(client);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    public Object getCondBarber() {
        return condBarber;
    }
    public static void main(String[] args) {
        Barberia barberia = new Barberia(3);
        Barber barber = new Barber("Alejandro");
        
        Thread barberThread = new Thread(barber);
        barberThread.start();
        
        barberia.executar();
    }


}
