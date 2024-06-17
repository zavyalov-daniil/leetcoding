package concurrency;

class Foo3 {
    boolean firstDone;
    boolean secondDone;
    Object lock;
    public Foo3() {
        this.firstDone = false;
        this.secondDone = false;
        this.lock = new Object();
    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (lock) {
            printFirst.run();
            firstDone = true;
            lock.notifyAll();
        }

    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock) {
            while (!firstDone) {
                lock.wait();
            }
            printSecond.run();
            secondDone = true;
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock) {
            while (!secondDone) { //при if может зациклиться
                lock.wait();
            }
            printThird.run();
        }
    }
}
