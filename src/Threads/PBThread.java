package Threads;

import Vehicle.Vehicle;

public class PBThread implements Runnable{
    private Vehicle v;

    public PBThread(Vehicle v){
        this.v = v;
    }
    
    @Override
    public void run(){
        System.out.println(v.getBrand());
    }
}
