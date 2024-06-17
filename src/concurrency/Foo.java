package concurrency;

class Foo {
    int order = 0;

    public Foo() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (this) {
            printFirst.run();
            ++order;
            notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (this) {
            while (order != 1) {
                wait();
            }
            printSecond.run();
            ++order;
            notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (this) {
            while (order != 2) {
                wait();
            }
            printThird.run();
            notifyAll();
        }
    }
}
