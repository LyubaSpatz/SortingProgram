package main.java.model;

import main.java.exceptions.SortingException;

import java.util.List;

/**
 * Created by Lyuba on 08.12.2016.
 */
public class StringSorting extends AbstractSorting {

    private String sortingMode;

    private List<String> rows;

    public StringSorting(String sortingMode) {
        this.sortingMode = sortingMode;
    }

    @Override
    public void setRows(List<String> rows) {
        this.rows = rows;
    }

    @Override
    public List<String> insertionSort() {
        if (rows != null && !rows.isEmpty()) {
            if (sortingMode.equals("-d")) {
                insertionSortDesc();
                return rows;

            } else {
                insertionSortA();
                return rows;

            }

        } else {
            SortingException.writeMessage("No strings (possibly in the input file).");
            return null;
        }
    }

    private void insertionSortDesc() {
        try {
            for (int i = 1; i < rows.size(); i++) {
                String key = rows.get(i);
                /*Whitespace check*/
                if (key.indexOf(' ') > -1) {
                    SortingException.writeMessage("The row \"" + key + "\" content whitespaces.");
                    rows = null;
                    return;
                }
                int j;
                for (j = i - 1; j >= 0 && key.compareTo(rows.get(j)) > 0; j--) {
                    rows.set(j + 1, rows.get(j));
                }
                rows.set(j + 1, key);
            }
        } catch (Exception ex) {
            SortingException.writeMessage(ex.getMessage());
            rows = null;
        }
    }

    private void insertionSortA() {
        try {
            for (int i = 1; i < rows.size(); i++) {
                String key = rows.get(i);
                /*Whitespace check*/
                if (key.indexOf(' ') > -1) {
                    SortingException.writeMessage("The row \"" + key + "\" content whitespaces.");
                    rows = null;
                    return;
                }
                int j;
                for (j = i - 1; j >= 0 && key.compareTo(rows.get(j)) < 0; j--) {
                    rows.set(j + 1, rows.get(j));
                }
                rows.set(j + 1, key);
            }
        } catch (Exception ex) {
            SortingException.writeMessage(ex.getMessage());
            rows = null;
        }
    }


}
