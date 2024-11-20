import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.reflect.*;

import Exceptions.DuplicateModelNameException;
import Exceptions.NoSuchModelNameException;
import MainVehicles.*;
import Vehicle.Vehicle;

public class Vehicles {
    public static double getArithmeticalMeanOfModelPrices(Vehicle vehicle){
        double sum = 0;
        for(double i : vehicle.getModelsPrices()){
            sum += i;
        }
        return sum/vehicle.getModelsSize();
    }
    public static void printAllModels(Vehicle vehicle){
        for(String s : vehicle.getModelsNames()){
            System.out.println(s);
        }
    }
    public static void printAllPrices(Vehicle vehicle){
        for(double i : vehicle.getModelsPrices()){
            System.out.print(i + " ");
        }
    }
    public static void outputVehicle(Vehicle v, OutputStream out) throws IOException{
        DataOutputStream outputStream = new DataOutputStream(out);

        byte[] bytes = v.getClass().toString().getBytes();
        outputStream.writeInt(bytes.length);
        for(byte b : bytes){
            outputStream.writeByte(b);
        }

        bytes = v.getBrand().getBytes();
        int n = bytes.length;
        outputStream.writeInt(n);
        for(int i = 0; i < n; i++){
            outputStream.writeByte(bytes[i]);
        }

        n = v.getModelsSize();
        outputStream.writeInt(n);
        String[] allModelsNames = v.getModelsNames();
        double[] allPrices = v.getModelsPrices();

        for(int i = 0; i < n; i++){
            int nameLength = allModelsNames[i].length();
            bytes = allModelsNames[i].getBytes();
            outputStream.writeInt(nameLength);
            for(int j = 0; j < nameLength; j++){
                outputStream.writeByte(bytes[j]);
            }
            outputStream.writeDouble(allPrices[i]);
        }
    }
    public static Vehicle inputVehicle(InputStream in) throws IOException, NoSuchModelNameException, DuplicateModelNameException{
        DataInputStream inputStream = new DataInputStream(in);

        int size = inputStream.readInt();
        byte[] bytes = new byte[size];
        for(int i = 0; i < size; i++){
            bytes[i] = inputStream.readByte();
        }
        String className = new String(bytes);

        size = inputStream.readInt();
        
        bytes = new byte[size];
        for(int i = 0; i < size; i++){
            bytes[i] = inputStream.readByte();
        }
        String brandName = new String(bytes);

        Vehicle vehicle;
        switch(className){
            case "class Car":
                vehicle = new Car(brandName, 0);
                break;
            case "class Motorcycle":
                vehicle = new Motorcycle(brandName, 0);
                break;
            case "class QuadBike":
                vehicle = new QuadBike(brandName, 0);
                break;
            case "class Moped":
                vehicle = new Moped(brandName, 0);
                break;
            case "class Scooter":
                vehicle = new Scooter(brandName, 0);
                break;
            default:
                vehicle = new Car(brandName, 0);
                break;
        }

        size = inputStream.readInt();
        for(int i = 0; i < size; i++){
            int nameLength = inputStream.readInt();
            bytes = new byte[nameLength];
            for(int j = 0; j < nameLength; j++){
                bytes[j] = inputStream.readByte();
            }
            String name = new String(bytes);

            double price = inputStream.readDouble();

            vehicle.addModel(name, price);
        }
        return vehicle;
    }
    public static void writeVehicle(Vehicle vehicle, Writer out){
        PrintWriter printWriter = new PrintWriter(out);

        printWriter.printf("Класс: %s%n", vehicle.getClass());

        printWriter.printf("Марка автомобиля: %s%n", vehicle.getBrand());
        for(int i = 0; i < vehicle.getModelsSize(); i++){
            String modelName = vehicle.getModelsNames()[i];
            double modelPrice = vehicle.getModelsPrices()[i];
            printWriter.printf("Название модели: %s, цена: %.2f%n", modelName, modelPrice);
        }
        printWriter.flush();
    }
    public static Vehicle readVehicle(Reader in) throws IOException, DuplicateModelNameException{
        Scanner scanner = new Scanner(in);

        String className = scanner.nextLine();

        String brandName = scanner.nextLine().split(": ")[1];

        Vehicle vehicle;
        switch (className) {
            case "Класс: class MainVehicles.Car":
                vehicle = new Car(brandName, 0);
                break;
            case "Класс: class MainVehicles.Motorcycle":
                vehicle = new Motorcycle(brandName, 0);
                break;
            case "Класс: class MainVehicles.QuadBike":
                vehicle = new QuadBike(brandName, 0);
                break;
            case "Класс: class MainVehicles.Moped":
                vehicle = new Moped(brandName, 0);
                break;
            case "Класс: class MainVehicles.Scooter":
                vehicle = new Scooter(brandName, 0);
                break;
            default:
                vehicle = new Car(brandName, 0);
                break;
        }
        

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();

            String[] info = line.split(": ");
            String modelName = info[1].split(",")[0];
            double modelPrice = Double.parseDouble(info[2].split(",")[0]);
            vehicle.addModel(modelName, modelPrice);
        }
        scanner.close();
        return vehicle;
    }
    public static Vehicle createVehicle(String markName, int modelsLength, Vehicle v){
        try{
            Class clazz = v.getClass();
            Constructor constructor = clazz.getConstructor(String.class, int.class);
            Vehicle vehicle = (Vehicle) constructor.newInstance(markName, modelsLength);
            return vehicle;
        }
        catch(InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        }
        return null;
    }
    public static double getVehiclesArithmeticalMean(Vehicle... vehicles){
        ArrayList<Double> prices = new ArrayList<>();
        double result = 0;
        int count = 0;
        for(Vehicle vehicle : vehicles){
            double[] localPrices = vehicle.getModelsPrices();
            Double[] localDoublePrices = new Double[vehicle.getModelsSize()];
            for(int i = 0; i < vehicle.getModelsSize(); i++){
                localDoublePrices[i] = localPrices[i];
            }
            prices.addAll(Arrays.asList(localDoublePrices));
        }
        for(Double price : prices){
            result += price;
            count++;
        }
        return count == 0 ? 0 : result / count;
    }
}   
