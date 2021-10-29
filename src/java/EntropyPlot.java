import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;

public class EntropyPlot extends JFrame {
    /**
     * Default sampling rate is 50.
     */
    private static final int DEFAULT_RATE = 50;
    /**
     * Default pixel width of graph window.
     */
    private static final int DEFAULT_X = 1400;
    /**
     * Default pixel height of graph window.
     */
    private static final int DEFAULT_Y = 900;
    /**
     * The sampling rate of the input file.
     * Default is 1:50 lines is graphed.
     */
    private static int samplingRate = DEFAULT_RATE;
    /**
     * The default input file is output.txt
     * It is output.txt because it was created by CalculateEntropy.
     */
    private static String fileIn = "output.txt";

    /**
     * Setter for samplingRate.
     *
     * @param sRate is the desired sampling rate.
     */
    public static void setSamplingRate(final int sRate) {
        EntropyPlot.samplingRate = sRate;
    }

    /**
     * Setter for input file.
     *
     * @param inputFile the String file name.
     */
    public static void setFileIn(final String inputFile) {
        EntropyPlot.fileIn = inputFile;
    }

    /**
     * @param chartName the name of the graph, Shannon Entropy.
     * @throws FileNotFoundException if input file not found.
     */
    public EntropyPlot(final String chartName) throws FileNotFoundException {
        super(chartName);
        DefaultCategoryDataset dataset = createDataset();
        JFreeChart chart = ChartFactory.createLineChart("Shannon Entropy",
                "Sector", "Entropy",
                dataset, PlotOrientation.VERTICAL, true, false, false);

        ChartPanel panel = new ChartPanel(chart);

        setContentPane(panel);
    }

    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset entropyDataset = new DefaultCategoryDataset();

        String datasetLabel = "Entropy";
        try (BufferedReader br = new BufferedReader(new FileReader(fileIn))) {
            String line;
            int lineNum = 0;
            while ((line = br.readLine()) != null) {
                if (lineNum % samplingRate == 0) {
                    String[] linePair = line.split(",");
                    entropyDataset.addValue(Integer.valueOf(linePair[1]),
                            datasetLabel, linePair[0]);
                }
                lineNum++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entropyDataset;
    }


    /**
     * Plots the dataset.
     */
    public static void plot() {
        SwingUtilities.invokeLater(() -> {
            EntropyPlot entropyPlot = null;
            try {
                entropyPlot = new EntropyPlot("Shannon Entropy");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            assert entropyPlot != null;
            entropyPlot.setAlwaysOnTop(true);
            entropyPlot.pack();
            entropyPlot.setSize(DEFAULT_X, DEFAULT_Y);
            entropyPlot.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            entropyPlot.setVisible(true);
        });
    }
}
