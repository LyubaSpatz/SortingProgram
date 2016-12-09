package main.java.model;

import main.java.exceptions.SortingException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Lyuba on 08.12.2016.
 */
public abstract class AbstractSorting {

   public List<String> readFile(String inputFileName) {

       try {
           Path inputFile = Paths.get(inputFileName);
           if (Files.notExists(inputFile)) {
               SortingException.writeMessage("Input file is not exist. "+inputFileName);
               return null;
           }
           if (!Files.isReadable(inputFile)) {
               SortingException.writeMessage("Input file is not readable. "+inputFileName);
               return null;
           }
           List<String> rows = Files.readAllLines(inputFile, Charset.forName("Unicode"));
           return rows;
       }
       catch (IOException ex) {
           SortingException.writeMessage("Reading input file. Input/Output exception. "+ex.getMessage());
           return null;
       }
       catch (Exception ex) {
           SortingException.writeMessage("Reading input file. "+ex.getMessage());
           return null;
       }

    }

    public void writeFile(String outputFileName, List<String> rows) {
        try {
            Path outputFile = Paths.get(outputFileName);
            if (Files.exists(outputFile)) {
                if (!Files.isWritable(outputFile)) {
                    SortingException.writeMessage("Output file is not writable. " + outputFileName);
                    return;
                }
            }

            Files.write(outputFile, rows, Charset.forName("Unicode"));
            if (!Files.isWritable(outputFile)) {
                System.out.println("Output file cannot be written. "+outputFileName);
            }
            System.out.println("Program is successfully completed.");
        }
        catch (IOException ex) {
            SortingException.writeMessage("Writing output file. Input/Output exception. "+ex.getMessage());

        }
        catch (Exception ex) {
            SortingException.writeMessage("Writing output file. "+ex.getMessage());

        }
    }

    public abstract List<String> insertionSort();
    public abstract void setRows(List<String> rows);
}
