package MainVehicles;

import Exceptions.DuplicateModelNameException;
import Exceptions.ModelPriceOutOfBoundsException;
import Exceptions.NoSuchModelNameException;
import Vehicle.Vehicle;

import java.util.LinkedList;

public class Moped implements Vehicle {
    private class Model{
        String modelName;
        double price;
        public Model(String modelName, double price){
            this.modelName = modelName;
            this.price = price;
        }
        public void setName(String name){
            modelName = name;
        }
        public String getName(){
            return modelName;
        }
        public void setPrice(double price){
            this.price = price;
        }
        public double getPrice(){
            return price;
        }
    }
    private String mark;
    private LinkedList<Model> models;
    public Moped(String mark, int modelsSize){
        this.mark = mark;
        models = new LinkedList<>();
        for(int i = 0; i < modelsSize; i++){
            models.add(new Model("Moped" + i, 100.0 + i));
        }
    }
    public String getBrand(){
        return mark;
    }
    public void setBrand(String brand){
        mark = brand;
    }
    public void setModelName(String originalName, String newName) throws NoSuchModelNameException, DuplicateModelNameException{
        boolean foundDuplicate = false;
        boolean foundOriginal = false;
        Model neededModel = models.get(0);
        for(Model model : models){
            if(model.getName().equals(newName)){
                foundDuplicate = true;
            }
            if(model.getName().equals(originalName)){
                foundOriginal = true;
                neededModel = model;
            }
        }
        if(foundDuplicate){
            throw new DuplicateModelNameException("Модель с таким названием уже существует.");
        }
        if(!foundOriginal){
            throw new NoSuchModelNameException("Модели с таким названием не существует.");
        }
        neededModel.setName(newName);
    }
    public String[] getModelsNames(){
        String[] modelsNames = new String[models.size()];
        for(int i = 0; i < models.size(); i++){
            modelsNames[i] = models.get(i).getName();
        }
        return modelsNames;
    }
    public double getPriceByName(String modelName) throws NoSuchModelNameException{
        for(Model model : models){
            if(model.getName().equals(modelName)){
                return model.getPrice();
            }
        }
        throw new NoSuchModelNameException("Модели с таким названием не существует");
    }
    public void setPrice(String modelName, double newPrice) throws NoSuchModelNameException{
        if(newPrice <= 0){
            throw new ModelPriceOutOfBoundsException("Цена модели не может ыть меньше либо равна 0.");
        }
        for(Model model : models){
            if(model.getName().equals(modelName)){
                model.setPrice(newPrice);
                return;
            }
        }
        throw new NoSuchModelNameException("Модели с таким названием не существует.");
    }
    public double[] getModelsPrices(){
        double[] modelsPrices = new double[models.size()];
        for(int i =0; i < models.size(); i++){
            modelsPrices[i] = models.get(i).getPrice();
        }
        return modelsPrices;
    }
    public void addModel(String modelName, double modelPrice) throws DuplicateModelNameException{
        if(modelPrice <= 0){
            throw new ModelPriceOutOfBoundsException("Цена модели не может ыть меньше либо равна 0.");
        }
        for(Model model : models){
            if(model.getName().equals(modelName)){
                throw new DuplicateModelNameException("Модель с таким названием уже существует.");
            }
        }
        models.add(new Model(modelName, modelPrice));
    }
    public void deleteModel(String modelName) throws NoSuchModelNameException{
        for(int i = 0; i < models.size(); i++){
            if(models.get(i).getName().equals(modelName)){
                models.remove(i);
                return;
            }
        }
        throw new NoSuchModelNameException("Модели с таким названием не существует.");
    }
    public int getModelsSize(){
        return models.size();
    }
    public void printInfo(){
        System.out.println(mark);
        for(Model model : models){
            System.out.println(model.getName() + " " + model.getPrice());
        }
    }
}
