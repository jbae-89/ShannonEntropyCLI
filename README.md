# ShannonEntropyCLI
A program to calculate and graph the shannon entropy of a file.

## Usage:
``` Entropy Calc [options] ```

### Options:
    -b, --buffer_size
      Change the buffer size used when reading a file

    -g, --graph_only
      Generate a graph from a preexisting dataset.
      
    -h, --help
      Displays usage information
      
    * -i, --input_file
      Absolute path to the file you want to calculate entropy of, plot or 
      both. 
      
    -n, --no_graph
      Will not plot the graph but the dataset will still be saved.
      
    -o, --output_file
      Name of the file where the entropy values are stored.
      
    -s, --sample_rate
      Change the amount of calculated data points that will be plotted.
      
    -v, --version
      ShannonEntropyCLI version
      
      
## Example:
``` java -jar ShannonEntropyCLI -i test.img -n ```

This will take test.img, calculate the entropy and store the calculations as output.txt, it will not provide a graph.


``` java -jar ShannonEntropyCLI -i output.txt -g```

This will take output.txt and generate a graph, it will not perform new entropy calculations.

## Dependencies

- [JavaFX](https://openjfx.io/) Java GUI framework. JavaFX is licenses under the terms of the GPL 2.0 License.
- [JFreeChart](https://www.jfree.org/) Java chart Library. JFreeChart is licenses under the terms of the LGPL License.
- [ctongfei/progressbar](https://github.com/ctongfei/progressbar) Java progress bar library. ctongfei/progressbar is licensed under the MIT License.
- [JCommander](https://jcommander.org/) Java command line argument parsing library. JCommander is licensed under the Apache 2.0 License.