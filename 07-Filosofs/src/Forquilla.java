import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Forquilla {
    private final int id;
    private final Lock lock = new ReentrantLock();

    public Forquilla(int id) {
        this.id = id;
    }

    public boolean agafar() {
        return lock.tryLock();
    }

    public void deixar() {
        lock.unlock();
    }

    public int getId() {
        return id;
    }
}