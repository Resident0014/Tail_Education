import org.junit.jupiter.api.Test;


import java.io.*;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class Test_For_Tail {


    @Test
    void test1() throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        String[] args = {"input/test1.txt", "-n", "2"};
        Launcher.main(args);
        assertEquals(output.toString().trim(),"Ничего уже не помню, извините." + System.lineSeparator() +
                "Только помню голос: \"Вы что творите?!\"");
    }

    @Test
    void test2() throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        String[] args = {"input/test1.txt", "-c", "5"};
        Launcher.main(args);
        assertEquals(output.toString().trim(), "те?!\"");
    }
}