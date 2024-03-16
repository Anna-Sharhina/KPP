import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Test.*;

public class ReflectionProgram {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        TestClass testClass = new TestClass(20, "Name");
        System.out.println(testClass);

        while (true){
            System.out.println("\nSelect the method:");
            System.out.println("1 - Class description");
            System.out.println("2 - Object methods");
            System.out.println("3 - Call methods");

            System.out.println("0 - EXIT");
            System.out.println("Enter your choose:");

            int choose = scanner.nextInt();

            switch (choose){
                case 1 -> {
                    scanner.nextLine();
                    System.out.print("Enter the class name -> ");
                    String className = scanner.nextLine();

                    try {
                        String description = getDescription(className);
                        System.out.println(description);
                    }
                    catch (ClassNotFoundException e){
                        System.err.println("Class not found!");
                    }
                }
                case 2 -> objectMethods(testClass);
                case 3 -> {
                    try {
                        methodCall(testClass, "remake", "newName", 40);
                        System.out.println(testClass);
                    } catch (FunctionNotFoundException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                }
                case 4 -> ArrayCreator.main(args);
                case 0 -> {
                    return;
                }
            }

        }

    }

    public static String getDescription(String nameClass) throws ClassNotFoundException{
        Class<?> aClass = Class.forName(nameClass);
        return getDescription(aClass);
    }

    public static String getDescription(Class<?> aClass){
        StringBuilder outString = new StringBuilder();

        outString.append("Package: ").append(aClass.getPackageName()).append("\n");
        outString.append("Class name: ").append(aClass.getSimpleName()).append("\n");

        outString.append("\nModifiers: ");

        int modifiers = aClass.getModifiers();
        outString.append(Modifier.toString(modifiers)).append("\n");

        if (aClass.getSuperclass() != null) {
            outString.append("\nSuperclass: ");
            outString.append(aClass.getSuperclass().getSimpleName()).append("\n");
        }

        Class<?>[] interfaces = aClass.getInterfaces();
        assert aClass.getSuperclass() != null;
        Class<?>[] superInterfaces = aClass.getSuperclass().getInterfaces();
        if (interfaces.length > 0 || superInterfaces.length > 0) {
            outString.append("\nInterfaces:\n");
            for(Class<?> i : interfaces){
                outString.append(i.getSimpleName()).append("\n");
            }
            for(Class<?> i : superInterfaces){
                outString.append(i.getSimpleName()).append("\n");
            }
        }

        Field[] fields = aClass.getDeclaredFields();
        if(fields.length > 0){
            outString.append("\nFields:\n");
            for(Field f : fields) {
                int fieldModifiers = f.getModifiers();
                outString.append("\tName: ").append(f.getName()).append("\n");
                outString.append("\tType: ");
                if (Modifier.isFinal(fieldModifiers)){
                    outString.append("final ");
                }
                outString.append(f.getType().getSimpleName()).append("\n\tAccess: ");
                outString.append(Modifier.toString(f.getModifiers())).append("\n");
                outString.append("\n");
            }
        }

        Constructor<?>[] constructors = aClass.getDeclaredConstructors();
        if (constructors.length > 0) {
            outString.append("Constructors:\n");
            for (Constructor<?> c : constructors) {
                outString.append("\tName: ").append(c.getDeclaringClass().getSimpleName()).append("\n");
                outString.append("\tModifiers: ").append(Modifier.toString(c.getModifiers())).append("\n");

                Parameter[] parameters = c.getParameters();
                if (parameters.length > 0) {
                    outString.append("\tParameters:\n");
                    for (Parameter p : parameters) {
                        outString.append("\t\tName: ").append(p.getName()).append("\n");
                        outString.append("\t\tType: ").append(p.getType().getSimpleName()).append("\n\n");
                    }
                }
                outString.append("\n");
            }
        }

        Method[] methods = aClass.getDeclaredMethods();
        if (methods.length > 0) {
            outString.append("Methods:\n");
            for (Method m : methods) {
                outString.append("\tName: ").append(m.getName()).append("\n");
                outString.append("\tReturn type: ").append(m.getReturnType().getSimpleName()).append("\n");
                outString.append("\tModifiers: ").append(Modifier.toString(m.getModifiers())).append("\n");

                Parameter[] parameters = m.getParameters();
                if (parameters.length > 0) {
                    outString.append("\tParameters:\n");
                    for (Parameter parameter : parameters) {
                        outString.append("\t\tName: ").append(parameter.getName()).append("\n");
                        outString.append("\t\tType: ").append(parameter.getType().getSimpleName()).append("\n");
                    }
                }
                outString.append("\n");
            }
        }

        return outString.toString();
    }

    public static void objectMethods (Object object) throws InvocationTargetException, IllegalAccessException {
        System.out.println("Object type: " + object.getClass().getSimpleName());

        System.out.println("Object state:");
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(object);
                System.out.print("\t" + Modifier.toString(field.getModifiers()) + " ");
                System.out.println(field.getType().getSimpleName() + " " + field.getName() + " = " + value);
            } catch (IllegalAccessException e) {
                System.out.println("Error accessing field " + field.getName());
            }
        }

        List<Method> allMethods = new ArrayList<>(List.of(object.getClass().getDeclaredMethods()));
        for (int i = 0; i < allMethods.size(); i++){
            if (allMethods.get(i).getParameters().length > 0 || !Modifier.isPublic(allMethods.get(i).getModifiers())){
                allMethods.remove(i--);
            }
        }

        int count = 1;
        int choose;
        if (!allMethods.isEmpty()) {
            System.out.println("Methods:");
            for (Method m : allMethods) {
                System.out.print(count++ + ")" + Modifier.toString(m.getModifiers()) + " ");
                System.out.print(m.getReturnType().getSimpleName() + " " + m.getName() + "()\n");
            }
            System.out.print("\nSelect method to call ->");
            choose = scanner.nextInt() - 1;
            while(choose <= 0 || choose >= allMethods.size()){
                System.out.println("Invalid value entered!");
                choose = scanner.nextInt() - 1;
            }
            System.out.println(allMethods.get(choose).invoke(object).toString());
        }
    }

    public static void methodCall(Object object, String methodName, Object... parameters) throws FunctionNotFoundException {
        try {

            Class<?>[] parameterTypes = new Class<?>[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                parameterTypes[i] = getPrimitiveClass(parameters[i].getClass());
                System.out.printf("Type: [%s], Value: [%s]\n", parameterTypes[i].getSimpleName(), parameters[i]);
            }

            Method method = object.getClass().getMethod(methodName, parameterTypes);
            System.out.println("Result: " + method.invoke(object, parameters));
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new FunctionNotFoundException("Method " + methodName + " not found on object " + object, e);
        }
    }

    static class FunctionNotFoundException extends Exception {
        public FunctionNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static Class<?> getPrimitiveClass(Class<?> obj) {
        if (obj.isPrimitive()) {
            return obj;
        } else if (obj == Boolean.class) {
            return boolean.class;
        } else if (obj == Byte.class) {
            return byte.class;
        } else if (obj == Character.class) {
            return char.class;
        } else if (obj == Short.class) {
            return short.class;
        } else if (obj == Integer.class) {
            return int.class;
        } else if (obj == Long.class) {
            return long.class;
        } else if (obj == Float.class) {
            return float.class;
        } else if (obj == Double.class) {
            return double.class;
        } else if (obj == String.class){
            return String.class;
        } else {
            return null;
        }
    }

}