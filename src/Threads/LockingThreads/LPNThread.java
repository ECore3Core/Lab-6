package Threads.LockingThreads;

import java.util.concurrent.locks.ReentrantLock;

import Vehicle.Vehicle;

public class LPNThread implements Runnable{
    Vehicle v;
    private ReentrantLock lock = new ReentrantLock();

    public LPNThread(Vehicle v){
        this.v = v;
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
