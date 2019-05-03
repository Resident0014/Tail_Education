import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Launcher {

    @Option(name = "-c", usage = "Num of returning symbols", forbids = {"-n"})
    private int numChar;

    @Option(name = "-n", usage = "Num of returning strings", forbids = {"-c"})
    private int numStr;

    @Option(name = "-o", usage = "Name of output file")
    private String ofile;

    @Argument(usage = "Files input", metaVar = "List<String>")
    private List<String> inputFiles = new ArrayList<>();


    public static void main(String[] args) {
        new Launcher().run(args);
    }

    private void run(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("Expected argument: [-c num| -n num] [-o ofile] file0 file1 file2 ...");
            parser.printUsage(System.err);
        }

        Tail sample = new Tail(numChar, numStr);
        for (String inputFile : inputFiles) System.out.println(inputFile);
        if (sample.numOfChars != null && sample.numOfStrings != null) throw new
                IllegalArgumentException("Допускается только использование -с или -n");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ofile));
            if (inputFiles.size() > 1) {
                for (String inputFile : inputFiles)
                    Tail.write(sample.tailList(Tail.read(inputFile)), ofile, inputFile, writer);
            } else
                Tail.write(sample.tailList(Tail.read(inputFiles.get(0))), ofile, null, writer);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
