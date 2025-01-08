package src;
import java.util.Random;

public class DormAleatori extends Thread {

    //Creació variables
    private long creacioTemps;  
    private String name;        
    private Random random;      
    
    // Constructor específic
    public DormAleatori(String name) {
        this.name = name;
        this.creacioTemps = System.currentTimeMillis();  
        random = new Random();
    }
    
    // Métode run
    @Override
    public void run() {

        // iteracions d'1 a10
        for (int i = 0; i < 10; i++) {

            // interval aleatori de 1000 millisegons
            int intervalAleatori = random.nextInt(1000);

            // calcul del temps total des de la creació.
            long tempsTotal = System.currentTimeMillis() - creacioTemps;
            
            // sortida
            System.out.printf("%s(%d) a dormir %dms total %d%n", name, i, intervalAleatori, tempsTotal);
            
            // dormo els fils durant l'interval aleatori
            try {

                Thread.sleep(intervalAleatori);

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

    // Métode main
    public static void main(String[] args) {

        // creo les instàncies per Joan i Pep
        DormAleatori joan = new DormAleatori("Joan");
        DormAleatori pep = new DormAleatori("Pep");
        
        //inicio els fils
        joan.start();
        pep.start();
        
        // mostro que el mètode principal ha acabat.
        System.out.println("-- Fi de main -----------");
    }
}
