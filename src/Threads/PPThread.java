package Threads;

import Vehicle.Vehicle;

public class PPThread extends Thread{
    private Vehicle vehicle;
    public PPThread(String threadName, Vehicle v){
        super(threadName);
        vehicle = v;
    }
    @Override
    public void run(){
        for(double price : vehicle.getModelsPrices()){
            System.out.println(price);
        }
    }
}
