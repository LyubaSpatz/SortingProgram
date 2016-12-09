package main.java;

import main.java.exceptions.SortingException;
import main.java.model.AbstractSorting;
import main.java.model.IntegerSorting;
import main.java.model.StringSorting;

import java.io.IOException;
import java.util.List;

/**
 * Created by Lyuba on 08.12.2016.
 */
public class MainSorting {
    public static void main(String[] args) throws IOException{
        try {
        /*Parameters check*/
            if (args.length != 4) {
                SortingException.writeMessage("Incorrect number of parameters.");
                return;
            }
            if (!args[3].equals("-a") && !args[3].equals("-d")) {
                SortingException.writeMessage("Unknown sorting mode parameter.");
                return;
            }

            AbstractSorting sorting;
            List<String> rows;

            if (args[2].equals("-i")) {
                sorting = new IntegerSorting(args[3]);
            } else if (args[2].equals("-s")) {
                sorting = new StringSorting(args[3]);
            } else {
                SortingException.writeMessage("Unknown content type parameter.");
                return;
            }

        /*Reading rows from file*/
            rows = sorting.readFile(args[0]);
            if (rows == null) {
                return;
            }

            sorting.setRows(rows);
            rows = sorting.insertionSort();
            if (rows == null) {
                return;
            }

        /*Writing rows in file*/
            sorting.writeFile(args[1], rows);
        }
        catch (Throwable throwable) {
            SortingException.writeMessage(throwable.getMessage());
        }
    }
}
