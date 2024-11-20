package MainVehicles;

import java.util.Arrays;
import java.util.HashMap;

import Exceptions.*;
import Vehicle.Vehicle;

public class Scooter implements Vehicle{
    private String mark;
    private HashMap<String, Double> models;

    public Scooter(String markName, int modelsSize){
        mark = markName;
        models = new HashMap<>();
        for(int i = 0; i < modelsSize; i++){
            models.put("Scooter" + i, 100.0 + i);
        }
    }
    public String getBrand(){
        return mark;
    }
    public void setBrand(String newMark){
        mark = newMark;
    }
    public void setModelName(String originalName, String newName) throws DuplicateModelNameException, NoSuchModelNameException{
        if(models.containsKey(newName)){
            throw new DuplicateModelNameException("Такая модель уже существует.");
        }
        if(!models.containsKey(originalName)){
            throw new NoSuchModelNameException("Модель с таким названием отсутствует");
        }
        double originalPrice = models.get(originalName);
        models.remove(originalName);
        models.put(newName, originalPrice);
    }
    public String[] getModelsNames(){
        return models.keySet().toArray(new String[models.size()]);
    }
    public double getPriceByName(String modelName)throws NoSuchModelNameException{
        if(!models.containsKey(modelName)){
            throw new NoSuchModelNameException("Модели с таким названием нет.");
        }
        return models.get(modelName);
    }
    public void setPrice(String modelName, double newPrice)throws NoSuchModelNameException{
        if(newPrice <= 0){
            throw new ModelPriceOutOfBoundsException("Цена модели не может ыть меньше либо равна 0.");
        }
        if(!models.containsKey(modelName)){
            throw new NoSuchModelNameException("Модели с таким названием не существует.");
        }
        models.put(modelName, newPrice);
    }
    public double[] getModelsPrices(){
        Double[] prices = models.values().toArray(new Double[models.size()]);
        double[] result = new double[prices.length];
        Arrays.setAll(result, i -> prices[i]);
        return result;
    }
    public void addModel(String modelName, double modelPrice)throws DuplicateModelNameException{
        if(modelPrice <= 0){
            throw new ModelPriceOutOfBoundsException("Цена модели не может ыть меньше либо равна 0.");
        }
        if(modelPrice <= 0){
            throw new ModelPriceOutOfBoundsException("Цена модели не может ыть меньше либо равна 0.");
        }
        if(models.containsKey(modelName)){
            throw new DuplicateModelNameException("Модель с таким названием уже существует.");
        }
        models.put(modelName, modelPrice);
    }
    public void deleteModel(String modelName)throws NoSuchModelNameException{
        if(!models.containsKey(modelName)){
            throw new NoSuchModelNameException("Модели с таким названием не существует.");
        }
        models.remove(modelName);
    }
    public int getModelsSize(){
        return models.size();
    }
    public void printInfo(){
        System.out.println(mark);
        for(String key : models.keySet()){
            System.out.println(key + " " + models.get(key));
        }
    }
}
