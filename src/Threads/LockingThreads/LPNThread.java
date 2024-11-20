package Threads.LockingThreads;

import java.util.concurrent.locks.ReentrantLock;

import Vehicle.Vehicle;

public class LPNThread implements Runnable{
    Vehicle v;
    private ReentrantLock lock;

    public LPNThread(Vehicle v, ReentrantLock lock){
        this.v = v;
        this.lock = lock;
    }

    @Override
    public void run(){
        lock.lock();
        try{
            for(String name : v.getModelsNames()){
                System.out.println(name);
            }
        }
        finally{
            lock.unlock();
        }
    }
}
