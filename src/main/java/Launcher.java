import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Launcher {

    @Option(name = "-c", usage = "Num of returning symbols", forbids = {"-n"})
    private int numChar = 0;

    @Option(name = "-n", usage = "Num of returning strings", forbids = {"-c"})
    private int numStr = 0;

    @Option(name = "-o", usage = "Name of output file")
    private String ofile;

    @Argument(required = true, index = 0, usage = "FileInput")
    private ArrayList<String> inputFiles;


    public static void main(String[] args) throws IOException {
        new Launcher().start(args);
    }


    private void start(String[] args) throws IOException {
        CmdLineParser parse = new CmdLineParser(this);
        try {
            parse.parseArgument(args);
        } catch (CmdLineException exc) {
            System.err.println(exc.getMessage());
            System.err.println("Expected argument: [-c num| -n num] [-o ofile] file0 file1 file2 ...");
            parse.printUsage(System.err);
        }
        new Tail(inputFiles);
        if (numStr > 0 && numChar > 0) throw new IllegalArgumentException();
        List<String> result;
        if (numChar == 0) {
            if (numStr != 0) result = Tail.separateLine(numStr);
            else result = Tail.separateLine(10);
        } else result = Tail.separateChar(numChar);
        if (ofile != null) {
            File file1 = new File(ofile);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file1));
            for (String res1 : result) {
                writer.write(res1 + System.lineSeparator());
            }
            writer.close();
        } else {
            for (String line : result) System.out.println(line);
        }
    }
}

