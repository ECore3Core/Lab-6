package Threads;

import Vehicle.Vehicle;

public class ShowNamesThread extends Thread{
    private Vehicle vehicle;
    public ShowNamesThread(String threadName, Vehicle v){
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
