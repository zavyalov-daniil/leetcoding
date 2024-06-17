package concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class BuildingH2O {
    public static void main(String[] args) throws InterruptedException { //Foo()
        H3O h2o = new H3O();
        Runnable hydrogen = () -> {
            try {
                h2o.hydrogen(() -> System.out.print("H"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Runnable oxygen = () -> {
            try {
                h2o.oxygen(() -> System.out.print("O"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Thread t1 = new Thread(hydrogen);
        Thread t2 = new Thread(hydrogen);
        Thread t3 = new Thread(hydrogen);
        Thread t4 = new Thread(hydrogen);
        Thread t5 = new Thread(oxygen);
        Thread t6 = new Thread(hydrogen);
        Thread t7 = new Thread(hydrogen);
        Thread t8 = new Thread(oxygen);
        Thread t9 = new Thread(oxygen);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();

        Thread.sleep(500);
        System.out.println();
        t1 = new Thread(hydrogen);
        t2 = new Thread(hydrogen);
        t3 = new Thread(hydrogen);
        t4 = new Thread(hydrogen);
        t5 = new Thread(oxygen);
        t6 = new Thread(hydrogen);
        t7 = new Thread(hydrogen);
        t8 = new Thread(oxygen);
        t9 = new Thread(oxygen);
        t6.start();
        t3.start();
        t9.start();
        t8.start();
        t7.start();
        t5.start();
        t2.start();
        t4.start();
        t1.start();
    }

    static class H2O {

        int hCount = 0;
        int oCount = 0;

        public synchronized void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            while (hCount == 2 && oCount != 1) {
                wait();
            }
            if (oCount == 1 && hCount == 2) {
                hCount = 0;
                oCount = 0;
            }
            hCount++;
            releaseHydrogen.run();
            notifyAll();
        }

        public synchronized void oxygen(Runnable releaseOxygen) throws InterruptedException {
            while (hCount != 2 && oCount == 1) {
                wait();
            }
            if (hCount == 2 && oCount == 1) {
                hCount = 0;
                oCount = 0;
            }
            oCount++;
            releaseOxygen.run();
            notifyAll();
        }
    }

    //Может выдавать малекулы в любом порядке (HHO, OHH, HOH). В теории может и за 0ms работать. Но у меня было 20.
    static class H3O {

        private final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        private final Semaphore hSemaphore = new Semaphore(2);
        private final Semaphore oSemaphore = new Semaphore(1);

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            try {
                hSemaphore.acquire();
                cyclicBarrier.await();
                releaseHydrogen.run();
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            hSemaphore.release();
        }

        public synchronized void oxygen(Runnable releaseOxygen) throws InterruptedException {
            try {
                oSemaphore.acquire();
                cyclicBarrier.await();
                releaseOxygen.run();
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            oSemaphore.release();
        }
    }

    //Просто прикольное чужое решение
    static class H4O {

        private final Semaphore hydrogenSemaphore;
        private final Semaphore oxygenSemaphore;

        public H4O() {
            hydrogenSemaphore = new Semaphore(2);
            oxygenSemaphore = new Semaphore(0);
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            hydrogenSemaphore.acquire();

            releaseHydrogen.run();

            oxygenSemaphore.release();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            oxygenSemaphore.acquire(2);

            releaseOxygen.run();

            hydrogenSemaphore.release(2);
        }
    }
}