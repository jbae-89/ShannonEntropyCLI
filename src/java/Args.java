import com.beust.jcommander.Parameter;

public class Args {

    @Parameter(names = {"-b", "--buffer_size"},
            description = "Change the buffer size used when reading a file")
    public int bufferSize = 512;

    @Parameter(names = {"-g", "--graph_only"},
            help = true,
            description = "Generate a graph from a preexisting dataset.")
    public boolean graphOnly = false;

    @Parameter(names = {"-i", "--input_file"},
            required = true,
            description = "Absolute path to the file you want to calculate entropy of, plot or both.")
    public String inputFile;

    @Parameter(names = {"-n", "--no_graph"},
            help = true,
            description = "Will not plot the graph but the dataset will still be saved.")
    public boolean noGraph = false;

    @Parameter(names = {"-o", "--output_file"},
            description = "Name of the file where the entropy values are stored.")
    public String outputFile;

    @Parameter(names = {"-s", "--sample_rate"},
            description = "Change the amount of calculated data points that will be plotted.")
    public int sampleRate = 50;

    @Parameter(names = {"-v", "--version"},
            help = true,
            description = "ShannonEntropyCLI version")
    public String version = Main.VERSION;

    @Parameter(names = {"-h", "--help"},
            help = true,
            description = "Displays usage information")
    public boolean help;

    @Override
    public String toString() {
        return "buffer size = " + bufferSize + "\n" +
                "graph only = " + graphOnly + "\n" +
                "input file = " + inputFile + "\n" +
                "no graph = " + noGraph + "\n" +
                "output file = " + outputFile + "\n" +
                "sample rate = " + sampleRate + "\n" +
                "version = " + version + "\n" +
                "help = " + help + "\n";
    }
}