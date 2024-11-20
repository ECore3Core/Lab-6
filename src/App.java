import Reflection.Reflection;
import Threads.PBThread;
import Threads.PNThread;
import Threads.PPThread;
import Threads.SynchronizedThreads.SPNThread;
import Threads.SynchronizedThreads.SPPThread;
import Threads.SynchronizedThreads.TransportSynchronizer;
import Threads.LockingThreads.LPNThread;
import Threads.LockingThreads.LPPThread;
import Vehicle.Vehicle;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

import Exceptions.DuplicateModelNameException;
import Exceptions.NoSuchModelNameException;
import MainVehicles.*;

public class App {
    public static void main(String[] args){
        //Задание 1
        System.out.println("-----------------------Задание 1-------------------------");

        Moped moped = new Moped("BMW", 10);
        PPThread thread1 = new PPThread("SPThread", moped);
        PNThread thread2 = new PNThread("SNTread", moped);

        thread1.setPriority(Thread.MIN_PRIORITY);
        thread2.setPriority(Thread.MAX_PRIORITY);
        
        thread1.start();
        thread2.start();

        try{
            thread1.join();
            thread2.join();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Оба потока завершены");


        //Задание 2
        System.out.println("-----------------------Задание 2-------------------------");

        QuadBike quadBike = new QuadBike("Mitsubishi", 15);

        TransportSynchronizer synchronizer = new TransportSynchronizer(quadBike);

        SPPThread sspRunnable = new SPPThread(synchronizer, quadBike);
        SPNThread ssnRunnable = new SPNThread(synchronizer, quadBike);

        Thread sspThread = new Thread(sspRunnable);
        Thread ssnThread = new Thread(ssnRunnable);

        sspThread.start();
        ssnThread.start();

        try{
            sspThread.join();
            ssnThread.join();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }


        //Задание 3
        System.out.println("-----------------------Задание 3-------------------------");

        ReentrantLock lock = new ReentrantLock();
        Thread lpnThread = new Thread(new LPNThread(quadBike, lock));
        Thread lppThread = new Thread(new LPPThread(quadBike, lock));

        lpnThread.start();
        lppThread.start();

        try{
            lppThread.join();
            lpnThread.join();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        //Задание 4
        System.out.println("-----------------------Задание 4-------------------------");


        Car car = new Car("Mercedes", 11);
        Scooter scooter = new Scooter("Yamaha", 1);
        QuadBike quadBike2 = new QuadBike("Ford", 20);
        Motorcycle moto = new Motorcycle("Honda", 2);

        PBThread pbTread1 = new PBThread(car);
        PBThread pbTread2 = new PBThread(scooter);
        PBThread pbTread3 = new PBThread(quadBike2);
        PBThread pbTread4 = new PBThread(moto);

        ExecutorService executors = Executors.newFixedThreadPool(2);
        
        executors.submit(pbTread1);
        executors.submit(pbTread2);
        executors.submit(pbTread3);
        executors.submit(pbTread4);

        executors.shutdown();
    }
}
