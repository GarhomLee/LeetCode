https://leetcode.com/problems/print-in-order/

idea: Use semaphore to block/unblock specific threads

class Foo {
    Semaphore sem1, sem2, sem3;
    
    public Foo() {
        sem1 = new Semaphore(1);
        sem2 = new Semaphore(0);
        sem3 = new Semaphore(0);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        sem1.acquire();
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        sem2.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        sem2.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        sem3.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        sem3.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        sem1.release();
    }
}