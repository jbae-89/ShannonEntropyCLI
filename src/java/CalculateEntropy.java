import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarBuilder;
import me.tongfei.progressbar.ProgressBarStyle;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;

public final class CalculateEntropy {
    /**
     * Megabyte is 1024 bytes.
     */
    private static final int MB = 1024;
    /**
     * mask for converting signed bytes to uint.
     */
    private static final int MASK = 0xff;
    /**
     * buffer size in bytes, will default to 512.
     */
    private static int bufferSize = MB / 2;
    /**
     * input filename, this is required.
     */
    private static String fileIn;
    /**
     * output filename, will default to output.txt.
     */
    private static String fileOut = "output.txt";

    /**
     * private constructor for checkstyle.
     */
    private CalculateEntropy() {
    }

    /***
     * Setter for bufferSize.
     * @param bSize is the int size of the buffer.
     */
    public static void setBufferSize(final int bSize) {
        CalculateEntropy.bufferSize = bSize;
    }

    /**
     * Setter for fileIn.
     *
     * @param inputFile is the String name of the input file.
     */
    public static void setFileIn(final String inputFile) {
        CalculateEntropy.fileIn = inputFile;
    }

    /**
     * Setter for fileOut.
     *
     * @param outputFile is the String name of the output file.
     */
    public static void setFileOut(final String outputFile) {
        CalculateEntropy.fileOut = outputFile;
    }

    /**
     * Calculates shannon entropy of a sector and returns a single int.
     *
     * @param value represents a sector.
     * @return int entropy of sector.
     */
    public static int calculateShannon(final int[] value) {
        double sum = 0;
        Hashtable<Integer, Integer> frequency = new Hashtable<>();

        for (int b : value) {
            if (!frequency.containsKey(b)) {
                frequency.put(b, 1);
            } else {
                frequency.put(b, frequency.get(b) + 1);
            }
        }

        for (Integer key : frequency.keySet()) {
            sum = sum + (frequency.get(key) / (double) value.length)
                    * (Math.log((frequency.get(key)
                    / (double) value.length)) / Math.log(2));
        }

        return (int) Math.ceil(sum * -1) * value.length;
    }

    /**
     * Reads fileIn to a buffer, calls calculateShannon and writes to outFile.
     *
     * @throws IOException if input file does not exist.
     */
    public static void readFileIn() throws IOException {
        int offset = 0;
        ProgressBarBuilder pbb = new ProgressBarBuilder();
        FileInputStream file = new FileInputStream(fileIn);
//        BufferedInputStream input = new BufferedInputStream(file, bufferSize);
        pbb.setInitialMax(fileIn.length())
                .setTaskName("Calculate Entropy: ")
                .setStyle(ProgressBarStyle.ASCII)
                .setUnit("MB", MB * MB);

        BufferedInputStream input = new BufferedInputStream(
                ProgressBar.wrap(new FileInputStream(fileIn), pbb));

        BufferedWriter writer = new BufferedWriter(
                new FileWriter(fileOut, true), bufferSize);

        int[] buffer = new int[bufferSize];
        byte[] byteBuffer = new byte[bufferSize];

        int bufferStream = input.read(byteBuffer, 0, bufferSize);

        while (bufferStream != -1) {
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = byteBuffer[i] & MASK;
            }
            bufferStream = input.read(byteBuffer, 0, bufferSize);
            writer.write(offset + "," + calculateShannon(buffer) + "\n");
            offset = offset + bufferSize;
        }

        input.close();
        writer.close();
        System.out.println("Your file has been saved to "
                + System.getProperty("user.dir") + "\\" + fileOut);
    }
}
