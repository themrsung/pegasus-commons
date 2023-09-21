package pegasus;

import pegasus.grid.AtomicArrayGrid;
import pegasus.grid.Grid;
import pegasus.pointer.DoublePointer;
import pegasus.pointer.ObjectPointer;
import pegasus.tuple.IntPair;

public class LosAlamos {
    public static void main(String[] args) {
        Grid<String> strings = new AtomicArrayGrid<>(10, 10);
        strings.setAll((r, c) -> new IntPair(r, c).toString());
        strings.forEachIndexed((r, c, v) -> System.out.println(new IntPair(r, c) + " = " + v));
    }
}
