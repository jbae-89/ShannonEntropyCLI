import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import java.io.IOException;

public class Main {
    /**
     * These are the arguments from Args.java.
     */
    private final Args mainArgs = new Args();
    /**
     * This is the current version of the program.
     */
    public static final String VERSION = "1.0.0";

    /**
     * Main method, takes command line arguments.
     *
     * @param args args are command line arguments.
     * @throws IOException raised if invalid input is given.
     */
    public static void main(final String[] args) throws IOException {
        Main main = new Main();
        main.inputFlags(args);
        main.run();
    }

    /**
     * Handles exceptions and provides usage.
     *
     * @param args are command line arguments.
     */
    void inputFlags(final String[] args) {
        JCommander jCommander = new JCommander(mainArgs);
        jCommander.setProgramName("Entropy Calc");

        try {
            jCommander.parse(args);
        } catch (ParameterException e) {
            System.out.println(e.getMessage());
            showUsage(jCommander);
        }
        if (mainArgs.help) {
            showUsage(jCommander);
        }
    }

    private void showUsage(final JCommander jCommander) {
        jCommander.usage();
        System.exit(0);
    }

    private void calculateEntropyRunner() throws IOException {
        if (mainArgs.bufferSize != 0) {
            CalculateEntropy.setBufferSize(mainArgs.bufferSize);
        }

        if (!mainArgs.graphOnly) {
            CalculateEntropy.setFileIn(mainArgs.inputFile);
        }

        if (mainArgs.outputFile != null) {
            CalculateEntropy.setFileOut(mainArgs.outputFile);
        }

        CalculateEntropy.readFileIn();
    }

    private void entropyPlotRunner() {

        if (mainArgs.sampleRate != 0) {
            EntropyPlot.setSamplingRate(mainArgs.sampleRate);
        }
        if (mainArgs.graphOnly) {
            EntropyPlot.setFileIn(mainArgs.inputFile);
        }

        EntropyPlot.plot();
    }

    private void run() throws IOException {
        if (!mainArgs.graphOnly) {
            calculateEntropyRunner();
        }

        if (!mainArgs.noGraph) {
            entropyPlotRunner();
        }
    }
}
