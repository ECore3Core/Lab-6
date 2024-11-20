package Threads;

import Vehicle.Vehicle;

public class ShowPricesThread extends Thread{
    private Vehicle vehicle;
    public ShowPricesThread(String threadName, Vehicle v){
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
