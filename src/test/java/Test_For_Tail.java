import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test_For_Tail {

    @Test
    public void test1() {
        Tail tail = new Tail(5, null);
        ArrayList<String> test = new ArrayList<>();
        test.add("asd asd asdasd asdasd asd asdfg");
        ArrayList<String> expext = new ArrayList<>();
        expext.add("asdfg");
        assertEquals(expext, tail.tailList(test));
    }
}
