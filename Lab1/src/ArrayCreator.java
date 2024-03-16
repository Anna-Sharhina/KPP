import Test.TestClass;

import java.lang.reflect.Array;

public class ArrayCreator {

    public static void main(String[] args) {

        Object arr = createArray(int.class, 20);
        for(int i = 0; i < Array.getLength(arr); i++){
            Array.set(arr, i, i);
        }
        System.out.println(toString(arr));

        System.out.println("Resizing:");
        arr = resizeArray(arr, 10);
        System.out.println(toString(arr) + "\n");

        Object arr2 = createMatrix(Float.class, 5, 4);
        System.out.println(toString(arr2));

        System.out.println("Resizing:");
        arr2 = resizeMatrix(arr2, 10, 2);
        System.out.println(toString(arr2) + "\n");

        Object arr3 = createMatrix(String.class, 6, 4);
        System.out.println(toString(arr3));

        System.out.println("Resizing:");
        arr3 = resizeMatrix(arr3, 2, 2);
        System.out.println(toString(arr3));
    }


    public static Object createArray(Class<?> type, int size) {
        return Array.newInstance(type, size);
    }

    public static Object createMatrix(Class<?> type, int rows, int colums){
        return Array.newInstance(type, rows, colums);
    }

    public static Object resizeArray(Object oldArray, int newSize) {
        Object newArray = createArray(oldArray.getClass().getComponentType(), newSize);
        int length = Math.min(Array.getLength(oldArray), newSize);
        System.arraycopy(oldArray, 0, newArray, 0, length);
        return newArray;
    }

    public static Object resizeMatrix(Object oldArray, int newRows, int newCols) {
        Class<?> componentType = oldArray.getClass().getComponentType().getComponentType();
        Object newArray = createMatrix(componentType, newRows, newCols);

        int oldRows = Array.getLength(oldArray);
        int oldCols = Array.getLength(Array.get(oldArray, 0));
        int rows = Math.min(oldRows, newRows);
        int cols = Math.min(oldCols, newCols);

        for (int i = 0; i < rows; i++) {
            System.arraycopy(Array.get(oldArray, i), 0, Array.get(newArray, i), 0, cols);
        }
        return newArray;
    }

    static String toString(Object arr) {
        StringBuilder sb = new StringBuilder();
        if (arr.getClass().isArray()) {
            Class<?> componentType = arr.getClass().getComponentType();
            int coll = Array.getLength(arr);
            if (componentType.isArray()) {
                sb.append(componentType.getComponentType().getSimpleName()).append("[").append(coll).append("]");
                Object arrRow = Array.get(arr, 0);
                int rows = Array.getLength(arrRow);
                sb.append("[").append(rows).append("] = {");
                for (int i = 0; i < coll; i++) {
                    sb.append("{");
                    for (int j = 0; j < rows; j++) {
                        sb.append(Array.get(arrRow, j));
                        if (j != Array.getLength(arrRow) - 1) sb.append(", ");
                    }
                    sb.append("}");
                    if (i != Array.getLength(arr) - 1) sb.append(", ");
                }
            } else {
                sb.append(componentType.getSimpleName()).append("[").append(coll).append("]");
                sb.append(" = {");
                for (int i = 0; i < Array.getLength(arr); i++) {
                    sb.append(Array.get(arr, i));
                    if (i != Array.getLength(arr) - 1) sb.append(", ");
                }
            }
            sb.append("}");
        }
        return sb.toString();
    }
}