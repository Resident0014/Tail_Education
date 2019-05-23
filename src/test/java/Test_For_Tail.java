import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Test_For_Tail {


    @Test
    void test1() throws IOException {
        ArrayList<String> res = new ArrayList<>();
        res.add("input/test1.txt");
        Tail tail = new Tail(43, 0, null, res);
        ArrayList<String> result = new ArrayList<>();
        result.add("ните.");
        result.add("Только помню голос: \"Вы что творите?!\"");
        assertEquals(result, tail.run());
    }

    @Test
    void test2() throws IOException {
        ArrayList<String> res = new ArrayList<>();
        res.add("input/test2.txt");
        Tail tail = new Tail(0, 3, null, res);
        ArrayList<String> result = new ArrayList<>();
        result.add("Чика-чика-чика-чика и без тебя мне никак.");
        result.add("Чика-чика-чика-чика, мою любовь верни-ка.");
        result.add("Чика-чика-чика-чика, верни-верни [верни-ка|Вероника].");
        assertEquals(result, tail.run());
    }

    @Test
    void test3() {
        ArrayList<String> res = new ArrayList<>();
        res.add("input/test2.txt");
        Tail tail = new Tail(3, 3, null, res);
        assertThrows(IllegalArgumentException.class, tail::run);
    }

    @Test
    void test4() throws IOException{
        ArrayList<String> res = new ArrayList<>();
        res.add("input/test1.txt");
        res.add("input/test2.txt");
        Tail tail = new Tail(10, 0, null, res);
        ArrayList<String> result = new ArrayList<>();
        result.add("input/test1.txt");
        result.add("творите?!\"");
        result.add("input/test2.txt");
        result.add("Вероника].");
        assertEquals(result, tail.run());
    }

    @Test
    void test5() throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        String[] args = {"input/test1.txt", "-c", "5"};
        Launcher.main(args);
        assertEquals(output.toString().trim(), "те?!\"");
    }
}