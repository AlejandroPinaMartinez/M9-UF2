class Forquilla {
    private final int id;
    private boolean enUs = false; 

    public Forquilla(int id) {
        this.id = id ; 
    }

    public synchronized boolean agafar() {
        if (!enUs) {
            enUs = true; 
            return true;
        }
        return false; 
    }

    public synchronized void deixar() {
        enUs = false; 
    }

    public int getId() {
        return id;
    }
}
