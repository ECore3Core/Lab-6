package Threads.SynchronizedThreads;

import Vehicle.Vehicle;

public class TransportSynchronizer {
    private Vehicle v;
    private volatile int current = 0;
    private Object lock = new Object();
    private boolean set = false;
   
    public TransportSynchronizer(Vehicle v) {
        this.v = v;
    }
   
    public void printPrice() throws InterruptedException {
        double val;
        synchronized(lock) {
            double [] p = v.getModelsPrices();
            if (!canPrintPrice()) throw new InterruptedException();
            while (set)
                lock.wait();
            val = p[current];
            System.out.println("Print price: " + val);
            set = true;
            lock.notifyAll();
        }
    }  
   
    public void printModel() throws InterruptedException {
        synchronized(lock) {
            String [] s = v.getModelsNames();
            if (!canPrintModel()) throw new InterruptedException();
            while (!set)
                lock.wait();
            System.out.println("Print model: " + s[current++]);
            set = false;
            lock.notifyAll();
        }
    }
    
    public boolean canPrintPrice() {
        return (!set && current < v.getModelsSize()) || (set && current < v.getModelsSize() - 1);
    }
    
    public boolean canPrintModel() {
        return (current < v.getModelsSize());
    }
}
