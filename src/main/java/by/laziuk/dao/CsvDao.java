package by.laziuk.dao;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CsvDao<T> extends Dao<T> {
    private PrintWriter printWriter;
    private Scanner lineScanner;

    public CsvDao(Class<T> type) {
        super(type);
    }

    private void writeNativeObject(Object object, Class<?> clazz) {
        printWriter.print(clazz.getCanonicalName());
        printWriter.print(";");
        printWriter.print(object);
    }

    private Object readNativeObject(Class<?> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return clazz.getConstructor(String.class).newInstance(lineScanner.next());
    }

    private void writeObject(Object object, Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (clazz.getPackage().equals(Integer.class.getPackage())) {
            writeNativeObject(object, clazz);
            return;
        }

        printWriter.print(clazz.getCanonicalName());
        if (!clazz.getSuperclass().equals(Object.class)) {
            printWriter.print(";");
            writeObject(object, clazz.getSuperclass());
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if(fieldName.toUpperCase(Locale.ROOT).equals(fieldName)) continue;
            printWriter.print(";");
            StringBuilder getterName = new StringBuilder();
            if (field.getType().equals(Boolean.class) || field.getType().getName().equals("boolean")) {
                getterName.append("is");
            } else {
                getterName.append("get");
            }
            getterName.append(Character.toUpperCase(fieldName.charAt(0))).append(fieldName.substring(1));
            Method getter = clazz.getMethod(getterName.toString());
            Object getterResult = getter.invoke(object);
            writeObject(getterResult, getterResult.getClass());
        }
    }

    private Object readObject(Object object) {
            try {
                Class<?> clazz = Class.forName(lineScanner.next());
                if (clazz.getPackage().equals(Integer.class.getPackage())) {
                    return readNativeObject(clazz);
                }
                if (object == null) {
                    object = clazz.getConstructor().newInstance();
                }
                if (!clazz.getSuperclass().equals(Object.class)) {
                    readObject(object);
                }
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    String fieldName = field.getName();
                    if(fieldName.toUpperCase(Locale.ROOT).equals(fieldName)) continue;
                    String setterName = "set" +
                            Character.toUpperCase(fieldName.charAt(0)) +
                            fieldName.substring(1);
                    Method setter = clazz.getMethod(setterName, field.getType());
                    setter.invoke(object, readObject(null));
                }
            } catch (Exception e) { e.printStackTrace(); }
        return object;
    }

    @Override
    public void write(List<T> list, String fileName) {
        try {
            printWriter = new PrintWriter(new File(fileName));
            for (T object : list) {
                writeObject(object, object.getClass());
                printWriter.print("\n");
            }
            printWriter.close();
        } catch(Exception e) { e.printStackTrace(); }
    }

    @Override
    public List<T> read(String fileName) {
        List<T> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                lineScanner = new Scanner(scanner.nextLine()).useDelimiter(";");
                list.add((T) readObject(null));
            }
        } catch(Exception e) { e.printStackTrace(); }

        return list;
    }
}
