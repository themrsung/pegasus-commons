package pegasus;

import pegasus.collection.Lists;
import pegasus.container.DoubleContainer;
import pegasus.pointer.ArrayPointer;
import pegasus.pointer.ObjectPointer;

import java.util.*;

public class CommonsTesting {
    public static void main(String[] args) {
        ObjectPointer<String> stringPointer = new ArrayPointer<>(10);
        stringPointer.setAll(i -> "VALUE_" + i);

        System.out.println(stringPointer);

        stringPointer.asList().replaceAll(String::toLowerCase);

        System.out.println(stringPointer);
    }
}
