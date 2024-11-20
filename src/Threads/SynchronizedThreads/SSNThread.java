package Threads.SynchronizedThreads;

import Vehicle.Vehicle;

public class SSNThread implements Runnable{
    TransportSynchronizer synchronizer;
    Vehicle v;

    public SSNThread(TransportSynchronizer synchronizer, Vehicle v){
        this.synchronizer = synchronizer;
        this.v = v;
    }

    @Override
    public void run(){
        try{
            for(int i = 0; i < v.getModelsSize(); i++){
                synchronizer.printModel();
            }
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
