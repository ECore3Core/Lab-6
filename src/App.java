import Reflection.Reflection;
import Threads.SNThread;
import Threads.SPThread;
import Threads.SynchronizedThreads.SSNThread;
import Threads.SynchronizedThreads.SSPThread;
import Threads.SynchronizedThreads.TransportSynchronizer;
import Vehicle.Vehicle;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Exceptions.DuplicateModelNameException;
import Exceptions.NoSuchModelNameException;
import MainVehicles.*;

public class App {
    public static void main(String[] args){
        //Задание 1
        System.out.println("-----------------------Задание 1-------------------------");

        Moped moped = new Moped("BMW", 10);
        SPThread thread1 = new SPThread("SPThread", moped);
        SNThread thread2 = new SNThread("SNTread", moped);

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

        SSPThread sspRunnable = new SSPThread(synchronizer, quadBike);
        SSNThread ssnRunnable = new SSNThread(synchronizer, quadBike);

        Thread sspThread = new Thread(sspRunnable);
        Thread ssnThread = new Thread(ssnRunnable);

        sspThread.start();
        ssnThread.start();
    }
}
