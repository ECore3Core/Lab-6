package Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection {
    public static void SetPriceByName(String className, String methodName, String name, String price){
        try{
            Class clazz = Class.forName(className);
            Constructor constructor = clazz.getConstructor(String.class, int.class);
            Object obj = constructor.newInstance("BMW", 10);
            Method method = clazz.getMethod(methodName, String.class, double.class);
            method.invoke(obj, name, Double.parseDouble(price));

            System.out.println(obj);
        }
        catch(NoSuchMethodException | ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | IllegalArgumentException e){
            e.printStackTrace();
        }
    }
}
