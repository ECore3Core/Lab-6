package Threads.SynchronizedThreads;

import Vehicle.Vehicle;

public class SPNThread implements Runnable{
    private TransportSynchronizer synchronizer;

    public SPNThread(TransportSynchronizer synchronizer){
        this.synchronizer = synchronizer;
    }

    @Override
    public void run(){
        try{
            while(synchronizer.canPrintModel()){
                synchronizer.printModel();
            }
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
