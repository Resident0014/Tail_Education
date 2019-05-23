import org.apache.commons.io.input.ReversedLinesFileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Tail {
    private int numOfChars;
    private int numOfString;
    private String outputName;
    private ArrayList<String> inputNames;


    public Tail(Integer numOfChars, Integer numOfString, String outputName ,ArrayList<String> inputNames) {
        this.numOfChars = numOfChars;
        this.numOfString = numOfString;
        this.outputName = outputName;
        this.inputNames = inputNames;
    }


    public List<String> run() throws IOException {
        if (numOfChars > 0 && numOfString > 0) throw new IllegalArgumentException();
        List<String> result;
        if (numOfChars == 0) {
            if (numOfString != 0) result = separateLine(numOfString);
            else {
                result = separateLine(10);
            }
        } else result = separateChar(numOfChars);
        return result;
    }


    public List<String> separateLine(int num) throws IOException {
        List<String> result = new ArrayList<>();
        if (inputNames != null) {
            for (int i = inputNames.size() - 1; i >= 0; i--) {
                File file1 = new File(inputNames.get(i));
                ReversedLinesFileReader reader = new ReversedLinesFileReader(file1);
                for (int j = 0; j < num; j++) {
                    String r = reader.readLine();
                    if (r != null) result.add(r);
                    else break;
                }
                if (inputNames.size() > 1)
                    result.add(inputNames.get(i));
            }
            Collections.reverse(result);
        } else {
            ArrayList<String> inputText = new ArrayList<>();
            Scanner scanner = new Scanner(System.in);
            String str = "";
            System.out.println("Ввод с консоли, напишите \"конец\", чтобы закончить ввод.");
            while (!str.equals("конец")) {
                str = scanner.nextLine();
                inputText.add(str);
            }
            inputText.remove(inputText.size() - 1);
            if (num > inputText.size()) return inputText;
            else return inputText.subList(inputText.size() - num, inputText.size());
        }
        return result;
    }


    public List<String> separateChar(int num) throws IOException {
        List<String> result = new ArrayList<>();
        if (inputNames != null) {
            for (int i = inputNames.size() - 1; i >= 0; i--) {
                File file1 = new File(inputNames.get(i));
                ReversedLinesFileReader reader = new ReversedLinesFileReader(file1);
                while (num > 0) {
                    String r = reader.readLine();
                    if (r != null) {
                        if (num >= r.length()) {
                            result.add(r);
                            num -= r.length();
                        } else {
                            result.add(r.substring(r.length() - num));
                            break;
                        }
                    } else break;
                }
                if (inputNames.size() > 1) result.add(inputNames.get(i));
            }
            Collections.reverse(result);
        }
        else {
            List<String> inputText = new ArrayList<>();
            Scanner scanner = new Scanner(System.in);
            String str = "";
            System.out.println("Ввод с консоли, напишите \"конец\", чтобы закончить ввод.");
            while (!str.equals("конец")) {
                str = scanner.nextLine();
                inputText.add(str);
            }
            inputText.remove(inputText.size() - 1);
            for (int i = inputText.size() - 1; i >= 0; i--) {
                if (num >= inputText.get(i).length()) {
                    result.add(inputText.get(i));
                    num -= inputText.get(i).length();
                } else {
                    result.add(inputText.get(i).substring(inputText.get(i).length() - num));
                    break;
                }
            }
        }
        return result;
    }

}













