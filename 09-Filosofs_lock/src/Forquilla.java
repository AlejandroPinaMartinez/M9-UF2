import java.util.concurrent.locks.ReentrantLock;

class Forquilla {
    private final int num;
    final ReentrantLock bloqueig = new ReentrantLock();

    public Forquilla(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void agafar() {
        bloqueig.lock();
    }

    public void deixar() {
        bloqueig.unlock();
    }
}
