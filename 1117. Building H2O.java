https://leetcode.com/problems/building-h2o/

idea: Use semaphore with required number of "the other" atoms (2 for O, 1 for H) in order to
    form the desired triplets.

class H2O {
    Semaphore semH, semO;
    
    public H2O() {
        semH = new Semaphore(0);
        semO = new Semaphore(2);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
		semH.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        semO.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {        
        semO.acquire(2);
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        
        semH.release(2);
    }
}