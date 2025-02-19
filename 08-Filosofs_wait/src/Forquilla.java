package src;
class Forquilla {
    private final int id;
    private int propietari; 

    public static final int LLIURE = -1;

    public Forquilla(int id) {
        this.id = id;
        this.propietari = LLIURE;
    }

    public synchronized int getPropietari() {
        return propietari;
    }

    public synchronized void agafar(int id) throws InterruptedException {
        while (propietari != LLIURE) {
            wait();
        }
        propietari = id; 
    }

    public synchronized void deixar() {
        propietari = LLIURE; 
        notifyAll(); 
    }

    public int getId() {
        return id;
    }
}
