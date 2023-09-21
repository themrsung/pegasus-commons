package pegasus;

import pegasus.grid.AtomicArrayGrid;
import pegasus.grid.AtomicGrid;

public class LosAlamos {
    public static void main(String[] args) {
        AtomicGrid<String> strings = new AtomicArrayGrid<>(10, 10);
        strings.fill("Hello world");
        System.out.println(strings);
    }
}
