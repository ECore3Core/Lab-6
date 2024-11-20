package Threads.LockingThreads;

import java.util.concurrent.locks.ReentrantLock;

import Vehicle.Vehicle;

public class LPPThread implements Runnable{
    Vehicle v;
    ReentrantLock lock = new ReentrantLock();

    public LPPThread(Vehicle v){
        this.v = v;
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
