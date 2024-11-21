package Threads.SynchronizedThreads;

import Vehicle.Vehicle;

public class SPPThread implements Runnable{
    private TransportSynchronizer synchronizer;
    
    public SPPThread(TransportSynchronizer synchronizer){
        this.synchronizer = synchronizer;
    }

    @Override
    public void run(){
        try{
            while(synchronizer.canPrintPrice()){
                synchronizer.printPrice();
            }
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
