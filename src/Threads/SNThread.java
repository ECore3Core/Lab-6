package Threads;

import Vehicle.Vehicle;

public class SNThread extends Thread{
    private Vehicle vehicle;
    public SNThread(String threadName, Vehicle v){
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
