import Reflection.Reflection;
import Threads.ShowNamesThread;
import Threads.ShowPricesThread;
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
        Moped moped = new Moped("BMW", 10);
        ShowPricesThread thread1 = new ShowPricesThread("SPThread", moped);
        ShowNamesThread thread2 = new ShowNamesThread("SNTread", moped);

        thread1.setPriority(Thread.MIN_PRIORITY);
        thread2.setPriority(Thread.MAX_PRIORITY);
        
        thread2.start();
        thread1.start();
        

        try{
            thread1.join();
            thread2.join();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Оба потока завершены");

    }
}
