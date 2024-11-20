package Threads.LockingThreads;

import java.util.concurrent.locks.ReentrantLock;

import Vehicle.Vehicle;

public class LPPThread implements Runnable{
    private Vehicle v;
    private ReentrantLock lock;

    public LPPThread(Vehicle v, ReentrantLock lock){
        this.v = v;
        this.lock = lock;
    }

    @Override
    public void run(){
        lock.lock();
        try{
            for(double price : v.getModelsPrices()){
                System.out.println(price);
            }
        }
        finally{
            lock.unlock();
        }
    }
}
