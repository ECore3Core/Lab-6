package Threads.SynchronizedThreads;

import Vehicle.Vehicle;

public class SPPThread implements Runnable{
    TransportSynchronizer synchronizer;
    Vehicle v;
    
    public SPPThread(TransportSynchronizer synchronizer, Vehicle v){
        this.synchronizer = synchronizer;
        this.v = v;
    }

    @Override
    public void run(){
        try{
            for(int i = 0; i < v.getModelsSize(); i++){
                synchronizer.printPrice();
            }
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
