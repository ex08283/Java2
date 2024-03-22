package be.kdg.stadiumproject.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ReflectionTools {
    public static void classAnalysis(Class<?>... aClassArray) {
        for (Class<?> aClass : aClassArray) {


            List<String> gettersList = new LinkedList<>();
            List<String> settersList = new LinkedList<>();
            List<String> otherMethodsList = new LinkedList<>();
            List<String> interfacesList = new LinkedList<>();
            List<String> constructorsList = new LinkedList<>();
            List<String> fieldsList = new LinkedList<>();


            for (Method method : aClass.getDeclaredMethods()) {//Inherited not needed
                if (method.getName().startsWith("get")) {
                    gettersList.add(method.getName());
                } else if (method.getName().startsWith("set")) {
                    settersList.add(method.getName());
                } else {
                    otherMethodsList.add(method.getName());
                }
            }

            //Build a list of attributes(Fields)
            for (Field declaredField : aClass.getDeclaredFields()) {
                fieldsList.add(declaredField.getName() + "(" + declaredField.getType().getSimpleName() + ")");
            }

            String fields = buildString(fieldsList);

            //Build a string of constructors
            for (Constructor constructor : aClass.getDeclaredConstructors()) {
                constructorsList.add(constructor.toGenericString());
            }

            //Build a string interfaces

            for (Class<?> anInterface : aClass.getInterfaces()) {
                interfacesList.add(anInterface.getSimpleName());
            }


            String stringBuilder = new StringBuilder()
                    .append(String.format("Analyse van de klasse: %s\n", aClass.getSimpleName()))
                    .append("======================================================================\n")
                    .append(String.format("%-25s  : %s\n", "Fully qualified name", aClass.getName()))
                    .append(String.format("%-25s  : %s\n", "Name of the superclass", aClass.getSuperclass().getName()))
                    .append(String.format("%-25s  : %s\n", "Name of the package", aClass.getPackageName()))
                    .append(String.format("%-25s  : %s\n", "Interfaces", buildString(interfacesList)))
                    .append(String.format("%-25s  : %s\n", "Constructors", buildString(constructorsList)))
                    .append(String.format("%-25s  : %s\n", "Attributes", buildString(fieldsList)))
                    .append(String.format("%-25s  : %s\n", "Getters", buildString(gettersList)))
                    .append(String.format("%-25s  : %s\n", "Setters", buildString(settersList)))
                    .append(String.format("%-25s  : %s\n", "Other method", buildString(otherMethodsList))).toString();
            ;

            System.out.println(stringBuilder);
        }
    }

    public static Object runAnnotated (Class<?> aClass) throws Exception{
        System.out.println("Initiating new object of the class: " + aClass.getName());
        Object object = aClass.getDeclaredConstructor().newInstance();

        Boolean  canRunFound = null;

        for (Method method : aClass.getDeclaredMethods()) {
            //Select only method that are annotated with Canrun and have 1 type
            if (method.getAnnotation(Canrun.class) != null && method.getGenericParameterTypes().length == 1){
                //the type has to be a string
                if (method.getGenericParameterTypes()[0].equals(String.class)){
                    canRunFound = true;
                    method.invoke(object, method.getAnnotation(Canrun.class).value());
                }
            }
        }

        //If methods that are annotated with Canrun and have 1 string return the invoked object else throw an exception
        if (Boolean.TRUE.equals(canRunFound)) {
            return object;
        } else throw new Exception("This class did not have mettod annotated with @Canrun");
    }

    private static String buildString (List < String > inputList) {
        StringBuilder stringBuilder = new StringBuilder().append("\n");
        for (String s : inputList) {
            stringBuilder
                    .append("\t\t\t\t")
                    .append(s)
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
