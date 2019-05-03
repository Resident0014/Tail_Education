import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tail {


    Integer numOfChars;
    Integer numOfStrings;

    public Tail(Integer numOfChars, Integer numOfStrings) {
        this.numOfChars = numOfChars;
        this.numOfStrings = numOfStrings;
    }

    public List<String> tailList(ArrayList<String> text) {
        if(!text.equals(new ArrayList<String>())) {
            if (numOfChars == null && numOfStrings == null) numOfStrings = 10;
            if (numOfStrings != null) {
                if (numOfStrings > text.size()) throw new IllegalArgumentException("количество заданных строк для вывода" +
                         "больше чем количество строк в заданном тексте");
                return text.subList(text.size() - numOfStrings, text.size());
            } else {
                int newNumOfChars = numOfChars;
                List<String> result = new ArrayList<>();
                int i = 1;
                while (text.get(text.size() - i).length() <= newNumOfChars) {
                    result.add(text.get(text.size() - i));
                    newNumOfChars -= text.get(text.size() - i).length();
                    i++;
                    if (i > text.size()) throw new IllegalArgumentException("заданное кол-во символов больше чем" +
                            "кол-во символов в заданном тексте");
                }
                if (newNumOfChars != 0) {
                    if (newNumOfChars > text.get(text.size() - i).substring(text.get(text.size() - i).length() - newNumOfChars).length())
                        throw new IllegalArgumentException("заданное кол-во символов больше чем кол-во символов в заданном тесте");
                    result.add(text.get(text.size() - i).substring(text.get(text.size() - i).length() - newNumOfChars));
                }
                return result;
            }
        }
        return new ArrayList<>();
    }

    public static void write(List<String> text, String outputName, String inputName, BufferedWriter writer) throws IOException {
        if (!text.equals(new ArrayList<String>())) {
            if (outputName != null) {
                if (inputName != null) {
                    writer.write(inputName);
                    writer.newLine();
                    writer.flush();
                }
                for (int i = text.size() - 1; i>=0; i--) {
                    writer.write(text.get(i));
                    writer.newLine();
                    writer.flush();
                }
            } else {
                if (inputName != null) System.out.println(inputName);
                for (String aText : text) {
                    System.out.println(aText);
                }
            }
        }
    }

    public static ArrayList<String> read(String inputName) throws IOException {
        ArrayList<String> text = new ArrayList<>();
        if (inputName == null) {
            System.out.println("напечатайте свой тест здесь и напишите \"close\", чтобы закончить ввод");
            Scanner reader = new Scanner(System.in);
            String temp = reader.nextLine();
            while (!temp.equals("close")) {
                text.add(temp);
        temp = reader.nextLine();
    }
            reader.close();
} else {
        Scanner reader = new Scanner(Paths.get(inputName));
        while (reader.hasNextLine()) {
        text.add(reader.nextLine());
        }
        }
        return text;
    }

}
