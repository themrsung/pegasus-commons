package pegasus;

import pegasus.grid.Grid;

public class LosAlamos {
    public static void main(String[] args) {
        Grid<String> strings = Grid.of(new String[][]{
                {"hi"}
        });

        System.out.println(strings);
    }
}
