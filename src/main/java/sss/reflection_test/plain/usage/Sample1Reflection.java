package sss.reflection_test.plain.usage;

import sss.reflection_test.plain.usage.custom_lib.BaseLibrary;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Sample1Reflection {

    public BaseLibrary getInstance(String featureName) {
        try {
            System.out.println("featureName = " + featureName);
            Class<?> libClass = Class.forName(featureName);
            Constructor<?> constructor = libClass.getConstructor();
            Object o = constructor.newInstance();
            return (BaseLibrary) o;
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {

            throw new RuntimeException("reflection test!! " +e);
        }
    }

}
