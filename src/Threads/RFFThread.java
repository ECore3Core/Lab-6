package Threads;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

import MainVehicles.Car;
import Vehicle.Vehicle;

public class RFFThread implements Runnable{
    private ArrayBlockingQueue<Vehicle> arrayBlockingQueue;
    private FileReader fileReader;

    public RFFThread(String fileName, ArrayBlockingQueue<Vehicle> arrayBlockingQueue){
        this.arrayBlockingQueue = arrayBlockingQueue;
        try {
            fileReader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        try {
            BufferedReader bf = new BufferedReader(fileReader);
            String brandName = bf.readLine();
            Car car = new Car(brandName, 0);
            arrayBlockingQueue.put(car);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}