package concurrency;

import java.util.concurrent.CountDownLatch;

class Foo2 {
    CountDownLatch first;
    CountDownLatch second;

    public Foo2() {
        first = new CountDownLatch(1);
        second = new CountDownLatch(1);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        first.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        first.await();
        printSecond.run();
        second.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        second.await();
        printThird.run();
    }
}
