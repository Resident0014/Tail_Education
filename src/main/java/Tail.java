import org.apache.commons.io.input.ReversedLinesFileReader;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Tail {
    private static ArrayList<String> inputNames;


    public Tail(ArrayList<String> inputNames) {
        Tail.inputNames = inputNames;
    }


    public static List<String> separateLine(int num) throws IOException {
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
            while (scanner.hasNextLine()) {
                str = scanner.nextLine();
                inputText.add(str);
            }

            if (num > inputText.size()) return inputText;
            else return inputText.subList(inputText.size() - num, inputText.size());
        }
        return result;
    }


    public static List<String> separateChar(int num) throws IOException {
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

            while (scanner.hasNextLine()) {
                str = scanner.nextLine();
                inputText.add(str);
            }

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













