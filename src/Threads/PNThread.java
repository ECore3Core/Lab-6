package Threads;

import Vehicle.Vehicle;

public class PNThread extends Thread{
    private Vehicle vehicle;
    public PNThread(String threadName, Vehicle v){
        super(threadName);
        vehicle = v;
    }
    @Override
    public void run(){
        for(String name : vehicle.getModelsNames()){
            System.out.println(name);
        }
    }
}
