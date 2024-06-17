package concurrency;

public class PrintInOrder {
    public static void main(String[] args) throws InterruptedException { //Foo()
        Foo foo = new Foo();
        Thread thread1 = new Thread(() -> {
            try {
                foo.first(() -> System.out.print("first"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                foo.second(() -> System.out.print("second"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread3 = new Thread(() -> {
            try {
                foo.third(() -> System.out.print("third"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread3.start();
        thread1.start();
        thread2.start();

        Thread.sleep(500);
        System.out.println();
        Foo2 foo2 = new Foo2();
        Thread thread21 = new Thread(() -> {
            try {
                foo2.first(() -> System.out.print("first"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread22 = new Thread(() -> {
            try {
                foo2.second(() -> System.out.print("second"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread23 = new Thread(() -> {
            try {
                foo2.third(() -> System.out.print("third"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread21.start();
        thread23.start();
        thread22.start();

        Thread.sleep(500);
        System.out.println();
        Foo3 foo3 = new Foo3();
        Thread thread31 = new Thread(() -> {
            try {
                foo3.first(() -> System.out.print("first"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread32 = new Thread(() -> {
            try {
                foo3.second(() -> System.out.print("second"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread33 = new Thread(() -> {
            try {
                foo3.third(() -> System.out.print("third"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread33.start();
        thread32.start();
        thread31.start();
    }
}